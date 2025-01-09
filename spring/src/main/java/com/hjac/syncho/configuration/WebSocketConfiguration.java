package com.hjac.syncho.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
// STOMP 사용 -> 메세지 브로커 사용
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // enableSimpleBroker => 스프링에서 제공하는 내장 브로커 사용
        // 파라미터 : 해당 값이 prefix로 붙은 메세지가 송신 되었을 때 메세지를 메시지 브로커가 처리
        // queue, topic 경로로 송신 -> 심플 브로커가 메세지르 받고 구독자에게 전달
        // queue => 1 : 1 / topic => 1 : n
        registry.enableSimpleBroker("/queue", "/topic");

        // setApplicationDestinationPrefixes
        // 바로 브로커로 가지 않고 핸들러를 통해 처리나 가공이 필요한 메세지
        // 메세지 핸들러로 라우팅되는 prefix 설정
        // app이 붙어있는 경로로 발신되면 해당 경로를 처리하고 있는 핸들러로 전달
        registry.setApplicationDestinationPrefixes("/app");
    }
}
