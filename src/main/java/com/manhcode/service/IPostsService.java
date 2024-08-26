package com.manhcode.service;

import com.manhcode.model.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostsService extends IGenericService<Posts>{
    Page<Posts> findAll(Pageable pageable);
    Page<Posts> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
