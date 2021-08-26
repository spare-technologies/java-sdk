package Payment.Client.SerializationConfiguration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.math.BigDecimal;
import java.util.Collection;

public final class  SerializerConfiguration {

    public static ObjectMapper SetConfiguration()
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        SimpleModule SerialisationModule = new SimpleModule();
        SerialisationModule.addSerializer(BigDecimal.class, new DecimalSerializer());
        mapper.getSerializerProvider().setNullValueSerializer(new NullSerializer());
        mapper.registerModule(SerialisationModule);
        return mapper;
    }
}
