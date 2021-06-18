package com.app.goaheadapp.models;

import java.util.List;

public class SubCatResponse {

    /**
     * status : true
     * code : 200
     * message :  Done Successfully !
     * items : [{"id":1,"name":"osama store","email":"osama@hotmail.com","license":"","category_id":1,"mobile":"1234567895","phone1":null,"phone2":null,"whats_up":"2586548965","image":"http://goaheadapp.net/uploads/store/5f4f3e6ec9459.jpeg","address":"الرياض","lan":"3.25","lat":"53.2","open":"14:02:00","close":"14:01:00","type_payment":0,"status":"active","type":0}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<SubCategory> items;

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

    public List<SubCategory> getItems() {
        return items;
    }

    public void setItems(List<SubCategory> items) {
        this.items = items;
    }
}
