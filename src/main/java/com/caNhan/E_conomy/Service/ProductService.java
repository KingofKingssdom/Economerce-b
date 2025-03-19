package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Entity.Brand;
import com.caNhan.E_conomy.Entity.Category;
import com.caNhan.E_conomy.Entity.Product;
import com.caNhan.E_conomy.Repository.BrandRepository;
import com.caNhan.E_conomy.Repository.CategoryRepository;
import com.caNhan.E_conomy.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private BrandRepository brandRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }
    public Product saveProduct (Product product, MultipartFile file, List<Integer> categoryIds, List<Integer> brandIds) throws Exception {
        if(!file.isEmpty()){
            product.setProductImage(file.getBytes());
        }
        List<Category> categories = new ArrayList<>();
        for(Integer categoryId: categoryIds){
            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            categories.add(categoryOptional.get());
        }
        List<Brand> brands = new ArrayList<>();
        for(Integer brandId: brandIds){
            Optional<Brand> brandOptional = brandRepository.findById(brandId);
            brands.add(brandOptional.get());
        }
        product.setCategories(categories);
        product.setBrands(brands);
        return productRepository.save(product);
    }

    public Product findProductById (int id) {
        return productRepository.findById(id).orElseThrow();
    }
    public List<Product> findAllProduct () {
        return productRepository.findAll();
    }
    public List<Product> findProductByCategoryId(Integer categoryId) {
        return productRepository.findByCategories_Id(categoryId);
    }
    public void deleteProduct (Product product) {
        productRepository.delete(product);
    }
    // Truy van san pham theo danh muc va sap xep tu lon den thap theo gia
    public List<Product> findProductByPriceWithCategory_IdDesc (Integer categoryId) {
        return productRepository.findProductByPriceCurrentAndCategories_IdDesc(categoryId);
    }
    public List<Product> findProductByCategory_IdWithPriceAsc (Integer categoryId){
        return  productRepository.findProductByCategories_IdWithPriceCurrentAsc(categoryId);
    }
//     Truy van san pham theo danh muc voi brand va sap xep tu nho den lon theo gia
    public List<Product> findProductByCategoryIdAndBrandIdWithPriceSortDesc (Integer categoryId, Integer brandId) {
        return productRepository.findProductByCategories_IdAndBrandIdWithPriceSortDesc(categoryId, brandId);
    }
    public List<Product> findProductByCategoryIdAndBrandIdWithPriceSortAsc (Integer categoryId, Integer brandId) {
        return productRepository.findProductByCategories_IdAndBrandIdWithPriceSortAsc(categoryId, brandId);
    }
    // lay toan bo san pham theo danh muc va brand
    public List<Product> findProductByCategoryIdAndBrandId(Integer categoryId, Integer brandId){
        return  productRepository.findProductByCategoriesIdAndBrandId(categoryId,brandId);
    }


}
