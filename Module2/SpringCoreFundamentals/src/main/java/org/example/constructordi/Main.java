package org.example.constructordi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static void main() {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Employee emp= ctx.getBean(Employee.class);
        emp.getEmpAddress();
    }
}
