package com.app.goaheadapp.models;

import java.util.List;

public class StoreResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : [{"id":1,"store_id":1,"title":"وجبات"},{"id":2,"store_id":1,"title":"ساندويتشات"}]
     */

    private boolean status;
    private int code;
    private String message;
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

    public static class ItemsBean {
        /**
         * id : 1
         * store_id : 1
         * title : وجبات
         */

        private int id;
        private int store_id;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
