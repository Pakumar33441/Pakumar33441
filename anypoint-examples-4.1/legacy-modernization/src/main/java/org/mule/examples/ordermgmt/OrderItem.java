/**
 * MuleSoft Examples
 * Copyright 2014 MuleSoft, Inc.
 *
 * This product includes software developed at
 * MuleSoft, Inc. (http://www.mulesoft.com/).
 */

package org.mule.examples.ordermgmt;

public class OrderItem {

    private String merchantSKU;
    private int quantity;
    private double price;

    public OrderItem() {
        // default no-arg constructor
    }

    public OrderItem(String merchantSKU, int quantity) {
        setMerchantSKU(merchantSKU);
        setQuantity(quantity);
    }

    public String getMerchantSKU() {
        return this.merchantSKU;
    }

    public void setMerchantSKU(String merchantSKU) {
        this.merchantSKU = merchantSKU;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
