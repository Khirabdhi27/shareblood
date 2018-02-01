package com.example.hp.shareblood;
import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class Users {

    public Users(String s, String age, String blood, String gender, String number, String location, String name, String email) {
    }
    public  String name;
    public  String age;
    public String blood;
    public String gender;
    public String number;
    public String location;
    public String username;
    public String email;
    public String password;
    public String cpassword;
    public String address;

    public Users(String name, String age, String blood, String gender, String number, String location, String username, String email, String password, String cpassword, String address) {
        this.name = name;
        this.age = age;
        this.blood = blood;
        this.gender = gender;
        this.number = number;
        this.location = location;
        this.username = username;
        this.email = email;
        this.password = password;
        this.cpassword = cpassword;
        this.address = address;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address){
        this.address=address;

    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

}