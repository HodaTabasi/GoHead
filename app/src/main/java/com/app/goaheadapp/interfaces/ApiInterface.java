package com.app.goaheadapp.interfaces;

import com.app.goaheadapp.models.AddAddressResponse;
import com.app.goaheadapp.models.AddSuccessfullyResponse;
import com.app.goaheadapp.models.AddressResponse;
import com.app.goaheadapp.models.AddsResponse;
import com.app.goaheadapp.models.CartResponse;
import com.app.goaheadapp.models.CatResponse;
import com.app.goaheadapp.models.ChatDetailsResponse;
import com.app.goaheadapp.models.DeleteCartResponse;
import com.app.goaheadapp.models.DeliveryCostResponse;
import com.app.goaheadapp.models.DrivierResponse;
import com.app.goaheadapp.models.FavoriteResponse;
import com.app.goaheadapp.models.IncteaseCuntResponse;
import com.app.goaheadapp.models.MyPaymentResponse;
import com.app.goaheadapp.models.NoteListResponse;
import com.app.goaheadapp.models.NotificationResponse;
import com.app.goaheadapp.models.OrderResponse;
import com.app.goaheadapp.models.PaymentMethodResponse;
import com.app.goaheadapp.models.PrivacyResponse;
import com.app.goaheadapp.models.ProductResponse;
import com.app.goaheadapp.models.RateResponse;
import com.app.goaheadapp.models.SearchResponse;
import com.app.goaheadapp.models.SetiingResponse;
import com.app.goaheadapp.models.SignUpResponse;
import com.app.goaheadapp.models.SliderResponse;
import com.app.goaheadapp.models.StoreResponse;
import com.app.goaheadapp.models.SubCatResponse;
import com.app.goaheadapp.models.UpdateImageResponse;
import com.app.goaheadapp.models.copunResponse;
import com.app.goaheadapp.models.getPaymentRresponse;

