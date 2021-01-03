package loc.example.dev.boarddemo20210102.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@ComponentScan(basePackages = {"loc.example.dev.boarddemo20210102.entity"})
public class JpaConfig {

}
