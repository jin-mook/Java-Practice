package hello.BoardWeb;

import hello.BoardWeb.TV.SamsungTV;
import hello.BoardWeb.TV.TV;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class BoardWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardWebApplication.class, args);
	}

}
