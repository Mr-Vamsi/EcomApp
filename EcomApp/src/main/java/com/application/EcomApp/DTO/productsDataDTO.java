package com.application.EcomApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class productsDataDTO {

    private String productname;
    private String productdescription;
    private String productquantity;
    private String productcost;
}
