package com.andersen.chronology.trips.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfig {

    @Bean
    WebClient webClient(WebClient.Builder builder) throws Exception {
        final int size = 5 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext)).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000) // timeout
                // restriction
                .responseTimeout(Duration.ofMillis(40000))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(40000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(15000, TimeUnit.MILLISECONDS)));

        return builder.clientConnector(new ReactorClientHttpConnector(httpClient)).exchangeStrategies(strategies).build();
    }
}
