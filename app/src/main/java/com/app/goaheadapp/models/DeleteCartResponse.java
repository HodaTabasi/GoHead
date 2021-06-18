package com.app.goaheadapp.models;

import java.util.List;

public class DeleteCartResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : []
     */

    private boolean status;
    private int code;
    private String message;
    private List<?> items;

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

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }
}
