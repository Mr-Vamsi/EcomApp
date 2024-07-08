package com.application.EcomApp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.EcomApp.DTO.productsDataDTO;
import com.application.EcomApp.Entities.usersData;
import com.application.EcomApp.RestUtils.restUtils;
import com.application.EcomApp.Services.userDataService;

import io.restassured.response.Response;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class usersDataController {
    HttpSession session;

    public usersDataController(HttpSession session) {
        this.session = session;
    }

    @Autowired
    private userDataService service;

    @GetMapping("/login")
    public String callLoginPage() {
        return "Login";
    }

    @GetMapping("/register")
    public String callRegistrationPage() {
        return "Register";
    }

    @GetMapping("/main")
    public String callIndexPage() {
        return "index";
    }

    @GetMapping("/home")
    public String callHome() {
        return "home";
    }

    @GetMapping("/reg")
    public String registerUser(@RequestParam String username, @RequestParam String useremail,
            @RequestParam String userphone, @RequestParam String userpassword, @RequestParam String cnfpassword,
            ModelMap modelMap) {
        if (userpassword.equals(cnfpassword)) {
            usersData data = new usersData();
            data.setUsername(username);
            data.setEmail(useremail);
            data.setUsermobile(userphone);
            data.setUserpassword(userpassword);
            usersData uData = service.saveRecord(data);
            if (uData != null) {
                return "Login";
            } else {
                modelMap.addAttribute("message", "Email is already used please use a different email id");
                return "Register";
            }

        } else {
            modelMap.addAttribute("message", "Passwords do not match please verify");
            return "Register";
        }
    }

    @PostMapping("/userlogin")
    public String validateUserLogin(@RequestParam String email, @RequestParam String password, ModelMap modelMap) {
        usersData data = service.validateUserLogin(email, password);
        if (data != (null)) {
            session.setAttribute("sessionVar", data.getEmail());
            return "home";
        } else {
            modelMap.addAttribute("message", "Please check the credentials");
            return "Login";
        }
    }

    @PostMapping("/listProducts")
    public String callListProducts(ModelMap modelMap) {
        Object var = session.getAttribute("sessionVar");
        System.out.println(var);
        if (var != null) {
            return "listProductsPage";
        } else {
            modelMap.addAttribute("message", "session Expired Please Login to access the application");
            return "Login";
        }
    }

    @PostMapping("/viewProducts")
    public String callViewProducts(ModelMap modelMap) {
        Object var = session.getAttribute("sessionVar");
        if (var != null) {
            List<productsDataDTO> productsList = service.fetchAllProducts();
            modelMap.addAttribute("products", productsList);
            return "productsPage";
        } else {
            modelMap.addAttribute("message", "session Expired Please Login to access the application");
            return "Login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        System.out.println(session.getAttribute("sessionVar"));
        return "Login";
    }

    @PostMapping("/updateUser")
    public String callUpdateUser(ModelMap modelMap) {
        Object var = session.getAttribute("sessionVar");
        System.out.println(var);
        if (var != null) {
            modelMap.addAttribute("email", var);
            return "updateUserDetails";
        } else {
            modelMap.addAttribute("message", "session Expired Please Login to access the application");
            return "Login";
        }
    }

    @PostMapping("/update")
    public String updateUserData(@RequestParam String name, @RequestParam String phone,
            @RequestParam String currentPassword, @RequestParam String updatedPassword,
            @RequestParam String confirmUpdatedPassword, ModelMap modelMap) {
        Object var = session.getAttribute("sessionVar");
        usersData entity = new usersData();
        if (var != null) {
            entity.setEmail(var.toString());
            entity.setUsermobile(phone);
            entity.setUsername(name);
            if (updatedPassword.equals(confirmUpdatedPassword)) {
                int data = service.updateUser(entity, currentPassword, confirmUpdatedPassword);
                if (data != 0) {
                    modelMap.addAttribute("message", "User Data is Updated");
                    return "updateUserDetails";
                } else {
                    modelMap.addAttribute("message", "Data not found ");
                    return "updateUserDetails";
                }
            } else {
                modelMap.addAttribute("message", "updated passwords did not match");
                modelMap.addAttribute("email", var);
                return "updateUserDetails";
            }
        } else {
            modelMap.addAttribute("message", "session Expired Please Login to access the application");
            return "Login";
        }
    }

    @PostMapping("/saveProduct")
    public String listProductData(@RequestParam String productname,@RequestParam String productdescription,@RequestParam String productquantity,@RequestParam String productcost, ModelMap modelMap) {
        Object var = session.getAttribute("sessionVar");
        if (var != null) {
            productsDataDTO entity = new productsDataDTO();
            entity.setProductname(productname);
            entity.setProductdescription(productdescription);
            entity.setProductquantity(productquantity);
            entity.setProductcost(productcost);
            Response response = service.addProduct(entity);
            if(response!=null){
                modelMap.addAttribute("Message","Product is added to the Inventory");
                return "listProductsPage";
            }else{
                modelMap.addAttribute("message","Product is not added to the inventory please try again later");
                return null;
            }
        } else {
            modelMap.addAttribute("message", "session Expired Please Login to access the application");
            return "Login";
        }
    }

}
