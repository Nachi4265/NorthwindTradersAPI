package com.pluralsight.NorthwindTradersAPI.Controllers;

import com.pluralsight.NorthwindTradersAPI.Dao.JdbcProductDao;
import com.pluralsight.NorthwindTradersAPI.Models.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//http://localhost:8080/products should return a list of all products
//http://localhost:8080/products/5 should return a specific product


@RestController
public class ProductController {

    JdbcProductDao pDao;

    public ProductController(JdbcProductDao pDao){

        this.pDao = pDao;

    }

    @RequestMapping(path="/products", method = RequestMethod.GET)
    public List<Product> getProdducts(){
        try{

           return pDao.getALL();

        }catch (SQLException e ){

            System.out.println("Error " + e.getMessage());

        }

       return null;
    }

    @RequestMapping(path="/products/{ID}",method = RequestMethod.GET)
    public Product getProduct(int ID){

        try{

           return pDao.getProductByID(ID);

        }catch (SQLException e ){
            System.out.println("Error " + e.getMessage() );
        }

        return null;
    }


}
