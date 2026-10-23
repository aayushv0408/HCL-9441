
package org.example.notificationservice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(Appconfig.class)) {

            Student student1 = context.getBean(Student.class);
            Student student2 = context.getBean(Student.class);

            System.out.println(student1.hashCode());
            System.out.println(student2.hashCode());

            student1.notifyStudent();
        }
    }
}
