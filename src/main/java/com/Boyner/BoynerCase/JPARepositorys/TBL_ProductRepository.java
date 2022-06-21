package com.Boyner.BoynerCase.JPARepositorys;

import com.Boyner.BoynerCase.model.TBL_ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Boyner.BoynerCase.model.TBL_Product;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface TBL_ProductRepository extends JpaRepository<TBL_Product, Long>{

    @Query("select c FROM TBL_Product c WHERE c.Id=:id AND c.Deleted<>1")
    public TBL_Product findByProductId(@RequestParam long id);

    @Query("SELECT c FROM TBL_Product c WHERE c.Name = :name AND c.Deleted<>1")
    public List<TBL_Product> findByName(@Param("name") String name);

    @Query("SELECT c FROM TBL_Product c WHERE c.Deleted<>1 AND c.product_catalog_id.Id IN (SELECT b.Id FROM TBL_ProductCategory b WHERE b.Deleted<>1 AND b.Name=:name)")
    public List<TBL_Product> findByCategoryName(@RequestParam String name);

    @Query("SELECT c FROM TBL_Product c WHERE c.Deleted<>1 AND c.Price BETWEEN :start AND :finish")
    public List<TBL_Product> findByPriceRange(@RequestParam float start, @RequestParam float finish);

    @Query("select c FROM TBL_Product c WHERE c.product_catalog_id.Id=:productCategoryId AND c.Deleted<>1")
    public List<TBL_Product> findByProductCategory(@RequestParam long productCategoryId);

}