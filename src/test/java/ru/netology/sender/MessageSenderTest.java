package ru.netology.sender;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.MessageSender;

public class MessageSenderTest {
    @Test
    void messageSenderTestRussianIP() {
        MessageSender messageSender = Mockito.mock(MessageSender.class);
        Map<String, String> headers = new HashMap<String, String>();
        Mockito.when(messageSender.send(headers)).thenReturn("Русский", "172");
        String expected = "Русский";
        String message = messageSender.send(headers);
        Assertions.assertEquals(expected, message);

    }

    @Test
    void messageSenderTestNotRussianIP() {
        MessageSender messageSender = Mockito.mock(MessageSender.class);
        Map<String, String> headers = new HashMap<String, String>();
        Mockito.when(messageSender.send(headers)).thenReturn("English", "78");
        String expected = "English";
        String message = messageSender.send(headers);
        Assertions.assertEquals(expected, message);

    }
}
