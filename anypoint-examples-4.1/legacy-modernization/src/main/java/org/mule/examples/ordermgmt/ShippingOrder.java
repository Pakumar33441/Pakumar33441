/**
 * MuleSoft Examples
 * Copyright 2014 MuleSoft, Inc.
 *
 * This product includes software developed at
 * MuleSoft, Inc. (http://www.mulesoft.com/).
 */

package org.mule.examples.ordermgmt;

public class ShippingOrder {

    private String shippingId;
    private Address billingAddress;
    private Address shippingAddress;
    private Order order;

    public ShippingOrder() {
        // default no-argument constructor
    }

    public ShippingOrder(String shippingId, Address billingAddress, Address shippingAddress, Order order) {
        setShippingId(shippingId);
        setBillingAddress(billingAddress);
        setShippingAddress(shippingAddress);
        setOrder(order);
    }

    public String getShippingId() {
        return this.shippingId;
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Address getBillingAddress() {
        return this.billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

}
