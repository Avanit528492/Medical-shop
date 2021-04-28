package com.example.medicineshop;

public class Rating_DATA {
    String Id;
    String Name;
    String Rating;
    String Comment;

    public Rating_DATA(String id, String name, String rating, String comment) {
        Id = id;
        Name = name;
        Rating = rating;
        Comment = comment;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
