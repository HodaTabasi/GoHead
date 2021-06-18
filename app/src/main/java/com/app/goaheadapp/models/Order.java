package com.app.goaheadapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.app.goaheadapp.BR;

import java.util.List;

public class Order extends BaseObservable implements Parcelable {

    /**
     * id : 1
     * reference_id : null
     * user_id : 2
     * store_id : 1
     * product_cost : 774
     * delivery_cost : 503
     * total_cost : 1277
     * status : 0
     * name : osama
     * mobile : 754457795
     * location : مكة المكرمة.شارع
     * lan : null
     * lat : null
     * type_payment : null
     * created_at : 2020-09-19 09:57:21
     * change_Status : جديد
     * customer_name : osama
     * customer_mobile : 754457795
     * driver : {"id":1,"company_id":2,"id_namber":"7527527525","address":"شارع حوار، الريان، الرياض","lat":52.3,"lan":3.256,"phone":"1234567895","name":"osamaAtef3","email":"osama_et@hotmail.com","mobile":"","mobile1":"4545555545","status":"active","type":1,"driver_not":"Ratione et non conse","profile_image":"http://goaheadapp.net/uploads/drivers/profile_images/5f899005bd317.png","image_identity":"http://goaheadapp.net/uploads/drivers/image_identity/5f4f446c34849.jpg","created_at":"2020-09-02 07:06:20","rate":1}
     * store_name : osama store
     * store_image : http://goaheadapp.net/uploads/store/5f4f3e6ec9459.jpeg
     * store_open : 14:02:00
     * store_close : 14:01:00
     * products : [{"id":1,"order_id":1,"product_id":1,"quantity":"1","price":"6","product":{"id":1,"store_id":1,"name":"دجاج","description":"دجاج مشوي بالفحم","image":"http://goaheadapp.net/uploads/products/images/5f4f41b7b9810.jpg","price":"15","offer_price":"6","category_id":1,"status":"active","delete":0,"is_favourite":1,"rate":4,"favourite_count":1}},{"id":2,"order_id":1,"product_id":3,"quantity":"2","price":"774","product":{"id":3,"store_id":1,"name":"Aaron Hayden","description":"Obcaecati magni duis","image":"http://goaheadapp.net/uploads/products/images/5f4f548b0e2a7.jpeg","price":"990","offer_price":"774","category_id":1,"status":"active","delete":0,"is_favourite":0,"rate":1,"favourite_count":0}},{"id":31,"order_id":1,"product_id":3,"quantity":"1","price":"774","product":{"id":3,"store_id":1,"name":"Aaron Hayden","description":"Obcaecati magni duis","image":"http://goaheadapp.net/uploads/products/images/5f4f548b0e2a7.jpeg","price":"990","offer_price":"774","category_id":1,"status":"active","delete":0,"is_favourite":0,"rate":1,"favourite_count":0}}]
     * store : {"id":1,"name":"osama store","description":null,"email":"osama@hotmail.com","license":"","category_id":1,"mobile":"1234567895","phone1":null,"phone2":null,"whats_up":"2586548965","image":"http://goaheadapp.net/uploads/store/5f4f3e6ec9459.jpeg","address":"الرياض","lan":"3.25","lat":"53.2","open":"14:02:00","close":"14:01:00","type_payment":1,"status":"active","type":0,"website":null,"rate":1,"is_favourite":1}
     */

    private int id;
    private int reference_id;
    private int user_id;
    private String store_id;
    private String product_cost;
    private int delivery_cost;
    private String total_cost;
    private int status;
    private String name;
    private String mobile;
    private String location;
    private String lan;
    private String lat;
    private String type_payment;
    private String created_at;
    private String change_Status;
    private String customer_name;
    private String customer_mobile;
    private User driver;
    private String store_name;
    private String store_image;
    private String store_open;
    private String store_close;
    private SubCategory store;
    private List<ProductOrder> products;

