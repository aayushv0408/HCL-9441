
package org.example.HRMS;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Employee emp1 = ctx.getBean("basicEmployee", Employee.class);
        Employee emp2 = ctx.getBean("dataAnalyst", Employee.class);
        Employee emp3 = ctx.getBean("humanResource", Employee.class);

        System.out.println("HRMS EMPLOYEE DETAILS");
        System.out.println("------------------------------");

        emp1.display();

        System.out.println("------------------------------");

        emp2.display();

        System.out.println("------------------------------");

        emp3.display();

        ctx.close();
    }
}
