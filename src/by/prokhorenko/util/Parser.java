package by.prokhorenko.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static final String DELIMITER = " ";

    public static String getValueParam(String request, String nameParam) {
        if (request == null && nameParam == null) {
            return null;
        }
        Pattern pat = Pattern.compile(nameParam + "=(\\S+)");
        Matcher mat = pat.matcher(request);
        if (mat.find()) {
            return mat.group(1);
        } else {
            return null;
        }
    }
}
