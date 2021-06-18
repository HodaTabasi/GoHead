package com.app.goaheadapp.models;

public class Address {

    /**
     * id : 1
     * user_id : 2
     * lat : 2.35
     * lan : 63585
     * address : مكة المكرمة.شارع
     */

    private int id;
    private int user_id;
    private String lat;
    private String lan;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return getAddress();
    }
}
