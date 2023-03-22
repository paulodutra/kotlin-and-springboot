package com.springapp.kotlin

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/hello")
class HelloController {

        @GetMapping("/")
        fun hello() =
                "Hello World from Kotlin using SpringBoot"
}