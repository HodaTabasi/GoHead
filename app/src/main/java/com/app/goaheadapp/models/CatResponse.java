package com.app.goaheadapp.models;

import java.util.List;

public class CatResponse {

    /**
     * status : true
     * code : 200
     * message :  Done Successfully !
     * items : [{"id":1,"logo":"http://goaheadapp.net/uploads/category/5f4f3c7c5aea8.png","status":"active","type":0,"title":"Marker"}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<Category> items;

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

    public List<Category> getItems() {
        return items;
    }

    public void setItems(List<Category> items) {
        this.items = items;
    }
}
