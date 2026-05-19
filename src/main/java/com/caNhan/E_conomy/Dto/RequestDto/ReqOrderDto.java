package com.caNhan.E_conomy.Dto.RequestDto;

import com.caNhan.E_conomy.Response.Enum.PaymentMethod;

import java.util.List;

public class ReqOrderDto {
    private Long userId;
    private List<Long> selectedCartItemIds;
    public PaymentMethod paymentMethod;
    public String receiverName;
    public String receiverPhone;
    public String ShippingAddress;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getSelectedCartItemIds() {
        return selectedCartItemIds;
    }

    public void setSelectedCartItemIds(List<Long> selectedCartItemIds) {
        this.selectedCartItemIds = selectedCartItemIds;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        ShippingAddress = shippingAddress;
    }
}
