package com.nhnacademy.board.repository;

import com.nhnacademy.board.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> getPostsBy(Pageable pageable);
}
