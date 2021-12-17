package com.yiyue.pojo;

public class Good {
    private Integer id;//商品ID
    private String brandName;//品牌名
    private String goodName;//商品名
    private Double price;//价格
    private String specification;//规格
    private String description;//描述
    private String img_src;//图片资源

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", goodName='" + goodName + '\'' +
                ", price=" + price +
                ", specification='" + specification + '\'' +
                ", description='" + description + '\'' +
                ", img_src='" + img_src + '\'' +
                '}';
    }
    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
