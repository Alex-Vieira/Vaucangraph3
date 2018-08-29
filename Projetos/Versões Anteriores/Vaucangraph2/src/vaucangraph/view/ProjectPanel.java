package vaucangraph.view;

import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import jgraph.view.Canvas;
import vaucangraph.model.Project;
import vaucangraph.model.enumeration.ProjectType;

/**
 * <b>Vaucangraph 2</b> - A evolução de uma ferramenta para desenho de autômatos
 * e grafos com geração de códigos Vaucanson-G <br><br>
 *
 * Desenvolvida por: <br><br>
 * Kleber Kruger <br>
 * José Ademar Peixoto de Souza <br>
 *
 * @version 2.0
 * @since 2010-05-15
 *
 * @author Kleber Kruger
 * @author José Ademar Peixoto de Souza
 */
public class ProjectPanel extends javax.swing.JPanel {

    protected final Vaucangraph vcg;
    protected final Project project;
    protected Canvas canvas;
    /**
     * Botão selecionado
     */
    private ButtonGroup buttonGroup;
    private JToggleButton selectedButton;

    /**
     * Cria um novo painel de projetos
     *
     * @param vcg
     * frame principal da aplicação
     *
     * @param project
     * objeto que contém as infprmações do projeto
     */
    public ProjectPanel(Vaucangraph vcg, Project project) {

        this.vcg = vcg;
        this.project = project;

        initComponents();
        createCanvas();
        configure();
    }

    /**
     * Cria o canvas e o adiciona no painel de projetos
     */
    private void createCanvas() {
        canvas = new Canvas(this);
        jpDesign.add(canvas);
    }

    private void configure() {
        // Define os valores (largura x altura) nos spinners
        jsWidth.setValue(project.getSize().getWidth());
        jsHeight.setValue(project.getSize().getHeight());

        // Define os componentes habilitados
        setVisibleProjectComponents();
        createButtonGroup();
    }

    private void setVisibleProjectComponents() {
        // Habilita os componentes padrão
        jtbSelector.setVisible(true);
        jsSeparator1.setVisible(true);
        jtbSimpleState.setVisible(true);
        jsSeparator2.setVisible(true);
        jtbSimpleTransition.setVisible(true);

        if (project.getType() == ProjectType.GRAPH_DIRECTED
                || project.getType() == ProjectType.GRAPH_UNDIRECTED) {

            // Desabilita os componentes de autômato
            jtbInitialState.setVisible(false);
            jtbFinalState.setVisible(false);
            jtbInitialFinalState.setVisible(false);
            jtbSelfTransition.setVisible(false);
            jtbArcTransition.setVisible(false);

            jtbSimpleState.setToolTipText("<html>\n<b>Vértice</b><br>\n" +
                    "Para criar um vértice, selecione esta ferramenta<br>\n" +
                    "e clique em algum lugar dentro do canvas\n</html>");

            jtbSimpleTransition.setToolTipText("<html>\n<b>Aresta</b><br>\n" +
                    "Para criar uma aresta, clique no vértice<br>\n" +
                    "de origem e arraste até o vértice de destino\n</html>");
        }
    }

    private void createButtonGroup() {
        // Cria um grupo de botões
        buttonGroup = new ButtonGroup() {

            @Override
            public void clearSelection() {
                super.clearSelection();

                // Desabilita transições no canvas
                canvas.setPortsVisible(false);
                selectedButton = null;
            }
        };
        // Adiciona os botões ao buttonGroup
        buttonGroup.add(jtbSelector);
        buttonGroup.add(jtbSimpleState);
        buttonGroup.add(jtbInitialState);
        buttonGroup.add(jtbFinalState);
        buttonGroup.add(jtbInitialFinalState);
        buttonGroup.add(jtbSelfTransition);
        buttonGroup.add(jtbSimpleTransition);
        buttonGroup.add(jtbArcTransition);
    }

