package by.prokhorenko.validation;

import java.util.Objects;

public class Validation {

    public static boolean isNull(Object o){
        return o == null;
    }

    public static boolean isMoreThanZero(int num){
        return num > 0;
    }

    public static boolean isMoreThanZero(long num){
        return num > 0;
    }
}
