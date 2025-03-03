package com.lopertut.dbcreation.repository;

import com.lopertut.dbcreation.entity.ArticleFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleFavoriteRepository extends JpaRepository<ArticleFavorite, Long> {
}
