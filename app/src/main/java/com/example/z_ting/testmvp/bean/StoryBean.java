package com.example.z_ting.testmvp.bean;

import java.util.List;

/**
 * Created by zhaoting on 16/7/6.
 */
public class StoryBean {

    /**
     * images : ["http://pic3.zhimg.com/d28baa6528029805fdc894827379caa2.jpg"]
     * type : 0
     * id : 8531854
     * ga_prefix : 070616
     * title : 这种「顶级」装订的书，不掉页，还天生防篡改
     */

    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private List<String> images;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
