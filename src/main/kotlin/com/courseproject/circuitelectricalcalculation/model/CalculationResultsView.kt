package com.courseproject.circuitelectricalcalculation.model

import org.bson.types.ObjectId

class CalculationResultsView(calculation: Calculation) {
    val calculationId: String
    val branchesVoltage: List<ComplexNumber>
    val branchesCurrents: List<ComplexNumber>
    val resultFotCTS: CalculationResultForCTS
    val resultsForTowers: List<CalculationResultForTower>

    init {
        calculationId = calculation.id.toString()
        branchesVoltage = calculation.calculationResults.branchesVoltage
        branchesCurrents = calculation.calculationResults.branchesCurrents
        resultFotCTS = calculation.calculationResults.resultFotCTS
        resultsForTowers = calculation.calculationResults.resultsForTowers
    }
}