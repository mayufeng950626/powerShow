package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class Swagger2 {

//    http://localhost:8088/swagger-ui/     原路径
//    http://localhost:8088/doc.html     原路径

    // 配置swagger2核心配置 docket
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)  // 指定api类型为swagger2
                    .apiInfo(apiInfo())                 // 用于定义api文档汇总信息
                    .select()
                    .apis(RequestHandlerSelectors
                            .basePackage("com.mayufeng.controller"))   // 指定controller包
                    .paths(PathSelectors.any())         // 所有controller
                    .build();
    }

    private ApiInfo apiInfo() {
//        Contact contact =
        return new ApiInfo(
                "个人开发项目api","专为马裕锋发提供的api文档","1.0.0","https://www.mayufeng.ltd",
                new Contact("mayufeng","https://www.mayufeng.ltd",
                        "2813299827.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

    }
//private ApiInfo apiInfo() {
//    return new ApiInfoBuilder()
//            //页面标题
//            .title("尼玛炸了")
//            //设置作者联系方式,可有可无
//            .contact(new Contact("chaoge", "https://my.csdn.net/xiaochaogge", "z28126308@163.com"))
//            //版本号
//            .version("1.0")
//            //描述
//            .description("API 描述")
//            .build();
//
//}

}
