package ru.netology.sender;

import org.mockito.Mockito;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

public class MessageSenderTest {
    @Test
    void messageSenderTestRussianIP() {
        HashMap<String, String> map = new HashMap<String, String>() {
            {
                put("x-real-ip", "172.1.1.1");
            }
        };

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.1.1.1"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String expected = "Добро пожаловать";

        String message = messageSender.send(map);
        Assertions.assertEquals(expected, message);
    }
    @Test
    void messageSenderTestNotRussianIP() {
        HashMap<String, String> map = new HashMap<String, String>() {
            {
                put("x-real-ip", "96.44.183.149");
            }
        };

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("96.44.183.149"))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String expected = "Welcome";

        String message = messageSender.send(map);
        Assertions.assertEquals(expected, message);
    }
}
