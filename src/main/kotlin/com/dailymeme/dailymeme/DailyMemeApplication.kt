package com.dailymeme.dailymeme

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableTransactionManagement
class DailyMemeApplication

fun main(args: Array<String>) {
    runApplication<DailyMemeApplication>(*args)
}
