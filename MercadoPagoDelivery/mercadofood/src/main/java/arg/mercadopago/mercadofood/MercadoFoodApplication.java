package arg.mercadopago.mercadofood;

import arg.mercadopago.mercadofood.service.SearchCancelToMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,SecurityAutoConfiguration.class})
@EnableAsync
public class MercadoFoodApplication {

	private static final Logger logger = LogManager.getLogger(MercadoFoodApplication.class);

	static SearchCancelToMP searchCancelToMP ;

	public MercadoFoodApplication(SearchCancelToMP searchCancelToMP) {
		this.searchCancelToMP = searchCancelToMP;
	}

	public static void main(String[] args) throws ClassNotFoundException {

		SpringApplication.run(MercadoFoodApplication.class, args);
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

				searchCancelToMP.start();


	}
}
