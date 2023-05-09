package com.courseproject.circuitelectricalcalculation.utils

import com.courseproject.circuitelectricalcalculation.model.*
import flanagan.complex.Complex
import flanagan.complex.ComplexMatrix

class CalculationUtils {

    private fun makeCalculation(calculationCreateModel: CalculationCreateModel): Pair<ComplexMatrix, ComplexMatrix> {
        val mNetworkTopology = ComplexMatrix(
            calculationCreateModel.networkTopology
                .map{x -> x.map{y -> y.toDouble()}.toDoubleArray()}
                .toTypedArray())

        val resistances =
            calculationCreateModel.branchResistances
                .map { x -> Complex(x.real, x.imag).pow(-1) }
                .toTypedArray()
        val mConductions = ComplexMatrix.diagonalMatrix(resistances.size, resistances)

        val EVMs =
            calculationCreateModel.branchEVMs
                .map { x -> Complex(x.real, x.imag) }
                .toTypedArray()
        val vEVMs = ComplexMatrix.columnMatrix(EVMs)

        val currentSources =
            calculationCreateModel.branchCurrentSources
                .map { x -> Complex(x.real, x.imag) }
                .toTypedArray()
        val vCurrentSources = ComplexMatrix.columnMatrix(currentSources)

        val towersNumbers = calculationCreateModel.towersNumbers.toTypedArray()
        val calculationPoints = calculationCreateModel.calculationPoints.toTypedArray()


        val mNodalConductions = mNetworkTopology * mConductions * mNetworkTopology.transpose()
        val vNodalCurrentSources = mNetworkTopology * (vCurrentSources - (mConductions * vEVMs))
        val vNodalPotential = mNodalConductions.inverse() * vNodalCurrentSources
        val vBranchesVoltage = mNetworkTopology.transpose().conjugate() * vNodalPotential
        val vPassiveBranchesVoltage = vBranchesVoltage + vEVMs
        val vPassiveBranchesCurrents = mConductions * vPassiveBranchesVoltage
        val vBranchesCurrents = vPassiveBranchesCurrents - vCurrentSources

        return Pair(vBranchesVoltage, vBranchesCurrents)
    }

    public fun getCalculationResults(calculationCreateModel: CalculationCreateModel): CalculationResults {
        val (vBranchesVoltage, vBranchesCurrents) = makeCalculation(calculationCreateModel)
        val resultForCTS = CalculationResultForCTS(
            ComplexNumber(vBranchesCurrents.getElementCopy(3, 0)),
            ComplexNumber(vBranchesCurrents.getElementCopy(5, 0)),
            ComplexNumber(vBranchesCurrents.getElementCopy(4, 0))
        )

        val resultsForTowers = calculationCreateModel.calculationPoints.map { p ->
            val index = calculationCreateModel.towersNumbers.indexOf(p)
            CalculationResultForTower(
                p,
                ComplexNumber(vBranchesVoltage.getElementCopy((index + 1) * 7, 0 )),
                ComplexNumber(vBranchesVoltage.getElementCopy((index + 1) * 7 + 2, 0)),
                ComplexNumber(vBranchesVoltage.getElementCopy((index + 1) * 7 + 1, 0)),
            )
        }

        return CalculationResults(
            vBranchesVoltage.getArrayCopy().map{ x -> ComplexNumber(x[0]) },
            vBranchesCurrents.getArrayCopy().map{ x -> ComplexNumber(x[0]) },
            resultForCTS,
            resultsForTowers
        );
    }

    public fun checkCalculationResults(calculationResults: CalculationResults): List<CheckResultView> {
        return calculationResults.resultsForTowers.map{ x -> CheckResultView(x) }
    }

}