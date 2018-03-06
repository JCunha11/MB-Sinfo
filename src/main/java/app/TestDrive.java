package app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import app.service.DataLoader;

@SpringBootApplication
public class TestDrive {

    public static void main(String[] args) {
        SpringApplication.run(TestDrive.class,args);
        DataLoader dataLoader = new DataLoader();
        dataLoader.loadData();
        
    }

    @Bean

    public ModelMapper modelMapper() {

        return new ModelMapper();

    }
}