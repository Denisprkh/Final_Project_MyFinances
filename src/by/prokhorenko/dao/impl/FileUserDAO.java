package by.prokhorenko.dao.impl;

import by.prokhorenko.bean.user.User;
import by.prokhorenko.dao.UserDAO;
import by.prokhorenko.dao.exception.DAOException;
import by.prokhorenko.dao.exception.InvalidFieldException;
import by.prokhorenko.dao.exception.InvalidParameterException;
import static by.prokhorenko.constants.ConstantDigits.*;
import by.prokhorenko.dao.util.UserConverter;
import by.prokhorenko.util.FileUtilDAO;
import by.prokhorenko.util.exception.UtilException;
import by.prokhorenko.validation.Validation;

import java.util.ArrayList;

import static by.prokhorenko.util.ConstantsDAO.LINK_USERS;

public class FileUserDAO implements UserDAO {
    @Override
    public void add(User user) throws DAOException {
        if(Validation.isNull(user)){
            String mes = "User is null";
            throw new DAOException(mes);
        }

        try {
            String trans = UserConverter.convertUserToString(user).trim();
            FileUtilDAO.writeToFile(LINK_USERS, trans);
        } catch (InvalidParameterException e) {
            String mes = "Transaction is null";
            throw new DAOException(mes, e);
        } catch (InvalidFieldException e) {
            String mes = "User has incorrect fields";
            throw new DAOException(mes);
        } catch (UtilException e) {
            String mes = "File problems happened";
            throw new DAOException(mes,e);
        }
    }

    @Override
    public User get(int id) throws DAOException {
        ArrayList<User> users = getAll();

        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        String mes = "User with such id doesn't exist";
        throw new DAOException(mes);
    }

    @Override
    public ArrayList<User> getAll() throws DAOException {
        ArrayList<User> users = new ArrayList<>();
        try {
            String notSeparated = FileUtilDAO.readFile(LINK_USERS).trim();
            String[] separatedUsers = notSeparated.split("\\n");
            for(int i = ZERO; i < separatedUsers.length; i++){
                users.add(UserConverter.parseUserToObject(separatedUsers[i]));
            }
        } catch (InvalidFieldException e) {
            String mes = "Invalid amount of fields";
            throw new DAOException(mes,e);
        } catch (InvalidParameterException e) {
            String mes = "Data is null";
            throw new DAOException(mes,e);
        } catch (UtilException e) {
            String mes = "File problems happened";
            throw new DAOException(mes,e);
        }

        return users;
    }

    @Override
    public void update(int id, User updatedUser) throws DAOException {

        ArrayList<User> users = getAll();
        try {


            FileUtilDAO.cleanFile(LINK_USERS);
            for (User user : users) {
                if (user.getId() == id) {
                    FileUtilDAO.writeToFile(LINK_USERS, UserConverter.convertUserToString(updatedUser));
                } else {
                    FileUtilDAO.writeToFile(LINK_USERS, UserConverter.convertUserToString(user));
                }
            }
        } catch (InvalidFieldException e) {
            String mes = "User has incorrect fields";
            throw new DAOException(mes,e);
        } catch (InvalidParameterException e) {
            String mes = "User is null";
            throw new DAOException(mes,e);
        } catch (UtilException e) {
            String mes = "File problems happened";
            throw new DAOException(mes,e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        ArrayList<User> users = getAll();
        try {

            FileUtilDAO.cleanFile(LINK_USERS);
            for (User user : users) {
                if (user.getId() != id) {
                    FileUtilDAO.writeToFile(LINK_USERS, UserConverter.convertUserToString(user));
                }
            }
        } catch (InvalidFieldException e) {
            String mes = "User has incorrect fields";
            throw new DAOException(mes,e);
        } catch (InvalidParameterException e) {
            String mes = "User is null";
            throw new DAOException(mes,e);
        } catch (UtilException e) {
            String mes = "File problems happened";
            throw new DAOException(mes,e);
        }
    }
}
