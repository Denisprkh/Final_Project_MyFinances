package by.prokhorenko.dao.impl;

import by.prokhorenko.bean.user.User;
import by.prokhorenko.dao.IUserDAO;
import by.prokhorenko.dao.exception.DAOException;
import by.prokhorenko.dao.exception.InvalidFieldDAOException;
import by.prokhorenko.dao.exception.InvalidParameterDAOException;
import by.prokhorenko.util.convertor.UserConverter;
import by.prokhorenko.util.FileUtilDAO;
import by.prokhorenko.util.exception.UtilException;
import by.prokhorenko.validation.Validation;

import java.util.ArrayList;

import static by.prokhorenko.util.ConstantsDAO.LINK_USERS;

public class FileUserDAO implements IUserDAO {
    @Override
    public void add(User user) throws DAOException {
        if(Validation.isNull(user)){
            String mes = "User is null";
            throw new DAOException(mes);
        }

        try {
            String trans = UserConverter.convertUserToString(user).trim();
            FileUtilDAO.writeToFile(LINK_USERS, trans);
        } catch (InvalidParameterDAOException e) {
            String mes = "Transaction is null";
            throw new DAOException(mes, e);
        } catch (InvalidFieldDAOException e) {
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
    public User get(String login) throws DAOException {
        if(Validation.isNull(login)){
            String mes = "Login_is_null";
            throw new DAOException(mes);
        }
        ArrayList<User> users = getAll();

        for(User user : users){
            if(user.getLogin().equals(login)){
                return user;
            }
        }
        String mes = "User with such login doesn't exist";
        throw new DAOException(mes);
    }

    @Override
    public ArrayList<User> getAll() throws DAOException {
        ArrayList<User> users = new ArrayList<>();
        try {
            String notSeparated = FileUtilDAO.readFile(LINK_USERS).trim();
            String[] separatedUsers = notSeparated.split("\\n");
            for(int i = 0; i < separatedUsers.length; i++){
                users.add(UserConverter.parseUserToObject(separatedUsers[i]));
            }
        } catch (InvalidFieldDAOException e) {
            String mes = "Invalid amount of fields";
            throw new DAOException(mes,e);
        } catch (InvalidParameterDAOException e) {
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
        } catch (InvalidFieldDAOException e) {
            String mes = "User has incorrect fields";
            throw new DAOException(mes,e);
        } catch (InvalidParameterDAOException e) {
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
        } catch (InvalidFieldDAOException e) {
            String mes = "User has incorrect fields";
            throw new DAOException(mes,e);
        } catch (InvalidParameterDAOException e) {
            String mes = "User is null";
            throw new DAOException(mes,e);
        } catch (UtilException e) {
            String mes = "File problems happened";
            throw new DAOException(mes,e);
        }
    }
}
