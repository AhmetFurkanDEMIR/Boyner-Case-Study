package com.Boyner.BoynerCase.JPARepositorys;

import com.Boyner.BoynerCase.model.TBL_ProductAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TBL_ProductAttributesRepository extends JpaRepository<TBL_ProductAttributes, Long> {

}