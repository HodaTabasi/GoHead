package com.app.goaheadapp.models;

import java.util.List;

public class ChatDetailsResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : [{"id":8,"messag_id":4,"messag":"sdbhdbdlkcj hjlwbsdnm","type":1,"updated_at":"2020-10-17 16:36:08","created_at":"2020-10-17 16:36:08"},{"id":9,"messag_id":4,"messag":"sdbhdbdlkcj hjlwbsdnm","type":1,"updated_at":"2020-10-17 16:36:44","created_at":"2020-10-17 16:36:44"}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<ChatDitails> items;

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

    public List<ChatDitails> getItems() {
        return items;
    }

    public void setItems(List<ChatDitails> items) {
        this.items = items;
    }
}
