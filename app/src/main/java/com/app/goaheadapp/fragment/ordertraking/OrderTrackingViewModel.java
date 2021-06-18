package com.app.goaheadapp.fragment.ordertraking;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.OrderResponse;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderTrackingViewModel extends ViewModel {
    MutableLiveData<OrderResponse> postsMutableLiveData = new MutableLiveData<>();


    public void getOrder(String id,final Context homeFragment) {
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " +"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjcyNmZmYTkyOGIwNmVlYThiY2RmNThhNWEzMjJlMGZkNzhmNmM2MWM2ZjBhODMwNzE1ZDQzYjhhNGFkMGNkNDkzMDI0NTllMjRjM2I0ZWRjIn0.eyJhdWQiOiIxIiwianRpIjoiNzI2ZmZhOTI4YjA2ZWVhOGJjZGY1OGE1YTMyMmUwZmQ3OGY2YzYxYzZmMGE4MzA3MTVkNDNiOGE0YWQwY2Q0OTMwMjQ1OWUyNGMzYjRlZGMiLCJpYXQiOjE1OTkwMzE4MTcsIm5iZiI6MTU5OTAzMTgxNywiZXhwIjoxNjMwNTY3ODE3LCJzdWIiOiIyIiwic2NvcGVzIjpbXX0.VEqDA_9fuPJdviDm11huTQV7fXbSaeQmMdaND9kZ4aJUx1JOVp96pOsUSvn7iLObz-InEcdbJD1bjyGLNNI9fkOlNbZFrSZIqV5DgZ0VFsdqGS5EwD1bJ35E7wdrQxSKI8HFJ128wr3vVNEHYDDGEXKrUel_DOdyOdMYVhDWG6Sq3RTQYRni7OMvDxQQ2Syghb059gzTzo7VB09_8dxAuhfRr9Y_XHPZToY3ewxoQpncYe4BAL9GnQM7hRbq-CresbyXrr6GcWKmUzyrUeFRbvyJVFjkSXi4E_kfJWebSmHk2Y1i_ehXC7B_1JuON0tZ5qQ01q02nN5OI3YZOF2vjNSScooQYfwUabdL-Q0UTzSDDpQxeMhpKYabpH-loHZuDJejgbYtZsqWx8RiqdcNrQ4DQ9hEJ8Ntr6JPLcVgX8WErwskw9MkazHN9YetQ6HeLSesk8K7vgdEUxFnORpUaUM5w2Icz85_aFEgHTPYxi1m9F70YQOKApSUUxWL_5rYLKWAVrcLQNJ73Uzpm2c_szzZUnP3OKc1Ywtm3ultUCLcXytq8njRWdblVk7H2Hj9TN8V5KU7F3hKT9_-aLlitRL21wflaPaNDLvx68JHE0w9H-EuLLvBW_ZLqg4nFJVppM5GaUkdXfajFbnuXDhWmZUC_wKoFgwL9-8DHa0vRqk";
//        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getOrder(token, currentLang, id).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                postsMutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                MyProgressDialog.dismissDialog();
                Log.e("ffffffffffffff",t.getMessage());
            }
        });
    }
}
