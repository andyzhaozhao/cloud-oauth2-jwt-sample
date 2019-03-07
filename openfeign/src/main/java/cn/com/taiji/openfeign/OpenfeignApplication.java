package cn.com.taiji.openfeign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class OpenfeignApplication {

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {

        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                JwtAuthenticationToken accessToken = (JwtAuthenticationToken) authentication;
                requestTemplate.header("Authorization", "Bearer " + accessToken.getToken().getTokenValue());
            }
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(OpenfeignApplication.class, args);
    }

}
