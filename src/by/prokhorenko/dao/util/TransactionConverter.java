package by.prokhorenko.dao.util;

import by.prokhorenko.bean.transaction.Transaction;
import by.prokhorenko.bean.transaction.TransactionType;
import by.prokhorenko.dao.exception.InvalidFieldException;
import by.prokhorenko.dao.exception.InvalidParameterException;
import by.prokhorenko.validation.Validation;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TransactionConverter {

    private static final String DATE_FORMAT = "dd-mm-yyyy hh:mm:ss";
    private static final int VALID_LENGTH = 6;
    private static final String DELIMETER = "|";
    private static final String REGEX= "\\|";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final String EXPENSE = "Expense";
    private static final String INCOME = "Income";


    public static String convertTransactionToString(Transaction transaction) throws InvalidParameterException,
            InvalidFieldException {
        if(Validation.isNull(transaction)){
            String mes = "Transaction is null";
            throw new InvalidParameterException(mes);
        }
        if(!allTransactionsFieldsAreCorrect(transaction)){
            String mes = "Transaction has incorrect fields";
            throw new InvalidFieldException(mes);
        }

        String result = transaction.getTransactionType() + DELIMETER + transaction.getAmount() + DELIMETER
                + transaction.getTransactionId() + DELIMETER +
                + transaction.getUsersId() + DELIMETER +dateFormat.format(transaction.getDate()) + DELIMETER +
                transaction.getComment();

        return result;

    }

    private static boolean allTransactionsFieldsAreCorrect(Transaction transaction){
        return !Validation.isNull(transaction.getDate()) && !Validation.isNull(transaction.getAmount()) &&
                Validation.isMoreThanZero(transaction.getTransactionId()) &&
                Validation.isMoreThanZero(transaction.getUsersId()) && !Validation.isNull(transaction.getComment());

    }

    public static Transaction parseTransactionToObject(String data) throws InvalidParameterException,
            InvalidFieldException, ParseException {
        if(Validation.isNull(data) || data.isEmpty()){
            String mes = "Data is null or empty";
            throw new InvalidParameterException(mes);
        }

        String[] transactionFields = data.split(REGEX);
        if(transactionFields.length != VALID_LENGTH){
            String mes = "Invalid amount of fields";
            throw new InvalidParameterException(mes);
        }
            Transaction transaction;
        if(transactionFields[0].equalsIgnoreCase(INCOME)){
            transaction = new Transaction(TransactionType.INCOME,new BigDecimal(Integer.parseInt(transactionFields[1])),
                   Long.parseLong(transactionFields[2]),
                    Long.parseLong(transactionFields[3]), dateFormat.parse(transactionFields[4]), transactionFields[5]);
        }else if(transactionFields[0].equalsIgnoreCase(EXPENSE) ){
            transaction = new Transaction(TransactionType.EXPENSE,new BigDecimal(Integer.parseInt(transactionFields[1])),
                    Long.parseLong(transactionFields[2]),
                    Long.parseLong(transactionFields[3]),dateFormat.parse(transactionFields[4]), transactionFields[5]);
        }else{
            String mes = "Invalid values of fields in data";
            throw new InvalidFieldException(mes);
        }
        return transaction;
    }




}
