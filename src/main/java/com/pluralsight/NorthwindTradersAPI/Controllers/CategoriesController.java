package com.pluralsight.NorthwindTradersAPI.Controllers;

import com.pluralsight.NorthwindTradersAPI.Models.Category;
import com.pluralsight.NorthwindTradersAPI.Models.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

public class CategoriesController {



    @RestController
    public class CategoriesController {

        private List<Category> categories;

        public CategoriesController(){

            this.categories = new ArrayList<>();
            
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
