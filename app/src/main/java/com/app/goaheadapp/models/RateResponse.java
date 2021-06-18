package com.app.goaheadapp.models;

import java.util.List;

public class RateResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : [{"id":5,"user_id":2,"product_id":null,"store_id":null,"driver_id":1,"value":1,"text":"texttexttexttexttexttext","created_at":"2020-09-03 12:40:02","updated_at":"2020-09-03 12:40:02","deleted_at":null,"user_name":"osama","driver_name":"osamaAtef3","user_mobile":"754457795","driver_mobile":"4545555545"},{"id":11,"user_id":2,"product_id":null,"store_id":null,"driver_id":1,"value":1,"text":"texttexttexttexttexttext","created_at":"2020-09-13 19:22:20","updated_at":"2020-09-13 19:22:20","deleted_at":null,"user_name":"osama","driver_name":"osamaAtef3","user_mobile":"754457795","driver_mobile":"4545555545"}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<Rate> items;

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

    public List<Rate> getItems() {
        return items;
    }

    public void setItems(List<Rate> items) {
        this.items = items;
    }

}
