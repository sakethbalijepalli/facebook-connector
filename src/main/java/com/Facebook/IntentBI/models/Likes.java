package com.Facebook.IntentBI.models;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Likes {
    @Id
    String name;
    String id;

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
