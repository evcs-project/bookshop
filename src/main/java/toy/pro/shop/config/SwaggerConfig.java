package toy.pro.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private String filterApiPath = "toy.pro.shop.web.controller";
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(filterApiPath))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(apiInfo("도서 책 쇼핑 프로젝트 API 문서", "1.0.0"));
    }

    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "도서 책 쇼핑 프로젝트 api",
                version,
                null,
                null,
                "라이센스 : elct",
                "https://www.notion.so/0ab318c646314aa0beefe9ff2e2785c2?v=f953a0c701114c9ca53d52d654f807bc",
                new ArrayList<>()
        );
    }
}
