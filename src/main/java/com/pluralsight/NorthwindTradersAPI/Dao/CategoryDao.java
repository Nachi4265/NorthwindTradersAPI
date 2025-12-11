package com.pluralsight.NorthwindTradersAPI.Dao;

import com.pluralsight.NorthwindTradersAPI.Models.Category;
import com.pluralsight.NorthwindTradersAPI.Models.Product;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {

    List<Category> getALL() throws SQLException;

    Category getCategoryByID(int ID) throws SQLException;

    Category insert(Category category) throws SQLException;

    void update(int ID , Category category)throws SQLException;

    void delete(int ID)throws SQLException;


}
