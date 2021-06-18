package com.app.goaheadapp.models;

import java.util.List;

public class ProductResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : [{"id":1,"store_id":1,"name":"دجاج","description":"دجاج مشوي بالفحم","image":"http://goaheadapp.net/uploads/products/images/5f4f41b7b9810.jpg","price":"15","offer_price":"6","category_id":1,"status":"active","delete":0},{"id":3,"store_id":1,"name":"Aaron Hayden","description":"Obcaecati magni duis","image":"http://goaheadapp.net/uploads/products/images/5f4f548b0e2a7.jpeg","price":"990","offer_price":"774","category_id":1,"status":"active","delete":0}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<Product> items;

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

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

}
