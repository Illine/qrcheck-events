package ru.softdarom.qrcheck.events.config;

import brave.Span;
import brave.Tracer;
import brave.propagation.TraceContext;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.*;
import ru.softdarom.qrcheck.events.config.property.LogbookProperties;

import java.util.Optional;

import static ru.softdarom.qrcheck.events.util.LogbackHelper.getLogger;

@Configuration
class LogbookConfig {

    @Bean
    HttpLogWriter writer(LogbookProperties properties) {
        return new QRCheckHttpLogWriter(properties);
    }

    @Bean
    public CorrelationId correlationId(Tracer tracer) {
        return new SleuthCorrelationId(tracer);
    }

    public static class QRCheckHttpLogWriter implements HttpLogWriter {

        private final Logger logger;

        public QRCheckHttpLogWriter(LogbookProperties properties) {
            logger = getLogger(properties.getName());
        }

        @Override
        public boolean isActive() {
            return logger.isInfoEnabled();
        }

        @Override
        public void write(Precorrelation precorrelation, String request) {
            logger.info(request);
        }

        @Override
        public void write(Correlation correlation, String response) {
            logger.info(response);
        }
    }

    public static class SleuthCorrelationId implements CorrelationId {

        private final Tracer tracer;

        SleuthCorrelationId(Tracer tracer) {
            this.tracer = tracer;
        }

        @Override
        public String generate(HttpRequest request) {
            return Optional.ofNullable(tracer.currentSpan())
                    .map(Span::context)
                    .map(TraceContext::traceIdString)
                    .orElse("[Span not found]");
        }
    }
}