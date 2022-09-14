package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}

// package hello.hellospring; 를 포함한 (실해하는 패키지)의 하위 패키지를 찾아서 스프링 빈으로 등록하나, 이 패키지와 동일하거나 하위 패키지가 아닌경우 기본적으로 컴포턴트 스캔에 대상이 되지않음.
// @ComponentScan 애노테이션이 있을경우 찾아서 들어오긴함.

