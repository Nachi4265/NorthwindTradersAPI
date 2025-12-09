package com.pluralsight.NorthwindTradersAPI.Controllers;

import com.pluralsight.NorthwindTradersAPI.Dao.JdbcCategoryDao;
import com.pluralsight.NorthwindTradersAPI.Models.Category;
import com.pluralsight.NorthwindTradersAPI.Models.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoriesController {

    JdbcCategoryDao cDao;

    public CategoriesController(JdbcCategoryDao cDao){
        this.cDao = cDao;;
    }

    @RequestMapping(path="/category", method = RequestMethod.GET)
    public List<Category>getCategories(){
        try{

            return cDao.getALL();

        }catch (SQLException e ){

            System.out.println("Error " + e.getMessage() );
        }

        return null;
    }

    @RequestMapping(path= "/category/{ID}", method = RequestMethod.GET)
    public Category getCategoryID(int ID){
        try{

             return cDao.getCategoryByID(ID);

        }catch (SQLException e ){
            System.out.println("Error " + e.getMessage() );
        }

        return null;
    }


}
