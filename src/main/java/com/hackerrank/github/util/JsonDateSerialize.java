package com.hackerrank.github.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Json date Serialize in the format used on the test
 */
public class JsonDateSerialize extends JsonSerializer<Date> {

    @Override
    public void serialize(final Date date, final JsonGenerator jgen, final SerializerProvider provider)
            throws IOException,
            JsonProcessingException {
        final SimpleDateFormat dataCompleta = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String formattedDate = dataCompleta.format(date);
        jgen.writeString(formattedDate);
    }
}