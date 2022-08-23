package kt.com.springproject.common;

import kt.com.springproject.user.UserVO;

import java.sql.SQLException;

public class AfterThrowingAdvice {

    // after-throwing 비즈니스 메소드
    public void exceptionLog(Object exceptionObj) {
        System.out.println("[예외 처리] 비즈니스 메소드 수행 중 예외 발생");

        if (exceptionObj instanceof IllegalArgumentException) {
            System.out.println("seq에는 1 이상의 값만 설정할 수 있습니다.");
        } else if (exceptionObj instanceof SQLException) {
            System.out.println("SQL 구문에 오류가 있습니다.");
        } else {
            System.out.println("원인 미상의 문제가 발생했습니다.");
        }
    }
}
