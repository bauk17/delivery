package com.bauk.deliveryrequest.dto;

import com.bauk.deliveryrequest.enums.OrderStatus;

import jakarta.annotation.Nonnull;

public class StatusUpdateRequest {

    @Nonnull
    private OrderStatus status;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
