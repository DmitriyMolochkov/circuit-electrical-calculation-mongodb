package com.courseproject.circuitelectricalcalculation.model

import com.fasterxml.jackson.annotation.JsonIgnore
import flanagan.complex.Complex

class ComplexNumber () {
    //@JsonIgnore
    //val id : Long = 0

    var real = 0.0
    var imag = 0.0

    //@get:JsonIgnore
    val abs: Double
        get() = Math.sqrt(Math.pow(real, 2.0) + Math.pow(imag, 2.0))

    constructor(_real: Double, _imag: Double) : this() {
        real = _real
        imag = _imag
    }

    constructor(complexNumber: Complex) : this() {
        real = complexNumber.real
        imag = complexNumber.imag
    }

}