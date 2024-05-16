// package com.example.configuration;

// import java.util.List;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.method.support.HandlerMethodArgumentResolver;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class EmployeeConfiguration implements WebMvcConfigurer {

//     @Override
//     public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//         PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
//         resolver.setOneIndexedParameters(true);
//         resolver.setMaxPageSize(50);
//         argumentResolvers.add(resolver);
//     }

// }