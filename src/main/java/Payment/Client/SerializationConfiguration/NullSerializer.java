package Payment.Client.SerializationConfiguration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class NullSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object obj, JsonGenerator jsonGen, SerializerProvider unused)
            throws IOException, JsonProcessingException
    {

    }
}
