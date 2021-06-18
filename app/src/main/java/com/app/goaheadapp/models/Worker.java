package com.app.goaheadapp.models;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.app.goaheadapp.BR;

public class Worker extends BaseObservable {

    /**
     * id : 7
     * title : one
     * desc :
     * job : myjob
     * bio : one one one one
     * facebook :
     * twitter :
     * instagram :
     * linkedin :
     * avatar : false
     */

    private String id;
    private String title;
    private String desc;
    private String job;
    private String bio;
    private String facebook;
    private String twitter;
    private String instagram;
    private String linkedin;
    private String image;

    public Worker() {
        this.title = "";
        this.desc = "";
        this.job = "";
        this.bio = "";
        this.facebook = "";
        this.twitter = "";
        this.instagram = "";
        this.linkedin = "";
        this.image = "";
    }

    public Worker(String title, String desc, String job, String bio, String facebook, String twitter, String instagram, String linkedin, String image) {
        this.title = title;
        this.desc = desc;
        this.job = job;
        this.bio = bio;
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.linkedin = linkedin;
        this.image = image;
    }

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
        notifyPropertyChanged(BR.desc);
    }

    @Bindable
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
        notifyPropertyChanged(BR.job);
    }

    @Bindable
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
        notifyPropertyChanged(BR.bio);
    }

    @Bindable
    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
        notifyPropertyChanged(BR.facebook);
    }

    @Bindable
    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
        notifyPropertyChanged(BR.twitter);
    }

    @Bindable
    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
        notifyPropertyChanged(BR.instagram);
    }

    @Bindable
    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
        notifyPropertyChanged(BR.linkedin);
    }

    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }


    @BindingAdapter(value = {"imageUrl"})
    public static void loadImage(ImageView imageView, String image) {
        Glide.with(imageView)
                .load(image)
                .into(imageView);
    }
}
