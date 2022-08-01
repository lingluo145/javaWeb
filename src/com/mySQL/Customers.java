package com.mySQL;


import java.sql.Blob;
import java.sql.Date;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.mySQL
 * @Auther:Chen
 * @CreateTime:2022--07--31 16--41
 * @Decription:T000
 */
public class Customers {
    private String name;
    private String email;
    private Date birth;
    private Blob photo;

    public Customers() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public Customers(String name, String email, Date birth) {
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    public Customers(String name, String email, Date birth, Blob photo) {
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", photo=" + photo +
                '}';
    }
}
