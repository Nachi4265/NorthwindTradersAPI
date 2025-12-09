package com.pluralsight.NorthwindTradersAPI.Dao;


import com.pluralsight.NorthwindTradersAPI.Models.Category;
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
public class JdbcCategoryDao implements CategoryDao {

    DataSource dataSource;

    @Autowired
    public JdbcCategoryDao(DataSource dataSource){
        this.dataSource = dataSource;
    }


    @Override
    public List<Category> getALL() throws SQLException {

        List<Category> categories = new ArrayList<>();

        String query = "SELECT CategoryID, CategoryName  FROM categories";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet results = statement.executeQuery()){

            while(results.next()){

                int categoryID = results.getInt(1);
                String categoryName = results.getString(2);

                categories.add(new Category(categoryID,categoryName));
            }
        }

        return categories;

    }

    public Category getCategoryByID(int ID) throws SQLException {

        Category categoryByID = null;

        String idQuery = "SELECT CategoryID, CategoryName  FROM categories Where CategoryID = 3 ";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(idQuery);
            ResultSet results = statement.executeQuery()
        ){

            while(results.next()){
                int categoryID = results.getInt(1);
                String categoryName = results.getString(2);

                  categoryByID = new Category(categoryID,categoryName);
            }
        }

        return categoryByID;
    }

}
