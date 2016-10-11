package biz.bagira.shds.eshop.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy on 09.10.2016.
 */
public class User extends AbstractEntity{

    private String name;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    private List<Integer> productIdList;


    public User() {
        productIdList = new ArrayList<Integer>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
