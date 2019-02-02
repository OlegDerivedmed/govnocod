package ua.kiev.gossips;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2@EnableJpaRepositories
public class GossipsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GossipsApplication.class, args);
    }

}