    private void setCanvasSize() {

        double zoom = canvas.getScale();
        canvas.setScale(1.0);

        int minWidth = (int) (50 * canvas.getScale());
        int minHeight = (int) (50 * canvas.getScale());

        // Obtém os novos valores de largura e altura
        int width = (int) Double.parseDouble(jsWidth.getValue() + "");
        int height = (int) Double.parseDouble(jsHeight.getValue() + "");

        width *= canvas.getScale();
        height *= canvas.getScale();

        if (canvas != null && canvas.getRoots().length > 0) {
            minWidth = (int) (canvas.getCellBounds(canvas.getRoots()).getMaxX() * canvas.getScale());
            minHeight = (int) (canvas.getCellBounds(canvas.getRoots()).getMaxY() * canvas.getScale());
        }

        if (width < minWidth) {
            width = minWidth;
            jsWidth.setValue(width);
        }

        if (height < minHeight) {
            height = minHeight;
            jsHeight.setValue(height);
        }
        // Altera o tamanho do canvas
        canvas.setSize(width, height);
        // Define o tamanho na barra de status
        canvas.getStatusBar().getJlCanvasSize().setText(width + " x " + height + " px");
        // Define o novo tamanho do canvas no projeto
        canvas.getProject().setSize(new Dimension(width, height));

        canvas.setScale(zoom);
    }

    private void selectorSelected() {
        if (jtbSelector != selectedButton) {
            // Desabilita transições no canvas
            canvas.setPortsVisible(false);
            selectedButton = jtbSelector;
        } else {
            buttonGroup.clearSelection();
            selectedButton = null;
        }
    }

    private void statesSelected(JToggleButton button) {
        if (button != selectedButton) {
            // Desabilita transições no canvas
            canvas.setPortsVisible(false);
            selectedButton = button;

        } else {
            buttonGroup.clearSelection();
            selectedButton = null;
        }
    }

    private void transitionSelected(JToggleButton button) {
        if (button != selectedButton) {
            // Habilita transições no canvas
            canvas.setPortsVisible(true);
            selectedButton = button;

        } else {
            canvas.setPortsVisible(false);
            buttonGroup.clearSelection();
            selectedButton = null;
        }
    }

