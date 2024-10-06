package com.ordersystem.ordersystem.repositories;

import com.ordersystem.ordersystem.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Article entities.
 * Provides CRUD operations for Article objects.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
}
