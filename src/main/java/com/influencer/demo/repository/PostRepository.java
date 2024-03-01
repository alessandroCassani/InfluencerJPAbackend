package com.influencer.demo.repository;

import com.influencer.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The {@code PostRepository} interface provides methods for interacting with the {@link Post} entity in the database.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * SQL query to retrieve a list of posts by a specific account.
     */
    String getPost = "SELECT *\n" +
            "FROM post p\n" +
            "WHERE p.account_id = :accountID";

    /**
     * SQL query to count the total number of posts in the database.
     */
    String count = "SELECT COUNT(*)\n" +
            "FROM post";

    /**
     * Retrieves a list of posts associated with a specific account.
     *
     * @param accountID The unique identifier of the account.
     * @return A list of posts associated with the specified account.
     */
    @Query(value = getPost, nativeQuery = true)
    List<Post> findPostsById(@Param("accountID") Long accountID);

    /**
     * Counts the total number of posts in the database.
     *
     * @return The total number of posts.
     */
    @Query(value = count, nativeQuery = true)
    int countElements();
}
