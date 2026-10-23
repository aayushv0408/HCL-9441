package org.example.fielddi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            Employee employee = context.getBean(Employee.class);
            String result = employee.displayAddress();
            System.out.println(result);
        }
    }
}
