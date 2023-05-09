package com.courseproject.circuitelectricalcalculation.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("calculation")
class Calculation (
    @JsonManagedReference val networkTopology: List<Array<Int>>,
    @JsonManagedReference val branchResistances: List<ComplexNumber>,
    @JsonManagedReference val branchEVMs: List<ComplexNumber>,
    @JsonManagedReference val branchCurrentSources: List<ComplexNumber>,
    @JsonManagedReference val towersNumbers: List<String>,
    @JsonManagedReference val calculationPoints: List<String>,
    @JsonManagedReference val calculationResults: CalculationResults
) {
    @Id lateinit var id: ObjectId
}