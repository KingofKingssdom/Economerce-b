package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.ProductDTO;
import com.caNhan.E_conomy.Dto.ProductVariantDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductColorResponseDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductResponseDTO;
import com.caNhan.E_conomy.Entity.Brand;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
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
    public ProductResponseDTO create(ProductDTO productDto) {
        try{
            Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
            Optional<Brand> brandOptional = brandRepository.findById(productDto.getBrandId());
            Product product;
            if(categoryOptional.isEmpty() && brandOptional.isEmpty()){
                throw new NoSuchCustomerExistsException("Không tạo được sản phẩm với danh mục Id = " + productDto.getCategoryId()
                                                        + " Id brand = " + productDto.getBrandId());
            }
            else{
                String productPath = FileStorageUtil.storeFile("Product",productDto.getUrlPhoto());
                product = new Product();
                product.setProductCode(productDto.getProductCode());
                product.setProductName(productDto.getProductName());
                product.setDescription(productDto.getDescription());
                product.setPhotoUrl(productPath);
                product.setFeatured(productDto.isFeatured());
                product.setPromotional(productDto.isPromotional());
                product.setQuantityProduct(productDto.getQuantityProduct());
                product.setCategory(categoryOptional.get());
                product.setBrand(brandOptional.get());
            }
            Product saveProduct =  productRepository.save(product);
            return modelMapper.map(saveProduct,ProductResponseDTO.class);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public Page<ProductResponseDTO> readAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize); // dùng pageNumber, pageSize truyền vào
        Page<Product> products = productRepository.findAll(pageable);

        return products.map(product -> {
            ProductResponseDTO dto = modelMapper.map(product, ProductResponseDTO.class);

            if (product.getProductVariants() != null) {
                dto.setProductVariants(
                        product.getProductVariants().stream()
                                .map(variant -> modelMapper.map(variant, ProductVariantDTO.class))
                                .toList()
                );
            }

            return dto;
        });}


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
                product.setFeatured(productDTO.isFeatured());
                product.setPromotional(productDTO.isPromotional());
                product.setPhotoUrl(productPath);
                product.setQuantityProduct(productDTO.getQuantityProduct());
                product.setCategory(category.get());
            }
            Product updateProduct = productRepository.save(product);
            return modelMapper.map(updateProduct,ProductResponseDTO.class);
        }
        catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Page<ProductResponseDTO> readByCategory(Long categoryId, int pageNumber, int pageSize) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if(categoryOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("Không tìm thấy sản phẩm với id danh mục = " + categoryId);
        }
        else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<Product> products = productRepository.findByCategoryId(categoryId,pageable);
            return products.map(product -> {
                        ProductResponseDTO dto = modelMapper.map(product, ProductResponseDTO.class);

                        if (product.getProductVariants() != null) {
                            dto.setProductVariants(
                                    product.getProductVariants().stream()
                                            .map(variant -> modelMapper.map(variant, ProductVariantDTO.class))
                                            .toList()
                            );
                        }
                        return dto;});
        }
    }

    @Override
    public Page<ProductResponseDTO> readByCategoryAndBrand(Long categoryId, Long brandId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> products = productRepository.findByCategoryAndBrand(categoryId, brandId, pageable);
        return products.map(product -> {
                    ProductResponseDTO dto = modelMapper.map(product, ProductResponseDTO.class);

                    if (product.getProductVariants() != null) {
                        dto.setProductVariants(
                                product.getProductVariants().stream()
                                        .map(variant -> modelMapper.map(variant, ProductVariantDTO.class))
                                        .toList()
                        );
                    }

                    return dto;
                });
    }

    @Override
    public List<ProductResponseDTO> readAllByFeatured(boolean featured) {
        List<Product> products = productRepository.findAllByFeatured(featured);

        return products.stream()
                .map(product -> {
            ProductResponseDTO dto = modelMapper.map(product, ProductResponseDTO.class);

            if (product.getProductVariants() != null) {
                dto.setProductVariants(
                        product.getProductVariants().stream()
                                .map(variant -> modelMapper.map(variant, ProductVariantDTO.class))
                                .toList()
                );
            }
            return dto;})
                .toList();
    }

    @Override
    public List<ProductResponseDTO> readByProductName(String productName) {
        List<Product> products = productRepository.findAllByProductName(productName);
        return products.stream()
                .map(product -> {
                    ProductResponseDTO dto = modelMapper.map(product, ProductResponseDTO.class);

                    if (product.getProductVariants() != null) {
                        dto.setProductVariants(
                                product.getProductVariants().stream()
                                        .map(variant -> modelMapper.map(variant, ProductVariantDTO.class))
                                        .toList()
                        );
                    }
                    return dto;})
                .toList();
    }
}
