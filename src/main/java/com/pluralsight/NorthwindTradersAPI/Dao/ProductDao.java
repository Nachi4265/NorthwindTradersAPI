package com.pluralsight.NorthwindTradersAPI.Dao;

import com.pluralsight.NorthwindTradersAPI.Models.Product;

import java.sql.SQLException;
import java.util.List;


public interface ProductDao {

    List<Product> getALL() throws SQLException;


}
