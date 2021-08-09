package com.app.goaheadapp.models;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;

import com.app.goaheadapp.BR;

import java.util.List;

public class SliderResponse {

    /**
     * status : true
     * code : 200
     * message :  Done Successfully !
     * items : [{"id":2,"image":"https://wecansa.com/uploads/sliders/images/5f50d0be980a7.jpg","status":"active","title":"Reiciendis molestiae","text":"Voluptates minima si"}]
     */

    private boolean status;
    private int code;
    private String message;
    /**
     * id : 2
     * image : https://wecansa.com/uploads/sliders/images/5f50d0be980a7.jpg
     * status : active
     * title : Reiciendis molestiae
     * text : Voluptates minima si
     */

    private List<ItemsBean> items;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean implements Observable {
        private int id;
        private String image;
        private String status;
        private String title;
        private String text;
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
        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
            notifyChange(BR.image);
        }

        @Bindable
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
            notifyChange(BR.status);
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
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
            notifyChange(BR.text);
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
}
