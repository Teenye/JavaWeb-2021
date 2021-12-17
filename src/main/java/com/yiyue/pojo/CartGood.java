package com.yiyue.pojo;

public class CartGood {
    private Integer id;
    private String brandName;
    private String goodName;
    private Double price;
    private String specification;
    private Integer num;
    private String date;

    @Override
    public String toString() {
        //23
        return "CartGood{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", goodName='" + goodName + '\'' +
                ", price=" + price +
                ", specification='" + specification + '\'' +
                ", num=" + num +
                ", date='" + date + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
