package com.app.goaheadapp.models;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;

import com.app.goaheadapp.BR;

public class Notification implements Observable {

    /**
     * id : 1
     * user_id : 2
     * order_id : 22
     * type : 0
     * status : 0
     * created_at : 2021-06-01 14:46:11
     * driver :
     * total : 1277
     * title : طلب جديد
     * body : تم انشاء طلبك بنجاح
     */

    private int id;
    private int user_id;
    private int order_id;
    private int type;
    private int status;
    private String created_at;
    private String driver;
    private String total;
    private String title;
    private String body;
    private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();


    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyChange(BR.id);
    }

    @Bindable
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
        notifyChange(BR.user_id);
    }

    @Bindable
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
        notifyChange(BR.order_id);
    }

    @Bindable
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        notifyChange(BR.type);
    }

    @Bindable
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        notifyChange(BR.status);
    }

    @Bindable
    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
        notifyChange(BR.created_at);
    }

    @Bindable
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
        notifyChange(BR.driver);
    }

    @Bindable
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
        notifyChange(BR.total);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyChange(BR.title);
    }

    @Bindable
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
        notifyChange(BR.body);
    }

    private synchronized void notifyChange(int propertyId) {
        if (propertyChangeRegistry == null) {
            propertyChangeRegistry = new PropertyChangeRegistry();
        }
        propertyChangeRegistry.notifyChange(this, propertyId);
    }

    @Override
    public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (propertyChangeRegistry == null) {
            propertyChangeRegistry = new PropertyChangeRegistry();
        }
        propertyChangeRegistry.add(callback);

    }

    @Override
    public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (propertyChangeRegistry != null) {
            propertyChangeRegistry.remove(callback);
        }
    }
}
