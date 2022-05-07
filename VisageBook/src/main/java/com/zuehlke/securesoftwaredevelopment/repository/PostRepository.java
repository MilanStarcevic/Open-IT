package com.zuehlke.securesoftwaredevelopment.repository;

import com.zuehlke.securesoftwaredevelopment.config.AuditLogger;
import com.zuehlke.securesoftwaredevelopment.domain.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {

    private static final Logger LOG = LoggerFactory.getLogger(PostRepository.class);
    private static final AuditLogger auditLogger = AuditLogger.getAuditLogger(PersonRepository.class);

    private static final String POSTS_TABLE = "posts";
    private DataSource dataSource;

    public PostRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Post findById(String id) {
        String sqlQuery = "SELECT id, picture, text FROM posts WHERE id=" + id;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sqlQuery)) {
            if (rs.next()) {
                return createPost(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param searchQuery picture or text
     * @return list of posts or empty in case of exception
     */
    public List<Post> search(String searchQuery) throws SQLException {
        List<Post> posts = new ArrayList<>();
        String sqlQuery =
                "SELECT id, picture, text FROM posts WHERE UPPER(picture) LIKE UPPER('%" + searchQuery + "%')" +
                        "OR UPPER(text) LIKE UPPER('%" + searchQuery + "%')";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sqlQuery)) {
            while (rs.next()) {
                posts.add(createPost(rs));
            }
        }
        return posts;
    }

    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        String sqlQuery = "SELECT id, picture, text FROM " + POSTS_TABLE;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sqlQuery)) {
            while (rs.next()) {
                posts.add(createPost(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    private Post createPost(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String picture = rs.getString(2);
        String text = rs.getString(3);
        return new Post(id, picture, text);
    }
}
