package com.caNhan.E_conomy.Dto.ResponseDto;

import com.caNhan.E_conomy.Response.Enum.OrderStatus;

public class OrderCountStatusResponseDTO {
    private OrderStatus status;
    private Long total;

    public OrderCountStatusResponseDTO(OrderStatus status, Long total) {
        this.status = status;
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
