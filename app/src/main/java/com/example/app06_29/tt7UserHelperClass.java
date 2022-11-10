package com.example.app06_29;

public class tt7UserHelperClass {

    int id;
    String fname, lname, email, pass, branch, gender;

    public tt7UserHelperClass(String fname, String lname, String email, String pass, String branch, String gender) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pass = pass;
        this.branch = branch;
        this.gender = gender;
    }

    public tt7UserHelperClass(int id, String fname, String lname, String email, String pass, String branch, String gender) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pass = pass;
        this.branch = branch;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}