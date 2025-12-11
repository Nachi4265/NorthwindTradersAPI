package com.pluralsight.NorthwindTradersAPI.Controllers;

import com.pluralsight.NorthwindTradersAPI.Dao.JdbcProductDao;
import com.pluralsight.NorthwindTradersAPI.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//http://localhost:8080/products should return a list of all products
//http://localhost:8080/products/5 should return a specific product

@RestController
public class ProductController {

    JdbcProductDao pDao;

    @Autowired
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


    @RequestMapping(path="/products", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product){

        try{
            return pDao.insert(product);
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return product;
    }


    @RequestMapping(path = "/products/{ID}" , method = RequestMethod.PUT)
    public void updateProduct(@PathVariable int ID , @RequestBody Product product){
        try{
            pDao.update(ID,product);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }


    @RequestMapping(path = "/products/{ID}" , method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int ID){

        try{
            pDao.delete(ID);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }





}
