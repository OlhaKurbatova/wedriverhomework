package com.epam.atm.homework5.webservices.restassured;

import com.epam.atm.homework5.webservices.jsonmodel.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JsonplaceholderTest {

    @BeforeTest
    public void init() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void checkStatusCode() {
        Response response = RestAssured.when().get("users").andReturn();
        Assert.assertEquals(response.statusCode(), 200, "Status code is wrong");
    }

    @Test
    public void checkResponseHeader() {
        Response response = RestAssured.when().get("users").andReturn();
        String contentTypeHeader = response.getHeader("Content-type");
        Assert.assertEquals(contentTypeHeader, "application/json; charset=utf-8", "Content type is wrong");
    }

    @Test
    public void checkResponseBody() {
        Response response = RestAssured.when().get("users").andReturn();
        ResponseBody<?> body = response.body();
        User[] users = body.as(User[].class);
        Assert.assertEquals(users.length, 10, "Users size is wrong");
    }
}
