package com.app.goaheadapp.models;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;

import com.app.goaheadapp.BR;

public class MyPayment implements Observable {

    /**
     * id : 1
     * user_id : 2
     * type : 1
     * holdername : ahmed omsa
     * cardNo : 10352652
     * email : osama@hotmail.com
     * expiredate : 2020-09-19
     * created_at : 2021-08-07 14:05:08
     * updated_at : 2021-08-07 14:05:08
     * deleted_at : 0000-00-00 00:00:00
     */

    private int id;
    private int user_id;
    private int type;
    private String holdername;
    private String cardNo;
    private String email;
    private String expiredate;
    private String created_at;
    private String updated_at;
    private String deleted_at;
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
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        notifyChange(BR.type);
    }

    @Bindable
    public String getHoldername() {
        return holdername;
    }

    public void setHoldername(String holdername) {
        this.holdername = holdername;
        notifyChange(BR.holdername);
    }

    @Bindable
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
        notifyChange(BR.cardNo);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyChange(BR.email);
    }

    @Bindable
    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
        notifyChange(BR.expiredate);
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
