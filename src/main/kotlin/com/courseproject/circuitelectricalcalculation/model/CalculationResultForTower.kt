package com.courseproject.circuitelectricalcalculation.model

import flanagan.complex.Complex


class CalculationResultForTower(
    val towerNumber: String,
    val voltagePhaseA: ComplexNumber,
    val voltagePhaseB: ComplexNumber,
    val voltagePhaseC: ComplexNumber
) {}