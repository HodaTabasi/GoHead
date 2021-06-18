package com.app.goaheadapp.models;

public class PaymentMethod {

    /**
     * user_id : 2
     * holdername : ahmed omsa
     * cardNo : 10352652
     * email : osama@hotmail.com
     * expiredate : 2020-09-19
     * type : 1
     * updated_at : 2020-10-19 15:43:57
     * created_at : 2020-10-19 15:43:57
     * id : 8
     */

    private int user_id;
    private String holdername;
    private String cardNo;
    private String email;
    private String expiredate;
    private String type;
    private String updated_at;
    private String created_at;
    private int id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getHoldername() {
        return holdername;
    }

    public void setHoldername(String holdername) {
        this.holdername = holdername;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
