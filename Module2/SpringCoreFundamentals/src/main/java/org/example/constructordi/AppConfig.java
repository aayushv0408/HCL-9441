package org.example.constructordi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Address address(){
        return new Address();
    }
    @Bean
    public Employee employee(Address address){
        return new Employee(address);
    }
}
