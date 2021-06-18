package com.app.goaheadapp.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.app.goaheadapp.BR;

public class ChatDitails extends BaseObservable {

    /**
     * id : 8
     * messag_id : 4
     * messag : sdbhdbdlkcj hjlwbsdnm
     * type : 1
     * updated_at : 2020-10-17 16:36:08
     * created_at : 2020-10-17 16:36:08
     */

    private int id;
    private int messag_id;
    private String messag;
    private int type;
    private String updated_at;
    private String created_at;

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public int getMessag_id() {
        return messag_id;
    }

    public void setMessag_id(int messag_id) {
        this.messag_id = messag_id;
        notifyPropertyChanged(BR.messag_id);
    }

    @Bindable
    public String getMessag() {
        return messag;
    }

    public void setMessag(String messag) {
        this.messag = messag;
        notifyPropertyChanged(BR.messag);
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
    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
        notifyPropertyChanged(BR.updated_at);
    }

    @Bindable
    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
        notifyPropertyChanged(BR.created_at);
    }
}
