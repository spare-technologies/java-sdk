package Payment.Models.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpareSdkResponse<T, TV> {

    @JsonProperty("error")
    public String Error;

    @JsonProperty("data")
    public T Data;

    @JsonProperty("meta")
    public TV Meta;
}
