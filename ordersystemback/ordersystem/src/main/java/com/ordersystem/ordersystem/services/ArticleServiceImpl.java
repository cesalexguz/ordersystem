package com.ordersystem.ordersystem.services;

import com.ordersystem.ordersystem.entities.Article;
import com.ordersystem.ordersystem.repositories.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ArticleService interface.
 * Provides methods for managing Article entity.
 */
@Service
public class ArticleServiceImpl implements ArticleService{

    /**
     * Repository for performing CRUD operations on Article entities.
     */
    @Autowired
    ArticleRepository articleRepository;

    @Transactional
    @Override
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    @Transactional
    @Override
    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> getArticle(String code) {
        return articleRepository.findById(code);
    }

    @Transactional
    @Override
    public void deleteArticle(String code) {
        articleRepository.deleteById(code);
    }

    @Override
    public List<String> getAllArticleCodes() {
        List<String> articleCodes = new ArrayList<>();
        List<Article> articles = articleRepository.findAll();
        for(Article article : articles) {
            articleCodes.add(article.getCode());
        }
        return articleCodes;
    }
}
