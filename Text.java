import java.lang.Thread;

/**
 * A library of text functions
 * 
 * attributes:
 * boolean useANSI - enable ANSI features like color and fast screen clear
 * int linesClearedNoANSI - lines that .cls() inserts if useANSI is false
 * int printWaittime between slowPrint() characters in milliseconds
 * final String[][] ANSI_COLORS - an array of arrays of ANSI colors
 * each color is in the format [color in english, ANSI code]
 * String currentDefaultColor - the default color used if useANSI is on
 * 
 * methods:
 * print(String str) - prints out a string that can be formatted with colors
 * print(String str, String color) - prints out a string with one color
 * slowPrint(String str) - prints out a string one char at a time that can be formatted with colors
 * slowPrint(String str, String color) - prints out a string one char at a time with one color
 * getAnsiColorCode(String color) - returns the ANSI escape code that turns letters after it a color
 * cls() - clear screen
 * wait(int ms) - waits ms milliseconds
 */
class Text {
    public static boolean useANSI = true;  // enable ANSI features like color and fast screen clear
    public static int linesClearedNoANSI = 50;  // lines that .cls() inserts if useANSI is false
    public static final int printWait = 30; // time between slowPrint() characters in milliseconds

    // ANSI escape codes that turn letters after it a color
    public static final String[][] ANSI_COLORS = {
            {"red", "\033[38;5;203m"},
            {"orange", "\033[38;5;215m"},
            {"yellow", "\033[38;5;228m"},
            {"green", "\033[38;5;48m"},
            {"cyan", "\u001b[36m"},
            {"blue", "\033[38;5;74m"},
            {"purple", "\033[38;5;5m"},
            {"pink", "\033[38;5;219m"},
            {"white", "\033[38;5;15m"},
            {"gray", "\033[38;5;242m"},
            {"brown", "\033[38;5;58m"},
            {"black", "\u001b[30m"},
    };

    public static String currentDefaultColor = "white";

    /**
     * prints out a string
     * the string can be formatted according to ANSI_COLORS and with the § symbol:
     * eg "hello §redCarl!"
     */
    public static void print(String str) {
        if (str.charAt(0) != '§') {
            str = "§" + currentDefaultColor + str;
        }

        String[] sections = str.split("§");

        for (String s : sections) {
            if (s.length() == 0) {
                continue; // ignore empty s[0]
            }

            // find which color to print
            for (String[] ANSI_dict : ANSI_COLORS) {
                if (s.length() > ANSI_dict[0].length()) {
                    // if the color at the front of section s matches a color
                    if (s.substring(0, ANSI_dict[0].length()).equals(ANSI_dict[0])) {
                        System.out.print(ANSI_dict[1] + s.substring(ANSI_dict[0].length(), s.length()));
                    }
                }
            }
        }
    }

    /**
     * prints out a string with a single color from ANSI_COLORS
     */
    public static void print(String str, String color) {
        if (useANSI) {
            System.out.print(getAnsiColorCode(color));
        }

        System.out.print(str);
    }

    /**
     * prints out a string one char at a time
     * the string can be formatted according to ANSI_COLORS and with the § symbol:
     * eg "hello §redCarl!"
     */
    public static void slowPrint(String str) {
        if (str.charAt(0) != '§') {
            str = "§" + currentDefaultColor + str;
        }

        String[] sections = str.split("§");

        for (String s : sections) {
            if (s.length() == 0) {
                continue; // ignore empty s[0]
            }

            // find which color to print
            for (String[] ANSI_dict : ANSI_COLORS) {
                if (s.length() > ANSI_dict[0].length()) {
                    // if the color at the front of section s matches a color
                    if (s.substring(0, ANSI_dict[0].length()).equals(ANSI_dict[0])) {
                        slowPrint(s.substring(ANSI_dict[0].length(), s.length()), ANSI_dict[0]);
                    }
                }
            }
        }
    }

    /**
     * prints out a string with a single color from ANSI_COLORS one char at a time
     */
    public static void slowPrint(String str, String color) {
        if (useANSI) {
            System.out.print(getAnsiColorCode(color));
        }

        try {
            for (int i = 0; i < str.length(); i++) {
                System.out.print(str.charAt(i));
                Thread.sleep(printWait);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * returns the ANSI escape code that turns letters after it a color
     */
    public static String getAnsiColorCode(String color) {
        for (String[] colorDict : ANSI_COLORS) {
            if (colorDict[0].equals(color)) {
                return colorDict[1];
            }
        }

        // this should probably throw an error but i don't feel like dealing with modding every method header
        return "INVALID COLOR";
    }

    /**
     * clear screen
     */
    public static void cls() {
        if (useANSI) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } else {
            for (int i = 0; i < linesClearedNoANSI; i++) {
                System.out.print("\n");
            }
        }
    }

    /**
     * waits ms milliseconds.
     * just a wrapper for Thread.sleep() so you don't have to deal with all the prereqs yourself.
     */
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
