package com.mySQL;

import java.util.Date;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.mySQL
 * @Auther:Chen
 * @CreateTime:2022--07--29 15--50
 * @Decription:T000
 */
public class order {
    private String name;
    private String email;
    private java.util.Date birth;

    public order() {
    }

    public order(String name, String email, Date birth) {
        this.name = name;
        this.email = email;
        this.birth = birth;
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

    @Override
    public String toString() {
        return "order{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                '}';
    }
}
