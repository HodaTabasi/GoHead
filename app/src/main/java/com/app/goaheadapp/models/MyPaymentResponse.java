package com.app.goaheadapp.models;

import java.util.List;

public class MyPaymentResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : [{"id":1,"user_id":2,"type":1,"holdername":"ahmed omsa","cardNo":"10352652","email":"osama@hotmail.com","expiredate":"2020-09-19","created_at":"2021-08-07 14:05:08","updated_at":"2021-08-07 14:05:08","deleted_at":"0000-00-00 00:00:00"},{"id":2,"user_id":2,"type":2,"holdername":null,"cardNo":null,"email":"osama@hotmail.com","expiredate":null,"created_at":"2021-08-07 14:28:26","updated_at":"2021-08-07 14:28:26","deleted_at":"0000-00-00 00:00:00"}]
     */

    private boolean status;
    private int code;
    private String message;
    /**
     * id : 1
     * user_id : 2
     * type : 1
     * holdername : ahmed omsa
     * cardNo : 10352652
     * email : osama@hotmail.com
     * expiredate : 2020-09-19
     * created_at : 2021-08-07 14:05:08
     * updated_at : 2021-08-07 14:05:08
     * deleted_at : 0000-00-00 00:00:00
     */

    private List<MyPayment> items;

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

    public List<MyPayment> getItems() {
        return items;
    }

    public void setItems(List<MyPayment> items) {
        this.items = items;
    }
}
