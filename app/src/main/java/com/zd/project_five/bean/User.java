package com.zd.project_five.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/*
 *Name：fanyanlong
 *Time：16:13
 *Name:project_five
 */
@Entity
public class User {
     @Id(autoincrement = true)
     long id;
     String name;
     String sex;
     int  age;
     String like;
     @Generated(hash = 1518594503)
    public User(long id, String name, String sex, int age, String like) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.like = like;
    }
    @Generated(hash = 586692638)
     public User() {
     }
     public String getName() {
         return this.name;
     }
     public void setName(String name) {
         this.name = name;
     }
     public String getSex() {
         return this.sex;
     }
     public void setSex(String sex) {
         this.sex = sex;
     }
     public int getAge() {
         return this.age;
     }
     public void setAge(int age) {
         this.age = age;
     }
     public String getLike() {
         return this.like;
     }
     public void setLike(String like) {
         this.like = like;
     }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

}
