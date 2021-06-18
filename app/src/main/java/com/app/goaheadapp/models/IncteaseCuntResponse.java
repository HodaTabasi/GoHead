package com.app.goaheadapp.models;

public class IncteaseCuntResponse {

    /**
     * status : true
     * code : 200
     * message :  تمت العملية بنجاح!
     * items : {"id":9,"user_id":4,"product_id":1,"store_id":1,"quantity":1,"price":15,"created_at":"2020-09-24 16:23:03","product":{"id":1,"store_id":1,"name":"دجاج","description":"دجاج مشوي بالفحم","image":"http://goaheadapp.net/uploads/products/images/5f4f41b7b9810.jpg","price":"15","offer_price":"6","category_id":1,"status":"active","delete":0,"is_favourite":0,"rate":4,"favourite_count":1}}
     */

    private boolean status;
    private int code;
    private String message;
    private ItemsBean items;

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

    public ItemsBean getItems() {
        return items;
    }

    public void setItems(ItemsBean items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * id : 9
         * user_id : 4
         * product_id : 1
         * store_id : 1
         * quantity : 1
         * price : 15
         * created_at : 2020-09-24 16:23:03
         * product : {"id":1,"store_id":1,"name":"دجاج","description":"دجاج مشوي بالفحم","image":"http://goaheadapp.net/uploads/products/images/5f4f41b7b9810.jpg","price":"15","offer_price":"6","category_id":1,"status":"active","delete":0,"is_favourite":0,"rate":4,"favourite_count":1}
         */

        private int id;
        private int user_id;
        private int product_id;
        private int store_id;
        private int quantity;
        private int price;
        private String created_at;
        private ProductBean product;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public static class ProductBean {
            /**
             * id : 1
             * store_id : 1
             * name : دجاج
             * description : دجاج مشوي بالفحم
             * image : http://goaheadapp.net/uploads/products/images/5f4f41b7b9810.jpg
             * price : 15
             * offer_price : 6
             * category_id : 1
             * status : active
             * delete : 0
             * is_favourite : 0
             * rate : 4
             * favourite_count : 1
             */

            private int id;
            private int store_id;
            private String name;
            private String description;
            private String image;
            private String price;
            private String offer_price;
            private int category_id;
            private String status;
            private int delete;
            private int is_favourite;
            private int rate;
            private int favourite_count;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getOffer_price() {
                return offer_price;
            }

            public void setOffer_price(String offer_price) {
                this.offer_price = offer_price;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getDelete() {
                return delete;
            }

            public void setDelete(int delete) {
                this.delete = delete;
            }

            public int getIs_favourite() {
                return is_favourite;
            }

            public void setIs_favourite(int is_favourite) {
                this.is_favourite = is_favourite;
            }

            public int getRate() {
                return rate;
            }

            public void setRate(int rate) {
                this.rate = rate;
            }

            public int getFavourite_count() {
                return favourite_count;
            }

            public void setFavourite_count(int favourite_count) {
                this.favourite_count = favourite_count;
            }
        }
    }
}
