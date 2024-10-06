package com.ordersystem.ordersystem.controllers;

import com.ordersystem.ordersystem.entities.Article;
import com.ordersystem.ordersystem.services.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Article entity.
 * Provides endpoints for creating, updating, retrieving, and deleting articles.
 */
@RestController
@RequestMapping("/article")
@CrossOrigin("http://localhost:4200/")
public class ArticleController {

    /**
     * Service implementation for managing Article entities.
     */
    @Autowired
    ArticleServiceImpl articleServiceImpl;

    /**
     * Endpoint for saving a new article.
     * @param article the article to save
     * @return the saved article or an error message
     */
    @PostMapping
    public ResponseEntity<?> saveArticle(@RequestBody Article article) {
        try {
            Article savedArticle = articleServiceImpl.saveArticle(article);
            return new ResponseEntity<>(savedArticle, HttpStatus.OK);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Data integrity violation: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (TransactionSystemException e) {
            return new ResponseEntity<>("Transaction system exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for updating an existing article.
     * @param article the article to update
     * @return the updated article or an error message
     */
    @PutMapping
    public ResponseEntity<?> updateArticle(@RequestBody Article article) {
        try {
            Article updatedArticle = articleServiceImpl.updateArticle(article);
            return new ResponseEntity<>(updatedArticle, HttpStatus.OK);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Data integrity violation: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (TransactionSystemException e) {
            return new ResponseEntity<>("Transaction system exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for retrieving all articles.
     * @return a list of all articles
     */
    @GetMapping
    public ResponseEntity<List<Article>> getArticles() {
        return new ResponseEntity<>(articleServiceImpl.getArticles(), HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving an article by its code.
     * @param code the code of the article to retrieve
     * @return the article if found, or a 404 status if not found
     */
    @GetMapping("/{code}")
    public ResponseEntity<Article> getArticleById(@PathVariable String code) {
        Optional<Article> article = articleServiceImpl.getArticle(code);
        if(article.isPresent()) {
            return new ResponseEntity<>(article.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint for deleting an article by its code.
     * @param code the code of the article to delete
     * @return a 200 status if the article was deleted, or a 404 status if not found
     */
    @DeleteMapping("/{code}")
    public ResponseEntity<Article> deleteArticle(@PathVariable String code) {
        Optional<Article> article = articleServiceImpl.getArticle(code);
        if(article.isPresent()) {
            articleServiceImpl.deleteArticle(article.get().getCode());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
