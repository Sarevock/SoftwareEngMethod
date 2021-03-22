package com.napier.sem;

import java.sql.ResultSet;
import java.sql.Statement;

public class Req10 {

    private DataConnect a;

    Req10(DataConnect dataConnect){ a = dataConnect; }

    public void cityReport()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = a.con2().createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE CountryCode = 'AFG'"
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                City city = new City();
                city.city_name = rset.getString("Name");
                city.city_country_code = rset.getString("CountryCode");
                city.city_district = rset.getString("District");
                city.city_population = rset.getInt("Population");


                //if the data is present

                //Show the result on screen
                System.out.println(
                        city.city_name+ " "
                                + city.city_country_code + " "
                                + city.city_district + " "
                                + city.city_population + "\n"
                );
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
        }
    }

}
