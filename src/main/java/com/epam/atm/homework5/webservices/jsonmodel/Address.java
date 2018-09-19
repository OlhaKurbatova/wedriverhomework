
package com.epam.atm.homework5.webservices.jsonmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("street")
    public String street;
    @SerializedName("suite")
    public String suite;
    @SerializedName("city")
    public String city;
    @SerializedName("zipcode")
    public String zipcode;
    @SerializedName("geo")
    public Geo geo;

}
