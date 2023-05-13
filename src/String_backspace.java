public class String_backspace {
    public static String chop (String str) {
        if (str == null || str.length() == 0) return "";
        return str.substring(0, str.length() - 1);
    }
}
