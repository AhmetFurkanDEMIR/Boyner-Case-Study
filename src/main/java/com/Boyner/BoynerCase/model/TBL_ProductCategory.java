package com.Boyner.BoynerCase.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_product_category")
public class TBL_ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name="name", columnDefinition="NVARCHAR(30)")
    private String Name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_attributes", referencedColumnName = "id")
    @NotBlank
    private TBL_CategoryAttributes category_attributes;

    @NotBlank
    private int Deleted;

    public TBL_ProductCategory(){
        super();
    }

    public TBL_ProductCategory(long Id, String Name, TBL_CategoryAttributes category_attributes, int Deleted) {
        super();
        this.Id = Id;
        this.Name=Name;
        this.category_attributes = category_attributes;
        this.Deleted = Deleted;
    }

    public TBL_CategoryAttributes getCategoryAttributes() {
        return category_attributes;
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

    public int getDeleted() {
        return Deleted;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCategoryAttributes(TBL_CategoryAttributes category_attributes) {
        this.category_attributes = category_attributes;
    }

    public void setDeleted(int deleted) {
        Deleted = deleted;
    }
}
