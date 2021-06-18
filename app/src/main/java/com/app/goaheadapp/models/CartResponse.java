package com.app.goaheadapp.models;

import java.util.List;

public class CartResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : {"total_price":2015,"cart":[{"id":1,"user_id":2,"product_id":1,"quantity":1,"price":15,"created_at":"2020-09-02 07:59:37","product":{"id":1,"store_id":1,"name":"دجاج","description":"دجاج مشوي بالفحم","image":"http://goaheadapp.net/uploads/products/images/5f4f41b7b9810.jpg","price":"15","offer_price":"6","category_id":1,"status":"active","delete":0}},{"id":2,"user_id":2,"product_id":3,"quantity":2,"price":990,"created_at":"2020-09-02 08:15:39","product":{"id":3,"store_id":1,"name":"Aaron Hayden","description":"Obcaecati magni duis","image":"http://goaheadapp.net/uploads/products/images/5f4f548b0e2a7.jpeg","price":"990","offer_price":"774","category_id":1,"status":"active","delete":0}},{"id":3,"user_id":2,"product_id":2,"quantity":2,"price":10,"created_at":"2020-09-05 08:12:26","product":{"id":2,"store_id":1,"name":"فلافل","description":"فلافل عالي الجودة","image":"http://goaheadapp.net/uploads/products/images/5f4f41db3c564.jpeg","price":"10","offer_price":"5","category_id":2,"status":"active","delete":0}}]}
     */

    private boolean status;
    private int code;
    private String message;
    private ItemsBean items;

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

    public ItemsBean getItems() {
        return items;
    }

    public void setItems(ItemsBean items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * total_price : 2015
         * cart : [{"id":1,"user_id":2,"product_id":1,"quantity":1,"price":15,"created_at":"2020-09-02 07:59:37","product":{"id":1,"store_id":1,"name":"دجاج","description":"دجاج مشوي بالفحم","image":"http://goaheadapp.net/uploads/products/images/5f4f41b7b9810.jpg","price":"15","offer_price":"6","category_id":1,"status":"active","delete":0}},{"id":2,"user_id":2,"product_id":3,"quantity":2,"price":990,"created_at":"2020-09-02 08:15:39","product":{"id":3,"store_id":1,"name":"Aaron Hayden","description":"Obcaecati magni duis","image":"http://goaheadapp.net/uploads/products/images/5f4f548b0e2a7.jpeg","price":"990","offer_price":"774","category_id":1,"status":"active","delete":0}},{"id":3,"user_id":2,"product_id":2,"quantity":2,"price":10,"created_at":"2020-09-05 08:12:26","product":{"id":2,"store_id":1,"name":"فلافل","description":"فلافل عالي الجودة","image":"http://goaheadapp.net/uploads/products/images/5f4f41db3c564.jpeg","price":"10","offer_price":"5","category_id":2,"status":"active","delete":0}}]
         */

        private int total_price;
        private List<Cart> cart;

        public int getTotal_price() {
            return total_price;
        }

        public void setTotal_price(int total_price) {
            this.total_price = total_price;
        }

        public List<Cart> getCart() {
            return cart;
        }

        public void setCart(List<Cart> cart) {
            this.cart = cart;
        }
    }
}
