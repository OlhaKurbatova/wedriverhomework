
package com.epam.atm.homework5.webservices.jsonmodel;

import com.google.gson.annotations.SerializedName;

public class Company {

    @SerializedName("name")
    public String name;
    @SerializedName("catchPhrase")
    public String catchPhrase;
    @SerializedName("bs")
    public String bs;

}
