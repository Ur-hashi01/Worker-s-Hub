package com.example.workerhub;

public class rcview_model {
    String email,fullname,services,pincode,phono;

    rcview_model()
    {

    }
    public String getPhono() {
        return phono;
    }

    public void setPhoneno(String phoneno) {
        this.phono = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}

