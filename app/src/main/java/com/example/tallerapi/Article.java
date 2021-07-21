package com.example.tallerapi;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable{
    private  String title;
    private  String section;
    private  String subSection;
    private  String date;
    private  String url;

    public Article(String title, String section, String subSection,String date, String url) {
        this.title = title;
        this.section = section;
        this.subSection = subSection;
        this.date = date;
        this.url = url;

    }

    protected Article(Parcel in){
        title = in.readString();
        section = in.readString();
        subSection = in.readString();
        date = in.readString();
        url = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public String getSubSection() {
        return subSection;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>
            () {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };


    public int describeContents() {
        return 0;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(section);
        dest.writeString(subSection);
        dest.writeString(date);
        dest.writeString(url);
    }

}
