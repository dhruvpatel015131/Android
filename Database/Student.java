package com.example.database;

public class Student {
    private  int rno, contact;
    private String name, email, course, address;
    Student(){}
    Student(int rno, String name, String email, int contact, String course, String address){
        this.rno = rno;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.course = course;
        this.address = address;
    }

     int getId() {return this.rno;}

     void setId(int id) {this.rno = id;}


     String getName() {return this.name;}

     void setName(String name) {this.name = name;}


     String getEmail() {return this.email;}

     void setEmail(String email) {this.email = email;}


     int getContact() {return this.contact;}

     void setContact(int contact) {this.contact = contact;}


     String getCourse() {return this.course;}

     void setCourse(String course) {this.course = course;}


     String getAddress() {return this.address;}

     void setAddress(String address) {this.address = address;}

}
