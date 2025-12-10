package com.pluralsight.NorthwindTradersAPI.Dao;

import com.pluralsight.NorthwindTradersAPI.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    DataSource dataSource;

    @Autowired
    public JdbcProductDao(DataSource dataSource){
        this.dataSource =dataSource;
    }



    public List<Product> getALL() throws SQLException {

        List<Product> products = new ArrayList<>();

        String query = "SELECT ProductID,ProductName,CategoryID,UnitPrice FROM products;";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet results = statement.executeQuery()){

            while(results.next()){
                int productid = results.getInt(1);
                String productname = results.getString(2);
                int categoryID = results.getInt(3);
                double price = results.getDouble(4);
                products.add(new Product(productid, productname, categoryID, price));
            }
        }
        return products;
    }

    public Product getProductByID(int ID) throws SQLException {

        Product productByID = null;

        String query = "SELECT ProductID,ProductName,CategoryID,UnitPrice FROM products;";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet results = statement.executeQuery()){

            while(results.next()){
                int productid = results.getInt(1);
                String productname = results.getString(2);
                int categoryID = results.getInt(3);
                double price = results.getDouble(4);

                productByID = new Product(productid,productname,categoryID,price);
            }
        }

        return productByID;
    }

    @Override
    public Product insert(Product product) throws SQLException {
        return null;
    }

    @Override
    public void update(int ID, Product product) throws SQLException {

    }

    @Override
    public void delete(int ID) throws SQLException {

    }



}
