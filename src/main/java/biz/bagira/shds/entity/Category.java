package biz.bagira.shds.entity;

/**
 * Created by Dmitriy on 05.10.2016.
 */
public class Category extends AbstractEntity{

    private String type;

    public Category(String type) {
        this.type = type;
    }

    public Category() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Category{" +
                "type='" + type + '\'' +
                '}';
    }
}
