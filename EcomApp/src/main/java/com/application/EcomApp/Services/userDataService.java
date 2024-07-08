package com.application.EcomApp.Services;

import java.util.List;
import com.application.EcomApp.DTO.productsDataDTO;
import com.application.EcomApp.Entities.usersData;

import io.restassured.response.Response;

public interface userDataService {
    public usersData saveRecord(usersData usersdata);
    public usersData validateUserLogin(String email, String password);
    public int updateUser(usersData udata,String CurrentPassword,String newPassword);
    public List<productsDataDTO> fetchAllProducts();
    public Response addProduct(productsDataDTO productsDTO);
}