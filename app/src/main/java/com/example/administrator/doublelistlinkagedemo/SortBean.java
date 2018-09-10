package com.example.administrator.doublelistlinkagedemo;


import java.io.Serializable;

public class SortBean implements Serializable {

    private String country;
    private String name;
    private String tag;
    private boolean isTitle;
    private String drawableId;

    @Override
    public String toString() {
        return "SortBean{" +
                "name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", isTitle=" + isTitle +
                '}' + "\n";
    }

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    public SortBean(String name, String drawableId) {
        this.name = name;
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(String drawableId) {
        this.drawableId = drawableId;
    }
}
