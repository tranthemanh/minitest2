package com.manhcode.repository;

import com.manhcode.model.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPostsRepository extends PagingAndSortingRepository<Posts, Integer> {
    Page<Posts> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
