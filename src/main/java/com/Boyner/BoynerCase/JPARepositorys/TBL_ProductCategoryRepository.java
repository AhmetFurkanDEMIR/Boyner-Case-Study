package com.Boyner.BoynerCase.JPARepositorys;

import com.Boyner.BoynerCase.model.TBL_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Boyner.BoynerCase.model.TBL_ProductCategory;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface TBL_ProductCategoryRepository extends JpaRepository<TBL_ProductCategory, Long> {

    @Query("SELECT c FROM TBL_ProductCategory c WHERE c.Name = :name AND c.Deleted<>1")
    public List<TBL_ProductCategory> findByName(@RequestParam String name);

    @Query("select c FROM TBL_ProductCategory c WHERE c.Id=:id AND c.Deleted<>1")
    public TBL_ProductCategory findByProductCategoryId(@RequestParam long id);

}