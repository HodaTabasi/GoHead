package com.app.goaheadapp.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.app.goaheadapp.BR;

public class Privacy extends BaseObservable {

    /**
     * id : 1
     * status : active
     * text : سياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصيةسياسة الخصوصية
     */

    private int id;
    private String status;
    private String text;

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
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
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }
}
