package com.manhcode.service;

import com.manhcode.model.Category;
import com.manhcode.repository.dto.ICategoryPostsNumber;

import java.util.List;

public interface ICategoryService extends IGenericService<Category>{
    void deleteCategoryById(Long id);
    List<Category> findAll();
    List<ICategoryPostsNumber> getCategoryPostsNumber();
}
