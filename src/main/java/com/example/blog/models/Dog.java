package com.example.blog.models;

import javax.persistence.*;

@Entity
@Table(name="dogs")
public class Dog {
    @Id
    @Column(columnDefinition = "int(11) unsigned")
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String name;

    @Column(name="reside_state", nullable = false, columnDefinition = "char(2)")
    private String state_residing;

    public Dog () {}
    public Dog(int age, String name, String state_residing) {
        this.age = age;
        this.name = name;
        this.state_residing = state_residing;
    }
    public int getId() {
        return id;
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public String getState_residing() {
        return state_residing;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setState_residing(String state_residing) {
        this.state_residing = state_residing;
    }

}
