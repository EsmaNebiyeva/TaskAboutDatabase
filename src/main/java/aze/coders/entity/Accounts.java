package aze.coders.entity;

public class Accounts {

    private int account_id;
    private String accountname;
    private int branch_id;
    private int currency_id;


    public Accounts(int account_id, String accountname, int branch_id, int currency_id){
        this.account_id = account_id;
        this.accountname = accountname;
        this.branch_id = branch_id;
        this.currency_id = currency_id;

    }

    public Accounts(){

    }

    @Override
    public String toString() {
        return "Accounts{" +
                "account_id=" + account_id +
                ", accountname='" + accountname + '\'' +
                ", branch_id=" + branch_id +
                ", currency_id=" + currency_id +
                '}';
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }
}
