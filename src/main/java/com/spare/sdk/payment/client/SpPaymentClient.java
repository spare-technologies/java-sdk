package com.spare.sdk.payment.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.spare.sdk.payment.models.Payment.Domestic.SpCreateDomesticPaymentResponse;
import com.spare.sdk.payment.models.Payment.Domestic.SpDomesticPayment;
import com.spare.sdk.payment.models.Payment.Domestic.SpDomesticPaymentResponse;
import com.spare.sdk.payment.models.Response.SpareSdkResponse;
import com.spare.sdk.utils.serialization.ObjectSerializer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SpPaymentClient implements ISpPaymentClient {

    private final SpPaymentClientOptions _clientOptions;

    public SpPaymentClient(SpPaymentClientOptions clientOptions) {
        if (clientOptions == null) {
            throw new NullPointerException("Missing client configuration");
        }

        clientOptions.ValidateConfiguration();

        _clientOptions = clientOptions;
    }

    /**
     * Create domestic payment
     *
     * @param payment
     * @param signature
     * @return
     * @throws Exception
     */
    @Override
    public SpCreateDomesticPaymentResponse CreateDomesticPayment(SpDomesticPayment payment, String signature) throws Exception {
        HttpClient client = GetClient();

        var headers = getHeaders();
        headers.add("x-signature");
        headers.add(signature);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(payment.toJsonString()))
                .headers(headers.toArray(String[]::new))
                .uri(URI.create(GetUrl(SpEndpoints.CreateDomesticPayments)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception(response.body());
        }

        var responseModel = ObjectSerializer.toObject(response.body(), new TypeReference<SpareSdkResponse<SpDomesticPaymentResponse, Object>>() {
        });

        var paymentResponse = new SpCreateDomesticPaymentResponse();
        paymentResponse.Payment = responseModel.Data;
        paymentResponse.Signature = response.headers().firstValue("x-signature").isPresent() ? response.headers().firstValue("x-signature").get() : null;

        return paymentResponse;
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
                .uri(URI.create(String.format("%s?id=%s", GetUrl(SpEndpoints.GetDomesticPayment), id)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (!(response.statusCode() == 200)) {
            throw new Exception(response.body());
        }

        return ObjectSerializer.toObject(response.body(), new TypeReference<SpareSdkResponse<SpDomesticPaymentResponse, Object>>() {
        }).Data;

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
        if (perPage == 0) {
            perPage = 100;
        }

        HttpClient client = GetClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .headers(getHeaders().toArray(String[]::new))
                .uri(URI.create(String.format("%s?start=%s&perPage=%s", GetUrl(SpEndpoints.ListDomesticPayments), start, perPage)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (!(response.statusCode() == 200)) {
            throw new Exception();
        }

        return ObjectSerializer.toObject(response.body(), new TypeReference<SpareSdkResponse<ArrayList<SpDomesticPaymentResponse>, Object>>() {
        }).Data;
    }

    /**
     * Get request url
     *
     * @param endpoints
     * @return
     */
    private String GetUrl(SpEndpoints endpoints) {
        return String.format("%s%s", _clientOptions.BaseUrl, endpoints.Value);
    }

    /**
     * Get http client
     *
     * @return
     */
    private HttpClient GetClient() {
        _clientOptions.ValidateConfiguration();

        return HttpClient.newBuilder()
                .build();
    }

    /**
     * Setup headers
     *
     * @return
     */
    private List<String> getHeaders() {
        return new ArrayList<>(Arrays.asList("Content-Type",
                "application/json",
                "accept",
                "application/json",
                "app-id",
                _clientOptions.AppId,
                "x-api-key",
                _clientOptions.ApiKey));
    }

}
