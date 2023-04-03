package com.cars;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Clock;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;

@Configuration
public class ClockConfiguration {
    @Bean
    @Primary
    public Clock clock() {
        final LocalDateTime NOW = LocalDateTime.of(2012, 5, 13, 0, 0, 0);
        return Clock.fixed(NOW.toInstant(UTC), UTC);
    }
}
