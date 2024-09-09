package com.example.stock.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.Set;

import static java.util.Collections.emptySet;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String name;

    @BatchSize(size = 2)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Article> articles = emptySet();

    public User(String name) {
        this.name = name;
    }
}
