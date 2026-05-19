package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Custom.CustomUserDetail;
import com.caNhan.E_conomy.Dto.RequestDto.ReqCarItemDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResCartItemDto;
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
    @PostMapping
    private ResponseEntity<?> create(ReqCarItemDto reqCarItemDto){
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
//        Long userId = customUserDetail.getId();
        ResCartItemDto cartItem = cartItemService.createCartItem(reqCarItemDto);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data created successfully",
                cartItem
        );
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("user/{userId}")
    private ResponseEntity<?> getByUser(@PathVariable Long userId){
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
//        Long userId = customUserDetail.getId();
        List<ResCartItemDto> cartItem = cartItemService.getCartItemByUserId(userId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                cartItem
        );
        return ResponseEntity.ok(responseData);
    }
    @PutMapping("/{cartItemId}")
    private ResponseEntity<?> update(@PathVariable Long cartItemId,
                                     @RequestParam(name = "newQuantity") int newQuantity){
        ResCartItemDto resCartItemDto = cartItemService.updateCartItemByQuantity(cartItemId, newQuantity);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data update successfully",
                resCartItemDto
        );
        return ResponseEntity.ok(responseData);
    }
    @DeleteMapping("/user/{userId}/delete-items")
    private ResponseEntity<?> deleteById(@RequestParam(name = "cartItemIds") List<Long> cartItemIds,
                                         @PathVariable Long userId){
        cartItemService.deleteCartItemById(cartItemIds, userId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data deleted successfully"
        );
        return ResponseEntity.ok(responseData);
    }
    @DeleteMapping("/user/{userId}")
    private ResponseEntity<?> delete(@PathVariable Long userId){
        cartItemService.deleteAllCartItem(userId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data deleted successfully"
        );
        return ResponseEntity.ok(responseData);
    }

}
