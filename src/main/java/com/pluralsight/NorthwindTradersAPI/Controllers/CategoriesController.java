package com.pluralsight.NorthwindTradersAPI.Controllers;

import com.pluralsight.NorthwindTradersAPI.Dao.JdbcCategoryDao;
import com.pluralsight.NorthwindTradersAPI.Models.Category;
import com.pluralsight.NorthwindTradersAPI.Models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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




    @RequestMapping(path="/category", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category){

        try{
            return cDao.insert(category);
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return category;
    }


    @RequestMapping(path = "/category/{ID}" , method = RequestMethod.PUT)
    public void updateCategory(@PathVariable int ID , @RequestBody Category category){
        try{
            cDao.update(ID , category);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }


    @RequestMapping(path = "/category/{ID}" , method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable int ID){

        try{
            cDao.delete(ID);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
