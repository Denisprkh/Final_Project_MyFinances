package by.prokhorenko.util;

public class ConstantsDAO {

    public static final String LINK_USERS;

    static {
        StringBuilder link = new StringBuilder();
        link.append("src\\resources\\Users.txt");
        LINK_USERS = link.toString();
    }

    public static final String LINK_TRANSACTIONS;

    static {
        StringBuilder link = new StringBuilder();
        link.append("src\\resources\\Transactions.txt");
        LINK_TRANSACTIONS = link.toString();
    }

}
