package com.app.goaheadapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.app.goaheadapp.BR;

public class Category extends BaseObservable implements Parcelable {

    /**
     * id : 1
     * logo : http://goaheadapp.net/uploads/category/5f4f3c7c5aea8.png
     * status : active
     * type : 0
     * title : Marker
     */

    private int id;
    private String logo;
    private String status;
    private int type;
    private String title;

    protected Category(Parcel in) {
        id = in.readInt();
        logo = in.readString();
        status = in.readString();
        type = in.readInt();
        title = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
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
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
        notifyPropertyChanged(BR.logo);
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
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @BindingAdapter(value = {"imageUrl"})
    public static void loadImage(ImageView imageView, String image) {
        Glide.with(imageView)
                .load(image)
                .into(imageView);
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(logo);
        parcel.writeString(status);
        parcel.writeInt(type);
        parcel.writeString(title);
    }
}
