package com.a306.chatting.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@RequiredArgsConstructor
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
  //1. 웹소켓 방식의 STOMP를 통해서 메세지 처리
  //웹소켓 통신을 할 때 주고 받는 메세지 form을 정해주는 프로토콜이라고 이해
  //커맨드 헤더 바디가 하나의 프레임으로 전달됨

  //2. STOMP는 Message Broker를 통해서 메세지를 수신자들에게 전달해주는데, 이를 위한 설정
  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    //simple message borker는 스프링부트 내부 메모리에서 동작 >> 다운되면 데이터 유실, 다른 서버의 사용자와 채팅이 불가능
    //그래서 Redis의 pub/sub 기능을 통해 메세지 브로커를 사용
    registry.enableSimpleBroker("/queue"); //내장 브로커를 사용, prefix가 붙은 메세지 송신 시 브로커가 처리함
    registry.setApplicationDestinationPrefixes("/pub"); //바로 브로커로 전달되지 않고, 핸들러에서 가공이 필요할 때, 메세지 핸들러로 라우팅 prefix
  }

  //3. 처음 소켓 통신을 연결할 때, 핸드쉐이크를 위한 주소 설정
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/stomp/chat") // ex ) ws://localhost:9000/stomp/chat
        .setAllowedOriginPatterns("*").withSockJS(); //CORS와 웹소켓 지원이 안되는 브라우저를 위한 설정
  }

  //사용자가 웹 소켓 연결될때 끊길 때 추가 기능을
  //configureClientInboundChannel
  //Override하여 구현할 수 있음
}