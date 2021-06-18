package com.app.goaheadapp.models;

import java.util.List;

public class AddsResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : [{"id":1,"image":"http://goaheadapp.net/uploads/ads/5f58bfcdc195c.jpeg","category":"الكترونيات","link":"https://www.google.com/"}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<Add> items;

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

    public List<Add> getItems() {
        return items;
    }

    public void setItems(List<Add> items) {
        this.items = items;
    }

}