    protected Order(Parcel in) {
        id = in.readInt();
        reference_id = in.readInt();
        user_id = in.readInt();
        store_id = in.readString();
        product_cost = in.readString();
        delivery_cost = in.readInt();
        total_cost = in.readString();
        status = in.readInt();
        name = in.readString();
        mobile = in.readString();
        location = in.readString();
        lan = in.readString();
        lat = in.readString();
        type_payment = in.readString();
        created_at = in.readString();
        change_Status = in.readString();
        customer_name = in.readString();
        customer_mobile = in.readString();
        store_name = in.readString();
        store_image = in.readString();
        store_open = in.readString();
        store_close = in.readString();
        store = in.readParcelable(SubCategory.class.getClassLoader());
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
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
    public int getReference_id() {
        return reference_id;
    }

    public void setReference_id(int reference_id) {
        this.reference_id = reference_id;
        notifyPropertyChanged(BR.reference_id);
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
    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
        notifyPropertyChanged(BR.store_id);
    }

    @Bindable
    public String getProduct_cost() {
        return product_cost;
    }

    public void setProduct_cost(String product_cost) {
        this.product_cost = product_cost;
        notifyPropertyChanged(BR.product_cost);
    }

    @Bindable
    public int getDelivery_cost() {
        return delivery_cost;
    }

    public void setDelivery_cost(int delivery_cost) {
        this.delivery_cost = delivery_cost;
        notifyPropertyChanged(BR.delivery_cost);
    }

    @Bindable
    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
        notifyPropertyChanged(BR.total_cost);
    }

    @Bindable
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        notifyPropertyChanged(BR.status);
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
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        notifyPropertyChanged(BR.mobile);
    }

    @Bindable
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        notifyPropertyChanged(BR.location);
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
    public String getType_payment() {
        return type_payment;
    }

    public void setType_payment(String type_payment) {
        this.type_payment = type_payment;
        notifyPropertyChanged(BR.type_payment);
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
    public String getChange_Status() {
        return change_Status;
    }

    public void setChange_Status(String change_Status) {
        this.change_Status = change_Status;
        notifyPropertyChanged(BR.change_Status);
    }

    @Bindable
    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
        notifyPropertyChanged(BR.customer_name);
    }

    @Bindable
    public String getCustomer_mobile() {
        return customer_mobile;
    }

    public void setCustomer_mobile(String customer_mobile) {
        this.customer_mobile = customer_mobile;
        notifyPropertyChanged(BR.customer_mobile);
    }

    @Bindable
        public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
        notifyPropertyChanged(BR.driver);
    }

    @Bindable
    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
        notifyPropertyChanged(BR.store_name);
    }

    @Bindable
    public String getStore_image() {
        return store_image;
    }

    public void setStore_image(String store_image) {
        this.store_image = store_image;
        notifyPropertyChanged(BR.store_image);
    }

    @BindingAdapter(value = {"imageUrl"})
    public static void loadImage(ImageView imageView, String image) {
        Glide.with(imageView)
                .load(image)
                .into(imageView);
    }

    @Bindable
    public String getStore_open() {
        return store_open;
    }

    public void setStore_open(String store_open) {
        this.store_open = store_open;
        notifyPropertyChanged(BR.store_open);
    }

    @Bindable
    public String getStore_close() {
        return store_close;
    }

    public void setStore_close(String store_close) {
        this.store_close = store_close;
        notifyPropertyChanged(BR.store_close);
    }

    @Bindable
    public SubCategory getStore() {
        return store;
    }

    public void setStore(SubCategory store) {
        this.store = store;
        notifyPropertyChanged(BR.store);
    }

    @Bindable
    public List<ProductOrder> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOrder> products) {
        this.products = products;
        notifyPropertyChanged(BR.products);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(reference_id);
        dest.writeInt(user_id);
        dest.writeString(store_id);
        dest.writeString(product_cost);
        dest.writeInt(delivery_cost);
        dest.writeString(total_cost);
        dest.writeInt(status);
        dest.writeString(name);
        dest.writeString(mobile);
        dest.writeString(location);
        dest.writeString(lan);
        dest.writeString(lat);
        dest.writeString(type_payment);
        dest.writeString(created_at);
        dest.writeString(change_Status);
        dest.writeString(customer_name);
        dest.writeString(customer_mobile);
        dest.writeString(store_name);
        dest.writeString(store_image);
        dest.writeString(store_open);
        dest.writeString(store_close);
        dest.writeParcelable(store, flags);
    }
}
