package com.chrismartyniuk.assignment_2.data.model;

/**
 * Created by cmartyniuk1656 on 12/4/2017.
 */

public class Money {

    public static final String TAG = Money.class.getSimpleName();
    //Table Name
    public static final String TABLE = "Money";

    //Colums names
    public static final String KEY_MemberName = "MemberName";
    public static final String KEY_MemberMoney = "MemberMoney";

    private String memberName;
    private int memberMoney;


    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }


    public int getMemberMoney() {
        return memberMoney;
    }

    public int getMemberWinMoney() {
        return memberMoney;
    }

    public void incrementMoney() {
        memberMoney += 100;
    }

    public void setMemberMoney(int memberMoney) {
        this.memberMoney = memberMoney;
    }

}
