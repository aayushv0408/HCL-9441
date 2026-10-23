
package org.example.notificationservice;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {

    private final NotificationService notificationService;

    @Autowired
    public Student(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void notifyStudent() {
        notificationService.sendMsg(
            "Your assignment is due tomorrow."
        );
    }

    @PostConstruct
    public void init() {
        System.out.println("Object created.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Object destroyed.");
    }
}
