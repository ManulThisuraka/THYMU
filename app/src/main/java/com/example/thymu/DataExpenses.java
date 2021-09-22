package com.example.thymu;


public class DataExpenses {
    private String bill_type;
    private String acc_num;
    private String next_bill;

    public DataExpenses() {
    }

    public String getBill_type() {
        return bill_type;
    }


    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }


    public String getAcc_num() {
        return acc_num;
    }

    public void setAcc_num(String acc_num) {
        this.acc_num = acc_num;
    }

    public String getNext_bill() {
        return next_bill;
    }

    public void setNext_bill(String next_bill) {
        this.next_bill = next_bill;
    }
}
