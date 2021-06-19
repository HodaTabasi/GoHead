package com.app.goaheadapp.models;

import java.util.List;

public class NotificationResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : [{"id":8,"user_id":2,"order_id":1,"type":1,"status":0,"created_at":"2021-06-14 17:07:42","driver":"osamaAtef3","total":"1277","title":"رسالة جديدة","body":"بىي"},{"id":7,"user_id":2,"order_id":21,"type":0,"status":2,"created_at":"2021-06-03 11:13:57","driver":"","total":"1277","title":"الطلب مكتمل","body":"تم اكتمال طلبك"},{"id":6,"user_id":2,"order_id":21,"type":0,"status":2,"created_at":"2021-06-03 11:01:17","driver":"","total":"1277","title":"قيد التوصيل","body":"طلبك قيد التوصيل"},{"id":5,"user_id":2,"order_id":21,"type":0,"status":1,"created_at":"2021-06-03 10:51:42","driver":"","total":"1277","title":"قيد التجهيز","body":"طلبك قيد التجهيز"},{"id":4,"user_id":2,"order_id":22,"type":0,"status":0,"created_at":"2021-06-01 16:02:50","driver":"","total":"1277","title":"قيد التجهيز","body":"طلبك قيد التجهيز"},{"id":3,"user_id":2,"order_id":22,"type":0,"status":0,"created_at":"2021-06-01 16:01:18","driver":"","total":"1277","title":"قيد التجهيز","body":"طلبك قيد التجهيز"},{"id":2,"user_id":2,"order_id":1,"type":1,"status":0,"created_at":"2021-06-01 15:47:28","driver":"osamaAtef3","total":"1277","title":"رسالة جديدة","body":"text message  text message  text message  text message  text message  text message  text message  text message  text message  text message  text message  text message"},{"id":1,"user_id":2,"order_id":22,"type":0,"status":0,"created_at":"2021-06-01 14:46:11","driver":"","total":"1277","title":"طلب جديد","body":"تم انشاء طلبك بنجاح"}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<Notification> items;

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

    public List<Notification> getItems() {
        return items;
    }

    public void setItems(List<Notification> items) {
        this.items = items;
    }

}
