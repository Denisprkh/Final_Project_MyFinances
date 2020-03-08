package by.prokhorenko.util.convertor;

import by.prokhorenko.bean.transaction.Transaction;
import by.prokhorenko.bean.transaction.TransactionType;
import by.prokhorenko.dao.exception.InvalidFieldDAOException;
import by.prokhorenko.dao.exception.InvalidParameterDAOException;
import by.prokhorenko.validation.Validation;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TransactionConverter {

    private static final String DATE_FORMAT = "dd-mm-yyyy";
    private static final int VALID_LENGTH = 6;
    private static final String DELIMITER = "|";
    private static final String REGEX= "\\|";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final String EXPENSE = "Expense";
    private static final String INCOME = "Income";


    public static String convertTransactionToString(Transaction transaction) throws InvalidParameterDAOException,
            InvalidFieldDAOException {
        if(Validation.isNull(transaction)){
            String mes = "Transaction is null";
            throw new InvalidParameterDAOException(mes);
        }
        if(!allTransactionsFieldsAreCorrect(transaction)){
            String mes = "Transaction has incorrect fields";
            throw new InvalidFieldDAOException(mes);
        }

        String result = transaction.getTransactionType() + DELIMITER + transaction.getAmount() + DELIMITER
                + transaction.getTransactionId() + DELIMITER +
                + transaction.getUsersId() + DELIMITER +dateFormat.format(transaction.getDate()) + DELIMITER +
                transaction.getComment();

        return result;

    }

    public static String convertTransactionToPrintableString(Transaction transaction) throws InvalidParameterDAOException,
            InvalidFieldDAOException {
        if(Validation.isNull(transaction)){
            String mes = "Transaction is null";
            throw new InvalidParameterDAOException(mes);
        }
        if(!allTransactionsFieldsAreCorrect(transaction)){
            String mes = "Transaction has incorrect fields";
            throw new InvalidFieldDAOException(mes);
        }

        String result = transaction.getTransactionType() + DELIMITER + transaction.getAmount() + DELIMITER
                + dateFormat.format(transaction.getDate()) + DELIMITER +
                transaction.getComment();

        return result;

    }


    private static boolean allTransactionsFieldsAreCorrect(Transaction transaction){
        return !Validation.isNull(transaction.getDate()) && !Validation.isNull(transaction.getAmount()) &&
                Validation.isMoreThanZero(transaction.getTransactionId()) &&
                Validation.isMoreThanZero(transaction.getUsersId()) && !Validation.isNull(transaction.getComment());

    }

    public static Transaction parseTransactionToObject(String data) throws InvalidParameterDAOException,
            InvalidFieldDAOException, ParseException {
        if(Validation.isNull(data) || data.isEmpty()){
            String mes = "Data is null or empty";
            throw new InvalidParameterDAOException(mes);
        }

        String[] transactionFields = data.split(REGEX);
        if(transactionFields.length != VALID_LENGTH){
            String mes = "Invalid amount of fields";
            throw new InvalidParameterDAOException(mes);
        }
            Transaction transaction;
        if(transactionFields[0].equalsIgnoreCase(INCOME)){
            transaction = new Transaction(TransactionType.INCOME,new BigDecimal(transactionFields[1]),
                   Long.parseLong(transactionFields[2]),
                    Long.parseLong(transactionFields[3]), dateFormat.parse(transactionFields[4]), transactionFields[5]);
        }else if(transactionFields[0].equalsIgnoreCase(EXPENSE) ){
            transaction = new Transaction(TransactionType.EXPENSE,new BigDecimal(transactionFields[1]),
                    Long.parseLong(transactionFields[2]),
                    Long.parseLong(transactionFields[3]),dateFormat.parse(transactionFields[4]), transactionFields[5]);
        }else{
            String mes = "Invalid values of fields in data";
            throw new InvalidFieldDAOException(mes);
        }
        return transaction;
    }




}
