package com.app.goaheadapp.models;

public class ProductOrder {

    /**
     * id : 6
     * order_id : 3
     * product_id : 3
     * quantity : 2
     * price : 774
     * product : {"id":3,"store_id":1,"name":"Aaron Hayden","description":"Obcaecati magni duis","image":"http://goaheadapp.net/uploads/products/images/5f4f548b0e2a7.jpeg","price":"990","offer_price":"774","category_id":1,"status":"active","delete":0,"is_favourite":0,"rate":1,"favourite_count":0}
     */

    private int id;
    private int order_id;
    private int product_id;
    private String quantity;
    private String price;
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
