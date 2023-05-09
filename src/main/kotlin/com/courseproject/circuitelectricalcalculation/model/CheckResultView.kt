package com.courseproject.circuitelectricalcalculation.model

class CheckResultView(resultForTower: CalculationResultForTower) {
    val towerNumber: String
    val maxVoltageLoss: Double
    val isValid: Boolean

    init {
        towerNumber = resultForTower.towerNumber

        val voltageArray =
            arrayOf(resultForTower.voltagePhaseA, resultForTower.voltagePhaseB, resultForTower.voltagePhaseC)
        maxVoltageLoss = (230.0 - voltageArray.minByOrNull { x -> x.abs }!!.abs) / 230 * 100

        isValid = maxVoltageLoss <= 5 // <= 5%
    }
}