package com.example.stock.join;

import com.example.stock.domain.Article;
import com.example.stock.domain.ArticleRepository;
import com.example.stock.domain.User;
import com.example.stock.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Transactional
@SpringBootTest
public class FetchJoin {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    private long userId;

    @BeforeEach
    void setUp() {
        User user = new User("test1");
        User savedUser = userRepository.save(user);
        this.userId = savedUser.getId();

        Article article1 = new Article("title1", "content1", savedUser);
        Article article2 = new Article("title2", "content2", savedUser);
        Article article3 = new Article("title3", "content3", savedUser);
        Article article4 = new Article("title4", "content4", savedUser);
        articleRepository.saveAll(List.of(article1, article2, article3, article4));

        User user2 = new User("test2");
        User savedUser2 = userRepository.save(user2);
        this.userId = savedUser.getId();

        Article article21 = new Article("title1", "content1", savedUser2);
        Article article22 = new Article("title2", "content2", savedUser2);
        Article article23 = new Article("title3", "content3", savedUser2);
        Article article24 = new Article("title4", "content4", savedUser2);
        articleRepository.saveAll(List.of(article21, article22, article23, article24));

        User user3 = new User("test2");
        User savedUser3 = userRepository.save(user3);
        this.userId = savedUser.getId();

        Article article31 = new Article("title1", "content1", savedUser3);
        Article article32 = new Article("title2", "content2", savedUser3);
        Article article33 = new Article("title3", "content3", savedUser3);
        Article article34 = new Article("title4", "content4", savedUser3);
        articleRepository.saveAll(List.of(article31, article32, article33, article34));
    }

    @Test
    @DisplayName("Eager type은 User를 단일 조회할 때 join문이 날아간다.")
    void userSingleFindTest() {
        System.out.println("== start ==");
        List<User> user = userRepository.findAll();
        System.out.println("== end ==");
//        System.out.println(user.getName());
    }

    @Test
    @DisplayName("fetch join을 paging처리에서 사용해도 N+1문제가 발생한다.")
    void pagingFetchJoinTest() {
        System.out.println("== start ==");
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<User> users = userRepository.findAllPage(pageRequest);
        System.out.println("== find all ==");
        for (User user : users) {
            System.out.println(user.getArticles().size());
        }
    }

    @Test
    @DisplayName("fetch join을 paging처리에서 사용해도 N+1문제가 발생한다.")
    void pagingFetchJoinTest2() {
        System.out.println("== start ==");
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Article> articles = articleRepository.findAllPage(pageRequest);
        System.out.println("== find all ==");
        for (Article article : articles) {
            System.out.println(article.getUser());
        }
    }

    @Test
    @DisplayName("fetch join을 paging처리에서 사용해도 N+1문제가 발생한다.")
    void pagingFetchJoinTest3() {
        System.out.println("== start ==");
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<User> users = userRepository.findAll(pageRequest);
        System.out.println("== find all ==");
//        System.out.println(users.getTotalPages());
        for (User user : users) {
            System.out.println(user.getArticles().size());
        }
    }
}
