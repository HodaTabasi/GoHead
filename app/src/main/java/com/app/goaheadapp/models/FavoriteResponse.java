package com.app.goaheadapp.models;

import java.util.List;

public class FavoriteResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : []
     */

    private boolean status;
    private int code;
    private String message;
    private List<Favorite> items;

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

    public List<Favorite> getItems() {
        return items;
    }

    public void setItems(List<Favorite> items) {
        this.items = items;
    }
}
