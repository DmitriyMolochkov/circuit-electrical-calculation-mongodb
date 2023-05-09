package com.courseproject.circuitelectricalcalculation.controller

import com.courseproject.circuitelectricalcalculation.model.*
import com.courseproject.circuitelectricalcalculation.repository.CalculationRepository
import com.courseproject.circuitelectricalcalculation.utils.CalculationUtils
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid


@RestController
@RequestMapping("/api")
class CalculationController(private val calculationRepository: CalculationRepository) {
    @GetMapping("/calculation")
    fun getCalculationList(): ResponseEntity<List<CalculationView>> {

        val calculations = calculationRepository.findAll()
        if (calculations.isEmpty()) {
            return ResponseEntity<List<CalculationView>>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<List<CalculationView>>((calculations.map { x -> CalculationView(x!!) }), HttpStatus.OK)
    }

    @GetMapping("/calculation/{id}")
    fun getCalculationById(@PathVariable("id") calculationId: String): ResponseEntity<CalculationView> {
        val calculation = calculationRepository.findById(ObjectId(calculationId))
        if (calculation.isPresent) {
            return ResponseEntity<CalculationView>(CalculationView(calculation.get()), HttpStatus.OK)
        }
        return ResponseEntity<CalculationView>(HttpStatus.NOT_FOUND)
    }

    @PostMapping("/calculation")
    fun createCalculation(
        @Valid @RequestBody calculationCreateModel: CalculationCreateModel,
        uri: UriComponentsBuilder
    ): ResponseEntity<CalculationResultsView> {

        val calculationResult = CalculationUtils().getCalculationResults(calculationCreateModel)

        val calculation = Calculation(
            calculationCreateModel.networkTopology,
            calculationCreateModel.branchResistances,
            calculationCreateModel.branchEVMs,
            calculationCreateModel.branchCurrentSources,
            calculationCreateModel.towersNumbers,
            calculationCreateModel.calculationPoints,
            calculationResult
        )
        calculationRepository.save(calculation)

        return ResponseEntity<CalculationResultsView>(CalculationResultsView(calculation), HttpStatus.CREATED)
    }

    @GetMapping("/calculation/{id}/check")
    fun checkCalculationById(@PathVariable("id") calculationId: String): ResponseEntity<List<CheckResultView>> {
        val calculation = calculationRepository.findById(ObjectId(calculationId))
        if (calculation.isPresent) {
            val checkCalculationResults =
                CalculationUtils().checkCalculationResults(calculation.get().calculationResults)
            return ResponseEntity<List<CheckResultView>>(checkCalculationResults, HttpStatus.OK)
        }
        return ResponseEntity<List<CheckResultView>>(HttpStatus.NOT_FOUND)
    }
}

