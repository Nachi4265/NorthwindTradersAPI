package com.pluralsight.NorthwindTradersAPI.Dao;

import com.pluralsight.NorthwindTradersAPI.Models.Product;

import java.sql.SQLException;
import java.util.List;


public interface ProductDao {

    List<Product> getALL() throws SQLException;

    Product getProductByID(int ID) throws SQLException;

    Product insert(Product product) throws SQLException;

    void update(int ID , Product product)throws SQLException;

    void delete(int ID)throws SQLException;

}
