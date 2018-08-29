package utils.formatters;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

/**
 * Classe utilitária para formatação
 * @author JDownloader
 */
public class Formatter {

    private static SimpleDateFormat DATE_FORMAT = null;

    /**
     * Formatiert Sekunden in das zeitformat stunden:minuten:sekunden returns
     * "~" vor values <0
     *
     * @param eta
     * @return formatierte Zeit
     */
    public static String formatSeconds(long eta) {
        if (eta < 0) {
            return "~";
        }
        long hours = eta / (60 * 60);
        eta -= hours * 60 * 60;
        long minutes = eta / 60;
        long seconds = eta - minutes * 60;
        String ret = "";
        if (hours != 0) {
            ret += hours + "h:";
        }
        if (minutes != 0) {
            ret += Formatter.fillInteger(minutes, 2, "0") + "m:";
        }
        ret += Formatter.fillInteger(seconds, 2, "0") + "s";
        return ret;
    }

    /**
     * FOIrmatiert im format hours:minutes:seconds.ms
     *
     * @param ms
     */
    public static String formatMilliseconds(long ms) {
        return formatSeconds(ms / 1000) + "." + Formatter.fillInteger(ms % 1000, 3, "0");
    }

    public static String formatFilesize(double value, int size) {
        if (value > 1024 && size < 5) {
            return formatFilesize(value / 1024.0, ++size);
        } else {
            DecimalFormat c = new DecimalFormat("0.00");
            switch (size) {
                case 0:
                    return c.format(value) + " B";
                case 1:
                    return c.format(value) + " KB";
                case 2:
                    return c.format(value) + " MB";
                case 3:
                    return c.format(value) + " GB";
                case 4:
                    return c.format(value) + " TB";
            }
        }
        return null;
    }

    public static String formatReadable(long value) {
        if (value < 0) {
            value = 0;
        }
        DecimalFormat c = new DecimalFormat("0.00");
        if (value >= (1024 * 1024 * 1024 * 1024l)) {
            return c.format(value / (1024 * 1024 * 1024 * 1024.0)) + " TB";
        }
        if (value >= (1024 * 1024 * 1024l)) {
            return c.format(value / (1024 * 1024 * 1024.0)) + " GB";
        }
        if (value >= (1024 * 1024l)) {
            return c.format(value / (1024 * 1024.0)) + " MB";
        }
        if (value >= 1024l) {
            return c.format(value / 1024.0) + " KB";
        }
        return value + " B";
    }

    public static double formatDouble(double number) {
        String numberString = new DecimalFormat().format(number);
        numberString = numberString.replace(",", ".");
        return Double.parseDouble(numberString);
    }

    public static String fillString(String binaryString, String pre, String post, int length) {
        while (binaryString.length() < length) {
            if (binaryString.length() < length) {
                binaryString = pre + binaryString;
            }
            if (binaryString.length() < length) {
                binaryString = binaryString + post;
            }
        }
        return binaryString;
    }

    /**
     * Hängt an i solange fill vorne an bis die zechenlänge von i gleich num ist
     *
     * @param i
     * @param num
     * @param fill
     * @return aufgefüllte Zeichenkette
     */
    public static String fillInteger(long i, int num, String fill) {
        String ret = "" + i;
        while (ret.length() < num) {
            ret = fill + ret;
        }
        return ret;
    }

    /**
     * GIbt den Integer der sich in src befindet zurück. alle nicht
     * integerzeichen werden ausgefiltert
     *
     * @param src
     * @return Integer in src
     */
    public static int filterInt(String src) {
        try {
            return Integer.parseInt(filterString(src, "1234567890"));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static long filterLong(String src) {
        try {
            return Long.parseLong(filterString(src, "1234567890"));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String filterString(String str) {
        String allowed = "QWERTZUIOPÜASDFGHJKLÖÄYXCVBNMqwertzuiopasdfghjklyxcvbnmöäü;:,._-&$%(){}#~+ 1234567890<>='\"/";
        return filterString(str, allowed);
    }

    /**
     * Filtert alle zeichen aus str die in filter nicht auftauchen
     *
     * @param str
     * @param filter
     * @return
     */
    public static String filterString(String str, String filter) {
        if (str == null || filter == null) {
            return "";
        }

        byte[] org = str.getBytes();
        byte[] mask = filter.getBytes();
        byte[] ret = new byte[org.length];
        int count = 0;
        int i;
        for (i = 0; i < org.length; i++) {
            byte letter = org[i];
            for (byte element : mask) {
                if (letter == element) {
                    ret[count] = letter;
                    count++;
                    break;
                }
            }
        }
        return new String(ret).trim();
    }

    /**
     * Returns a string containing an HTTP-formatted date.
     *
     * @param time
     * The date to format (current time in msec).
     *
     * @return HTTP date string representing the given time.
     */
    public static String formatTime(long time) {
        if (DATE_FORMAT == null) {
            DATE_FORMAT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            DATE_FORMAT.setTimeZone(new SimpleTimeZone(0, "GMT"));
            DATE_FORMAT.setLenient(true);
        }
        return DATE_FORMAT.format(new Date(time)).substring(0, 29);
    }
}
