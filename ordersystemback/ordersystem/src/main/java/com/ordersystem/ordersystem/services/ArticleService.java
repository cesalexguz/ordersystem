package com.ordersystem.ordersystem.services;

import com.ordersystem.ordersystem.entities.Article;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing Article entity.
 * Provides methods for saving, updating, retrieving, and deleting articles.
 */
public interface ArticleService {

    /**
     * Saves a new article.
     * @param article the article to save
     * @return the saved article
     */
    Article saveArticle(Article article);

    /**
     * Updates an existing article.
     * @param article the article to update
     * @return the updated article
     */
    Article updateArticle(Article article);

    /**
     * Retrieves all articles.
     * @return a list of all articles
     */
    List<Article> getArticles();

    /**
     * Retrieves an article by its code.
     * @param code the code of the article to retrieve
     * @return an Optional containing the article if found, or empty if not found
     */
    Optional<Article> getArticle(String code);

    /**
     * Deletes an article by its code.
     * @param code the code of the article to delete
     */
    void deleteArticle(String code);

    /**
     * Retrieves all article codes.
     * @return a list of all article codes
     */
    List<String> getAllArticleCodes();
}
