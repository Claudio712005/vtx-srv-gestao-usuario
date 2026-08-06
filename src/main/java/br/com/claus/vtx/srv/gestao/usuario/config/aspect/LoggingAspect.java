package br.com.claus.vtx.srv.gestao.usuario.config.aspect;

import br.com.claus.vtx.srv.gestao.usuario.utils.annotations.Tracer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect {

    @Around("@annotation(tracer)")
    public void rastrearExecucao(
        ProceedingJoinPoint joinPoint,
        Tracer tracer
    ) {


    }
}
