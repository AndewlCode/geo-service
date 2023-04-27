package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @Test
    void localeUSA() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected = "Welcome";
        Country usa = Country.USA;

        String country = localizationService.locale(usa);

        assertEquals(expected,country);
    }
    @Test
    void localeRu(){
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected = "Добро пожаловать";
        Country russia = Country.RUSSIA;

        String country = localizationService.locale(russia);

        assertEquals(expected,country);
    }
}