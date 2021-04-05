package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static DataConnect app;

    @BeforeAll
    static void init()
    {
        //Connection
        app = new DataConnect();
        app.connect("localhost:33060");

    }

    @Test
    void testGetCity()
    {
        /**
         * Mumbai (Bombay) IND Maharashtra 10500000
         *
         * Ciudad de México MEX Distrito Federal 8591309
         *
         * La Habana CUB La Habana 2256000
         *
         * Kabul AFG Kabol 1780000
         *
         * La Matanza ARG Buenos Aires 1266461
         */

        ReqCity city = new ReqCity(app);

        //Basic
        city.cityReport(Location.Basic, 1 );
        City basic = city.Ritorna();
        //Assert Basic
        assertEquals(basic.City_name(), "Mumbai (Bombay)");
        assertEquals(basic.City_country_name(), "IND");
        assertEquals(basic.City_district(), "Maharashtra");
        assertEquals(basic.City_population(), 10500000);

        //Continent
        city.cityReport(Location.Continent, 1 );
        City continent = city.Ritorna();
        //Assert Continent
        assertEquals(continent.City_name(), "Ciudad de México");
        assertEquals(continent.City_country_name(), "MEX");
        assertEquals(continent.City_district(), "Distrito Federal");
        assertEquals(continent.City_population(), 8591309);

        //Region
        city.cityReport(Location.Region, 1 );
        City region = city.Ritorna();
        //Assert Region
        assertEquals(region.City_name(), "La Habana");
        assertEquals(region.City_country_name(), "CUB");
        assertEquals(region.City_district(), "La Habana");
        assertEquals(region.City_population(), 2256000);

        //Country
        city.cityReport(Location.Country, 1 );
        City country = city.Ritorna();
        //Assert Country
        assertEquals(country.City_name(), "Kabul");
        assertEquals(country.City_country_name(), "AFG");
        assertEquals(country.City_district(), "Kabol");
        assertEquals(country.City_population(), 1780000);

        //District
        city.cityReport(Location.District, 1 );
        City district = city.Ritorna();
        //Assert District
        assertEquals(district.City_name(), "La Matanza");
        assertEquals(district.City_country_name(), "ARG");
        assertEquals(district.City_district(), "Buenos Aires");
        assertEquals(district.City_population(), 1266461);
    }

    @Test
    void testGetCountry()
    {

        /**
         * CHN China Asia Eastern Asia 1277558000
         *
         * USA United States North America North America 278357000
         *
         * CUB Cuba North America Caribbean 11201000
         */
        ReqCountry a = new ReqCountry(app);

        a.countryReport(Location.Basic, 1 );
        Country basic = a.Ritorna();
        assertEquals(basic.Country_code(), "CHN");
        assertEquals(basic.Country_name(), "China");
        assertEquals(basic.Country_continent(), "Asia");
        assertEquals(basic.Region(), "Eastern Asia");
        assertEquals(basic.Population(), 1277558000);
        a.countryReport(Location.Continent, 1);
        Country continent = a.Ritorna();
        assertEquals(continent.Country_code(), "USA");
        assertEquals(continent.Country_name(), "United States");
        assertEquals(continent.Country_continent(), "North America");
        assertEquals(continent.Region(), "North America");
        assertEquals(continent.Population(), 278357000);
        a.countryReport(Location.Region, 1 );
        Country region = a.Ritorna();
        assertEquals(region.Country_code(), "CUB");
        assertEquals(region.Country_name(), "Cuba");
        assertEquals(region.Country_continent(), "North America");
        assertEquals(region.Region(), "Caribbean");
        assertEquals(region.Population(), 11201000);
    }

    @Test
    void extraPopulation(){
        /**
         * 6078749450
         *
         * 3705025700
         *
         * 38140000
         *
         * 37032000
         *
         * 360593
         *
         * 440900
         */

        //Population
        ExtraPopReq a = new ExtraPopReq(app);

        a.totalPop(Location.Basic);
        assertEquals(a.Ritorna(), 6078749450L);
        a.totalPop(Location.Continent);
        assertEquals(a.Ritorna(), 3705025700L);
        a.totalPop(Location.Region);
        assertEquals(a.Ritorna(), 38140000);
        a.totalPop(Location.Country);
        assertEquals(a.Ritorna(), 37032000);
        a.totalPop(Location.District);
        assertEquals(a.Ritorna(), 360593);
        a.totalPop(Location.City);
        assertEquals(a.Ritorna(), 440900);
    }

    @Test
    void Language(){
        /**
         * Chinese 1191843539 19.61
         *
         * Hindi 405633085 6.67
         *
         * Spanish 355029461 5.84
         *
         * English 347077860 5.71
         *
         * Arabic 233839240 3.85
         */

        ReqLanguage a = new ReqLanguage(app);
        a.Parlare();
        Language b = a.Ritorna();

        assertEquals(b.Lingua(), "Arabic");
        assertEquals(b.Parlare(), 233839240);
        assertEquals(b.Percentuale(), 3.85f);
    }

    @Test
    void disconnect(){
        app.disconnect();
    }
}
