package website.skillforge.be.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class PayPalService {
    private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static void main(String[] args) {

        SpringApplication.run(PayPalService.class, args);
        LOGGER.log(Level.INFO, "SERVER RUNNING IN PORT:8080");
    }
}
