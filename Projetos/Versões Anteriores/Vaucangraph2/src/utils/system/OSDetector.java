package utils.system;

/**
 * Classe utilitária para detecção de sistema operacional
 * @author JDownloader
 */
public class OSDetector {

    private static byte OS_ID = -1;
    private static String OS_STRING;
    public static final byte OS_LINUX_OTHER = 6;
    public static final byte OS_MAC_OTHER = 5;
    public static final byte OS_WINDOWS_OTHER = 4;
    public static final byte OS_WINDOWS_NT = 3;
    public static final byte OS_WINDOWS_2000 = 2;
    public static final byte OS_WINDOWS_XP = 0;
    public static final byte OS_WINDOWS_2003 = 7;
    public static final byte OS_WINDOWS_VISTA = 1;
    public static final byte OS_WINDOWS_7 = 8;

    /**
     * Método utilitário que obtém o sistema operacional
     */
    private static void getOS() {
        String OS = getOSString().toLowerCase();
        if (OS.indexOf("windows 7") > -1) {
            OS_ID = OS_WINDOWS_7;
        } else if (OS.indexOf("windows xp") > -1) {
            OS_ID = OS_WINDOWS_XP;
        } else if (OS.indexOf("windows vista") > -1) {
            OS_ID = OS_WINDOWS_VISTA;
        } else if (OS.indexOf("windows 2000") > -1) {
            OS_ID = OS_WINDOWS_2000;
        } else if (OS.indexOf("windows 2003") > -1) {
            OS_ID = OS_WINDOWS_2003;
        } else if (OS.indexOf("nt") > -1) {
            OS_ID = OS_WINDOWS_NT;
        } else if (OS.indexOf("windows") > -1) {
            OS_ID = OS_WINDOWS_OTHER;
        } else if (OS.indexOf("mac") > -1) {
            OS_ID = OS_MAC_OTHER;
        } else {
            OS_ID = OS_LINUX_OTHER;
        }
    }

    /**
     * Método utilitário que obtém o código (id) do sistema operacional
     *
     * @return id
     * código do sistema operacional <br>
     */
    public static byte getOSID() {
        if (OS_ID < 0) {
            OSDetector.getOS();
        }
        return OS_ID;
    }

    /**
     * Método utilitário que identifica o sistema operacional
     * @return true (verdadeiro) caso o sistema operacional for Linux
     */
    public static boolean isLinux() {
        byte id = OSDetector.getOSID();
        switch (id) {
            case OS_LINUX_OTHER:
                return true;
        }
        return false;
    }

    /**
     * Método utilitário que identifica o sistema operacional
     * @return true (verdadeiro) caso o sistema operacional for MacOS
     */
    public static boolean isMac() {
        byte id = OSDetector.getOSID();
        switch (id) {
            case OS_MAC_OTHER:
                return true;
        }
        return false;
    }

    /**
     * Método utilitário que identifica o sistema operacional
     * @return true (verdadeiro) caso o sistema operacional for Windows
     */
    public static boolean isWindows() {
        byte id = OSDetector.getOSID();
        switch (id) {
            case OS_WINDOWS_XP:
            case OS_WINDOWS_VISTA:
            case OS_WINDOWS_2000:
            case OS_WINDOWS_2003:
            case OS_WINDOWS_NT:
            case OS_WINDOWS_OTHER:
            case OS_WINDOWS_7:
                return true;
        }
        return false;
    }

    /**
     * Método utilitário que identifica o atual ambiente gráfico do sistema
     * @return true (verdadeiro) caso o ambiente gráfico atual seja Gnome
     */
    public static boolean isGnome() {
        if (!isLinux()) {
            return false;
        }
        // gdm session
        String gdmSession = System.getenv("GDMSESSION");
        if (gdmSession != null && gdmSession.toLowerCase().contains("gnome")) {
            return true;
        }

        // desktop session
        String desktopSession = System.getenv("DESKTOP_SESSION");
        if (desktopSession != null && desktopSession.toLowerCase().contains("gnome")) {
            return true;
        }

        // gnome desktop id
        String gnomeDesktopSessionId = System.getenv("GNOME_DESKTOP_SESSION_ID");
        if (gnomeDesktopSessionId != null && gnomeDesktopSessionId.trim().length() > 0) {
            return true;
        }

        return false;
    }

    /**
     * Método utilitário que identifica o atual ambiente gráfico do sistema
     * @return true (verdadeiro) caso o ambiente gráfico atual seja KDE
     */
    public static boolean isKDE() {
        if (!isLinux()) {
            return false;
        }

        // gdm session
        String gdmSession = System.getenv("GDMSESSION");
        if (gdmSession != null && gdmSession.toLowerCase().contains("kde")) {
            return true;
        }

        // desktop session
        String desktopSession = System.getenv("DESKTOP_SESSION");
        if (desktopSession != null && desktopSession.toLowerCase().contains("kde")) {
            return true;
        }

        // window manager
        String windowManager = System.getenv("WINDOW_MANAGER");
        if (windowManager != null && windowManager.trim().toLowerCase().endsWith("kde")) {
            return true;
        }

        return false;
    }

    public static String getOSString() {
        if (OS_STRING == null) {
            OS_STRING = System.getProperty("os.name");
        }
        return OS_STRING;
    }

    public static void setOSString(String property) {
        OS_STRING = property;
    }
}
