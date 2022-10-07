package br.com.frigelar.service;

import br.com.frigelar.dto.request.OccRequestDTO;
import br.com.frigelar.dto.response.MiddlewareResponse;

import java.io.IOException;

public interface MiddlewareService {
    MiddlewareResponse sendOrder(OccRequestDTO dto) throws Exception;
}
