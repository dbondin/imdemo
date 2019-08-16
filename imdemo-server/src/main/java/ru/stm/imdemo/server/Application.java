package ru.stm.imdemo.server;
/**
 * Главный класс приложения, откуда оно запускается
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String args[]){
        SpringApplication.run(Application.class,args);
    }
}
