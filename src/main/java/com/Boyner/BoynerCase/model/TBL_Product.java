package com.Boyner.BoynerCase.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_product")
public class TBL_Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_catalog_id", referencedColumnName = "id")
    @NotBlank
    private TBL_ProductCategory product_catalog_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_attributes", referencedColumnName = "id")
    @NotBlank
    private TBL_ProductAttributes product_attributes;

    @Column(name="name", columnDefinition="NVARCHAR(30)")
    private String Name;

    @NotBlank
    private float Price;

    @NotBlank
    private int Deleted;

    public TBL_Product(){
        super();
    }

    public TBL_Product(long Id, TBL_ProductCategory product_catalog_id, TBL_ProductAttributes product_attributes, String Name, float Price, int Deleted) {
        super();
        this.Id = Id;
        this.product_catalog_id = product_catalog_id;
        this.product_attributes = product_attributes;
        this.Name = Name;
        this.Price=Price;
        this.Deleted=Deleted;
    }

    public TBL_ProductAttributes getProductAttributes() {
        return product_attributes;
    }

    public TBL_ProductCategory getProductCatalogId() {
        return product_catalog_id;
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public float getPrice() {
        return Price;
    }

    public int getDeleted() {
        return Deleted;
    }

    public void setProductCatalogId(TBL_ProductCategory productCatalogId) {

        this.product_catalog_id = productCatalogId;
    }

    public void setProductAttributes(TBL_ProductAttributes productAttributes) {
        this.product_attributes = productAttributes;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public void setDeleted(int deleted) {
        Deleted = deleted;
    }
}
