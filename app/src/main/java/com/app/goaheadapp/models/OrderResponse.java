package com.app.goaheadapp.models;

import java.util.List;

public class OrderResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : [{"id":3,"reference_id":null,"user_id":2,"store_id":"1","product_cost":"1554","delivery_cost":0,"total_cost":"1554","status":4,"name":"osama","mobile":"754457795","location":"مكة المكرمة.شارع","type_payment":0,"created_at":"2020-09-02 10:01:46","change_Status":"مكتمل","customer_name":"osama","customer_mobile":"754457795","products":[{"id":5,"order_id":3,"product_id":1,"quantity":"1","price":"6","product":{"id":1,"store_id":1,"name":"دجاج","description":"دجاج مشوي بالفحم","image":"http://goaheadapp.net/uploads/products/images/5f4f41b7b9810.jpg","price":"15","offer_price":"6","category_id":1,"status":"active","delete":0,"is_favourite":1,"rate":4,"favourite_count":1}},{"id":6,"order_id":3,"product_id":3,"quantity":"2","price":"774","product":{"id":3,"store_id":1,"name":"Aaron Hayden","description":"Obcaecati magni duis","image":"http://goaheadapp.net/uploads/products/images/5f4f548b0e2a7.jpeg","price":"990","offer_price":"774","category_id":1,"status":"active","delete":0,"is_favourite":0,"rate":1,"favourite_count":0}}],"store":{"id":1,"name":"osama store","description":null,"email":"osama@hotmail.com","license":"","category_id":1,"mobile":"1234567895","phone1":null,"phone2":null,"whats_up":"2586548965","image":"http://goaheadapp.net/uploads/store/5f4f3e6ec9459.jpeg","address":"الرياض","lan":"3.25","lat":"53.2","open":"14:02:00","close":"14:01:00","type_payment":0,"status":"active","type":0,"website":null,"is_favourite":1,"rate":0}},{"id":4,"reference_id":null,"user_id":2,"store_id":"1","product_cost":"6","delivery_cost":0,"total_cost":"6","status":4,"name":"osama","mobile":"754457795","location":"مكة المكرمة.شارع","type_payment":0,"created_at":"2020-09-02 12:12:59","change_Status":"مكتمل","customer_name":"osama","customer_mobile":"754457795","products":[],"store":{"id":1,"name":"osama store","description":null,"email":"osama@hotmail.com","license":"","category_id":1,"mobile":"1234567895","phone1":null,"phone2":null,"whats_up":"2586548965","image":"http://goaheadapp.net/uploads/store/5f4f3e6ec9459.jpeg","address":"الرياض","lan":"3.25","lat":"53.2","open":"14:02:00","close":"14:01:00","type_payment":0,"status":"active","type":0,"website":null,"is_favourite":1,"rate":0}}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<Order> items;

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

    public List<Order> getItems() {
        return items;
    }

    public void setItems(List<Order> items) {
        this.items = items;
    }
}
