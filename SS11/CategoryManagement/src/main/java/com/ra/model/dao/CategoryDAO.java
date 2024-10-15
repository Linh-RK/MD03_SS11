package com.ra.model.dao;

import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
    Boolean create(Category category);
    Category findById(int id);
    Boolean update(Category category);
    void delete(int id);
}
