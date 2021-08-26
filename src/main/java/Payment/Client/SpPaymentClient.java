package Payment.Client;

import Payment.Client.SerializationConfiguration.SerializerConfiguration;
import Payment.Models.Payment.Domestic.SpDomesticPayment;
import Payment.Models.Payment.Domestic.SpDomesticPaymentResponse;
import Payment.Models.Response.SpareSdkResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class SpPaymentClient implements ISpPaymentClient {

    private SpPaymentClientOptions _clientOptions;
    public ObjectMapper objectMapper;
    @Autowired
    public SpPaymentClient(SpPaymentClientOptions clientOptions) {
        if (clientOptions.BaseUrl == null) {
            throw new NullPointerException();
        }
        if (clientOptions.AppId.isBlank() || clientOptions.AppId == null) {
            throw new NullPointerException();
        }
        if (clientOptions.ApiKey.isBlank() || clientOptions.ApiKey == null) {
            throw new NullPointerException();
        }

        _clientOptions = clientOptions;
        objectMapper = SerializerConfiguration.SetConfiguration();
    }

    /**
     * Create domestic payment
     *
     * @param payment
     * @return
     */
    @Override
    public SpDomesticPaymentResponse CreateDomesticPayment(SpDomesticPayment payment) throws Exception {
        HttpClient client = GetClient();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(GetBody(payment)))
                .headers(getHeaders().toArray(String[]::new))
                .uri(URI.create(GetUrl(SpEndpoints.CreateDomesticPayments)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (!(response.statusCode() == 200)) {
            throw new Exception();
        }
        JavaType type = objectMapper.getTypeFactory().constructParametricType(SpareSdkResponse.class, SpDomesticPaymentResponse.class, Object.class );
        SpareSdkResponse<SpDomesticPaymentResponse, Object> model =
                 objectMapper.readValue(response.body(), type);
        return model.Data;
    }

    /**
     * Get domestic payment
     *
     * @param id
     * @return
     */
    @Override
    public SpDomesticPaymentResponse GetDomesticPayment(String id) throws Exception {
        HttpClient client = GetClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .headers(getHeaders().toArray(String[]::new))
                .uri(URI.create(GetUrl(SpEndpoints.GetDomesticPayment) + "?id=" + id))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (!(response.statusCode() == 200)) {
            throw new Exception();
        }
        JavaType type = objectMapper.getTypeFactory().constructParametricType(SpareSdkResponse.class, SpDomesticPaymentResponse.class, Object.class );
        SpareSdkResponse<SpDomesticPaymentResponse, Object> model =
                objectMapper.readValue(response.body(), type);
        return model.Data;

    }

    /**
     * List domestic payments
     *
     * @param start
     * @param perPage
     * @return
     */
    @Override
    public ArrayList<SpDomesticPaymentResponse> ListDomesticPayments(int start, int perPage) throws Exception {
        HttpClient client = GetClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .headers(getHeaders().toArray(String[]::new))
                .uri(URI.create(GetUrl(SpEndpoints.ListDomesticPayments) + "?start=" + 0 + "&perPage=" + perPage))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (!(response.statusCode() == 200)) {
            throw new Exception();
        }
        SpareSdkResponse<ArrayList<SpDomesticPaymentResponse>, Object> model =
                objectMapper.readValue(response.body(), new TypeReference<SpareSdkResponse<ArrayList<SpDomesticPaymentResponse>, Object>>() {});
        return model.Data;
    }

    /**
     * Serialize body
     *
     * @param o
     * @return
     */
    private static String GetBody(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(o);
    }

    /**
     * Get request url
     *
     * @param endpoints
     * @return
     */
    private String GetUrl(SpEndpoints endpoints) {
        return _clientOptions.BaseUrl + endpoints.Value;
    }

    /**
     * Get http client
     *
     * @return
     */
    private HttpClient GetClient() {
        if (_clientOptions.AppId.isBlank() || _clientOptions.AppId == null) {
            throw new NullPointerException();
        }

        if (_clientOptions.ApiKey.isBlank() || _clientOptions.ApiKey == null) {
            throw new NullPointerException();
        }
        HttpClient client = HttpClient.newBuilder()
                .build();
        return client;
    }

    /**
     * Setup headers
     * @return
     */
    private List<String> getHeaders() {
        List<String> headers = new ArrayList<>();
        headers.add("Content-Type");
        headers.add("application/json");
        headers.add("accept");
        headers.add("application/json");
        headers.add("app-id");
        headers.add(_clientOptions.AppId);
        headers.add("x-api-key");
        headers.add(_clientOptions.ApiKey);
        return headers;
    }

}
