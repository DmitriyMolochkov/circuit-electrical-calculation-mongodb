package com.courseproject.circuitelectricalcalculation.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import flanagan.complex.ComplexMatrix


class CalculationResults (
    @JsonManagedReference val branchesVoltage: List<ComplexNumber>,
    @JsonManagedReference val branchesCurrents: List<ComplexNumber>,
    @JsonManagedReference val resultFotCTS: CalculationResultForCTS,
    @JsonManagedReference val resultsForTowers: List<CalculationResultForTower>
) {}