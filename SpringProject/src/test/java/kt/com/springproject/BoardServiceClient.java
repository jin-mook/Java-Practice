package kt.com.springproject;

import kt.com.springproject.board.BoardServiceImpl;
import kt.com.springproject.board.BoardVO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class BoardServiceClient {

    public static void main(String[] args) {

        // 1. 스프링 컨테이너를 구동한다.
        GenericXmlApplicationContext container = new GenericXmlApplicationContext("business-layer.xml");

//        AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. 테스트할 비즈니스 객체를 Lookup 한다.
        BoardServiceImpl boardService = container.getBean(BoardServiceImpl.class);


        // 3. Lookup한 객체를 테스트한다.
        BoardVO vo = new BoardVO();
        vo.setTitle("스프링 테스트");
        vo.setWriter("테스터");
        vo.setContent("스프링 테스트 중입니다...");
        boardService.insertBoard(vo);

        List<BoardVO> boardList = boardService.getBoardList(vo);
        for (BoardVO board : boardList) {
            System.out.println("--->" + board.toString());
        }

        // 4. 컨테이너를 종료한다.
        container.close();
    }

    @ComponentScan
    static class AppConfig {
    }
}
