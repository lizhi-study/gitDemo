package com.powernode.controller;

import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

public class User {
    private int id;
    private String name;
    private double result;


    private Date date;


    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", result=" + result +
                ", date=" + date +
                '}';
    }
}
