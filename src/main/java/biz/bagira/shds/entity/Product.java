package biz.bagira.shds.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Dmitriy on 05.10.2016.
 */
public class Product extends AbstractEntity{


    private Category category;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer sum;
    private byte[] picture;
    private List<Review> reviewList;


    public Product() {
    }

    public Product(Integer id, Category category, String name, BigDecimal price, Integer sum) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.sum = sum;
    }



    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", sum=" + sum +
                ", pictureSize=" + (picture == null ? 0 : picture.length)+
                '}';
    }
}
