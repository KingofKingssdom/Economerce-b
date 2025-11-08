package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Custom.CustomUserDetail;
import com.caNhan.E_conomy.Dto.ResponseDto.CartItemResponseDTO;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {
    private CartItemService cartItemService;
    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }
    @PostMapping("create")
    private ResponseEntity<?> createCartItem(@RequestParam (name = "productId") Long productId,
                                             @RequestParam (name = "productVariantId") Long productVariantId,
                                             @RequestParam (name = "productColorId") Long productColorId){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        Long userId = customUserDetail.getId();
        CartItemResponseDTO cartItem = cartItemService.create(userId, productId ,productVariantId, productColorId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Thêm sản phẩm vào đơn hàng thành công",
                cartItem
        );
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/all")
    private ResponseEntity<?> getCartItemByUser(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        Long userId = customUserDetail.getId();
        List<CartItemResponseDTO> cartItem = cartItemService.findAllByCartId(userId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lấy toàn bộ hàng trong giỏ hàng thành công",
                cartItem
        );
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/delete")
    private ResponseEntity<?> deleteById(@RequestParam(name = "cartItemId") Long cartItemId){
        cartItemService.deleteByCartId(cartItemId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Xóa món hàng ra khỏi giỏ hàng thành công"
        );
        return ResponseEntity.ok(responseData);
    }

}
