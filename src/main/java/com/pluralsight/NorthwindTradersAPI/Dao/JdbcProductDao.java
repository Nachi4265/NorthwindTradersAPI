package com.pluralsight.NorthwindTradersAPI.Dao;

import com.pluralsight.NorthwindTradersAPI.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    DataSource dataSource;

    @Autowired
    public JdbcProductDao(DataSource dataSource){
        this.dataSource =dataSource;
    }


    @Override
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

//                Product product = new Product();
//                product.setProductID(productid);
//                product.getProductName();
            }
        }
        return products;
    }

    @Override
    public Product getProductByID(int ID) throws SQLException {

        Product productByID = null;

        String query = "SELECT ProductID,ProductName,CategoryID,UnitPrice FROM products Where ProductID = ? ;";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);


        ){
            statement.setInt(1 ,ID);

            try( ResultSet results = statement.executeQuery()

            ){
                if(results.next()){
                int productid = results.getInt(1);
                String productname = results.getString(2);
                int categoryID = results.getInt(3);
                double price = results.getDouble(4);

                 return productByID = new Product(productid,productname,categoryID,price);
                }else{
                    return null;
                }
            }
        }
    }

    @Override
    public Product insert(Product product) throws SQLException {

        String query = """
                INSERT INTO products ( ProductName, CategoryID, UnitPrice )
                VALUES ( ?, ?, ? );
                """;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getCategoryID());
            statement.setDouble(3, product.getUnitPrice());

            int rows = statement.executeUpdate();

            try(ResultSet keys = statement.getGeneratedKeys()){
                if(keys.next()){
                    int productId =  keys.getInt(1);
                    product.setProductID(productId);
                    return product;
                }
            }
        }

        return null;


    }

    @Override
    public void update(int ID, Product product) throws SQLException {
        String query = """
                UPDATE products
                SET
                ProductName = ?,
                CategoryId = ?,
                UnitPrice = ?
                WHERE ProductId = ?;""";


        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getCategoryID());
            statement.setDouble(3, product.getUnitPrice());
            statement.setInt(4, ID);

            int rows = statement.executeUpdate();

        }
    }

    @Override
    public void delete(int ID) throws SQLException {

        String query = """
                DELETE FROM products
                WHERE productId = ?;""";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, ID);

            int rows = statement.executeUpdate();

        }
    }

}
