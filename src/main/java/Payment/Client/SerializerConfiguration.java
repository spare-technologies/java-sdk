package Payment.Client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigDecimal;

public final class  SerializerConfiguration {

    public static ObjectMapper SetConfiguration()
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        SimpleModule SerialisationModule = new SimpleModule();
        SerialisationModule.addSerializer(BigDecimal.class, new ToStringSerializer());
        mapper.registerModule(SerialisationModule);
        return mapper;
    }
}
