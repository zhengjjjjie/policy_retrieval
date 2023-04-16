package stackoverflow.project.policyretrieval.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.*;
import stackoverflow.project.policyretrieval.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/api/policy/search**",
                        "/api/auth/**",
                        "/api/counter/**",
                        "/js/**",
                        "/css/**",
                        "/images/**");
    }

    //设置跨域问题 这里最好是在使用网关时配置 CorsWebFilter    CORS
    public void addCorsMappings(CorsRegistry registry) {
        //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
        CorsRegistration corsRegistration =
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowCredentials(true)
                        .allowedMethods("GET", "POST", "DELETE", "PUT")
//                        .exposedHeaders(HttpHeaders.CONTENT_DISPOSITION)
                        .maxAge(3600);

        //允许哪些ip或域名可以跨域访问 *表示允许所以,不要写*，否则cookie就无法使用了(这里建议不要用 * ，*表示所以请求都允许跨域，安全部门请喝茶 )
//        corsRegistration.allowedOriginPatterns("*");
//        corsRegistration.allowedOriginPatterns("http://localhost:8080");
        //是否发送Cookie信息
//        corsRegistration.allowCredentials(true);

        //允许的请求方式
//        corsRegistration.allowedMethods("GET", "POST", "DELETE", "PUT");
//
//        //表示访问请求中允许携带哪些Header信息，如：Accept、Accept-Language、Content-Language、Content-Type
//        corsRegistration.allowedHeaders("*");
//
//        //暴露哪些头部信息(因为跨域访问默认不能获取全部头部信息)
//        corsRegistration.exposedHeaders(HttpHeaders.CONTENT_DISPOSITION);
//
//        //设置等待时间
//        corsRegistration.maxAge(3600);
    }
}
