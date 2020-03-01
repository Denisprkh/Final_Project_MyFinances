package by.prokhorenko.dao.util;

import by.prokhorenko.bean.user.User;
import by.prokhorenko.dao.exception.InvalidFieldException;
import by.prokhorenko.dao.exception.InvalidParameterException;
import by.prokhorenko.validation.Validation;

import java.math.BigDecimal;

public class UserConverter {

    private static final int VALID_LENGTH = 4;
    private static final String DELIMETER = "|";
    private static final String REGEX= "\\|";

    public static String convertUserToString(User user) throws InvalidParameterException, InvalidFieldException {
        if(Validation.isNull(user)){
            String mes = "User is null";
            throw new InvalidParameterException(mes);
        }
        if(!allUsersFieldsAreCorrect(user)){
            String mes = "User has incorrect fields";
            throw new InvalidFieldException(mes);
        }

        String result = user.getLogin() + DELIMETER + user.getPassword() + DELIMETER + user.getId() + DELIMETER
                + user.getBalance();

        return result;
    }

    public static User parseUserToObject(String data) throws InvalidParameterException, InvalidFieldException {
        if(Validation.isNull(data)){
            String mes = "Data is null";
            throw new InvalidParameterException(mes);
        }

        String[] usersFields = data.split(REGEX);
        if(usersFields.length != VALID_LENGTH){
            String mes = "Invalid amount of fields";
            throw new InvalidFieldException(mes);
        }

        User user = new User(usersFields[0],usersFields[1],Long.parseLong(usersFields[2]),
                new BigDecimal(usersFields[3]));

        return user;
    }

    private static boolean allUsersFieldsAreCorrect(User user){

        return Validation.isMoreThanZero(user.getId()) && !Validation.isNull(user.getPassword()) &&
                !Validation.isNull(user.getLogin()) && !user.getPassword().isEmpty() && !user.getLogin().isEmpty();
    }
}
