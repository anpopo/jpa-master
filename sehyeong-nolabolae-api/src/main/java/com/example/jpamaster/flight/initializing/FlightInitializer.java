package com.example.jpamaster.flight.initializing;

import com.example.jpamaster.flight.feign.AirlineFeignClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class FlightInitializer {

    public static final String AIRPORT_INFO_FILE_PATH = "static/airport/airport_info.csv";

    @Value("${open-api.airline.api-key}")
    private String serviceKey;

    private final AirlineFeignClient airlineFeignClient;
    private final FlightInitBusiness flightInitBusiness;

    @Transactional
    @EventListener(value = ApplicationReadyEvent.class)
    public void airlineInitializeEvent () {
        AirlineInfoVo airlineInfo = airlineFeignClient.fetchAirline(serviceKey, "json");
        if (airlineInfo != null && airlineInfo.getResponse() != null && airlineInfo.getResponse().getBody() != null) {
            airlineInfo.getResponse().getBody().getItems()
                       .forEach(flightInitBusiness::airlineInitializeUpsert);
        }
    }

    @Transactional
    @EventListener(value = ApplicationReadyEvent.class)
    public void airportInitializeEvent () {
        ClassPathResource classPathResource = new ClassPathResource(AIRPORT_INFO_FILE_PATH);

        if(!classPathResource.exists()){
            log.error("Invalid filePath : {}", AIRPORT_INFO_FILE_PATH);
            return;
        }

        log.info("file path exists = {}", classPathResource.exists());
        try (BufferedReader br = new BufferedReader(new InputStreamReader(classPathResource.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                flightInitBusiness.airportInitializing(line);
            }
        } catch (IOException e) {
            log.error("[AIRPORT] parseToAirport >> file stream has got an error", e);
        }
    }

    @Getter
    public static class AirlineInfoVo {

        private Response response;

        @Getter
        static class Response {
            private Object header;
            private Body body;

            @Getter
            static class Body {
                private List<Item> items;

                @Getter
                static class Item {
                    private String airlineImage;
                    private String airlineName;
                    private String airlineTel;
                    private String airlineIcTel;
                    private String airlineIata;
                    private String airlineIcao;
                }
            }
        }
    }
}
