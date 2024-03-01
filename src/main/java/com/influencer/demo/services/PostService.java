package com.influencer.demo.services;

import com.influencer.demo.entity.Account;
import com.influencer.demo.entity.Post;

import java.util.List;
/**
 * Service interface for managing operations related to Posts.
 */
public interface PostService {

    /**
     * Creates a new post and associates it with the specified account.
     *
     * @param post    The post to be created.
     * @param account The account to which the post will be associated.
     */
    void createPost(Post post, Account account);

    /**
     * Updates the description of an existing post.
     *
     * @param postId      The ID of the post to be updated.
     * @param description The new description for the post.
     */
    void updatePost(Long postId, String description);

    /**
     * Deletes a post.
     *
     * @param post The post to be deleted.
     */
    void deletePost(Post post);

    /**
     * Retrieves a post by its ID.
     *
     * @param postId The ID of the post to retrieve.
     * @return The retrieved post.
     */
    Post getPost(Long postId);

    /**
     * Retrieves a list of posts associated with a specific account.
     *
     * @param accountId The ID of the account for which to retrieve posts.
     * @return A list of posts associated with the specified account.
     */
    List<Post> getPostList(Long accountId);

    /**
     * Counts the total number of posts.
     *
     * @return The total number of posts.
     */
    int countElements();
}
