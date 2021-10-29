package com.springboot.st.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//스프링에서 인식 될 수 있도록 하는 클래스
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

}
