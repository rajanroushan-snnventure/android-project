package com.anhttvn.customrecyclerview.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter {

    @SerializedName("title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("rows")
    public List<Data> data = new ArrayList();

    public class Data {


        @SerializedName("title")
        public String title;

        @SerializedName("description")
        public String description;

        @SerializedName("imageHref")
        public String imageHref;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageHref() {
            return imageHref;
        }

        public void setImageHref(String imageHref) {
            this.imageHref = imageHref;
        }
    }
}
