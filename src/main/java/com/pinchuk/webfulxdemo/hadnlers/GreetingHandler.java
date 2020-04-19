package com.pinchuk.webfulxdemo.hadnlers;

import com.pinchuk.webfulxdemo.domain.Message;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author Pinchuk Yevhen
 * @created 19/04/2020
 */
@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        Long start = request.queryParam("start")
                .map(Long::valueOf)
                .orElse(0L);
        Long count = request.queryParam("count")
                .map(Long::valueOf)
                .orElse(3L);

        Flux<Message> data = Flux
                .just("Hello reactive",
                        "1 post",
                        "2 post",
                        "3 post"
                )
                .skip(start)
                .take(count)
                .map(Message::new);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data, Message.class);
    }

    public Mono<ServerResponse> index(ServerRequest request){
        String user = request.queryParam("user")
                .orElse("No body");
        return ServerResponse
                .ok()
                .render("index", Map.of("user",user));
    }
}
