
package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    static void main() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
            Employee emp1 = ctx.getBean(Employee.class);
            Employee emp2 = ctx.getBean(Employee.class);
        emp1.display();
        emp2.display();
    }
}
