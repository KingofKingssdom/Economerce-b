package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.ProductDTO;
import com.caNhan.E_conomy.Dto.ProductVariantDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductResponseDTO;
import com.caNhan.E_conomy.Entity.Category;
import com.caNhan.E_conomy.Entity.Product;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Repository.BrandRepository;
import com.caNhan.E_conomy.Repository.CategoryRepository;
import com.caNhan.E_conomy.Repository.ProductRepository;
import com.caNhan.E_conomy.Service.ProductService;
import com.caNhan.E_conomy.Util.FileStorageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private BrandRepository brandRepository;
    private ModelMapper modelMapper;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository,
                              ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Product create(ProductDTO productDto) {
        try{
            Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
            Product product;
            if(category.isEmpty()){
                throw new NoSuchCustomerExistsException("Không tạo được sản phẩm với danh mục Id = " + productDto.getCategoryId());
            }
            else{
                String productPath = FileStorageUtil.storeFile("Product",productDto.getUrlPhoto());
                product = new Product();
                product.setProductCode(productDto.getProductCode());
                product.setProductName(productDto.getProductName());
                product.setDescription(productDto.getDescription());
                product.setPhotoUrl(FileStorageUtil.fullUrl(productPath));
                product.setCategory(category.get());
            }
            return  productRepository.save(product);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<ProductResponseDTO> readAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> {
                    ProductResponseDTO dto = modelMapper.map(product, ProductResponseDTO.class);

                    if (product.getProductVariants() != null) {
                        dto.setProductVariants(
                                product.getProductVariants().stream()
                                        .map(variant -> modelMapper.map(variant, ProductVariantDTO.class))
                                        .toList()
                        );
                    }

                    return dto;
                })
                .toList();}

    @Override
    public ProductResponseDTO readById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
       ProductDTO productDTO;
        if(productOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("Không tìm thấy sản phẩm với id = " + productId);

        }
        else {
            return modelMapper.map(productOptional.get(), ProductResponseDTO.class) ;
        }

    }

    @Override
    public ProductResponseDTO update(Long productId, ProductDTO productDTO) {
        try{
            Optional<Product> productOptional = productRepository.findById(productId);
            Product product;
            if(productOptional.isEmpty()){
                throw new NoSuchCustomerExistsException("Không tìm thấy sản phẩm với id = " + productId);
            }
            else {
                Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId());
                String productPath = FileStorageUtil.storeFile("Product",productDTO.getUrlPhoto());
                product = new Product();
                product.setProductCode(productDTO.getProductCode());
                product.setProductName(productDTO.getProductName());
                product.setDescription(productDTO.getDescription());
                product.setPhotoUrl(FileStorageUtil.fullUrl(productPath));
                product.setCategory(category.get());
            }
            Product updateProduct = productRepository.save(product);
            return modelMapper.map(updateProduct,ProductResponseDTO.class);
        }
        catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

//    public Product saveProduct (Product product, MultipartFile file, List<Integer> categoryIds, List<Integer> brandIds) throws Exception {
//        if(!file.isEmpty()){
//            product.setProductImage(file.getBytes());
//        }
//        List<Category> categories = new ArrayList<>();
//        for(Integer categoryId: categoryIds){
//            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
//            categories.add(categoryOptional.get());
//        }
//        List<Brand> brands = new ArrayList<>();
//        for(Integer brandId: brandIds){
//            Optional<Brand> brandOptional = brandRepository.findById(brandId);
//            brands.add(brandOptional.get());
//        }
//        product.setCategories(categories);
//        product.setBrands(brands);
//        return productRepository.save(product);
//    }

//    public Product findProductById (int id) {
//        return productRepository.findById(id).orElseThrow();
//    }
//    public List<Product> findAllProduct () {
//        return productRepository.findAll();
//    }
//    public List<Product> findProductByCategoryId(Integer categoryId) {
//        return productRepository.findByCategories_Id(categoryId);
//    }
//    public void deleteProduct (Product product) {
//        productRepository.delete(product);
//    }
//    // Truy van san pham theo danh muc va sap xep tu lon den thap theo gia
//    public List<Product> findProductByPriceWithCategory_IdDesc (Integer categoryId) {
//        return productRepository.findProductByPriceCurrentAndCategories_IdDesc(categoryId);
//    }
//    public List<Product> findProductByCategory_IdWithPriceAsc (Integer categoryId){
//        return  productRepository.findProductByCategories_IdWithPriceCurrentAsc(categoryId);
//    }
////     Truy van san pham theo danh muc voi brand va sap xep tu nho den lon theo gia
//    public List<Product> findProductByCategoryIdAndBrandIdWithPriceSortDesc (Integer categoryId, Integer brandId) {
//        return productRepository.findProductByCategories_IdAndBrandIdWithPriceSortDesc(categoryId, brandId);
//    }
//    public List<Product> findProductByCategoryIdAndBrandIdWithPriceSortAsc (Integer categoryId, Integer brandId) {
//        return productRepository.findProductByCategories_IdAndBrandIdWithPriceSortAsc(categoryId, brandId);
//    }
//    // lay toan bo san pham theo danh muc va brand
//    public List<Product> findProductByCategoryIdAndBrandId(Integer categoryId, Integer brandId){
//        return  productRepository.findProductByCategoriesIdAndBrandId(categoryId,brandId);
//    }


}
