package com.app.goaheadapp;

import com.app.goaheadapp.interfaces.ApiInterface;
import com.app.goaheadapp.models.AddAddressResponse;
import com.app.goaheadapp.models.AddSuccessfullyResponse;
import com.app.goaheadapp.models.Address;
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
import com.app.goaheadapp.models.StoreResponse;
import com.app.goaheadapp.models.SubCatResponse;
import com.app.goaheadapp.models.UpdateImageResponse;
import com.app.goaheadapp.models.User;
import com.app.goaheadapp.models.getPaymentRresponse;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    private final String BASE_URL = "http://goaheadapp.net/api/";


    private static NetworkUtils instance;
    private ApiInterface apiInterface;

    public static NetworkUtils getInstance() {
        if (instance == null) {
            instance = new NetworkUtils();
        }
        return instance;
    }

    private NetworkUtils() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(360, TimeUnit.SECONDS)
                .connectTimeout(360, TimeUnit.SECONDS)
                .writeTimeout(360, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public ApiInterface getPhotosApiInterface() {
        return apiInterface;
    }

    public Call<SignUpResponse> Register(User user, String password) {
        return apiInterface.register(user.getName(), user.getEmail(), user.getMobile(), password, password);
    }

    public Call<SignUpResponse> Login(String userName, String password) {
        return apiInterface.login(userName, password);
    }

    public Call<AddressResponse> getAddress(String token) {
        return apiInterface.getAddress(token);
    }

    public Call<AddAddressResponse> addAddress(String token, Address address) {
        return apiInterface.addAddress(token, address.getLat(), address.getLan(), address.getAddress());
    }

    public Call<CatResponse> getCat(String token, String lang, String id) {
        return apiInterface.getCat(token, lang, id);
    }

    public Call<SubCatResponse> getSubCat(String token, String lang, String id) {
        return apiInterface.getSubCat(token, lang, id);
    }

    public Call<StoreResponse> getStoreCat(String token, String lang, String id) {
        return apiInterface.getStoreCat(token, lang, id);
    }

    public Call<ProductResponse> getProducts(String token, String lang, String store_id, String category_store_id) {
        return apiInterface.getProducts(token, lang, store_id, category_store_id);
    }

    public Call<AddSuccessfullyResponse> addToCart(String token, String lang, String product_id, String quantity) {
        return apiInterface.addToCart(token, lang, product_id, quantity);
    }

    public Call<CartResponse> getCart(String token, String lang) {
        return apiInterface.getCart(token, lang);
    }

    public Call<DeleteCartResponse> deleteFromCart(String token, String lang, String id) {
        return apiInterface.deleteFromCart(token, lang, id);
    }

    public Call<IncteaseCuntResponse> increaseQuantity(String token, String lang, String id) {
        return apiInterface.increaseQuantity(token, lang, id);
    }

    public Call<IncteaseCuntResponse> decreaseQuantity(String token, String lang, String id) {
        return apiInterface.decreaseQuantity(token, lang, id);
    }

    public Call<AddSuccessfullyResponse> addToFav(String token, String lang, String id) {
        return apiInterface.addToFav(token, lang, id);
    }

    public Call<AddSuccessfullyResponse> removeFromFav(String token, String lang, String id) {
        return apiInterface.deleteFromFav(token, lang, id);
    }

    public Call<AddSuccessfullyResponse> addToFavStore(String token, String lang, String id) {
        return apiInterface.addToFavStore(token, lang, id);
    }

    public Call<AddSuccessfullyResponse> removeFromFavStore(String token, String lang, String id) {
        return apiInterface.deleteFromFavStore(token, lang, id);
    }

    public Call<DeleteCartResponse> rateProduct(String token, String lang, String id, String value, String text) {
        return apiInterface.rateProduct(token, lang, id, value, text);
    }

    public Call<FavoriteResponse> getFav(String token, String lang) {
        return apiInterface.getFav(token, lang);
    }

    public Call<OrderResponse> getOrder(String token, String lang, String id) {
        return apiInterface.getOrder(token, lang, id);
    }

    public Call<OrderResponse> getDriverOrder(String token, String lang, String id) {
        return apiInterface.getDriverOrder(token, lang, id);
    }

    public Call<DeleteCartResponse> rateDriver(String token, String lang, String id, String value, String text) {
        return apiInterface.rateDriver(token, lang, id, value, text);
    }

    public Call<DeleteCartResponse> rateStore(String token, String lang, String id, String value, String text) {
        return apiInterface.rateStore(token, lang, id, value, text);
    }

    public Call<SignUpResponse> getProfile(String token, String lang) {
        return apiInterface.getProfile(token, lang);
    }

    public Call<SignUpResponse> editProfile(User user, String token, String lang, String password) {
        return apiInterface.editProfile(token, lang, user.getName(), user.getEmail(), user.getMobile(), password);
    }

    public Call<DeliveryCostResponse> getDeliveryCost(String token, String lang) {
        return apiInterface.getDeliveryCost(token, lang);
    }

    public Call<getPaymentRresponse> getPaymentMethod(String token, String lang) {
        return apiInterface.getPaymentMethod(token, lang);
    }

    public Call<SetiingResponse> getSetting(String token, String lang) {
        return apiInterface.getSetting(token, lang);
    }

    public Call<DeleteCartResponse> sendContact(String token, String lang, String text, String email) {
        return apiInterface.sendContact(token, lang, text, email);
    }

    public Call<PrivacyResponse> privacy(String token, String lang) {
        return apiInterface.getPrivacy(token, lang);
    }

    public Call<SearchResponse> getSearch(String token, String lang, HashMap<String, String> fields) {
        return apiInterface.search(token, lang, fields);
    }

    public Call<AddsResponse> getAdds(String id, String lang) {
        return apiInterface.getAdds(id, lang);
    }

    public Call<AddSuccessfullyResponse> clearAll(String token, String lang) {
        return apiInterface.clearAll(token, lang);
    }

    public Call<PaymentMethodResponse> addPaymentMethod(String token, String lang, HashMap<String, String> fields) {
        return apiInterface.addPaymentMethod(token, lang, fields);
    }

    public Call<NoteListResponse> getNoteList(String token, String lang) {
        return apiInterface.getNotesList(token, lang);
    }

    public Call<ChatDetailsResponse> getNoteDetails(String token, String lang, String id) {
        return apiInterface.getChatDetail(id, token, lang);
    }

    public Call<AddSuccessfullyResponse> sendMessage(String token, String lang, String orderId, String userId, String message) {
        return apiInterface.sendMessage(token, lang, orderId, userId, message);
    }

    public Call<AddSuccessfullyResponse> sendMessageToDriver(String token, String lang, String orderId, String driver_id, String message) {
        return apiInterface.sendMessageToDriver(token, lang, orderId, driver_id, message);
    }

    public Call<AddSuccessfullyResponse> sendOrderForDriver(String token, String lang, String orderId, String driver_id) {
        return apiInterface.sendOrderForDriver(token, lang, orderId, driver_id);
    }

    public Call<UpdateImageResponse> uploadImage(String token, String lang, MultipartBody.Part body) {
        return apiInterface.uploadImage(token, lang, body);
    }

    public Call<RateResponse> getRateDriver(String token, String lang) {
        return apiInterface.getDriverRate(token, lang);
    }

    public Call<NotificationResponse> getNotification(String token, String lang) {
        return apiInterface.getNotification(token, lang);
    }

    public Call<DrivierResponse> getDrivers(String token, String lang) {
        return apiInterface.getDrivers(token, lang);
    }

    public Call<AddSuccessfullyResponse> approvOrReject(String token, String lang, String orderId, String type) {
        return apiInterface.approvOrReject(token, lang, orderId, type);
    }

//    public Call<GetResponse> getProduct(String token){
//        return apiInterface.getProduct(token);
//    }
//
//    public Call<UpdateResponse> updateProduct(String token,Worker worker){
//
//        return apiInterface.update(token,worker.getId(),worker.getBio(),worker.getTitle(),worker.getJob());
//    }
//
//    public Call<UpdateResponse> getSingleProduct(String token,String id){
//        return apiInterface.getSingleProduct(id,token);
//    }
}
