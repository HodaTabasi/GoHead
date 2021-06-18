package com.app.goaheadapp.models;

import java.util.List;

public class NoteListResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : [{"id":4,"user_id":2,"driver_id":"osamaAtef3","order_id":3,"created_at":"16:36","updated_at":"2020-10-17 16:36:08","last_messages":"sdbhdbdlkcj hjlwbsdnm","user_name":"osama","driver_name":"","user_mobile":"754457795","driver_mobile":""},{"id":3,"user_id":2,"driver_id":"osamaAtef3","order_id":1,"created_at":"16:29","updated_at":"2020-10-17 16:29:10","last_messages":"text message  text message  text message  text message  text message  text message  text message  text message  text message  text message  text message  text message","user_name":"osama","driver_name":"","user_mobile":"754457795","driver_mobile":""},{"id":5,"user_id":2,"driver_id":"osamaAtef3","order_id":2,"created_at":"22:15","updated_at":"2020-10-30 22:15:24","last_messages":"Mmm","user_name":"osama","driver_name":"","user_mobile":"754457795","driver_mobile":""}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<MessageList> items;

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

    public List<MessageList> getItems() {
        return items;
    }

    public void setItems(List<MessageList> items) {
        this.items = items;
    }
}
