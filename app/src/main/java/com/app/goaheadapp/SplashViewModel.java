package com.app.goaheadapp;

import android.app.Activity;
import android.content.Intent;

import com.app.goaheadapp.activitys.AddAddressActivity;
import com.app.goaheadapp.activitys.login.Login;
import com.app.goaheadapp.activitys.login.LoginDraivir;
import com.app.goaheadapp.activitys.register.Register;
import com.app.goaheadapp.activitys.ResetPassword;
import com.app.goaheadapp.activitys.savedaddresses.SavedAddress;
import com.app.goaheadapp.activitys.Sliders;

public class SplashViewModel {
    Activity context;

    public SplashViewModel(Activity context) {
        this.context = context;
    }

    public void goTOApp(){
        context.startActivity(new Intent(context, Sliders.class));
    }

    public void changeLocation(int i){
        Intent intent = new Intent(context, SavedAddress.class);
        context.startActivityForResult(intent, i);
    }

    public void toGoogleMap(){
        context.startActivity(new Intent(context, AddAddressActivity.class));
    }

    public void toLoginActivity(){
        context.startActivity(new Intent(context, Login.class));
    }
    public void toRegisterActivity(){
        context.startActivity(new Intent(context, Register.class));
    }

    public void toDriverActivity(){
        context.startActivity(new Intent(context, LoginDraivir.class));
    }

    public void toRestActivity(){
        context.startActivity(new Intent(context, ResetPassword.class));
    }


}