import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("signUp")
    public Call<SignUpResponse> register(@Field("name") String username, @Field("email") String email, @Field("mobile") String mobile, @Field("password") String password, @Field("confirm_password") String confirm_password);

    @FormUrlEncoded
    @POST("login")
    public Call<SignUpResponse> login(@Field("email") String email, @Field("password") String password);

    @Headers({
            "Accept: application/json"
    })
    @GET("allAddressForUser")
    public Call<AddressResponse> getAddress(@Header("Authorization") String Authorization);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("addNewAddress")
    public Call<AddAddressResponse> addAddress(@Header("Authorization") String Authorization, @Field("lat") String lat, @Field("lan") String lan, @Field("address") String address);

    @Headers({
            "Accept: application/json"
    })
    @GET("categories/{id}")
    public Call<CatResponse> getCat(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Path("id") String id);

    @Headers({
            "Accept: application/json"
    })
    @GET("storeByCategory/{id}")
    public Call<SubCatResponse> getSubCat(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Path("id") String id);

    @Headers({
            "Accept: application/json"
    })
    @GET("storeCategory/{id}")
    public Call<StoreResponse> getStoreCat(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Path("id") String id);

    @Headers({
            "Accept: application/json"
    })
    @GET("productsBycategoryForStore/{store_id}/{category_store_id}")
    public Call<ProductResponse> getProducts(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Path("store_id") String store_id, @Path("category_store_id") String category_store_id);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("addProductToCart")
    public Call<AddSuccessfullyResponse> addToCart(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Field("product_id") String product_id, @Field("quantity") String quantity);

    @Headers({
            "Accept: application/json"
    })
    @GET("getMyCart")
    public Call<CartResponse> getCart(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage);

    @Headers({
            "Accept: application/json"
    })
    @GET("deleteProductFromToCart/{id}")
    public Call<DeleteCartResponse> deleteFromCart(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Path("id") String id);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("increaseQuantity")
    public Call<IncteaseCuntResponse> increaseQuantity(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Field("product_id") String id);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("decreaseQuantity")
    public Call<IncteaseCuntResponse> decreaseQuantity(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Field("product_id") String id);


    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("favoriteProduct")
    public Call<AddSuccessfullyResponse> addToFav(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Field("product_id") String id);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("unFavoriteProduct")
    public Call<AddSuccessfullyResponse> deleteFromFav(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Field("product_id") String id);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("favoriteStore")
    public Call<AddSuccessfullyResponse> addToFavStore(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Field("store_id") String id);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("unFavoriteStore")
    public Call<AddSuccessfullyResponse> deleteFromFavStore(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Field("store_id") String id);


    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("rateProduct")
    public Call<DeleteCartResponse> rateProduct(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Field("product_id") String id, @Field("value") String value, @Field("text") String text);


    @Headers({
            "Accept: application/json"
    })
    @GET("myFavorites")
    public Call<FavoriteResponse> getFav(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage);

    @Headers({
            "Accept: application/json"
    })
    @GET("getMyOrder/{id}")
    public Call<OrderResponse> getOrder(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Path("id") String id);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("rateDriver")
    public Call<DeleteCartResponse> rateDriver(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Field("driver_id") String id, @Field("value") String value, @Field("text") String text);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("rateStore")
    public Call<DeleteCartResponse> rateStore(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Field("store_id") String id, @Field("value") String value, @Field("text") String text);

    @Headers({
            "Accept: application/json"
    })
    @GET("myProfile")
    public Call<SignUpResponse> getProfile(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("editDriverProfile")
    public Call<SignUpResponse> editProfile(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Field("name") String username, @Field("email") String email, @Field("phone") String mobile, @Field("password") String password);

    @Headers({
            "Accept: application/json"
    })
    @GET("getDeliveryCost")
    public Call<DeliveryCostResponse> getDeliveryCost(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage);


    @Headers({
            "Accept: application/json"
    })
    @GET("getPayMethod")
    public Call<getPaymentRresponse> getPaymentMethod(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage);

    @Headers({
            "Accept: application/json"
    })
    @GET("myPayments")
    public Call<MyPaymentResponse> getMyPayments(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage);

    @Headers({
            "Accept: application/json"
    })
    @GET("deletePayment/{id}")
    public Call<AddSuccessfullyResponse> deletePayment(@Path("id") String id,@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage);


    @Headers({
            "Accept: application/json"
    })
    @GET("settings")
    public Call<SetiingResponse> getSetting(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("contact_us")
    public Call<DeleteCartResponse> sendContact(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Field("text") String text, @Field("email") String email);

    @Headers({
            "Accept: application/json"
    })
    @GET("privacy")
    public Call<PrivacyResponse> getPrivacy(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage);


    @GET("ads/{id}")
    public Call<AddsResponse> getAdds(@Path("id") String id,@Header("Accept-Language") String AcceptLanguage);

    @GET("slider")
    public Call<SliderResponse> getSliders(@Header("Accept-Language") String AcceptLanguage);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("filter")
    Call<SearchResponse> search(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @FieldMap HashMap<String, String> fields);

    @Headers({
            "Accept: application/json"
    })
    @GET("deleteAllCart")
    Call<AddSuccessfullyResponse> clearAll(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("addPaymentToUser")
    Call<PaymentMethodResponse> addPaymentMethod(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @FieldMap HashMap<String, String> fields);

    @Headers({
            "Accept: application/json"
    })
    @GET("getDriverOrder/{id}")
    Call<OrderResponse> getDriverOrder(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage,@Path("id") String id);

    @Headers({
            "Accept: application/json"
    })
    @GET("notes")
    Call<NoteListResponse> getNotesList(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage);

    @Headers({
            "Accept: application/json"
    })
    @GET("notes_details/{id}")
    Call<ChatDetailsResponse> getChatDetail(@Path("id") String id, @Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("sendMassegToUser")
    Call<AddSuccessfullyResponse> sendMessage(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage ,@Field("order_id") String order_id,@Field("user_id") String user_id,@Field("message") String message);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("sendMassegeForeDriver")
    Call<AddSuccessfullyResponse> sendMessageToDriver(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage ,@Field("order_id") String order_id,@Field("driver_id") String driver_id,@Field("message") String message);

 @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("sendOrderForeDriver")
    Call<AddSuccessfullyResponse> sendOrderForDriver(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage ,@Field("order_id") String order_id,@Field("driver_id") String driver_id);


    @Headers({
            "Accept: application/json"
    })
    @Multipart
    @POST("updateImageDeliver")
    Call<UpdateImageResponse> uploadImage(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage , @Part MultipartBody.Part body);

    @Headers({
            "Accept: application/json"
    })
    @GET("ratesDriver")
    public Call<RateResponse> getDriverRate(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage);

    @Headers({
            "Accept: application/json"
    })
    @GET("myNotifications")
    public Call<NotificationResponse> getNotification(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage);


    @Headers({
            "Accept: application/json"
    })
    @GET("getDrivers")
    public Call<DrivierResponse> getDrivers(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage);


    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("approvOrReject")
    public Call<AddSuccessfullyResponse> approvOrReject(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage,@Field("order_id") String order_id,@Field("type") String type);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("checkOutProduct")
    public Call<AddSuccessfullyResponse> checkOutProduct(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage,@Field("order_id") String order_id,@Field("type") String type);

    @Headers({
            "Accept: application/json"
    })
    @FormUrlEncoded
    @POST("getCapon")
    public Call<copunResponse> getCapon(@Header("Authorization") String Authorization, @Header("Accept-Language") String AcceptLanguage, @Field("code") String code, @Field("total_price") String total_price);


//    @FormUrlEncoded
//    @POST("api?action=update&model=workers")
//    public Call<UpdateResponse> update(@Query("api_token") String mytoken,@Field("id") String id,@Field("bio") String bio,@Field("title") String title,@Field("job") String job);
//
//    @GET("api?action=index&model=workers")
//    public Call<GetResponse> getProduct(@Query("api_token") String mytoken);
//
//    @GET("api?action=show&model=workers")
//    public Call<UpdateResponse> getSingleProduct(@Query("id") String myid,@Query("api_token") String mytoken);
}
