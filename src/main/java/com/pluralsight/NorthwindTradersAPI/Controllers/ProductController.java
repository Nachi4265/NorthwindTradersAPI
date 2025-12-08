package com.pluralsight.NorthwindTradersAPI.Controllers;

import com.pluralsight.NorthwindTradersAPI.Models.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//http://localhost:8080/products should return a list of all products
//http://localhost:8080/products/5 should return a specific product


@RestController
public class ProductController {

    private List<Product>products;

    public ProductController(){

        List<Product> products = new ArrayList<>();

        products.add(new Product(1,"apple", 5, 1.99));
        products.add(new Product(2,"bannana", 5, 2.99));
        products.add(new Product(3,"orange", 5, 3.99));
        products.add(new Product(4,"graps", 5, 4.99));
    }

    @RequestMapping(path="/products", method = RequestMethod.GET)
    public List<Product>getProdducts(){
        return products;
    }

    @RequestMapping(path="/products/{ID}",method = RequestMethod.GET)
    public Product getProduct(int ID){
        for(Product p : products){
            if (p.getProductID() == ID){
                return p;
            }
        }
        return null;
    }


}
