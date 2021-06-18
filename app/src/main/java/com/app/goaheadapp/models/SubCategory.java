package com.app.goaheadapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.app.goaheadapp.BR;

public class SubCategory extends BaseObservable implements Parcelable {

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
     * website : null
     * is_favourite : 1
     * rate : 0
     */

    private int id;
    private String name;
    private String email;
    private String license;
    private int category_id;
    private String mobile;
    private Object phone1;
    private Object phone2;
    private String whats_up;
    private String image;
    private String address;
    private String lan;
    private String lat;
    private String open;
    private String close;
    private int type_payment;
    private String status;
    private String website;
    private String description;
    private int type;
    private int is_favourite;
    private int rate;


    protected SubCategory(Parcel in) {
        id = in.readInt();
        name = in.readString();
        email = in.readString();
        license = in.readString();
        category_id = in.readInt();
        mobile = in.readString();
        whats_up = in.readString();
        image = in.readString();
        address = in.readString();
        lan = in.readString();
        lat = in.readString();
        open = in.readString();
        close = in.readString();
        type_payment = in.readInt();
        status = in.readString();
        website = in.readString();
        description = in.readString();
        type = in.readInt();
        is_favourite = in.readInt();
        rate = in.readInt();
    }

    public static final Creator<SubCategory> CREATOR = new Creator<SubCategory>() {
        @Override
        public SubCategory createFromParcel(Parcel in) {
            return new SubCategory(in);
        }

        @Override
        public SubCategory[] newArray(int size) {
            return new SubCategory[size];
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
        notifyPropertyChanged(BR.license);
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
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        notifyPropertyChanged(BR.mobile);
    }

    @Bindable
    public Object getPhone1() {
        return phone1;
    }

    public void setPhone1(Object phone1) {
        this.phone1 = phone1;
        notifyPropertyChanged(BR.phone1);
    }

    @Bindable
    public Object getPhone2() {
        return phone2;
    }

    public void setPhone2(Object phone2) {
        this.phone2 = phone2;
        notifyPropertyChanged(BR.phone2);
    }

    @Bindable
    public String getWhats_up() {
        return whats_up;
    }

    public void setWhats_up(String whats_up) {
        this.whats_up = whats_up;
        notifyPropertyChanged(BR.whats_up);
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
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }

    @Bindable
    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
        notifyPropertyChanged(BR.lan);
    }

    @Bindable
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
        notifyPropertyChanged(BR.lat);
    }

    @Bindable
    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
        notifyPropertyChanged(BR.open);
    }

    @Bindable
    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
        notifyPropertyChanged(BR.close);
    }

    @Bindable
    public int getType_payment() {
        return type_payment;
    }

    public void setType_payment(int type_payment) {
        this.type_payment = type_payment;
        notifyPropertyChanged(BR.type_payment);
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
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(license);
        parcel.writeInt(category_id);
        parcel.writeString(mobile);
        parcel.writeString(whats_up);
        parcel.writeString(image);
        parcel.writeString(address);
        parcel.writeString(lan);
        parcel.writeString(lat);
        parcel.writeString(open);
        parcel.writeString(close);
        parcel.writeInt(type_payment);
        parcel.writeString(status);
        parcel.writeString(website);
        parcel.writeString(description);
        parcel.writeInt(type);
        parcel.writeInt(is_favourite);
        parcel.writeInt(rate);
    }

    @Bindable
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
        notifyPropertyChanged(BR.website);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }
}
