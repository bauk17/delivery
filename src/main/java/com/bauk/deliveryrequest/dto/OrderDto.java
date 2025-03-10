package com.bauk.deliveryrequest.dto;

import java.util.Date;

import com.bauk.deliveryrequest.models.Order;

public class OrderDto {
    private String id;
    private Integer quantity;
    private Date order_date;
    private String costumerId;

    public OrderDto() {
        this.costumerId = null;
        this.quantity = null;
        this.order_date = null;
        this.id = null;
    }

    public OrderDto(Order obj) {
        this.costumerId = obj.getCustomerId();
        this.quantity = obj.getQuantity();
        this.order_date = obj.getOrder_date();
        this.id = obj.getId();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public String getCostumer() {
        return costumerId;
    }

    public String getId() {
        return id;
    }

}
