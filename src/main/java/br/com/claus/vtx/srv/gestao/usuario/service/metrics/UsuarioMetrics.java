package br.com.claus.vtx.srv.gestao.usuario.service.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioMetrics {

    private final MeterRegistry registry;


}
