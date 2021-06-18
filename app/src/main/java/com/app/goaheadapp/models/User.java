package com.app.goaheadapp.models;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.app.goaheadapp.BR;
import com.app.goaheadapp.R;

public class User extends BaseObservable {

    /**
     * id : 3
     * company_id : 0
     * id_namber :
     * address :
     * lat : 0
     * lan : 0
     * phone :
     * name : osama
     * email : osa1@hotmail.com
     * mobile : 754457796
     * mobile1 :
     * status : active
     * type : 0
     * driver_not :
     * profile_image :
     * image_identity :
     * created_at : 2020-09-03 19:38:59
     * rate:1
     * access_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImQ0NTE3MGY2NDk2OTA0ZDk2OWZjYTMyNDk5ODNmNzNkZjFkOTJjNWNiODI3YWY4ZGYxNDQ2ODlhZGYyZGZiMjA2OTIyNDIxNWQwZmQ3ZjM3In0.eyJhdWQiOiIxIiwianRpIjoiZDQ1MTcwZjY0OTY5MDRkOTY5ZmNhMzI0OTk4M2Y3M2RmMWQ5MmM1Y2I4MjdhZjhkZjE0NDY4OWFkZjJkZmIyMDY5MjI0MjE1ZDBmZDdmMzciLCJpYXQiOjE1OTkxNTExMzksIm5iZiI6MTU5OTE1MTEzOSwiZXhwIjoxNjMwNjg3MTM5LCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.hymqU4SUo3shKE-SAnAKIeCG_4DLXePEXK1VDqoQ665T3sXYOODPgiNWipxd0oOtZHsXDvtGN8GgWmI8WMuWFGJ1sGGmgIa7qf9u05AwlvNOi6TnE8w1k_WDLjoYZ25YiACPZhNIOsd9JgKfTmQu5eRvKLX9rdQskhR9XuCIV5Bw2b_RkjJo5C-FKM17RahYZ-evpv31FmbNcqwuytf9ta4hi7FPNBTaKMaWg7e1YPcBoyPhlnQoUK7eT5YfUyZA4ySA5SsKWkgowiomJsqSOaTbZAr-gpLB86pA50C8NmVyEhX74f0_LrTqKt2kfIFd8ayVMZBrOWnQoG2c6t6hwh1t1W72oDv8CgbtRLR7EKeEsKcmBLRpokuI2XSmk756InUbdSNvYz0AaxIef5xqkbP2Rdk7mpJvrf3O0WQhhMuhy-GgcHhSxulNr9qs7cNewDj6RF-SyOi1L_csok-RyBfEjf35IUyoAMaaQ0zfzKccGAj8crKi7p8eWRWQG3jeO-NKBMMDWiKeAkwTb52fki1N3WG0WVZvrBZ6-eDwGyCSYVdkQXDFOHlfGKLpW7ZmhCbpYFtSFo7JTAVYikwxTd-smU2FGBHfckMoxrmoI4p38MsAmUXEW7Zip5qgVlr2kNOgvsMhKhqmxeUbUPEwWr5c9aKHL-mgc4eDEsTGQ3c
     */

    private int id;
    private int company_id;
    private String id_namber;
    private String address;
    private double lat;
    private double lan;
    private String phone;
    private String name;
    private String email;
    private String mobile;
    private String mobile1;
    private String status;
    private int type;
    private String driver_not;
    private String profile_image;
    private String image_identity;
    private String created_at;
    private String access_token;
    private int rate;

    @BindingAdapter(value = {"imageUrl"})
    public static void loadImage(ImageView imageView, String image) {
        Glide.with(imageView)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.c_blue_shape)
                .into(imageView);
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
        notifyPropertyChanged(BR.company_id);
    }

    @Bindable
    public String getId_namber() {
        return id_namber;
    }

    public void setId_namber(String id_namber) {
        this.id_namber = id_namber;
        notifyPropertyChanged(BR.id_namber);
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
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
        notifyPropertyChanged(BR.lat);
    }

    @Bindable
    public double getLan() {
        return lan;
    }

    public void setLan(double lan) {
        this.lan = lan;
        notifyPropertyChanged(BR.lan);
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
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
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        notifyPropertyChanged(BR.mobile);
    }

    @Bindable
    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
        notifyPropertyChanged(BR.mobile1);
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
    public String getDriver_not() {
        return driver_not;
    }

    public void setDriver_not(String driver_not) {
        this.driver_not = driver_not;
        notifyPropertyChanged(BR.driver_not);
    }

    @Bindable
    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
        notifyPropertyChanged(BR.profile_image);
    }

    @Bindable
    public String getImage_identity() {
        return image_identity;
    }

    public void setImage_identity(String image_identity) {
        this.image_identity = image_identity;
        notifyPropertyChanged(BR.image_identity);
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
    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
        notifyPropertyChanged(BR.access_token);
    }

    @Bindable
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
        notifyPropertyChanged(BR.rate);
    }
}
