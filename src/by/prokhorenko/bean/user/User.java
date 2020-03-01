package by.prokhorenko.bean.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import static by.prokhorenko.constants.ConstantDigits.*;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private static int usersCounter = ZERO;
    private String login;
    private String password;
    private long id ;
    private BigDecimal balance;

    public User(){
        super();
        this.id = ++usersCounter;
        this.login = "user" + id;
        this.password = "user" + id;
    }

    public User(String login, String password){
        super();
        this.login = login;
        this.password = password;
        this.id = ++usersCounter;
        this.balance = new BigDecimal(ZERO);
    }

    public User(String login, String password,long id, BigDecimal balance){
        super();
        this.login = login;
        this.password = password;
        this.id = id;
        this.balance = balance;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(balance, user.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, id, balance);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", balance=" + balance +
                '}';
    }
}
