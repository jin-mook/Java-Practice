package kt.com.springproject;

import kt.com.springproject.board.BoardServiceImpl;
import kt.com.springproject.board.BoardVO;
import kt.com.springproject.user.UserServiceImpl;
import kt.com.springproject.user.UserVO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class UserServiceClient {

    public static void main(String[] args) {
        // 1. 스프링 컨테이너를 구동한다.
        GenericXmlApplicationContext container = new GenericXmlApplicationContext("business-layer.xml");

//        AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. 테스트할 비즈니스 객체를 Lookup 한다.
        UserServiceImpl userService = container.getBean(UserServiceImpl.class);


        // 3. Lookup한 객체를 테스트한다.
        UserVO vo = new UserVO();
        vo.setId("test");
        vo.setPassword("test");
        UserVO user = userService.getUser(vo);
        if (user != null) {
            System.out.println(user.getName() + "님 로그인 환영");
        } else {
            System.out.println("로그인 실패");
        }

        // 4. 컨테이너를 종료한다.
        container.close();
    }

    @ComponentScan
    static class AppConfig {

    }

}
