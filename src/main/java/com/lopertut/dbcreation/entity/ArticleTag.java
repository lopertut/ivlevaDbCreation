package com.lopertut.dbcreation.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "article_tag")
public class ArticleTag {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public Article getArticle() {
        return article;
    }

    public Tag getTag() {
        return tag;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
