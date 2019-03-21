//package cn.com.taiji.zuul;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * 此类与默认配置相同，不加此类也可以演示
// */
//@EnableWebSecurity(debug = true)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // @formatter:off
//        http.authorizeRequests()
//                //.antMatchers("/message/**").hasAuthority("SCOPE_message:read")
//                .anyRequest().authenticated();
//        http.oauth2Login();
////        http.oauth2Client();
//        // @formatter:on
//    }
//}
