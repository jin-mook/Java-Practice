package com.example.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CallTypeTest {

    @DisplayName("원시타입 호출 하는 테스트")
    @Test
    void callByValue() {
        // given
        String a = "test";
        // when
        modify(a);
        // then
        System.out.println(a);
    }

    public void modify(String a) {
        a = "test modify";
    }


    @DisplayName("참조 타입을 호출하는 테스트")
    @Test
    void reference() {
        // given
        User user = new User(10);
        // when
        modify(user);
        // then
        System.out.println(user.age);
    }

    public void modify(User user) {
        user.age += 1;
    }

    public static class User {
        public int age;

        public User(int age) {
            this.age = age;
        }
    }
}
