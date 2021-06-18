package com.app.goaheadapp.models;

import java.util.List;

public class UpdateImageResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : [{"id":1,"company_id":2,"id_namber":"7527527525","address":"شارع حوار، الريان، الرياض","lat":52.3,"lan":3.256,"phone":"1234567895","name":"osamaAtef3","email":"osama_et@hotmail.com","mobile":"","mobile1":"4545555545","status":"active","type":1,"driver_not":"Ratione et non conse","profile_image":"http://goaheadapp.net/uploads/drivers/profile_images/5fb4dc0b5a4a0.png","image_identity":"http://goaheadapp.net/uploads/drivers/image_identity/5f4f446c34849.jpg","created_at":"2020-09-02 07:06:20","rate":1},{"id":2,"company_id":0,"id_namber":"","address":"","lat":0,"lan":0,"phone":"","name":"osama","email":"osa@hotmail.com","mobile":"754457795","mobile1":"","status":"active","type":0,"driver_not":"","profile_image":"","image_identity":"","created_at":"2020-09-02 07:29:54","rate":0}]
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
         * company_id : 2
         * id_namber : 7527527525
         * address : شارع حوار، الريان، الرياض
         * lat : 52.3
         * lan : 3.256
         * phone : 1234567895
         * name : osamaAtef3
         * email : osama_et@hotmail.com
         * mobile :
         * mobile1 : 4545555545
         * status : active
         * type : 1
         * driver_not : Ratione et non conse
         * profile_image : http://goaheadapp.net/uploads/drivers/profile_images/5fb4dc0b5a4a0.png
         * image_identity : http://goaheadapp.net/uploads/drivers/image_identity/5f4f446c34849.jpg
         * created_at : 2020-09-02 07:06:20
         * rate : 1
         */

        private int id;
        private int company_id;
        private String id_namber;
        private String address;
        private double lat;
        private double lan;
        private String phone;
        private String name;
        private String email;
        private String mobile;
        private String mobile1;
        private String status;
        private int type;
        private String driver_not;
        private String profile_image;
        private String image_identity;
        private String created_at;
        private int rate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public String getId_namber() {
            return id_namber;
        }

        public void setId_namber(String id_namber) {
            this.id_namber = id_namber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLan() {
            return lan;
        }

        public void setLan(double lan) {
            this.lan = lan;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMobile1() {
            return mobile1;
        }

        public void setMobile1(String mobile1) {
            this.mobile1 = mobile1;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDriver_not() {
            return driver_not;
        }

        public void setDriver_not(String driver_not) {
            this.driver_not = driver_not;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        public String getImage_identity() {
            return image_identity;
        }

        public void setImage_identity(String image_identity) {
            this.image_identity = image_identity;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }
    }
}
