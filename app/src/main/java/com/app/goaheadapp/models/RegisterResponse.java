package com.app.goaheadapp.models;

import java.util.List;

public class RegisterResponse {

    /**
     * erorrs : ["Username already taken","Invalid email"]
     * status : false
     */

    private boolean status;
    private List<String> erorrs;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getErorrs() {
        return erorrs;
    }

    public void setErorrs(List<String> erorrs) {
        this.erorrs = erorrs;
    }
}
