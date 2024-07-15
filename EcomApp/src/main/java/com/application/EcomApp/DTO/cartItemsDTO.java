package com.application.EcomApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class cartItemsDTO {

    private String productname;
    private String productquantity;
    private String productcost;
    private String useremail;
}
