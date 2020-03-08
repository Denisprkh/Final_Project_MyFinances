package by.prokhorenko.bean.user;

import java.io.Serializable;
import java.util.Objects;


public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    private String login;
    private String password;
    private long id;


    public User(){
        super();
        this.id = 0;
        this.login = "user" + id;
        this.password = "user" + id;
    }

    public User(String login, String password, long id){
        super();
        this.login = login;
        this.password = password;

        this.id = id;

    }





    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User other = (User) o;
       if(id != other.id) return false;
       if(login == null){
           if(other.login != null) return false;
       }else if(!login.equals(other.login)) return false;
       if(password == null){
           if(other.password != null) return false;
       }else if(!password.equals(other.password)) return false;

       return true;
    }

    @Override
    public int hashCode(){
        final int PRIME = 31;
        int result = 1;
        result = result * PRIME + (int) (id ^ (id >>> 32));
        result = result * PRIME + (login == null ? 0 : login.hashCode());
        result = result * PRIME + (password == null ? 0 : password.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
