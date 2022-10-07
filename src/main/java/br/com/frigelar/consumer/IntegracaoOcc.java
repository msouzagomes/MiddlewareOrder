package br.com.frigelar.consumer;

import okhttp3.Response;

import java.io.IOException;

public interface IntegracaoOcc {
    Response getIntegracaoOcc(String occUrl) throws IOException;
    Response puttIntegracaoOcc(String jsonBody, String occUrl) throws IOException;
    Response postIntegracaoOcc(String jsonBody, String occUrl) throws IOException;
}
