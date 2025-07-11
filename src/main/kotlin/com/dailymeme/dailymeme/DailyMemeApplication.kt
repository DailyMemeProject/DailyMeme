package com.dailymeme.dailymeme

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DailyMemeApplication

fun main(args: Array<String>) {
    runApplication<DailyMemeApplication>(*args)
}
