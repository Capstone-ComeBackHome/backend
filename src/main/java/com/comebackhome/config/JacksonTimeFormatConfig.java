package com.comebackhome.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.YearMonthDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearMonthSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonTimeFormatConfig {

    private static final String dateFormat = "yyyy-MM-dd";
    private static final String yearMonthFormat = "yyyy-MM";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {

            jacksonObjectMapperBuilder.serializers(new YearMonthSerializer(DateTimeFormatter.ofPattern(yearMonthFormat)));
            jacksonObjectMapperBuilder.deserializers(new YearMonthDeserializer(DateTimeFormatter.ofPattern(yearMonthFormat)));

            jacksonObjectMapperBuilder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
            jacksonObjectMapperBuilder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)));
        };
    }
}
