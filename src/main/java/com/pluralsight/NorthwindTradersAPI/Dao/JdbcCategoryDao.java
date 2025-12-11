package com.pluralsight.NorthwindTradersAPI.Dao;


import com.pluralsight.NorthwindTradersAPI.Models.Category;
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


    @Override
    public Category getCategoryByID(int ID) throws SQLException {

        Category categoryByID = null;

        String idQuery = "SELECT CategoryID, CategoryName  FROM categories Where CategoryID = ? ";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(idQuery);

        ){

            statement.setInt(1,ID);

            try(ResultSet results = statement.executeQuery()){

                if(results.next()){

                    int categoryID = results.getInt(1);
                    String categoryName = results.getString(2);

                    categoryByID = new Category(categoryID,categoryName);
                }
            }
        }

        return categoryByID;
    }


    @Override
    public Category insert(Category category) throws SQLException {

        String query = "INSERT INTO categories (CategoryID , CategoryName) Values ( ? , ? )";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);


        ){
            statement.setInt(1,category.getCategoryID());
            statement.setString(2,category.getCategoryName());

            int rows = statement.executeUpdate();

            try(ResultSet keys = statement.getGeneratedKeys()){

                if(keys.next()){
                    int categoryID = keys.getInt(1);
                    category.setCategoryID(categoryID);
                    return category;
                }
            }
        }
        return null;
    }

    @Override
    public void update(int ID, Category category) throws SQLException {

        String query = "UPDATE categories SET categoryName = ?  WHERE categoryID = ? ;";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
        ){
            statement.setString(1 , category.getCategoryName());
            statement.setInt(2 , category.getCategoryID());

            int rows  = statement.executeUpdate();
        }
    }

    @Override
    public void delete(int ID) throws SQLException {

        String query = """
                DELETE FROM categories
                WHERE categoryID = ? ;""";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, ID);

            int rows = statement.executeUpdate();

        }
    }



}
