package pl.umk.mat.locals

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LocalsApplication

fun main(args: Array<String>) {
    runApplication<LocalsApplication>(*args)
}
