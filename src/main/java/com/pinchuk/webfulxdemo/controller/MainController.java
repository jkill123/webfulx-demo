package com.pinchuk.webfulxdemo.controller;

import com.pinchuk.webfulxdemo.domain.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author Pinchuk Yevhen
 * @created 19/04/2020
 */
@RestController
@RequestMapping("/controller")
public class MainController {

    @GetMapping
    public Flux<Message> list(@RequestParam Long start, @RequestParam Long count){
        return Flux
                .just("Hello reactive",
                        "1 post",
                        "2 post",
                        "3 post"
                )
                .skip(start)
                .take(count)
                .map(Message::new);
    }
}
