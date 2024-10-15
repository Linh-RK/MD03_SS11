package com.ra.model.dao;

import com.ra.database.ConnectionDB;
import com.ra.model.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public List<Category> findAll() {
        Connection connection = null;
        List<Category> categories = new ArrayList<>();
        try {
            connection = ConnectionDB.openConnection();
            String sql = "SELECT * FROM categories";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("id"));
                category.setCategoryName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                category.setStatus(resultSet.getBoolean("status"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return categories;
    }


    @Override
    public Boolean create(Category category) {
        Connection connection = null;

        try {
            connection = ConnectionDB.openConnection();
            CallableStatement statement = connection.prepareCall("{call proc_add_cate(?,?,?)}");
            statement.setString(1,category.getCategoryName());
            statement.setString(2, category.getDescription());
            statement.setBoolean(3,category.isStatus());
            if(statement.executeUpdate() > 0){
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public Boolean update(Category category) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
