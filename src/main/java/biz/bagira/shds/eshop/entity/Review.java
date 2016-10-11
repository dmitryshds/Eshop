package biz.bagira.shds.eshop.entity;

import java.sql.Date;

/**
 * Created by Dmitriy on 05.10.2016.
 */
public class Review extends AbstractEntity{


    private String review;
     private Date dateReview;

    public Review() {

    }


    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getDateReview() {
        return dateReview;
    }

    public void setDateReview(Date dateReview) {
        this.dateReview = dateReview;
    }

    @Override
    public String toString() {
        return "Review{" +
                "review='" + review + '\'' +
                ", dateReview=" + dateReview +
                '}';
    }
}
