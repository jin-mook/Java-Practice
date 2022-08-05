package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false)  // 기본값이 true 이므로 TestBean이 빈이 아니기 때문에 기본값으로 두면 에러가 발생한다.
        public void setBean1(Member member) {
            System.out.println("member = " + member);
        }

        @Autowired
        public void setBean2(@Nullable Member member) {
            System.out.println("member = " + member);
        }

        @Autowired
        public void setBean3(Optional<Member> member) {
            System.out.println("member = " + member);
        }
    }
}
