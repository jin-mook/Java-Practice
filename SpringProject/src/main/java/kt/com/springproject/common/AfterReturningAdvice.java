package kt.com.springproject.common;

import kt.com.springproject.user.UserVO;

public class AfterReturningAdvice {

    // after-returning 비즈니스 메소드
    public void afterLog(Object returnObj) {
        System.out.println("[사후 처리] 비즈니스 메소드 리턴값 : " + returnObj.toString());

        // 비즈니스 메소드가 리턴한 데이터가 UserVO 타입의 데이터라면
        if (returnObj instanceof UserVO) {
            UserVO user = (UserVO) returnObj;
            // UserVO 객체의 권한이 ADMIN이냐?
            if (user.getRole().equals("Admin")) {
                System.out.println(user.getName() + "님 관리자 페이지로 이동합니다.");
            }
        }
    }
}
