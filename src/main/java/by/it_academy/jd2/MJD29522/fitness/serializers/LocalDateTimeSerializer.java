package by.it_academy.jd2.MJD29522.fitness.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.toInstant(ZoneOffset.UTC).
                //getEpochSecond());
               toEpochMilli());
    }
}
//public class LocalDateTimeSerializer{//
//    public static class LocalDateTimeConverter extends StdSerializer<LocalDateTime> {//
//        private static final long serialVersionUID = 1L;
//
//        protected LocalDateTimeConverter(Class<LocalDateTime> t) {
//            super(t);
//        }
//
//        @Override
//        public void serialize(LocalDateTime value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//            jsonGenerator.writeNumber(value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//        }//
//    }

//}
