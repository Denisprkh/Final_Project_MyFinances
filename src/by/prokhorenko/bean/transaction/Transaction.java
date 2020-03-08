package by.prokhorenko.bean.transaction;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Transaction implements Serializable {


    private static final long serialVersionUID = 1L;

    private static long transactionCounter;

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
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Transaction other = (Transaction) o;
        if(transactionId != other.transactionId) return false;
        if(usersId != other.usersId) return false;
        if(transactionType == null){
            if(other.transactionType != null) return false;
        }else if(!transactionType.equals(other.transactionType)) return false;
        if(date == null){
            if(other.date != null) return false;
        }else if(!date.equals(other.date)) return false;
        if(amount == null){
            if(other.amount != null) return false;
        }else if(!amount.equals(other.amount)) return false;
        if(comment == null){
            if(other.comment != null) return false;
        }else if(!comment.equals(other.comment)) return false;
        return true;
    }


    @Override
    public int hashCode() {

        final int PRIME = 31;
        int result = 1;
        result = result * PRIME + (int) (transactionId ^ (transactionId >>> 32));
        result = result * PRIME + (int) (usersId ^ (usersId >>> 32));
        result = result * PRIME + (date == null ? 0 : date.hashCode());
        result = result * PRIME + (amount == null ? 0 : amount.hashCode());
        result = result * PRIME + (transactionType == null ? 0 : transactionType.hashCode());
        result = result * PRIME + (comment == null ? 0 : comment.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"{" +
                "transactionId=" + transactionId +
                ", date=" + date +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                ", usersId=" + usersId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
