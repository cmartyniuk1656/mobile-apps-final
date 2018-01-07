package com.chrismartyniuk.assignment_2.data.model;

/**
 * Created by cmartyniuk1656 on 12/4/2017.
 */

public class Member {

    public static final String TAG = Member.class.getSimpleName();
    //Table Name
    public static final String TABLE = "Member";

    //Column names
    public static final String KEY_MemberId = "MemberId";
    public static final String KEY_MemberName = "MemberName";
    public static final String KEY_MemberPassword = "MemberPassword";

    private String memberId;
    private String memberName;
    private String memberPassword;

    public String getMemberId() {
       return memberId;
   }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }


}
