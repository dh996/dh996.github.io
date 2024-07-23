package kr.co.dh996.project11re;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("kr.co.dh996.project11re.mapper") //jpa리포지토리로 처리했기에 매퍼인터페이스 구현x
public class Project11reApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project11reApplication.class, args);
	}

}
