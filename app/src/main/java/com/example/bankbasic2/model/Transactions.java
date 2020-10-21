package com.example.bankbasic2.model;

public class Transactions
{
    private String s_name;
    private String r_name;
    private int t_amt;
    private int s_acc;
   private int r_acc;
    private int id2;
    private String time;
    private int sbbt;
    private int sbat;
    private int rbbt;
    private int rbat;

    public Transactions() {
        this.s_name = s_name;
        this.r_name = r_name;
        this.t_amt = t_amt;
        this.s_acc = s_acc;
        this.r_acc = r_acc;
        this.id2 = id2;
        this.time= time;
        this.rbbt=rbbt;
        this.rbat=rbat;
        this.sbbt=sbbt;
        this.sbat=sbat;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public int getT_amt() {
        return t_amt;
    }

    public void setT_amt(int t_amt) {
        this.t_amt = t_amt;
    }

    public int getS_acc() {
        return s_acc;
    }

    public void setS_acc(int s_acc) {
        this.s_acc = s_acc;
    }

    public int getR_acc() {
        return r_acc;
    }

    public void setR_acc(int r_acc) {
        this.r_acc = r_acc;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSbbt() {
        return sbbt;
    }

    public void setSbbt(int sbbt) {
        this.sbbt = sbbt;
    }

    public int getSbat() {
        return sbat;
    }

    public void setSbat(int sbat) {
        this.sbat = sbat;
    }

    public int getRbbt() {
        return rbbt;
    }

    public void setRbbt(int rbbt) {
        this.rbbt = rbbt;
    }

    public int getRbat() {
        return rbat;
    }

    public void setRbat(int rbat) {
        this.rbat = rbat;
    }
}
