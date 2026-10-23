package org.example.notificationservice;

import org.springframework.stereotype.Component;

@Component 
public class WhatsappService implements NotificationService {
    @Override
    public void sendMsg(String msg) {
        System.out.println("Whatsapp message sent: " + msg);
    }
    
}