package org.example.setterdi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            Employee employee = context.getBean(Employee.class);
            employee.displayAddress();
        }
    }
}
