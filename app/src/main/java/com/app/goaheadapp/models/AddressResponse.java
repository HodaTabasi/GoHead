package com.app.goaheadapp.models;

import java.util.List;

public class AddressResponse {

    /**
     * status : true
     * code : 200
     * message :  Done Successfully !
     * items : [{"id":1,"user_id":2,"lat":"2.35","lan":"63585","address":"مكة المكرمة.شارع"}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<Address> items;

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

    public List<Address> getItems() {
        return items;
    }

    public void setItems(List<Address> items) {
        this.items = items;
    }
}
