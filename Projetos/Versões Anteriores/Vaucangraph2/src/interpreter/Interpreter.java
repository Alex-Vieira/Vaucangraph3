package interpreter;

import jgraph.view.Canvas;
import utils.math.MetricConversor;
import vaucansong.bean.State;
import vaucansong.bean.Transition;
import vaucansong.bean.enumeration.StateSize;
import vaucansong.bean.enumeration.TransitionType;

/**
 * Interpretador de Vaucanson-G
 * @author Kleber Kruger
 */
public class Interpreter {

    public static String getCode(Canvas canvas) {
        String code = "\\begin{VCPicture}{"
                + "(0,-" + (int) MetricConversor.toCentimeters(canvas.getProject().getSize().getWidth()) + ") "
                + "(" + +(int) MetricConversor.toCentimeters(canvas.getProject().getSize().getHeight()) + ",0)}" + "\n"
                + "%states\n";

        String header = code;

        //Laço para a geração dos estados (inicial e simples)
        for (State state : canvas.getProject().getStates()) {
            if (!state.isFinal()) {
                int x = (int) (float) MetricConversor.toCentimeters(state.getX());
                int y = (int) (float) MetricConversor.toCentimeters(state.getY());
                String position = "";
                if (y != 0) {
                    position = x + ",-" + y;
                } else {
                    position = x + "," + y;
                }
                String name = state.getName();
                if (!name.isEmpty()) {
                    name = "[" + name + "]";
                }
                String size = "";
                if (state.getSize() == StateSize.VERY_SMALL) {
                    size = "\\VerySmallState";
                }
                if (state.getSize() == StateSize.SMALL) {
                    size = "\\SmallState";
                }
                if (state.getSize() == StateSize.LARGE) {
                    size = "\\LargeState";
                }
                code += size + "\\State" + name + "{(" + position + ")}{" + state.getId() + "}" + "\n";
            }
        }


        // Laço para a geração dos estados finais
        for (State state : canvas.getProject().getStates()) {
            if (state.isFinal()) {
                int x = (int) (float) MetricConversor.toCentimeters(state.getX());
                int y = (int) (float) MetricConversor.toCentimeters(state.getY());
                String position = "";
                if (y != 0) {
                    position = x + ",-" + y;
                } else {
                    position = x + "," + y;
                }
                String name = state.getName();
                if (!name.equals("")) {
                    name = "[" + name + "]";
                }
                code += "\\FinalState" + name + "{(" + position + ")}{" + state.getId() + "}" + "\n";
            }
        }

        // Laço que define o estado inicial
        for (State state : canvas.getProject().getStates()) {
            if (state.isInitial()) {
                code += "\\Initial{" + state.getId() + "}\n";
            }
        }

        String transitionsCode = "";
        // Laço para a geração das transisões de um estado para outro
        for (Transition transition : canvas.getProject().getTransitions()) {
            int sourceId = transition.getSourceState().getId();
            int targetId = transition.getTargetState().getId();
            String name = transition.getName();
            //transition.isDirigida();
            if (transition.getType() == TransitionType.SIMPLE_TRANSITION
                    || transition.getType() == TransitionType.EDGE_DIRECTED
                    || transition.getType() == TransitionType.EDGE_DIRECTED) {
                transitionsCode += "\\EdgeL{" + sourceId + "}{" + targetId + "}{" + name + "}\n";
            }
        }

        // Laço para a geração das transisões de um estado para outro
        for (Transition transition : canvas.getProject().getTransitions()) {
            int sourceId = transition.getSourceState().getId();
            int targetId = transition.getTargetState().getId();
            String name = transition.getName();
            //transition.isDirigida();
            if (transition.getType() == TransitionType.ARC_TRANSITION) {
                transitionsCode += "\\ArcL{" + sourceId + "}{" + targetId + "}{" + name + "}\n";
            }
        }

        // Laço para geração dos loops
        for (Transition transition : canvas.getProject().getTransitions()) {
            int sourceId = transition.getSourceState().getId();
            String name = transition.getName();
            if (transition.getType() == TransitionType.SELF_TRANSITION) {
                transitionsCode += "\\LoopN{" + sourceId + "}{" + name + "}\n";
            }
        }

        if (!transitionsCode.isEmpty()) {
            code += "%transitions\n";
            code += transitionsCode;
        }
        if (!header.equals(code)) {
            code = code + "%\n"
                    + "\\end{VCPicture}";
        } else {
            code = "";
        }
        return code;
    }
}
