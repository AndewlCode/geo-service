package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    void byIpFromMoscow() {
        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);
        Location expected = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Location location = geoService.byIp(GeoServiceImpl.MOSCOW_IP);

        assert expected.getCity().equals(location.getCity());
    }
    @Test
    void byIpFromNewYork(){
        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);
        Location expected = new Location("New York", Country.USA, " 10th Avenue", 32);
        Location location = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);

        assert expected.getCity().equals(location.getCity());
    }


}