package com.bauk.deliveryrequest.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private Integer quantity;
    private Date order_date;
    private String status;
    private User customer;

    public Order() {
    }

    public Order(String id, Integer quantity, Date order_date, String status, User customer) {
        this.id = id;
        this.quantity = quantity;
        this.order_date = order_date;
        this.status = status;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

}
