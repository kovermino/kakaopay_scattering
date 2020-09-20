package com.jongwoo.kakao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.jongwoo.kakao.repository")
public class KakaoPayAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakaoPayAssignmentApplication.class, args);
	}

}
