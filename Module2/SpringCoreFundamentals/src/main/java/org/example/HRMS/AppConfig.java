package org.example.HRMS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Employee basicEmployee() {
        return new Employee(
                101,
                "Rahul",
                new Address("Ghaziabad", "Uttar Pradesh"),
                new Department(1, "Administration"),
                new Salary("Basic", 30000)
        );
    }

    @Bean
    public Employee dataAnalyst() {
        return new Employee(
                102,
                "Aditya",
                new Address("Delhi", "Delhi"),
                new Department(2, "Analytics"),
                new Salary("Data Analyst", 50000)
        );
    }

    @Bean
    public Employee humanResource() {
        return new Employee(
                103,
                "Priya",
                new Address("Noida", "Uttar Pradesh"),
                new Department(3, "Human Resources"),
                new Salary("Human Resource", 40000)
        );
    }
}