package cn.com.taiji.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;

@Component
public class OAuth2TokenRelayFilter extends ZuulFilter {

    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String TOKEN_TYPE = "TOKEN_TYPE";

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

//    @Autowired
//    private OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;

    @Override
    public boolean shouldFilter() {
        // 写法参考官网：https://docs.spring.io/spring-security/site/docs/5.1.4.RELEASE/reference/htmlsingle/#oauth2Client-authorized-client
        if (SecurityContextHolder.getContext().getAuthentication() instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken auth = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthorizedClient authorizedClient =
                    this.authorizedClientService.loadAuthorizedClient(
                            auth.getAuthorizedClientRegistrationId(), auth.getName());

            OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
            RequestContext ctx = RequestContext.getCurrentContext();
            ctx.set(ACCESS_TOKEN, accessToken.getTokenValue());
            ctx.set(TOKEN_TYPE, accessToken.getTokenType() == null ? "Bearer" : accessToken.getTokenType().getValue());

            return true;
        }
        return false;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader("authorization", ctx.get(TOKEN_TYPE) + " " + getAccessToken(ctx));
        return null;
    }

    private String getAccessToken(RequestContext ctx) {
        String value = (String) ctx.get(ACCESS_TOKEN);
        return value;
    }

}
