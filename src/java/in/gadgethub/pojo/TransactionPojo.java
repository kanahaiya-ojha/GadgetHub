/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.pojo;

import java.sql.Date;

/**
 *
 * @author JASRAJ
 */
public class TransactionPojo {
    private String transactionId;
    private String useremail;
    private Date transTime;
    private double amount;

    @Override
    public String toString() {
        return "TransactionPojo{" + "transactionId=" + transactionId + ", useremail=" + useremail + ", transTime=" + transTime + ", amount=" + amount + '}';
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionPojo(String transactionId, String useremail, Date transTime, double amount) {
        this.transactionId = transactionId;
        this.useremail = useremail;
        this.transTime = transTime;
        this.amount = amount;
    }

    public TransactionPojo() {
    }
}