    /**
     * Mostra/oculta os componentes do painel de projeto
     * 
     * @param bln
     * booleano que indica se os componentes estarão visíveis
     */
    private void setEnableAllComponents(boolean bln) {
        // Mostra/oculta os componentes
        jpToolBar.setVisible(bln);
        jtbComponents.setVisible(bln);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpToolBar = new javax.swing.JPanel();
        jlWidth = new javax.swing.JLabel();
        jsWidth = new javax.swing.JSpinner();
        jlHeight = new javax.swing.JLabel();
        jsHeight = new javax.swing.JSpinner();
        jlStateSize = new javax.swing.JLabel();
        jcbStateSize = new javax.swing.JComboBox();
        jtbComponents = new javax.swing.JToolBar();
        jtbSelector = new javax.swing.JToggleButton();
        jsSeparator1 = new javax.swing.JToolBar.Separator();
        jtbInitialState = new javax.swing.JToggleButton();
        jtbInitialFinalState = new javax.swing.JToggleButton();
        jtbSimpleState = new javax.swing.JToggleButton();
        jtbFinalState = new javax.swing.JToggleButton();
        jsSeparator2 = new javax.swing.JToolBar.Separator();
        jtbSimpleTransition = new javax.swing.JToggleButton();
        jtbSelfTransition = new javax.swing.JToggleButton();
        jtbArcTransition = new javax.swing.JToggleButton();
        jtpProject = new javax.swing.JTabbedPane();
        jspDesign = new javax.swing.JScrollPane();
        jpDesign = new javax.swing.JPanel();
        jspCode = new javax.swing.JScrollPane();
        jepCode = new javax.swing.JEditorPane();

        jlWidth.setText("Largura:");

        jsWidth.setModel(new javax.swing.SpinnerNumberModel(400, 50, 50000, 1));
        jsWidth.setToolTipText("<html>\n<b>Dimensão do projeto</b><br>\nAltere a largura do canvas\n</html>");
        jsWidth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsWidthStateChanged(evt);
            }
        });

        jlHeight.setText("Altura:");

        jsHeight.setModel(new javax.swing.SpinnerNumberModel(400, 50, 50000, 1));
        jsHeight.setToolTipText("<html>\n<b>Dimensão do projeto</b><br>\nAltere a altura do canvas\n</html>");
        jsHeight.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsHeightStateChanged(evt);
            }
        });

        jlStateSize.setText("Tamanho do Estado:");

        jcbStateSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Muito pequeno", "Pequeno", "Médio", "Grande" }));
        jcbStateSize.setSelectedIndex(2);
        jcbStateSize.setToolTipText("<html>\n<b>Tamanho do estado</b><br>\nAltere o tamanho do estado a ser inserido\n</html>");

        javax.swing.GroupLayout jpToolBarLayout = new javax.swing.GroupLayout(jpToolBar);
        jpToolBar.setLayout(jpToolBarLayout);
        jpToolBarLayout.setHorizontalGroup(
            jpToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpToolBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlWidth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlHeight)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlStateSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbStateSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpToolBarLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jcbStateSize, jsHeight, jsWidth});

        jpToolBarLayout.setVerticalGroup(
            jpToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpToolBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlWidth)
                    .addComponent(jsWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlHeight)
                    .addComponent(jsHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlStateSize)
                    .addComponent(jcbStateSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtbComponents.setFloatable(false);
        jtbComponents.setOrientation(1);
        jtbComponents.setRollover(true);

        jtbSelector.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vaucangraph/icons/selector.png"))); // NOI18N
        jtbSelector.setToolTipText("<html>\n<b>Ferramenta Seleção</b><br>\nSelecione, posicione ou altere objetos\n</html>");
        jtbSelector.setFocusable(false);
        jtbSelector.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jtbSelector.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jtbSelector.setMaximumSize(new java.awt.Dimension(43, 33));
        jtbSelector.setMinimumSize(new java.awt.Dimension(43, 33));
        jtbSelector.setPreferredSize(new java.awt.Dimension(43, 33));
        jtbSelector.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbComponents.add(jtbSelector);
        jtbComponents.add(jsSeparator1);

        jtbInitialState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vaucangraph/icons/initialstate.png"))); // NOI18N
        jtbInitialState.setToolTipText("<html>\n<b>Estado Inicial</b><br>\nPara criar um estado inicial, selecione esta ferramenta<br> \ne clique em algum lugar dentro do canvas\n</html>");
        jtbInitialState.setFocusable(false);
        jtbInitialState.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbInitialState.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbInitialState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbInitialStateActionPerformed(evt);
            }
        });
        jtbComponents.add(jtbInitialState);

        jtbInitialFinalState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vaucangraph/icons/initialfinalstate.png"))); // NOI18N
        jtbInitialFinalState.setToolTipText("<html>\n<b>Estado Inicial e Final</b><br>\nPara criar um estado inicial e final, selecione esta ferramenta<br>\ne clique em algum lugar dentro do canvas\n</html>");
        jtbInitialFinalState.setFocusable(false);
        jtbInitialFinalState.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtbInitialFinalState.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbInitialFinalState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbInitialFinalStateActionPerformed(evt);
            }
        });
        jtbComponents.add(jtbInitialFinalState);

        jtbSimpleState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vaucangraph/icons/simplestate.png"))); // NOI18N
        jtbSimpleState.setToolTipText("<html>\n<b>Estado Simples</b><br>\nPara criar um estado simples, selecione esta ferramenta<br>\ne clique em algum lugar dentro do canvas\n</html>");
        jtbSimpleState.setFocusable(false);
        jtbSimpleState.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jtbSimpleState.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jtbSimpleState.setMaximumSize(new java.awt.Dimension(43, 33));
        jtbSimpleState.setMinimumSize(new java.awt.Dimension(43, 33));
        jtbSimpleState.setPreferredSize(new java.awt.Dimension(43, 33));
        jtbSimpleState.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbSimpleState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbSimpleStateActionPerformed(evt);
            }
        });
        jtbComponents.add(jtbSimpleState);

        jtbFinalState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vaucangraph/icons/finalstate.png"))); // NOI18N
        jtbFinalState.setToolTipText("<html>\n<b>Estado Final</b><br>\nPara criar um estado final, selecione esta ferramenta<br>\ne clique em algum lugar dentro do canvas\n</html>");
        jtbFinalState.setFocusable(false);
        jtbFinalState.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jtbFinalState.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jtbFinalState.setMaximumSize(new java.awt.Dimension(43, 33));
        jtbFinalState.setMinimumSize(new java.awt.Dimension(43, 33));
        jtbFinalState.setPreferredSize(new java.awt.Dimension(43, 33));
        jtbFinalState.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbFinalState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbFinalStateActionPerformed(evt);
            }
        });
        jtbComponents.add(jtbFinalState);
        jtbComponents.add(jsSeparator2);

        jtbSimpleTransition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vaucangraph/icons/simpletransition.png"))); // NOI18N
        jtbSimpleTransition.setToolTipText("<html>\n<b>Transição Simples</b><br>\nPara criar uma transição simples, clique no estado<br>\nde origem e arraste até o estado de destino\n</html>");
        jtbSimpleTransition.setFocusable(false);
        jtbSimpleTransition.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jtbSimpleTransition.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jtbSimpleTransition.setMaximumSize(new java.awt.Dimension(43, 33));
        jtbSimpleTransition.setMinimumSize(new java.awt.Dimension(43, 33));
        jtbSimpleTransition.setPreferredSize(new java.awt.Dimension(43, 33));
        jtbSimpleTransition.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbSimpleTransition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbSimpleTransitionActionPerformed(evt);
            }
        });
        jtbComponents.add(jtbSimpleTransition);

        jtbSelfTransition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vaucangraph/icons/selftransition.png"))); // NOI18N
        jtbSelfTransition.setToolTipText("<html>\n<b>Transição Para Si Mesmo</b><br>\nPara criar uma transição para si mesmo, basta<br>\nclicar em algum estado dentro do canvas\n</html>");
        jtbSelfTransition.setFocusable(false);
        jtbSelfTransition.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jtbSelfTransition.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jtbSelfTransition.setMaximumSize(new java.awt.Dimension(43, 33));
        jtbSelfTransition.setMinimumSize(new java.awt.Dimension(43, 33));
        jtbSelfTransition.setPreferredSize(new java.awt.Dimension(43, 33));
        jtbSelfTransition.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbSelfTransition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbSelfTransitionActionPerformed(evt);
            }
        });
        jtbComponents.add(jtbSelfTransition);

        jtbArcTransition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vaucangraph/icons/arctransition.png"))); // NOI18N
        jtbArcTransition.setToolTipText("<html>\n<b>Transição em Arco</b><br>\nPara criar uma transição em arco, clique no estado<br>\nde origem e arraste até o estado de destino\n</html>");
        jtbArcTransition.setFocusable(false);
        jtbArcTransition.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jtbArcTransition.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jtbArcTransition.setMaximumSize(new java.awt.Dimension(43, 33));
        jtbArcTransition.setMinimumSize(new java.awt.Dimension(43, 33));
        jtbArcTransition.setPreferredSize(new java.awt.Dimension(43, 33));
        jtbArcTransition.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbArcTransition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbArcTransitionActionPerformed(evt);
            }
        });
        jtbComponents.add(jtbArcTransition);

        jtpProject.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jtpProject.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jtpProject.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jtpProject.setFont(new java.awt.Font("Tahoma", 1, 12));
        jtpProject.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jtpProjectStateChanged(evt);
            }
        });

        jspDesign.setBorder(null);

        jpDesign.setBackground(java.awt.Color.gray);

        javax.swing.GroupLayout jpDesignLayout = new javax.swing.GroupLayout(jpDesign);
        jpDesign.setLayout(jpDesignLayout);
        jpDesignLayout.setHorizontalGroup(
            jpDesignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );
        jpDesignLayout.setVerticalGroup(
            jpDesignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );

        jspDesign.setViewportView(jpDesign);

        jtpProject.addTab("Desenho", jspDesign);

        jspCode.setBorder(null);

        jepCode.setEditable(false);
        jepCode.setFont(new java.awt.Font("Monospaced", 0, 12));
        jepCode.setText("\\begin{VCPicture}{(0,0) (0,0)}\n\n\\end{VCPicture}");
        jspCode.setViewportView(jepCode);

        jtpProject.addTab("Código", jspCode);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jtbComponents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtpProject, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtpProject, 0, 0, Short.MAX_VALUE)
                    .addComponent(jtbComponents, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jsWidthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsWidthStateChanged
        setCanvasSize();
    }//GEN-LAST:event_jsWidthStateChanged

    private void jsHeightStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsHeightStateChanged
        setCanvasSize();
    }//GEN-LAST:event_jsHeightStateChanged

    private void jtbInitialStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbInitialStateActionPerformed
        statesSelected(jtbInitialState);
    }//GEN-LAST:event_jtbInitialStateActionPerformed

    private void jtbSimpleStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbSimpleStateActionPerformed
        statesSelected(jtbSimpleState);
    }//GEN-LAST:event_jtbSimpleStateActionPerformed

    private void jtbFinalStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbFinalStateActionPerformed
        statesSelected(jtbFinalState);
    }//GEN-LAST:event_jtbFinalStateActionPerformed

    private void jtbInitialFinalStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbInitialFinalStateActionPerformed
        statesSelected(jtbInitialFinalState);
    }//GEN-LAST:event_jtbInitialFinalStateActionPerformed

    private void jtbSelfTransitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbSelfTransitionActionPerformed
        transitionSelected(jtbSelfTransition);
    }//GEN-LAST:event_jtbSelfTransitionActionPerformed

    private void jtbSimpleTransitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbSimpleTransitionActionPerformed
        transitionSelected(jtbSimpleTransition);
    }//GEN-LAST:event_jtbSimpleTransitionActionPerformed

    private void jtbArcTransitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbArcTransitionActionPerformed
        transitionSelected(jtbArcTransition);
    }//GEN-LAST:event_jtbArcTransitionActionPerformed

    private void jtbSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbSelectorActionPerformed
        selectorSelected();
    }//GEN-LAST:event_jtbSelectorActionPerformed

    private void jtpProjectStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jtpProjectStateChanged
        if (jtpProject.getSelectedIndex() == 0) {
            setEnableAllComponents(true);
        } else {
            setEnableAllComponents(false);
        }
    }//GEN-LAST:event_jtpProjectStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jcbStateSize;
    private javax.swing.JEditorPane jepCode;
    private javax.swing.JLabel jlHeight;
    private javax.swing.JLabel jlStateSize;
    private javax.swing.JLabel jlWidth;
    private javax.swing.JPanel jpDesign;
    private javax.swing.JPanel jpToolBar;
    private javax.swing.JSpinner jsHeight;
    private javax.swing.JToolBar.Separator jsSeparator1;
    private javax.swing.JToolBar.Separator jsSeparator2;
    private javax.swing.JSpinner jsWidth;
    private javax.swing.JScrollPane jspCode;
    private javax.swing.JScrollPane jspDesign;
    private javax.swing.JToggleButton jtbArcTransition;
    private javax.swing.JToolBar jtbComponents;
    private javax.swing.JToggleButton jtbFinalState;
    private javax.swing.JToggleButton jtbInitialFinalState;
    private javax.swing.JToggleButton jtbInitialState;
    private javax.swing.JToggleButton jtbSelector;
    private javax.swing.JToggleButton jtbSelfTransition;
    private javax.swing.JToggleButton jtbSimpleState;
    private javax.swing.JToggleButton jtbSimpleTransition;
    private javax.swing.JTabbedPane jtpProject;
    // End of variables declaration//GEN-END:variables

    public JToggleButton[] getAllButtons() {
        return new JToggleButton[]{jtbSelector, jtbInitialState, jtbSimpleState,
                    jtbFinalState, jtbInitialFinalState, jtbSimpleTransition,
                    jtbSelfTransition, jtbArcTransition};
    }

    /**
     * @return the vaucangraph
     */
    public Vaucangraph getVaucangraph() {
        return vcg;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @return the canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * @param canvas the canvas to set
     */
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * @return the buttonGroup
     */
    public javax.swing.ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    /**
     * @return the jcbStateSize
     */
    public javax.swing.JComboBox getJcbStateSize() {
        return jcbStateSize;
    }

    /**
     * @return the jepCode
     */
    public javax.swing.JEditorPane getJepCode() {
        return jepCode;
    }

    /**
     * @return the jlHeight
     */
    public javax.swing.JLabel getJlHeight() {
        return jlHeight;
    }

    /**
     * @return the jlStateSize
     */
    public javax.swing.JLabel getJlStateSize() {
        return jlStateSize;
    }

    /**
     * @return the jlWidth
     */
    public javax.swing.JLabel getJlWidth() {
        return jlWidth;
    }

    /**
     * @return the jpBackground
     */
    public javax.swing.JPanel getJpBackground() {
        return jpToolBar;
    }

    /**
     * @return the jsHeight
     */
    public javax.swing.JSpinner getJsHeight() {
        return jsHeight;
    }

    /**
     * @return the jsWidth
     */
    public javax.swing.JSpinner getJsWidth() {
        return jsWidth;
    }

    /**
     * @return the jspCode
     */
    public javax.swing.JScrollPane getJspCode() {
        return jspCode;
    }

    /**
     * @return the jspDesign
     */
    public javax.swing.JScrollPane getJspDesign() {
        return jspDesign;
    }

    /**
     * @return the jpDesign
     */
    public javax.swing.JPanel getJpDesign() {
        return jpDesign;
    }

    /**
     * @return the jtbArcTransition
     */
    public javax.swing.JToggleButton getJtbArcTransition() {
        return jtbArcTransition;
    }

    /**
     * @return the jtbComponents
     */
    public javax.swing.JToolBar getJtbComponents() {
        return jtbComponents;
    }

    /**
     * @return the jtbFinalState
     */
    public javax.swing.JToggleButton getJtbFinalState() {
        return jtbFinalState;
    }

    /**
     * @return the jtbInitialFinalState
     */
    public javax.swing.JToggleButton getJtbInitialFinalState() {
        return jtbInitialFinalState;
    }

    /**
     * @return the jtbInitialState
     */
    public javax.swing.JToggleButton getJtbInitialState() {
        return jtbInitialState;
    }

    /**
     * @return the jtbSelfTransition
     */
    public javax.swing.JToggleButton getJtbSelfTransition() {
        return jtbSelfTransition;
    }

    /**
     * @return the jtbSimpleState
     */
    public javax.swing.JToggleButton getJtbSimpleState() {
        return jtbSimpleState;
    }

    /**
     * @return the jtbSimpleTransition
     */
    public javax.swing.JToggleButton getJtbSimpleTransition() {
        return jtbSimpleTransition;
    }

    /**
     * @return the jtpProject
     */
    public javax.swing.JTabbedPane getJtpProject() {
        return jtpProject;
    }
}
