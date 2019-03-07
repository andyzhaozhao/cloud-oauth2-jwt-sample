//package cn.com.taiji.resource;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity(debug = true)
//public class OAuth2ResourceServerSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // @formatter:off
//        http.authorizeRequests()
//                //.antMatchers("/message/**").hasAuthority("SCOPE_message:read")
//                .anyRequest().authenticated()
//                .and()
//                .oauth2ResourceServer()
//                .jwt().jwkSetUri("https://dev-269876.okta.com/oauth2/v1/keys");
//        // @formatter:on
//    }
//}
