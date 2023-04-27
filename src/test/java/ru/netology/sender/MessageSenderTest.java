package ru.netology.sender;

import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

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
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Привет");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String expected = "Привет";

        String message = messageSender.send(map);
        Assertions.assertEquals(expected, message);
    }
//    void messageSenderTestRussianIP() {
//        HashMap<String, String> hashMap = new HashMap<String, String>() {{
//            put("x-real-ip", "172.0.32.11");
//        }};
//
//        GeoServiceImpl geoservice = Mockito.mock(GeoServiceImpl.class);
////        Mockito.when(geoservice.byIp("172.1.1.1"))
////                        .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
//        Location location = Mockito.mock(Location.class);
//        Mockito.when(location.getCountry())
//                .thenReturn(Country.RUSSIA);
//
//        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
//        Mockito.when(localizationService.locale(Country.RUSSIA))
//                        .thenReturn("Русский");
//
//        MessageSender messageSender = new MessageSenderImpl(geoservice,localizationService);
//
//
//        String expected = "Русский";
//        String message = messageSender.send(hashMap);
//        Assertions.assertEquals(expected, message);
//    }

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
