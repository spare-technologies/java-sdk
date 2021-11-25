package com.spare.sdk.utils.helpers.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spare.sdk.utils.serialization.ObjectSerializer;

public class SpModel {
    /**
     * Serialize object
     * @return
     * @throws JsonProcessingException
     */
    public final String toJsonString() throws JsonProcessingException {
        return ObjectSerializer.toJsonString(this);
    }
}
