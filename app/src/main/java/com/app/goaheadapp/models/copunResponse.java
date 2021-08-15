package com.app.goaheadapp.models;

public class copunResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : 10
     */

    private boolean status;
    private int code;
    private String message;
    private String items;

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

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}
