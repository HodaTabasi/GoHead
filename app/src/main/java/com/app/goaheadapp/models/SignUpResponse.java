package com.app.goaheadapp.models;

public class SignUpResponse {

    /**
     * status : true
     * code : 200
     * message :  Done Successfully !
     * data : {"id":3,"company_id":0,"id_namber":"","address":"","lat":0,"lan":0,"phone":"","name":"osama","email":"osa1@hotmail.com","mobile":"754457796","mobile1":"","status":"active","type":0,"driver_not":"","profile_image":"","image_identity":"","created_at":"2020-09-03 19:38:59","access_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImQ0NTE3MGY2NDk2OTA0ZDk2OWZjYTMyNDk5ODNmNzNkZjFkOTJjNWNiODI3YWY4ZGYxNDQ2ODlhZGYyZGZiMjA2OTIyNDIxNWQwZmQ3ZjM3In0.eyJhdWQiOiIxIiwianRpIjoiZDQ1MTcwZjY0OTY5MDRkOTY5ZmNhMzI0OTk4M2Y3M2RmMWQ5MmM1Y2I4MjdhZjhkZjE0NDY4OWFkZjJkZmIyMDY5MjI0MjE1ZDBmZDdmMzciLCJpYXQiOjE1OTkxNTExMzksIm5iZiI6MTU5OTE1MTEzOSwiZXhwIjoxNjMwNjg3MTM5LCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.hymqU4SUo3shKE-SAnAKIeCG_4DLXePEXK1VDqoQ665T3sXYOODPgiNWipxd0oOtZHsXDvtGN8GgWmI8WMuWFGJ1sGGmgIa7qf9u05AwlvNOi6TnE8w1k_WDLjoYZ25YiACPZhNIOsd9JgKfTmQu5eRvKLX9rdQskhR9XuCIV5Bw2b_RkjJo5C-FKM17RahYZ-evpv31FmbNcqwuytf9ta4hi7FPNBTaKMaWg7e1YPcBoyPhlnQoUK7eT5YfUyZA4ySA5SsKWkgowiomJsqSOaTbZAr-gpLB86pA50C8NmVyEhX74f0_LrTqKt2kfIFd8ayVMZBrOWnQoG2c6t6hwh1t1W72oDv8CgbtRLR7EKeEsKcmBLRpokuI2XSmk756InUbdSNvYz0AaxIef5xqkbP2Rdk7mpJvrf3O0WQhhMuhy-GgcHhSxulNr9qs7cNewDj6RF-SyOi1L_csok-RyBfEjf35IUyoAMaaQ0zfzKccGAj8crKi7p8eWRWQG3jeO-NKBMMDWiKeAkwTb52fki1N3WG0WVZvrBZ6-eDwGyCSYVdkQXDFOHlfGKLpW7ZmhCbpYFtSFo7JTAVYikwxTd-smU2FGBHfckMoxrmoI4p38MsAmUXEW7Zip5qgVlr2kNOgvsMhKhqmxeUbUPEwWr5c9aKHL-mgc4eDEsTGQ3c"}
     * items : {"id":3,"company_id":0,"id_namber":"","address":"","lat":0,"lan":0,"phone":"","name":"osama","email":"osa1@hotmail.com","mobile":"754457796","mobile1":"","status":"active","type":0,"driver_not":"","profile_image":"","image_identity":"","created_at":"2020-09-03 19:38:59","access_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImQ0NTE3MGY2NDk2OTA0ZDk2OWZjYTMyNDk5ODNmNzNkZjFkOTJjNWNiODI3YWY4ZGYxNDQ2ODlhZGYyZGZiMjA2OTIyNDIxNWQwZmQ3ZjM3In0.eyJhdWQiOiIxIiwianRpIjoiZDQ1MTcwZjY0OTY5MDRkOTY5ZmNhMzI0OTk4M2Y3M2RmMWQ5MmM1Y2I4MjdhZjhkZjE0NDY4OWFkZjJkZmIyMDY5MjI0MjE1ZDBmZDdmMzciLCJpYXQiOjE1OTkxNTExMzksIm5iZiI6MTU5OTE1MTEzOSwiZXhwIjoxNjMwNjg3MTM5LCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.hymqU4SUo3shKE-SAnAKIeCG_4DLXePEXK1VDqoQ665T3sXYOODPgiNWipxd0oOtZHsXDvtGN8GgWmI8WMuWFGJ1sGGmgIa7qf9u05AwlvNOi6TnE8w1k_WDLjoYZ25YiACPZhNIOsd9JgKfTmQu5eRvKLX9rdQskhR9XuCIV5Bw2b_RkjJo5C-FKM17RahYZ-evpv31FmbNcqwuytf9ta4hi7FPNBTaKMaWg7e1YPcBoyPhlnQoUK7eT5YfUyZA4ySA5SsKWkgowiomJsqSOaTbZAr-gpLB86pA50C8NmVyEhX74f0_LrTqKt2kfIFd8ayVMZBrOWnQoG2c6t6hwh1t1W72oDv8CgbtRLR7EKeEsKcmBLRpokuI2XSmk756InUbdSNvYz0AaxIef5xqkbP2Rdk7mpJvrf3O0WQhhMuhy-GgcHhSxulNr9qs7cNewDj6RF-SyOi1L_csok-RyBfEjf35IUyoAMaaQ0zfzKccGAj8crKi7p8eWRWQG3jeO-NKBMMDWiKeAkwTb52fki1N3WG0WVZvrBZ6-eDwGyCSYVdkQXDFOHlfGKLpW7ZmhCbpYFtSFo7JTAVYikwxTd-smU2FGBHfckMoxrmoI4p38MsAmUXEW7Zip5qgVlr2kNOgvsMhKhqmxeUbUPEwWr5c9aKHL-mgc4eDEsTGQ3c"}
     */

    private boolean status;
    private int code;
    private String message;
    private User data;
    private User items;

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

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public User getItems() {
        return items;
    }

    public void setItems(User items) {
        this.items = items;
    }
}
