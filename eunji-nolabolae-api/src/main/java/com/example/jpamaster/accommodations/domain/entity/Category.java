package com.example.jpamaster.accommodations.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accommodation_category")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("카테고리 Seq")
    @Column(name = "category_seq")
    private Long seq;

    @Comment("카테고리 이름")
    @Column(name = "category_name")
    private String categoryName;

    @Comment("카테고리 부모 seq")
    @Column(name = "category_parent_seq")
    private Long parentSeq;
}
