package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Entity.Product;
import com.caNhan.E_conomy.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestParam String productName,
                              @RequestParam double pricePrevious,
                              @RequestParam double priceCurrent,
                              @RequestParam String discountPercent,
                              @RequestParam String quantity,
                              @RequestParam String status,
                              @RequestParam String installment,
                              @RequestParam String description,
                              @RequestParam(name = "productImage", required = false)MultipartFile productImage,
                                        @RequestParam (value = "categoryIds", required = false) List<Integer> categoryIds,
                                        @RequestParam(value = "brandIds", required = false) List<Integer> brandIds) throws Exception {
        try {
            Product product = new Product();
            product.setProductName(productName);
            product.setPricePrevious(pricePrevious);
            product.setPriceCurrent(priceCurrent);
            product.setDiscountPercent(discountPercent);
            product.setQuantity(quantity);
            product.setStatus(status);
            product.setInstallment(installment);
            product.setDescription(description);
            Product addProduct = productService.saveProduct(product, productImage, categoryIds, brandIds);
            return  ResponseEntity.ok(addProduct);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
       return ResponseEntity.badRequest().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id,
                                           @RequestParam  String productName,
                                           @RequestParam double pricePrevious,
                                           @RequestParam double priceCurrent,
                                           @RequestParam String discountPercent,
                                           @RequestParam String quantity,
                                           @RequestParam String status,
                                           @RequestParam String installment,
                                           @RequestParam String description,
                                           @RequestParam(name = "productImage", required = false)MultipartFile productImage,
                                           @RequestParam (value = "categoryIds", required = false) List<Integer> categoryIds,
                                           @RequestParam(value = "brandIds", required = false) List<Integer> brandIds) throws Exception  {

        Product product = productService.findProductById(id);
        if (product != null){
            product.setProductName(productName);
            product.setPricePrevious(pricePrevious);
            product.setPriceCurrent(priceCurrent);
            product.setDiscountPercent(discountPercent);
            product.setQuantity(quantity);
            product.setStatus(status);
            product.setInstallment(installment);
            product.setDescription(description);
            Product addProduct = productService.saveProduct(product, productImage, categoryIds, brandIds);
            return  ResponseEntity.ok(addProduct);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }
    @GetMapping("/getAll")
    public List<Product> getAll() {
        return productService.findAllProduct();
    }
    @GetMapping("/category")
    public ResponseEntity<?> getProductByCategoryId(@RequestParam Integer categoryId){
        List<Product> products = productService.findProductByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }
    @GetMapping("category/price/desc")
    public ResponseEntity<?> getPriceCurrentByCategoryIdSort(@RequestParam Integer categoryId) {
        List<Product> products = productService.findProductByPriceWithCategory_IdDesc(categoryId);
        return ResponseEntity.ok(products);
    }
    @GetMapping("category/price/asc")
    public ResponseEntity<?> getProductByCategoryIdWithPriceSortAsc(@RequestParam Integer categoryId) {
        List<Product> products = productService.findProductByCategory_IdWithPriceAsc(categoryId);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/category/brand/price/desc")
    public ResponseEntity<?> getProductByCategoryIdAndBrandIdWithPriceSortDesc (@RequestParam(value = "categoryId") Integer categoryId, @RequestParam(value = "brandId") Integer brandId){
        List<Product> products = productService.findProductByCategoryIdAndBrandIdWithPriceSortDesc(categoryId,brandId);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/category/brand/price/asc")
    public ResponseEntity<?> getProductByCategoryIdAndBrandIdWithPriceSortAsc (@RequestParam(value = "categoryId") Integer categoryId, @RequestParam(value = "brandId") Integer brandId){
        List<Product> products = productService.findProductByCategoryIdAndBrandIdWithPriceSortAsc(categoryId,brandId);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/category/brand")
    public ResponseEntity<?> getProductByCategoryIdAndBrandId (@RequestParam(value = "categoryId") Integer categoryId, @RequestParam(value = "brandId") Integer brandId){
        List<Product> products = productService.findProductByCategoryIdAndBrandIdWithPriceSortAsc(categoryId,brandId);
        return ResponseEntity.ok(products);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int id) {
        Product product = productService.findProductById(id);
        if(product != null){
            productService.deleteProduct(product);
        }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
