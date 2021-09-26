package com.example.thymu;


import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;

public class DataExpenses implements Serializable {

    @Exclude
    private String key;
    private String bill_type;
    private String acc_num;
    private String next_bill;

    private String position;

    public DataExpenses() {
    }

    public DataExpenses(String name, String position) {
        this.acc_num = name;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
