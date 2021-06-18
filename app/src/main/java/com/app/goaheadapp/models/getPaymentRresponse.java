package com.app.goaheadapp.models;

public class getPaymentRresponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : 3
     */

    private boolean status;
    private int code;
    private String message;
    private int items;

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

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }
}
