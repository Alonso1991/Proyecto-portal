package cl.alsea.portal.propinas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class PortalPropinasServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalPropinasServiceApplication.class, args);
    }

}
