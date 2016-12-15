package net.tralfamadore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class: SearchDto
 * Created by billreh on 12/15/16.
 */
public class SearchDto {
    @JsonProperty("_city")
    private String city;
    @JsonProperty("_state")
    private String state;
    @JsonProperty("_zipCode")
    private String zipCode;
    @JsonProperty("_houseType")
    private String houseType;
    @JsonProperty("_bedrooms")
    private int bedrooms;
    @JsonProperty("_bathrooms")
    private float bathrooms;
    @JsonProperty("_squareFeet")
    private int squareFeet;
    @JsonProperty("_price")
    private int price;

    public SearchDto() {
    }

    public SearchDto(String city, String state, String zipCode, String houseType, int bedrooms, float bathrooms, int squareFeet, int price) {
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.houseType = houseType;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.squareFeet = squareFeet;
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public float getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(float bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(int squareFeet) {
        this.squareFeet = squareFeet;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SearchDto{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", houseType='" + houseType + '\'' +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", squareFeet=" + squareFeet +
                ", price=" + price +
                '}';
    }
}
