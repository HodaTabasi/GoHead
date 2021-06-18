package com.app.goaheadapp.models;

import java.util.List;

public class PrivacyResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : [{"id":1,"status":"active","text":"سياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصية"}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<Privacy> items;

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

    public List<Privacy> getItems() {
        return items;
    }

    public void setItems(List<Privacy> items) {
        this.items = items;
    }

}
