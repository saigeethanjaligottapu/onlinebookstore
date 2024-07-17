package com.bittercode.model;

import java.util.Objects;

public class Address {
    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String state;
    private final String country;
    private final long pinCode;
    private final String phone;

    public Address(String addressLine1, String addressLine2, String city, String state, String country, long pinCode, String phone) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.phone = phone;
    }

    // Additional methods for better functionality
    public String getAddressSummary() {
        return String.format("%s, %s, %s, %s, %s", addressLine1, addressLine2, city, state, country);
    }

    // Implementing equals() and hashCode() for better object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return pinCode == address.pinCode &&
                Objects.equals(addressLine1, address.addressLine1) &&
                Objects.equals(addressLine2, address.addressLine2) &&
                Objects.equals(city, address.city) &&
                Objects.equals(state, address.state) &&
                Objects.equals(country, address.country) &&
                Objects.equals(phone, address.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressLine1, addressLine2, city, state, country, pinCode, phone);
    }

    // Additional methods as needed

    @Override
    public String toString() {
        return "Address{" +
                "addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pinCode=" + pinCode +
                ", phone='" + phone + '\'' +
                '}';
    }
}