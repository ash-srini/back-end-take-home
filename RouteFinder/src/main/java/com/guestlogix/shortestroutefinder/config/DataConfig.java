package com.guestlogix.shortestroutefinder.config;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.guestlogix.shortestroutefinder.helper.DataHelper;
import com.guestlogix.shortestroutefinder.model.Airline;
import com.guestlogix.shortestroutefinder.model.Airport;
import com.guestlogix.shortestroutefinder.model.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author aishwaryasrinivasan
 */
@Configuration
@PropertySource("classpath:config.properties")
public class DataConfig {

    Logger logger = LoggerFactory.getLogger(DataConfig.class);

    @Value("${configuration.csv.test.airlines}")
    private String airlinesCSVTestFile;

    @Value("${configuration.csv.full.airlines}")
    private String airlinesCSVFullFile;

    @Value("${configuration.csv.test.airports}")
    private String airportCSVTestFile;

    @Value("${configuration.csv.full.airports}")
    private String airportCSVFullFile;

    @Value("${configuration.csv.test.routes}")
    private String routeCSVTestFile;

    @Value("${configuration.csv.full.routes}")
    private String routeCSVFullFile;

    @Bean
    public List<Airport> cacheAirports() {
        return getDataFromCsv(Airport.class, airportCSVFullFile);
    }

    @Bean
    public List<Airline> cacheAirlines() {
        return getDataFromCsv(Airline.class, airlinesCSVFullFile);
    }

    @Bean
    public List<Route> cachedRoute(List<Airport> airportList) {
        List<Route> routeList = getDataFromCsv(Route.class, routeCSVFullFile);
        addAirportConnections(routeList, airportList);
        return routeList;
    }

    private void addAirportConnections(List<Route> routeList, List<Airport> airportList){
        for(Route route : routeList){
            Airport start = DataHelper.findAirportfromList(route.getStart(), airportList);
            Airport end = DataHelper.findAirportfromList(route.getEnd(), airportList);
            if(start.getConnectedAirports() == null){
                List<Airport> connectionList = new ArrayList<>();
                connectionList.add(end);
                start.setConnectedAirports(connectionList);
            }else{
                start.getConnectedAirports().add(end);
            }
        }
    }

    private <T> List<T> getDataFromCsv(Class<T> type, String fileName) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper.typedSchemaFor(type).withHeader();
        List<T> list = new ArrayList<>();
        try{
            ClassPathResource cl = new ClassPathResource(fileName);
            MappingIterator<T> readValues = new CsvMapper().readerFor(type)
                    .with(csvSchema)
                    .readValues(new InputStreamReader(cl.getInputStream()));

            list = readValues.readAll();
            return list;
        }catch(IOException e) {
            e.printStackTrace();
            logger.error("Could not fine file {}", fileName);
        }
       return list;
    }

}
