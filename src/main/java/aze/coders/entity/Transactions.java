package aze.coders.entity;

public class Transactions {
    private int transaction_id ;
    private String date;
    private int sender_account;
    private int receiver_account;
    private double amount;
    private String currency;


    public Transactions(String date, int sender_account, int receiver_account, int amount, String currency) {
        this.date = date;
        this.sender_account = sender_account;
        this.receiver_account = receiver_account;
        this.amount = amount;
        this.currency = currency;
    }

    public Transactions(){

    }

    @Override
    public String toString() {
        return "Transactions{" +
                "transaction_id=" + transaction_id +
                ", date='" + date + '\'' +
                ", sender_account=" + sender_account +
                ", receiver_account=" + receiver_account +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSender_account() {
        return sender_account;
    }

    public void setSender_account(int sender_account) {
        this.sender_account = sender_account;
    }

    public int getReceiver_account() {
        return receiver_account;
    }

    public void setReceiver_account(int receiver_account) {
        this.receiver_account = receiver_account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
