package com.app.goaheadapp.models;

public class Add {

    /**
     * id : 1
     * image : http://goaheadapp.net/uploads/ads/5f58bfcdc195c.jpeg
     * category : الكترونيات
     * link : https://www.google.com/
     */

    private int id;
    private String image;
    private String category;
    private String link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
