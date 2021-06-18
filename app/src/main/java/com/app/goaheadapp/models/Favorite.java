package com.app.goaheadapp.models;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Favorite {

    /**
     * id : 1
     * name : osama store
     * description : null
     * email : osama@hotmail.com
     * license :
     * category_id : 1
     * mobile : 1234567895
     * phone1 : null
     * phone2 : null
     * whats_up : 2586548965
     * image : http://goaheadapp.net/uploads/store/5f4f3e6ec9459.jpeg
     * address : الرياض
     * lan : 3.25
     * lat : 53.2
     * open : 14:02:00
     * close : 14:01:00
     * type_payment : 0
     * status : active
     * type : 0
     * is_favourite : 1
     * rate : 0
     */

    private int id;
    private String name;
    private String description;
    private String email;
    private String license;
    private int category_id;
    private String mobile;
    private int phone1;
    private int phone2;
    private String whats_up;
    private String image;
    private String address;
    private String lan;
    private String lat;
    private String open;
    private String close;
    private int type_payment;
    private String status;
    private int type;
    private int is_favourite;
    private int rate;

    /**
     * store_id : 1
     * price : 15
     * offer_price : 6
     * delete : 0
     * favourite_count : 2
     */

    private int store_id;
    private String price;
    private String offer_price;
    private int delete;
    private int favourite_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Object getPhone1() {
        return phone1;
    }

    public void setPhone1(int phone1) {
        this.phone1 = phone1;
    }

    public int getPhone2() {
        return phone2;
    }

    public void setPhone2(int phone2) {
        this.phone2 = phone2;
    }

    public String getWhats_up() {
        return whats_up;
    }

    public void setWhats_up(String whats_up) {
        this.whats_up = whats_up;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public int getType_payment() {
        return type_payment;
    }

    public void setType_payment(int type_payment) {
        this.type_payment = type_payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIs_favourite() {
        return is_favourite;
    }

    public void setIs_favourite(int is_favourite) {
        this.is_favourite = is_favourite;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(String offer_price) {
        this.offer_price = offer_price;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public int getFavourite_count() {
        return favourite_count;
    }

    public void setFavourite_count(int favourite_count) {
        this.favourite_count = favourite_count;
    }

    @BindingAdapter(value = {"imageUrl"})
    public static void loadImage(ImageView imageView, String image) {
        Glide.with(imageView)
                .load(image)
                .into(imageView);
    }
}
