
> 本项目使用了spring security5.x的新的oauth2包，参考：
[spring boot 2和spring cloud G版本Oauth2功能包依赖说明](http://tech.taiji.com.cn/community/blogShow?id=d135348b1b5e4d2eb9d144434538572f)

## 文档
开发者社区博客文档：[Spring Cloud基于Oauth2的认证和鉴权](http://tech.taiji.com.cn/community/blogShow?id=d94287c697d74425aeda2b2cc404acca)

## 运行
本项目支持okta和[TDF-oauth-server](http://tech.taiji.com.cn/community/blog/%E7%BB%9F%E4%B8%80%E8%AE%A4%E8%AF%81%E5%B9%B3%E5%8F%B0TDF-oauth-server)
* 如果使用TDF-oauth-server作为认证服务器请参考http://tech.taiji.com.cn/community/blog/%E7%BB%9F%E4%B8%80%E8%AE%A4%E8%AF%81%E5%B9%B3%E5%8F%B0TDF-oauth-server
* 如果使用okta，需要申请okta的开发者账号https://developer.okta.com/signup/

1. 运行eureka
2. 运行zuul
3. 运行resource
4. 运行openfeign

浏览器输入：http://localhost:8080/openfeign/dc  

浏览器显示字符串：Services: [zuul, resource, openfeign]

## 微服务体系
* eureka：是Eureka Server
* zuul: 是基于zuul的网关，代理resource和openfeign
* resource；普通微服务
* openfeign: 通过openfeign调用resource

## eureka
服务发现中心

## zuul
引用了spring-boot-starter-oauth2-client包，作为okta的client。自定义OAuth2TokenRelayFilter，做token relay操作

## resource
引用了pring-boot-starter-oauth2-resource-server作为资源服务器，配置：jwk-set-uri

## openfeign
引用了pring-boot-starter-oauth2-resource-server作为资源服务器。自定义了RequestInterceptor，做token relay操作
 
## 遇到的问题
1. zuul项目注释中的其他认证服务器同时存在时候，zuul项目启动会报错，目前只配置使用okta作为认证服务器

## 联系人
zhaozhao@mail.taiji.com.cn

## 参考
* https://github.com/spring-projects/spring-security/tree/master/samples/boot/oauth2login
* https://github.com/oktadeveloper/okta-spring-boot-oauth2-migration-example


