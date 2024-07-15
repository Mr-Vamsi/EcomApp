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
        // System.out.println(response.getBody().asString());
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

    public static Response addProductToCart(productsDataDTO productsData, String email) {

        int qty = Integer.parseInt(productsData.getProductquantity());
        int cost = Integer.parseInt(productsData.getProductcost());
        int totalCost = qty * cost;

        String Body = "{\"productname\":\"" + productsData.getProductname() + "\",\"productquantity\":\""
                + productsData.getProductquantity() + "\",\"productcost\":\"" + totalCost
                + "\",\"useremail\":\""+ email + "\"}";
        Response response = RestAssured.given().baseUri("http://localhost:7070/addToCart").contentType(ContentType.JSON)
                .body(Body).post();
        if (response.getStatusCode() == 200) {
            return response;
        } else {
            return null;
        }
    }

    public static Response getallRecordsFromCart(String email) {
        String Body = "{\"useremail\":\""+email+"\"}";
        Response response = RestAssured.given().baseUri("http://localhost:7070/getAllCartItems")
                .contentType(ContentType.JSON).body(Body).post();
        if (response.getStatusCode() == 200) {
            return response;
        } else {
            return null;
        }
    }

    public static void clearCart(String email) {
        String Body = "{\"useremail\":\""+email+"\"}";
        Response response = RestAssured.given().baseUri("http://localhost:7070/buy").contentType(ContentType.JSON)
                .body(Body).post();
        if (response.getStatusCode() == 200) {
            System.out.println("Cart is Cleared ");
        } else {
            System.out.println("Cart is not Cleared");
        }
    }
}
