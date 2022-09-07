package tv.cybergames;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableFeignClients
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CyberGamesApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(CyberGamesApplication.class, args);
        log.info("Toggle logger DEBUG mode to inspect Spring Boot beans list");

        final String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (final String beanName : beanNames) {
            log.debug("BeanName {}", beanName);
        }

        log.info("*************************************");
        log.info("*   CyberGamesApplication started   *");
        log.info("*************************************");
    }

}
