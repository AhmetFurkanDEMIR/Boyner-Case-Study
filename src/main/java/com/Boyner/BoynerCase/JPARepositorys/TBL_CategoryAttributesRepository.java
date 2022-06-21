package com.Boyner.BoynerCase.JPARepositorys;

import com.Boyner.BoynerCase.model.TBL_CategoryAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TBL_CategoryAttributesRepository extends JpaRepository<TBL_CategoryAttributes, Long> {



}