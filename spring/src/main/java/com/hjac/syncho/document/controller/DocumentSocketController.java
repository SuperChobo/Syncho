package com.hjac.syncho.document.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

// STOMP 사용 시 따로 상속을 받을 필요 X
// 컨트롤러 어노테이션 사용 가능
@Controller
public class DocumentSocketController {


    // MessageMapping : requestMapping 어노테이션과 비슷한 역할
    // STOMP 웹소켓 통신을 통해 메세지가 들어오면 메세지의 distination 헤더와 MessageMapping에 설정된
    // 경로가 일치하는 핸들러를 찾아 그 핸들러가 메세지를 처리
    // configuration 파일에서 /app을 핸들러에 매핑 하였으니 /app/hello 헤더를 가진 메세지가 이 핸들러를 통과
    @MessageMapping("/hello")
    // SendTo : 핸들러에서 처리를 마친 후 경로로 메세지를 반환하겠다는 어노테이션
    @SendTo("")

}
