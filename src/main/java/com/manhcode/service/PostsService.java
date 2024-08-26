package com.manhcode.service;

import com.manhcode.model.Posts;
import com.manhcode.repository.IPostsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostsService implements IPostsService{
    private final IPostsRepository postsRepository;

    public PostsService(IPostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }


    @Override
    public Page<Posts> findAll(Pageable pageable) {
        return postsRepository.findAll(pageable);
    }

    @Override
    public Page<Posts> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        if (name == null || name.isEmpty()) {
            return findAll(pageable);
        }
        return postsRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Posts findById(int id) {
        return postsRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Posts posts) {
        postsRepository.save(posts);
    }

    @Override
    public void delete(Posts posts) {
        postsRepository.delete(posts);
    }
}
