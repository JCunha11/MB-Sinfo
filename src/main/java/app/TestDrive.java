package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.services.DataLoader;

@SpringBootApplication
public class TestDrive {

    public static void main(String[] args) {
        SpringApplication.run(TestDrive.class,args);
        DataLoader dataLoader = new DataLoader();
        dataLoader.loadData();
        
    }
}