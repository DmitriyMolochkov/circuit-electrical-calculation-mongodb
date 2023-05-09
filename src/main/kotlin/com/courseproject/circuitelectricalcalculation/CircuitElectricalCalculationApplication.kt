package com.courseproject.circuitelectricalcalculation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class CircuitElectricalCalculationApplication

fun main(args: Array<String>) {
	runApplication<CircuitElectricalCalculationApplication>(*args)
}
