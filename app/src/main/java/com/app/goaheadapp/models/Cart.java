package com.app.goaheadapp.models;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.app.goaheadapp.BR;

public class Cart extends BaseObservable {

    /**
     * id : 1
     * user_id : 2
     * product_id : 1
     * quantity : 1
     * price : 15
     * created_at : 2020-09-02 07:59:37
     * product : {"id":1,"store_id":1,"name":"دجاج","description":"دجاج مشوي بالفحم","image":"http://goaheadapp.net/uploads/products/images/5f4f41b7b9810.jpg","price":"15","offer_price":"6","category_id":1,"status":"active","delete":0}
     */

    private int id;
    private int user_id;
    private int product_id;
    private int quantity;
    private int price;
    private String created_at;
    private Product product;

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
        notifyPropertyChanged(BR.user_id);
    }

    @Bindable
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
        notifyPropertyChanged(BR.product_id);
    }

    @Bindable
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        notifyPropertyChanged(BR.quantity);
    }

    @Bindable
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
        notifyPropertyChanged(BR.created_at);
    }

    @Bindable
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        notifyPropertyChanged(BR.product);
    }

    @BindingAdapter(value = {"imageUrl"})
    public static void loadImage(ImageView imageView, String image) {
        Glide.with(imageView)
                .load(image)
                .into(imageView);
    }
}
