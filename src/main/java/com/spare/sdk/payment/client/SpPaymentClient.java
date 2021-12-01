package com.spare.sdk.payment.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.spare.sdk.payment.models.Payment.Domestic.SpCreateDomesticPaymentResponse;
import com.spare.sdk.payment.models.Payment.Domestic.SpDomesticPayment;
import com.spare.sdk.payment.models.Payment.Domestic.SpDomesticPaymentResponse;
import com.spare.sdk.payment.models.Response.SpareSdkResponse;
import com.spare.sdk.utils.serialization.ObjectSerializer;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

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

        HttpUriRequest request = RequestBuilder.create("POST")
                .setUri(URI.create(GetUrl(SpEndpoints.CreateDomesticPayments)))
                .setEntity(new StringEntity(payment.toJsonString(), ContentType.APPLICATION_JSON))
                .build();

        request.addHeader(new BasicHeader("x-signature", signature));

        HttpResponse response = client.execute(request);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new Exception(EntityUtils.toString(response.getEntity()));
        }

        SpareSdkResponse<SpDomesticPaymentResponse, Object> responseModel = ObjectSerializer.toObject(EntityUtils.toString(response.getEntity()), new TypeReference<SpareSdkResponse<SpDomesticPaymentResponse, Object>>() {
        });

        Header[] responseSignature = response.getHeaders("x-signature");

        SpCreateDomesticPaymentResponse paymentResponse = new SpCreateDomesticPaymentResponse();
        paymentResponse.Payment = responseModel.Data;
        paymentResponse.Signature = responseSignature != null && responseSignature.length != 0 ? responseSignature[0].getValue() : null;

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

        HttpUriRequest request = RequestBuilder.create("GET")
                .setUri(URI.create(String.format("%s?id=%s", GetUrl(SpEndpoints.GetDomesticPayment), id)))
                .build();


        HttpResponse response = client.execute(request);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new Exception(EntityUtils.toString(response.getEntity()));
        }


        return ObjectSerializer.toObject(EntityUtils.toString(response.getEntity()), new TypeReference<SpareSdkResponse<SpDomesticPaymentResponse, Object>>() {
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

        HttpUriRequest request = RequestBuilder.create("GET")
                .setUri(URI.create(String.format("%s?start=%s&perPage=%s", GetUrl(SpEndpoints.ListDomesticPayments), start, perPage)))
                .build();

        HttpResponse response = client.execute(request);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new Exception(EntityUtils.toString(response.getEntity()));
        }

        return ObjectSerializer.toObject(EntityUtils.toString(response.getEntity()), new TypeReference<SpareSdkResponse<ArrayList<SpDomesticPaymentResponse>, Object>>() {
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
    private CloseableHttpClient GetClient() {
        _clientOptions.ValidateConfiguration();

        return HttpClients.custom()
                .setDefaultHeaders(new ArrayList<>(Arrays.asList(
                        new BasicHeader("Content-Type", "application/json"),
                        new BasicHeader("accept", "application/json"),
                        new BasicHeader("app-id", _clientOptions.AppId),
                        new BasicHeader("x-api-key", _clientOptions.ApiKey)
                )))
                .build();
    }
}
