package by.prokhorenko.bean.transaction;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Transaction implements Serializable {


    private static final long serialVersionUID = 1L;

    private static long transactionCounter = 0;

    private long transactionId;
    private Date date;
    private BigDecimal amount;
    private TransactionType transactionType;
    private long usersId;
    private String comment;

    public Transaction(){
        super();
    }

    public Transaction(TransactionType transactionType, BigDecimal amount, Date date, long usersId, String comment){
        super();
        this.transactionId = ++transactionCounter;
        this.date = date;
        this.amount = amount;
        this.transactionType = transactionType;
        this.usersId = usersId;
        this.comment = comment;

    }

    public Transaction(TransactionType transactionType, BigDecimal amount, long transactionId,long usersId,Date date,
                       String comment){
        super();
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.transactionType = transactionType;
        this.usersId = usersId;
        this.comment = comment;

    }


    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public long getUsersId() {
        return usersId;
    }

    public void setUsersId(long usersId) {
        this.usersId = usersId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionId == that.transactionId &&
                usersId == that.usersId &&
                Objects.equals(date, that.date) &&
                Objects.equals(amount, that.amount) &&
                transactionType == that.transactionType &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, date, amount, transactionType, usersId, comment);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", date=" + date +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                ", usersId=" + usersId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
