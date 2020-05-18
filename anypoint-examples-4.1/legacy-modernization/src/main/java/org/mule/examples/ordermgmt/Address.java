/**
 * MuleSoft Examples
 * Copyright 2014 MuleSoft, Inc.
 *
 * This product includes software developed at
 * MuleSoft, Inc. (http://www.mulesoft.com/).
 */

package org.mule.examples.ordermgmt;

public class Address {

    private String name;
    private String line1;
    private String line2;
    private String city;
    private String stateOrProvinceCode;
    private String countryCode;
    private String postalCode;

    public Address() {
        // no argument default constructor
    }

    public Address(String name, String line1, String line2, String city, String stateOrProvinceCode, String countryCode,
            String postalCode) {
        this.setName(name);
        this.setLine1(line1);
        this.setLine2(line2);
        this.setCity(city);
        this.setStateOrProvinceCode(stateOrProvinceCode);
        this.setCountryCode(countryCode);
        this.setPostalCode(postalCode);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine1() {
        return this.line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return this.line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateOrProvinceCode() {
        return this.stateOrProvinceCode;
    }

    public void setStateOrProvinceCode(String stateOrProvinceCode) {
        this.stateOrProvinceCode = stateOrProvinceCode;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}
