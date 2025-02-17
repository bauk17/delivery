package com.bauk.deliveryrequest.dto;

import java.util.Date;

import com.bauk.deliveryrequest.models.Order;
import com.bauk.deliveryrequest.models.User;

public class OrderDto {
    private Integer quantity;
    private Date order_date;
    private User costumer;

    public OrderDto() {
        this.costumer = null;
        this.quantity = null;
        this.order_date = null;
    }

    public OrderDto(Order obj) {
        this.costumer = obj.getCustomer();
        this.quantity = obj.getQuantity();
        this.order_date = obj.getOrder_date();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public User getCostumer() {
        return costumer;
    }

}
