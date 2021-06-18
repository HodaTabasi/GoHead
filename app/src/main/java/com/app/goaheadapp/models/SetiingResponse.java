package com.app.goaheadapp.models;

public class SetiingResponse {

    /**
     * status : true
     * code : 200
     * message :  Done Successfully !
     * items : {"id":1,"logo":"http://goaheadapp.net/uploads/settings/5f4cdbc807522.png","app_store_url":"https://www.apple.com/ios/app-store/","play_store_url":"https://play.google.com/store?hl=en","info_email":"mahmoud@gohead.com","mobile":"412563879","facebook":"https://www.facebook.com/","twitter":"https://www.Twitter.com/","instagram":"https://www.instegram.com/","longitude":"47.984147966796854","created_at":null,"updated_at":"2020-08-31 11:15:20","title":"GoHead","description":"Lorem Ipsum Dolor Sit Amet, Consectetur Adipisicing Elit, Sed Do Eiusmod Tempor Incididunt Ut Labore Et Dolore Magna Aliqua","address":"السعودية _جدة"}
     */

    private boolean status;
    private int code;
    private String message;
    private AboutUs items;

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

    public AboutUs getItems() {
        return items;
    }

    public void setItems(AboutUs items) {
        this.items = items;
    }
}
