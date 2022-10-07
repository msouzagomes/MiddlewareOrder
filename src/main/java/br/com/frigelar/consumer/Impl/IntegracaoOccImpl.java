package br.com.frigelar.consumer.Impl;

import br.com.frigelar.consumer.IntegracaoOcc;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class IntegracaoOccImpl implements IntegracaoOcc {

    @Override
    public Response puttIntegracaoOcc(String jsonBody, String occUrl) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonBody);
        Request request = new Request.Builder()
                .url(occUrl)
                .method("PUT", body)
                .addHeader("Authorization", "true")
                .addHeader("Content-Type", "application/json")
                .build();
        return client.newCall(request).execute();
    }

    @Override
    public Response postIntegracaoOcc(String jsonBody, String occUrl) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonBody);
        Request request = new Request.Builder()
                .url(occUrl)
                    .method("POST", body)
                .addHeader("Authorization", "true")
                .addHeader("Content-Type", "application/json")
                .build();
        return client.newCall(request).execute();
    }

    @Override
    public Response getIntegracaoOcc(String occUrl) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(occUrl)
                .method("GET", null)
                .addHeader("Authorization", "true")
                .build();
        return client.newCall(request).execute();
    }
}