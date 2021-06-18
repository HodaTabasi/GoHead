package com.app.goaheadapp.models;

public class PaymentMethodResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : {"user_id":2,"holdername":"ahmed omsa","cardNo":"10352652","email":"osama@hotmail.com","expiredate":"2020-09-19","type":"1","updated_at":"2020-10-19 15:43:57","created_at":"2020-10-19 15:43:57","id":8}
     */

    private boolean status;
    private int code;
    private String message;
    private PaymentMethod items;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PaymentMethod getItems() {
        return items;
    }

    public void setItems(PaymentMethod items) {
        this.items = items;
    }
}
