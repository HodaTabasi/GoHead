package com.app.goaheadapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.app.goaheadapp.BR;

public class Product extends BaseObservable implements Parcelable {


    /**
     * id : 1
     * store_id : 1
     * name : دجاج
     * description : دجاج مشوي بالفحم
     * image : http://goaheadapp.net/uploads/products/images/5f4f41b7b9810.jpg
     * price : 15
     * offer_price : 6
     * category_id : 1
     * status : active
     * delete : 0
     * is_favourite : 1
     * rate : 4
     * favourite_count : 1
     */

    private int id;
    private int store_id;
    private String name;
    private String description;
    private String image;
    private String price;
    private String offer_price;
    private int category_id;
    private String status;
    private int delete;
    private int is_favourite;
    private int rate;
    private int favourite_count;


    protected Product(Parcel in) {
        id = in.readInt();
        store_id = in.readInt();
        name = in.readString();
        description = in.readString();
        image = in.readString();
        price = in.readString();
        offer_price = in.readString();
        category_id = in.readInt();
        status = in.readString();
        delete = in.readInt();
        is_favourite = in.readInt();
        rate = in.readInt();
        favourite_count = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
        notifyPropertyChanged(BR.store_id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }

    @Bindable
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public String getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(String offer_price) {
        this.offer_price = offer_price;
        notifyPropertyChanged(BR.offer_price);
    }

    @Bindable
    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
        notifyPropertyChanged(BR.category_id);
    }

    @Bindable
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyPropertyChanged(BR.status);
    }

    @Bindable
    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
        notifyPropertyChanged(BR.delete);
    }

    @Bindable
    public int getIs_favourite() {
        return is_favourite;
    }

    public void setIs_favourite(int is_favourite) {
        this.is_favourite = is_favourite;
        notifyPropertyChanged(BR.is_favourite);
    }

    @Bindable
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
        notifyPropertyChanged(BR.rate);
    }

    @Bindable
    public int getFavourite_count() {
        return favourite_count;
    }

    public void setFavourite_count(int favourite_count) {
        this.favourite_count = favourite_count;
        notifyPropertyChanged(BR.favourite_count);
    }

    @BindingAdapter(value = {"imageUrl"})
    public static void loadImage(ImageView imageView, String image) {
        Glide.with(imageView)
                .load(image)
                .into(imageView);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(store_id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(image);
        parcel.writeString(price);
        parcel.writeString(offer_price);
        parcel.writeInt(category_id);
        parcel.writeString(status);
        parcel.writeInt(delete);
        parcel.writeInt(is_favourite);
        parcel.writeInt(rate);
        parcel.writeInt(favourite_count);
    }
}
