package com.application.EcomApp.Services;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.EcomApp.DTO.cartItemsDTO;
import com.application.EcomApp.DTO.productsDataDTO;
import com.application.EcomApp.Entities.usersData;
import com.application.EcomApp.Repositories.usersDataRepository;
import com.application.EcomApp.RestUtils.restUtils;

import io.restassured.response.Response;

@Service
public class userDataServiceImpl implements userDataService {

    @Autowired
    private usersDataRepository repo;

    @SuppressWarnings("unused")
    @Override
    public usersData saveRecord(usersData usersdata) {
        List<usersData> usersList = repo.findAll();
        boolean status = false;
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getEmail().equals(usersdata.getEmail())) {
                status = true;
                break;
            } else {
                status = false;
            }
        }
        if (status == false) {
            usersData data = repo.save(usersdata);
            if (data != null) {
                return data;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public usersData validateUserLogin(String email, String password) {
        try {
            usersData data = repo.findByEmail(email);
            if (data.email.equals(email) && data.userpassword.equals(password)) {
                return data;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public int updateUser(usersData udata, String currentPassword, String newPassword) {
        usersData updateData = new usersData();
        String email = udata.getEmail();
        usersData data = repo.findByEmail(email);
        if (udata != null && udata.getEmail().equals(data.getEmail())
                && currentPassword.equals(data.getUserpassword())) {
            updateData.setEmail(email);
            updateData.setUsername(udata.getUsername());
            updateData.setUsermobile(udata.getUsermobile());
            updateData.setUserpassword(newPassword);
            int test = repo.updateUser(udata.getUsername(), udata.getUsermobile(), newPassword, email);
            return test;
        } else {
            return 0;
        }

    }

    @Override
    public List<productsDataDTO> fetchAllProducts() {
        Response response = restUtils.fetchAllProducts();
        // System.out.println(response.getBody().asString());
        if (response != null) {
            JSONArray array = new JSONArray(response.getBody().asString());
            array.length();
            List<productsDataDTO> productsList = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                productsDataDTO dto = new productsDataDTO();
                JSONObject object = array.getJSONObject(i);
                dto.setProductname(object.getString("productname"));
                dto.setProductdescription(object.getString("productdescription"));
                dto.setProductquantity(object.getString("productquantity"));
                dto.setProductcost(object.getString("productcost"));
                productsList.add(dto);
            }
            // System.out.println("Products List is :- "+productsList);
            return productsList;
        } else {
            return null;
        }
    }

    @Override
    public Response addProduct(productsDataDTO productsDTO) {
        Response response = restUtils.addProducts(productsDTO);
        if (response.getStatusCode() == 200) {
            return response;
        } else {
            return null;
        }
    }

    @Override
    public Response addProductToCart(productsDataDTO productData,String email) {
        Response response = restUtils.addProductToCart(productData,email);
        if (response.getStatusCode() == 200) {
            return response;
        } else {
            return null;
        }
    }

    @Override
    public int getNoOfRecordsInTheCart(String email) {
        Response response = restUtils.getallRecordsFromCart(email);
        if (response != null) {
            JSONArray array = new JSONArray(response.getBody().asString());
            int noOfRecords = array.length();
            return noOfRecords;
        } else {
            return 0;
        }
    }

    @Override
    public List<cartItemsDTO> getAllCartItems(String email) {
        Response response = restUtils.getallRecordsFromCart(email);
        if (response != null) {
            JSONArray array = new JSONArray(response.getBody().asString());
            array.length();
            List<cartItemsDTO> cartItemsList = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                cartItemsDTO dto = new cartItemsDTO();
                JSONObject object = array.getJSONObject(i);
                dto.setProductname(object.getString("productname"));
                dto.setProductquantity(object.getString("productquantity"));
                dto.setProductcost(object.getString("productcost"));
                dto.setUseremail(object.getString("useremail"));
                cartItemsList.add(dto);
            }
            return cartItemsList;
        } else {
            return null;
        }
    }

    @Override
    public void clearCart(String email) {
        restUtils.clearCart(email);
    }
}
