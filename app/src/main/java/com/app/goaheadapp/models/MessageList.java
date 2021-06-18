package com.app.goaheadapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.app.goaheadapp.BR;

public class MessageList extends BaseObservable implements Parcelable {


    /**
     * id : 5
     * user_id : 2
     * driver_id : osamaAtef3
     * order_id : 2
     * created_at : 22:15
     * updated_at : 2020-10-30 22:15:24
     * last_messages : Mmm
     * user_name : osama
     * driver_name :
     * user_mobile : 754457795
     * driver_mobile :
     */

    private int id;
    private int user_id;
    private String driver_id;
    private int order_id;
    private String created_at;
    private String updated_at;
    private String last_messages;
    private String user_name;
    private String driver_name;
    private String user_mobile;
    private String driver_mobile;

    protected MessageList(Parcel in) {
        id = in.readInt();
        user_id = in.readInt();
        driver_id = in.readString();
        order_id = in.readInt();
        created_at = in.readString();
        updated_at = in.readString();
        last_messages = in.readString();
        user_name = in.readString();
        driver_name = in.readString();
        user_mobile = in.readString();
        driver_mobile = in.readString();
    }

    public static final Creator<MessageList> CREATOR = new Creator<MessageList>() {
        @Override
        public MessageList createFromParcel(Parcel in) {
            return new MessageList(in);
        }

        @Override
        public MessageList[] newArray(int size) {
            return new MessageList[size];
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
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
        notifyPropertyChanged(BR.user_id);
    }

    @Bindable
    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
        notifyPropertyChanged(BR.driver_id);
    }

    @Bindable
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
        notifyPropertyChanged(BR.order_id);
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
    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
        notifyPropertyChanged(BR.updated_at);
    }

    @Bindable
    public String getLast_messages() {
        return last_messages;
    }

    public void setLast_messages(String last_messages) {
        this.last_messages = last_messages;
        notifyPropertyChanged(BR.last_messages);
    }

    @Bindable
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
        notifyPropertyChanged(BR.user_name);
    }

    @Bindable
    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
        notifyPropertyChanged(BR.driver_name);
    }

    @Bindable
    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
        notifyPropertyChanged(BR.user_mobile);
    }

    @Bindable
    public String getDriver_mobile() {
        return driver_mobile;
    }

    public void setDriver_mobile(String driver_mobile) {
        this.driver_mobile = driver_mobile;
        notifyPropertyChanged(BR.driver_mobile);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(user_id);
        parcel.writeString(driver_id);
        parcel.writeInt(order_id);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
        parcel.writeString(last_messages);
        parcel.writeString(user_name);
        parcel.writeString(driver_name);
        parcel.writeString(user_mobile);
        parcel.writeString(driver_mobile);
    }
}
