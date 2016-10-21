package biz.bagira.shds.eshop.entity;

import java.util.Arrays;

/**
 * Created by Dmitriy on 13.10.2016.
 */
public class PasswordEntity {
    private Integer userId;
    private String password;
    private byte[] salt;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getSalt() {
        return salt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PasswordEntity{" +
                "password=" + password +
                ", salt=" + Arrays.toString(salt) +
                ", saltSize = " + salt.length+
                '}';
    }
}
