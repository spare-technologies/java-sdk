package com.spare.sdk.utils.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public class ObjectSerializer {

    /**
     * Serialize object
     * @param thiz
     * @return
     * @throws JsonProcessingException
     */
    public static String toJsonString(Object thiz) throws JsonProcessingException {
        return new ObjectMapper()
                .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true))
                .writeValueAsString(thiz);
    }

    /**
     * Create object from json string
     * @param json
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T toObject(String json, TypeReference<T> reference) throws JsonProcessingException {
        if (json == null || json.isBlank()) {
            return null;
        }
        return new ObjectMapper()
                .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true))
                .readValue(json, reference);
    }
}
