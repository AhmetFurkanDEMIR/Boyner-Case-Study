package com.Boyner.BoynerCase.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_category_attributes")
public class TBL_CategoryAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name="size", columnDefinition="NVARCHAR(10)")
    private String Size;

    @Column(name="color", columnDefinition="NVARCHAR(10)")
    private String Color;

    @Column(name="gender", columnDefinition="NVARCHAR(10)")
    private String Gender;

    @Column(name="brand", columnDefinition="NVARCHAR(20)")
    private String Brand;


    public TBL_CategoryAttributes(){
        super();
    }

    public TBL_CategoryAttributes(long Id, String Size, String Color, String Gender, String Brand) {
        super();
        this.Id = Id;
        this.Size = Size;
        this.Color = Color;
        this.Gender=Gender;
        this.Brand = Brand;
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getSize() {
        return Size;
    }

    public String getColor() {
        return Color;
    }

    public String getGender() {
        return Gender;
    }

    public String getBrand() {
        return Brand;
    }

    public void setSize(String size) {
        Size = size;
    }

    public void setColor(String color) {
        Color = color;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }
}
