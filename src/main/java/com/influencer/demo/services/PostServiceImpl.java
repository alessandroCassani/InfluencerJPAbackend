package com.influencer.demo.services;

import com.influencer.demo.entity.Account;
import com.influencer.demo.entity.Post;
import com.influencer.demo.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    /**
     * This method creates and saves to the database a new post associated with a specific account
     * @param post The Post to be created and saved
     * @param account The Account with which the post is associated
     */
    @Override
    @Transactional
    public void createPost(Post post, Account account) {
        post.setAccountPost(account);
        postRepository.save(post);
    }

    /**
     * This method updates the description of a post and saves the changes to the database
     * @param postId The ID of the Post to be edited description
     * @param new_description New description associated with the post
     * @throws EntityNotFoundException If the Post is not found in the database
     */
    @Override
    @Transactional
    public void updatePost(Long postId, String new_description) {
        Optional<Post> postOptional = postRepository.findById(postId);

        if(postOptional.isPresent()){
            Post post = postOptional.get();
            post.setDescription(new_description);
            postRepository.save(post);
        }else {
            throw new EntityNotFoundException("Post with ID " + postId + " not found");
        }
    }

    /**
     * This method deletes a post from database
     * @param post The Post to be deleted
     */
    @Override
    @Transactional
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    /**
     * This method returns a post based on its ID
     * @param postId The ID of the Post to retrieve
     * @return The Post with the specific ID
     * @throws EntityNotFoundException If the Post is not found in the database
     */
    @Override
    @Transactional
    public Post getPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);

        if(post.isPresent()){
            return post.get();
        }else {
            throw new EntityNotFoundException("Post with ID " + postId + " not found");
        }
    }

    /**
     * Get a list of posts associated with an account
     * This method returns a list of posts, saved to database, associated with a specific account
     * @param accountId The ID of the Account
     * @return A list of posts of a specific ID
     */
    @Override
    @Transactional
    public List<Post> getPostList(Long accountId) {
        return postRepository.findPostsById(accountId);
    }

    public int countElements(){
        return postRepository.countElements();
    }
}
