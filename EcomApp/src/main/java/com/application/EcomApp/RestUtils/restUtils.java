package com.application.EcomApp.RestUtils;

import com.application.EcomApp.DTO.productsDataDTO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class restUtils {
    public static Response fetchAllProducts() {
        String Body = "{}";
        Response response = RestAssured.given().baseUri("http://localhost:9090/fetchAllProducts")
                .contentType(ContentType.JSON).body(Body).post();
        System.out.println(response.getBody().asString());
        if (response.getStatusCode() == 200) {
            return response;
        } else {
            return null;
        }
    }

    public static Response addProducts(productsDataDTO dto) {
        String Body = "{\"productname\":\"" + dto.getProductname() + "\",\r\n" + //
                "            \"productdescription\":\"" + dto.getProductdescription() + "\",\r\n" + //
                "            \"productquantity\":\"" + dto.getProductquantity() + "\",\r\n" + //
                "            \"productcost\":\"" + dto.getProductcost() + "\"}";
        Response response = RestAssured.given().baseUri("http://localhost:9090/addProduct")
                .contentType(ContentType.JSON).body(Body).post();
        if (response.getStatusCode() == 200) {
            return response;
        } else {
            return null;
        }

    }
}
