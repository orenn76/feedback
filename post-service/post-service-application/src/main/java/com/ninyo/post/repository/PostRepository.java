package com.ninyo.post.repository;

import com.ninyo.crudcore.repository.BaseRepository;
import com.ninyo.post.model.Post;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends BaseRepository<String, Post> {
}
