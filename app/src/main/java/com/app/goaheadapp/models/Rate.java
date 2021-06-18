package com.app.goaheadapp.models;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;

import com.app.goaheadapp.BR;

public class Rate implements Observable {

    /**
     * id : 5
     * user_id : 2
     * product_id : null
     * store_id : null
     * driver_id : 1
     * value : 1
     * text : texttexttexttexttexttext
     * created_at : 2020-09-03 12:40:02
     * updated_at : 2020-09-03 12:40:02
     * deleted_at : null
     * user_name : osama
     * driver_name : osamaAtef3
     * user_mobile : 754457795
     * driver_mobile : 4545555545
     */

    private int id;
    private int user_id;
    private int product_id;
    private int store_id;
    private int driver_id;
    private int value;
    private String text;
    private String created_at;
    private String updated_at;
    private String deleted_at;
    private String user_name;
    private String driver_name;
    private String user_mobile;
    private String driver_mobile;
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
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
        notifyChange(BR.product_id);
    }

    @Bindable
    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
        notifyChange(BR.store_id);
    }

    @Bindable
    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
        notifyChange(BR.driver_id);
    }

    @Bindable
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        notifyChange(BR.value);
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyChange(BR.text);
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
    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
        notifyChange(BR.updated_at);
    }

    @Bindable
    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
        notifyChange(BR.deleted_at);
    }

    @Bindable
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
        notifyChange(BR.user_name);
    }

    @Bindable
    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
        notifyChange(BR.driver_name);
    }

    @Bindable
    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
        notifyChange(BR.user_mobile);
    }

    @Bindable
    public String getDriver_mobile() {
        return driver_mobile;
    }

    public void setDriver_mobile(String driver_mobile) {
        this.driver_mobile = driver_mobile;
        notifyChange(BR.driver_mobile);
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
