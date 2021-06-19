package com.app.goaheadapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.activitys.savedaddresses.SavedAddress;
import com.app.goaheadapp.interfaces.GetUserData;
import com.app.goaheadapp.models.DeleteCartResponse;
import com.app.goaheadapp.models.Order;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderViewModel {
    Context context;
    GetUserData getUserData;

    public OrderViewModel(Context context) {
        this.context = context;
    }

    public OrderViewModel(Context context,GetUserData getUserData) {
        this.context = context;
        this.getUserData = getUserData;
    }

    public void showEditUserDialog() {
//        NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment);
//        navController.navigate(R.id.action_shopContentFragment_to_cartFragment);

        final Dialog dialog = new Dialog(context, R.style.mydialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_edit_user_data);

        AppCompatEditText user_name = dialog.findViewById(R.id.user_name);
        AppCompatEditText phone = dialog.findViewById(R.id.phone);

        AppCompatButton close = dialog.findViewById(R.id.cancel);
        AppCompatButton ok = dialog.findViewById(R.id.ok);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public void toSavedAddress() {
        Intent intent = new Intent(context, SavedAddress.class);
        context.startActivity(intent);
    }

    public void toDriverNote() {
        final Dialog dialog = new Dialog(context, R.style.mydialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_send_note);

        AppCompatEditText note = dialog.findViewById(R.id.note);
        AppCompatTextView call = dialog.findViewById(R.id.call);

        AppCompatButton close = dialog.findViewById(R.id.cancel);
        AppCompatButton ok = dialog.findViewById(R.id.send);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public void editUser(String s,String s1) {
        final Dialog dialog = new Dialog(context, R.style.mydialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_edit_user_data);

        final AppCompatEditText userName = dialog.findViewById(R.id.user_name);
        final AppCompatEditText phone = dialog.findViewById(R.id.phone);

        userName.setText(s);
        phone.setText(s1);

        AppCompatButton close = dialog.findViewById(R.id.cancel);
        AppCompatButton ok = dialog.findViewById(R.id.ok);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserData.getData(userName.getText().toString(),phone.getText().toString());
                dialog.dismiss();
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public void Rate(int id, Order order) {
        final Dialog dialog = new Dialog(context, R.style.mydialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_rate);

        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        AppCompatEditText note = dialog.findViewById(R.id.note);
        RatingBar rate = dialog.findViewById(R.id.ratingBarr);

        AppCompatButton close = dialog.findViewById(R.id.cancel);
        AppCompatButton ok = dialog.findViewById(R.id.send);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                MyProgressDialog.showDialog(context);
                if (id == 1){
                    NetworkUtils.getInstance()
                            .rateStore(token,currentLang,String.valueOf(order.getStore().getId()),String.valueOf(rate.getNumStars()),note.getText().toString()).enqueue(new Callback<DeleteCartResponse>() {
                        @Override
                        public void onResponse(Call<DeleteCartResponse> call, Response<DeleteCartResponse> response) {
                            DeleteCartResponse deleteCartResponse = response.body();
                            MyProgressDialog.dismissDialog();
                            Toast.makeText(context, ""+deleteCartResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<DeleteCartResponse> call, Throwable t) {
                            MyProgressDialog.dismissDialog();
                        }
                    });
                }else if (id == 2){
                    NetworkUtils.getInstance()
                            .rateDriver(token,currentLang,String.valueOf(order.getDriver().getId()),String.valueOf(rate.getNumStars()),note.getText().toString()).enqueue(new Callback<DeleteCartResponse>() {
                        @Override
                        public void onResponse(Call<DeleteCartResponse> call, Response<DeleteCartResponse> response) {
                            DeleteCartResponse deleteCartResponse = response.body();
                            MyProgressDialog.dismissDialog();
                            Toast.makeText(context, ""+deleteCartResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<DeleteCartResponse> call, Throwable t) {
                            MyProgressDialog.dismissDialog();
                        }
                    });
                }
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public void doneDialog() {
        final Dialog dialog = new Dialog(context, R.style.mydialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_ok);


        AppCompatTextView message = dialog.findViewById(R.id.message);

        message.setText(context.getResources().getString(R.string.ask_driver_done));

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

}
