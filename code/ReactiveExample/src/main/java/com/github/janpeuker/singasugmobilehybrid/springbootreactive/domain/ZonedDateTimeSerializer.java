package com.github.janpeuker.singasugmobilehybrid.springbootreactive.domain;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by janpeuker on 3/11/14.
 */
public class ZonedDateTimeSerializer extends com.fasterxml.jackson.databind.ser.std.StdSerializer<ZonedDateTime> {

    public ZonedDateTimeSerializer() {
        super(ZonedDateTime.class);
    }

    @Override
    public void serialize(ZonedDateTime value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeString(value.format(DateTimeFormatter.ISO_DATE_TIME));
    }
}
