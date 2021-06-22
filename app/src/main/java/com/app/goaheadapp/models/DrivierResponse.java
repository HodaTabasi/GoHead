package com.app.goaheadapp.models;

import java.util.List;

public class DrivierResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : [{"id":1,"company_id":2,"id_namber":"7527527525","address":"شارع حوار، الريان، الرياض","lat":52.3,"lan":3.256,"phone":"1234567895","name":"osamaAtef3","email":"osama_et@hotmail.com","mobile":"","mobile1":"4545555545","status":"active","type":1,"driver_not":"Ratione et non conse","profile_image":"http://goaheadapp.net/uploads/drivers/profile_images/60c767d322058.jpg","image_identity":"http://goaheadapp.net/uploads/drivers/image_identity/5f4f446c34849.jpg","lang":"ar","created_at":"2020-09-02 10:06:20","rate":1},{"id":7,"company_id":1,"id_namber":"1234567687","address":"Consequat Ex laboru","lat":3.25,"lan":6359,"phone":"95632658659","name":"Lynn Lynch","email":"xane@mailinator.com","mobile":"159632154","mobile1":"78541236548","status":"active","type":1,"driver_not":"gnnchgcncghgh","profile_image":"http://goaheadapp.net/uploads/drivers/profile_images/5f5dd35f88daa.jpg","image_identity":"http://goaheadapp.net/uploads/drivers/image_identity/5f5dd35f8917a.jpg","lang":"ar","created_at":"2020-09-13 14:07:59","rate":0},{"id":11,"company_id":1,"id_namber":"1234567687","address":"Consequat Ex laboru","lat":3.25,"lan":6359,"phone":"95632658659","name":"Lynn Lynch","email":"xane22@mailinator.com","mobile":"159632154","mobile1":"78541236548","status":"active","type":1,"driver_not":"gnnchgcncghgh","profile_image":"http://goaheadapp.net/uploads/drivers/profile_images/5f5dd35f88daa.jpg","image_identity":"http://goaheadapp.net/uploads/drivers/image_identity/5f5dd35f8917a.jpg","lang":"ar","created_at":"2020-09-13 14:07:59","rate":0}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<User> items;

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

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }
}
