package com.dyz.skeleton;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@MapperScan("com.dyz.skeleton.infrastructure.db.mapper")
@SpringBootApplication
public class SpringbootSkeletonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSkeletonApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
