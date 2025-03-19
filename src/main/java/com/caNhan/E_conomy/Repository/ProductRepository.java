package com.caNhan.E_conomy.Repository;

import com.caNhan.E_conomy.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByCategories_Id (Integer categoryId);
//    Lay san pham theo  danh muc  sap xep theo gia tu lon den nho
        @Query(value = "SELECT products.* FROM products " +
            "JOIN product_category ON products.id = product_category.product_id " +
            "WHERE product_category.category_id = ? " +
            "ORDER BY price_current DESC ", nativeQuery = true)
    List<Product> findProductByPriceCurrentAndCategories_IdDesc (Integer categoryId);

        // Lay san pham theo danh muc va sap xep theo gia tu nho den lon
        @Query(value = "SELECT products.* FROM products " +
                "JOIN product_category ON products.id = product_category.product_id " +
                "WHERE product_category.category_id = ? " +
                "ORDER BY price_current ASC ", nativeQuery = true)
    List<Product> findProductByCategories_IdWithPriceCurrentAsc(Integer categoryId);
// Lay san pham theo danh muc va theo brand va sap xep lon den nho theo gia hien tai
   @Query(value = "SELECT products.* FROM products " +
           "JOIN product_brand ON products.id = product_brand.product_id " +
           "JOIN product_category ON products.id = product_category.product_id " +
           "WHERE product_category.category_id = ? AND product_brand.brand_id = ? " +
           "ORDER BY price_current DESC ", nativeQuery = true)
    List<Product> findProductByCategories_IdAndBrandIdWithPriceSortDesc (Integer categoryId, Integer brandId);
    // Lay san pham theo danh muc va theo brand va sap xep nho den lon theo gia hien tai
    @Query(value = "SELECT products.* FROM products " +
            "JOIN product_brand ON products.id = product_brand.product_id " +
            "JOIN product_category ON products.id = product_category.product_id " +
            "WHERE product_category.category_id = ? AND product_brand.brand_id = ? " +
            "ORDER BY price_current ASC ", nativeQuery = true)
    List<Product> findProductByCategories_IdAndBrandIdWithPriceSortAsc (Integer categoryId, Integer brandId);

    // Lay toan bo san pham theo categoryId va brandId

    @Query(value = "SELECT products.* FROM products "+
            "JOIN product_brand ON products.id = product_brand.product_id " +
            "JOIN product_category ON products.id = product_category.product_id ",nativeQuery = true)
    List<Product> findProductByCategoriesIdAndBrandId(Integer categoryId, Integer brandId);
}
