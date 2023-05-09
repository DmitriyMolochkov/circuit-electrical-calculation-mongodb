package com.courseproject.circuitelectricalcalculation.validator;

import com.courseproject.circuitelectricalcalculation.model.CalculationCreateModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CalculationCreateModelValidator implements
        ConstraintValidator<CalculationCreateModelConstraint, CalculationCreateModel> {

    @Override
    public void initialize(CalculationCreateModelConstraint calculationCreateModelConstraint) {
    }


    @Override
    public boolean isValid(CalculationCreateModel calculationCreateModel, ConstraintValidatorContext cxt) {
        int columnNumber = calculationCreateModel.getNetworkTopology().get(0).length;
        int rowNumber = calculationCreateModel.getNetworkTopology().size();

        if (
                calculationCreateModel.getBranchResistances().size() != columnNumber ||
                calculationCreateModel.getBranchEVMs().size() != columnNumber ||
                calculationCreateModel.getBranchCurrentSources().size() != columnNumber ||
                calculationCreateModel.getTowersNumbers().size() * 7 + 3 != columnNumber ||
                calculationCreateModel.getTowersNumbers().size() != calculationCreateModel.getTowersNumbers().stream().distinct().count() ||
                calculationCreateModel.getCalculationPoints().stream().anyMatch(x -> !calculationCreateModel.getTowersNumbers().contains(x))
        )
            return false;

        return true;
    }

}