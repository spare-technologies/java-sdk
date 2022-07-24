package com.spare.sdk.payment.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.spare.sdk.payment.models.payment.domestic.SpCreateDomesticPaymentResponse;
import com.spare.sdk.payment.models.payment.domestic.SpDomesticPaymentRequest;
import com.spare.sdk.payment.models.payment.domestic.SpDomesticPaymentResponse;
import com.spare.sdk.payment.models.response.SpareSdkResponse;
import com.spare.sdk.utils.serialization.ObjectSerializer;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

public final class SpPaymentClient implements ISpPaymentClient {

    private final SpPaymentClientOptions clientOptions;

    public SpPaymentClient(SpPaymentClientOptions clientOptions) {
        if (clientOptions == null) {
            throw new NullPointerException("Missing client configuration");
        }

        clientOptions.validateConfiguration();

        this.clientOptions = clientOptions;
    }

    /**
     * Create domestic payment
     */
    @Override
    public SpCreateDomesticPaymentResponse createDomesticPayment(SpDomesticPaymentRequest paymentRequest, String signature) throws Exception {

        HttpClient client = getClient();

        HttpUriRequest request = RequestBuilder.create("POST")
                .setUri(URI.create(getUrl(SpEndpoints.createDomesticPayments)))
                .setEntity(new StringEntity(paymentRequest.toJsonString(), ContentType.APPLICATION_JSON))
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
        paymentResponse.setPayment(responseModel.getData());
        paymentResponse.setSignature(responseSignature != null && responseSignature.length != 0 ? responseSignature[0].getValue() : null);

        return paymentResponse;
    }

    /**
     * Get domestic payment
     */
    @Override
    public SpareSdkResponse<SpDomesticPaymentResponse, Object> getDomesticPayment(String id) throws Exception {
        HttpClient client = getClient();

        HttpUriRequest request = RequestBuilder.create("GET")
                .setUri(URI.create(String.format("%s?id=%s", getUrl(SpEndpoints.getDomesticPayment), id)))
                .build();


        HttpResponse response = client.execute(request);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new Exception(EntityUtils.toString(response.getEntity()));
        }


        return ObjectSerializer.toObject(EntityUtils.toString(response.getEntity()), new TypeReference<SpareSdkResponse<SpDomesticPaymentResponse, Object>>() {});

    }

    /**
     * List domestic payments
     */
    @Override
    public SpareSdkResponse<ArrayList<SpDomesticPaymentResponse>, Object> listDomesticPayments(int start, int perPage) throws Exception {
        if (perPage == 0) {
            perPage = 100;
        }

        HttpClient client = getClient();

        HttpUriRequest request = RequestBuilder.create("GET")
                .setUri(URI.create(String.format("%s?start=%s&perPage=%s", getUrl(SpEndpoints.listDomesticPayments), start, perPage)))
                .build();

        HttpResponse response = client.execute(request);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new Exception(EntityUtils.toString(response.getEntity()));
        }

        return ObjectSerializer.toObject(EntityUtils.toString(response.getEntity()), new TypeReference<SpareSdkResponse<ArrayList<SpDomesticPaymentResponse>, Object>>() {
        });
    }

    /**
     * Get request url
     */
    private String getUrl(SpEndpoints endpoints) {
        return String.format("%s%s", clientOptions.getBaseUrl(), endpoints.getValue());
    }

    /**
     * Get http client
     */
    private CloseableHttpClient getClient() {
        clientOptions.validateConfiguration();

        HttpClientBuilder clientBuilder = HttpClients.custom()
                .setDefaultHeaders(new ArrayList<>(Arrays.asList(
                        new BasicHeader("Content-Type", "application/json"),
                        new BasicHeader("accept", "application/json"),
                        new BasicHeader("app-id", clientOptions.getAppId()),
                        new BasicHeader("x-api-key", clientOptions.getApiKey())
                )));

        if (clientOptions.getProxy() != null && clientOptions.getProxy().getHost() != null) {
            if (clientOptions.getProxy().getCredentials() == null) {
                return clientBuilder.setProxy(clientOptions.getProxy().getHost())
                        .build();
            }

            AuthScope authScope = new AuthScope(clientOptions.getProxy().getHost().getHostName(), clientOptions.getProxy().getHost().getPort());
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(authScope, clientOptions.getProxy().getCredentials());

            return clientBuilder.setProxy(clientOptions.getProxy().getHost())
                    .setDefaultCredentialsProvider(credentialsProvider)
                    .build();
        }

        return clientBuilder.build();
    }
}
