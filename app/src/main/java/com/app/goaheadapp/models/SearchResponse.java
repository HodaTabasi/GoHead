package com.app.goaheadapp.models;

import java.util.List;

public class SearchResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : [{"id":3,"name":"Jelani Bell","description":"Qui consequatur Con","email":"qudawowaz@mailinator.com","license":"","category_id":1,"mobile":"123456785","phone1":null,"phone2":null,"whats_up":"9512357854","image":"http://goaheadapp.net/uploads/store/5f58c763d7f47.jpg","address":"الرياض","lan":"63.3","lat":"5.2","open":"20:26:00","close":"01:47:00","type_payment":1,"status":"active","type":0,"website":"https://www.fedolutobiqyj.org","rate":0,"is_favourite":0}]
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
