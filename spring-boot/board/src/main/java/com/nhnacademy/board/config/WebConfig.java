package com.nhnacademy.board.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.board.interceptor.TimeCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.List;
import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Autowired
//    private NoticeInterceptor noticeInterceptor;
//    @Autowired
//    private QnaInterceptor qnaInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        // 공지사항 게시판 - 관리자만 작성 가능
//        registry.addInterceptor(noticeInterceptor)
//                .addPathPatterns("/notice/*")
//                .excludePathPatterns("/notice/list")
//                .excludePathPatterns("/notice/select");
//
//        // 모든 게시판 - 회원만 작성 가능
//        registry.addInterceptor(qnaInterceptor)
//                .addPathPatterns("/qna/*")
//                .excludePathPatterns("/qna/list");
//    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "loginForm");
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
        return cookieLocaleResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("locale");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(new TimeCheckInterceptor());
    }

}

