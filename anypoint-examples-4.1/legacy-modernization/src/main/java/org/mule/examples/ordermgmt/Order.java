/**
 * MuleSoft Examples
 * Copyright 2014 MuleSoft, Inc.
 *
 * This product includes software developed at
 * MuleSoft, Inc. (http://www.mulesoft.com/).
 */

package org.mule.examples.ordermgmt;

import java.util.List;

public class Order {

    private List<OrderItem> orderItemList;

    public Order() {
        // default no-argument constructor
    }

    public Order(List<OrderItem> orderItemList) {
        setOrderItemList(orderItemList);
    }

    public List<OrderItem> getOrderItemList() {
        return this.orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public double getTotal() {
        double result = 0.;
        for (OrderItem orderItem : orderItemList) {
            result += orderItem.getPrice() * orderItem.getQuantity();
        }
        return result;
    }
}
