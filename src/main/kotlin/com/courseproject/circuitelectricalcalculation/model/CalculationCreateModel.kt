package com.courseproject.circuitelectricalcalculation.model

import com.courseproject.circuitelectricalcalculation.validator.CalculationCreateModelConstraint
import com.courseproject.circuitelectricalcalculation.validator.NetworkTopologyConstraint


@CalculationCreateModelConstraint
public data class CalculationCreateModel(
    public @NetworkTopologyConstraint val networkTopology: List<Array<Int>>,
    val branchResistances: List<ComplexNumber>,
    val branchEVMs: List<ComplexNumber>,
    val branchCurrentSources: List<ComplexNumber>,
    val towersNumbers: List<String>,
    val calculationPoints: List<String>
)