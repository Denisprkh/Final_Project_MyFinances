package by.prokhorenko.util.convertor;

import by.prokhorenko.bean.user.User;
import by.prokhorenko.dao.exception.InvalidFieldDAOException;
import by.prokhorenko.dao.exception.InvalidParameterDAOException;
import by.prokhorenko.validation.Validation;

public class UserConverter {

    private static final int VALID_LENGTH = 3;
    private static final String DELIMITER = "|";
    private static final String REGEX= "\\|";

    public static String convertUserToString(User user) throws InvalidParameterDAOException, InvalidFieldDAOException {
        if(Validation.isNull(user)){
            String mes = "User is null";
            throw new InvalidParameterDAOException(mes);
        }
        if(!allUsersFieldsAreCorrect(user)){
            String mes = "User has incorrect fields";
            throw new InvalidFieldDAOException(mes);
        }

        String result = user.getLogin() + DELIMITER + user.getPassword() + DELIMITER + user.getId();

        return result;
    }

    public static User parseUserToObject(String data) throws InvalidParameterDAOException, InvalidFieldDAOException {
        if(Validation.isNull(data)){
            String mes = "Data is null";
            throw new InvalidParameterDAOException(mes);
        }
        User user = new User();
        if(data.isEmpty()){
            return user;
        }

        String[] usersFields = data.split(REGEX);
        if(usersFields.length != VALID_LENGTH){
            String mes = "Invalid amount of fields";
            throw new InvalidFieldDAOException(mes);
        }

        user = new User(usersFields[0],usersFields[1],Long.parseLong(usersFields[2]));

        return user;
    }

    private static boolean allUsersFieldsAreCorrect(User user){

        return Validation.isMoreThanZero(user.getId()) && !Validation.isNull(user.getPassword()) &&
                !Validation.isNull(user.getLogin()) && !user.getPassword().isEmpty() && !user.getLogin().isEmpty();
    }
}
