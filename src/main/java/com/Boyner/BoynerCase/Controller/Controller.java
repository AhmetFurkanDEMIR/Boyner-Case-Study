package com.Boyner.BoynerCase.Controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Boyner.BoynerCase.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Boyner.BoynerCase.JPARepositorys.*;
import com.Boyner.BoynerCase.model.*;
import org.json.JSONObject;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private TBL_ProductAttributesRepository ProductAttributesRepository;

    @Autowired
    private TBL_CategoryAttributesRepository CategoryAttributesRepository;

    @Autowired
    private TBL_ProductCategoryRepository ProductCategoryRepository;

    @Autowired
    private TBL_ProductRepository ProductRepository;

    @GetMapping("/productName")
    public ResponseEntity<List<TBL_Product>> getProductByName(@RequestParam String name)
            throws ResourceNotFoundException {
        List<TBL_Product> product =  ProductRepository.findByName(name);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/productCategoryName")
    public ResponseEntity<List<TBL_Product>> getProductCategoryName(@RequestParam String name)
            throws ResourceNotFoundException {
        List<TBL_Product> product =  ProductRepository.findByCategoryName(name);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/productPriceRange")
    public ResponseEntity<List<TBL_Product>> getProductPriceRange(@RequestParam float start,
                                                                  @RequestParam float finish)
            throws ResourceNotFoundException {
        List<TBL_Product> product =  ProductRepository.findByPriceRange(start, finish);
        return ResponseEntity.ok().body(product);
    }


    @GetMapping("/PrCategoryName")
    public ResponseEntity<List<TBL_ProductCategory>> getProductCategoryByName(@RequestParam String name)
            throws ResourceNotFoundException {
        List<TBL_ProductCategory> productCategory =  ProductCategoryRepository.findByName(name);
        return ResponseEntity.ok().body(productCategory);
    }


    @DeleteMapping("/deleteProduct")
    public Map<String, Boolean> deleteProduct(@RequestParam long id)
            throws ResourceNotFoundException {
        TBL_Product product = (TBL_Product) ProductRepository.findByProductId(id);
        product.setDeleted(1);
        final TBL_Product updatedProduct= ProductRepository.save(product);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping("/deleteProductCategory")
    public Map<String, Boolean> deleteProductCategory(@RequestParam long id)
            throws ResourceNotFoundException {

        TBL_ProductCategory productCategory = ProductCategoryRepository.findByProductCategoryId(id);
        productCategory.setDeleted(1);
        final TBL_ProductCategory updatedCategoryProduct= ProductCategoryRepository.save(productCategory);

        List<TBL_Product> products = (List<TBL_Product>) ProductRepository.findByProductCategory(id);

        for(TBL_Product product : products){
            product.setDeleted(1);
            final TBL_Product updatedProduct= ProductRepository.save(product);
        }

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/createProduct")
    public TBL_Product createProduct(@Valid @RequestBody String product) throws ResourceNotFoundException {

        JSONObject json = new JSONObject(product);

        String name = (String) json.get("name");
        BigDecimal price = (BigDecimal) json.get("price");

        int product_attributes = (int) json.get("product_attributes");
        int product_catalog_id = (int) json.get("product_catalog_id");

        TBL_ProductAttributes ProductAttributes = ProductAttributesRepository.findById((long) product_attributes)
                .orElseThrow(() -> new ResourceNotFoundException("CategoryAttributes not found for this id :: " + product_attributes));

        TBL_ProductCategory ProductCategory = ProductCategoryRepository.findByProductCategoryId((long) product_catalog_id);

        TBL_Product productFinal = new TBL_Product();
        productFinal.setName(name);
        productFinal.setPrice(price.floatValue());

        productFinal.setProductAttributes(ProductAttributes);
        productFinal.setProductCatalogId(ProductCategory);

        productFinal = ProductRepository.save(productFinal);

        return productFinal;
    }

    @PostMapping("/createProductCategory")
    public TBL_ProductCategory createProductCategory(@Valid @RequestBody String productCategory) throws ResourceNotFoundException {

        JSONObject json = new JSONObject(productCategory);

        String name = (String) json.get("name");
        int category_attributesId = (int) json.get("category_attributes");

        TBL_CategoryAttributes CategoryAttributes = CategoryAttributesRepository.findById((long) category_attributesId)
                .orElseThrow(() -> new ResourceNotFoundException("CategoryAttributes not found for this id :: " + category_attributesId));

        TBL_ProductCategory productCategoryFinal = new TBL_ProductCategory();
        productCategoryFinal.setCategoryAttributes(CategoryAttributes);
        productCategoryFinal.setName(name);

        productCategoryFinal = ProductCategoryRepository.save(productCategoryFinal);

        return productCategoryFinal;
    }

    @PostMapping("/createProductAttributes")
    public TBL_ProductAttributes createProductAttributes(@Valid @RequestBody TBL_ProductAttributes productAttributes) throws ResourceNotFoundException {

        return ProductAttributesRepository.save(productAttributes);
    }

    @PostMapping("/createProductCategoryAttributes")
    public TBL_CategoryAttributes createProductAttributes(@Valid @RequestBody TBL_CategoryAttributes productCategoryAttributes) throws ResourceNotFoundException {

        return CategoryAttributesRepository.save(productCategoryAttributes);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<TBL_Product> updateProduct(@Valid @RequestBody String productId) throws ResourceNotFoundException {

        JSONObject json = new JSONObject(productId);

        int id = (int) json.get("id");
        String name = (String) json.get("name");
        int product_catalog_id = (int) json.get("product_catalog_id");
        int product_attributes = (int) json.get("product_attributes");
        BigDecimal price = (BigDecimal) json.get("price");

        TBL_Product product = ProductRepository.findByProductId((long) id);

        product.setName(name);

        TBL_ProductAttributes ProductAttributes = ProductAttributesRepository.findById((long) product_attributes)
                .orElseThrow(() -> new ResourceNotFoundException("CategoryAttributes not found for this id :: " + product_attributes));

        TBL_ProductCategory ProductCategory = ProductCategoryRepository.findByProductCategoryId((long) product_catalog_id);

        product.setProductCatalogId(ProductCategory);
        product.setProductAttributes(ProductAttributes);
        product.setPrice(price.floatValue());

        product = ProductRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/updateProductCategory")
    public ResponseEntity<TBL_ProductCategory> updateProductCategory(@Valid @RequestBody String productCategoryId) throws ResourceNotFoundException {

        JSONObject json = new JSONObject(productCategoryId);

        int id = (int) json.get("id");
        String name = (String) json.get("name");
        int category_attributes = (int) json.get("category_attributes");

        TBL_ProductCategory productCategory = ProductCategoryRepository.findByProductCategoryId((long) id);

        productCategory.setName(name);

        TBL_CategoryAttributes ProductCategoryAttributes = CategoryAttributesRepository.findById((long) category_attributes)
                .orElseThrow(() -> new ResourceNotFoundException("CategoryAttributes not found for this id :: " + category_attributes));
        productCategory.setCategoryAttributes(ProductCategoryAttributes);

        productCategory = ProductCategoryRepository.save(productCategory);
        return ResponseEntity.ok(productCategory);
    }

}