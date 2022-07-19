package self.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EShopApplication {
        public static void main(String[] args){
            SpringApplication.run(EShopApplication.class,args);
        }
}
