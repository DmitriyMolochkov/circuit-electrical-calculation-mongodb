package com.courseproject.circuitelectricalcalculation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class NetworkTopologyValidator implements
        ConstraintValidator<NetworkTopologyConstraint, List<Integer[]>> {

    @Override
    public void initialize(NetworkTopologyConstraint networkTopologyConstraint) {
    }


    @Override
    public boolean isValid(List<Integer[]> networkTopology, ConstraintValidatorContext cxt) {
        if (!(networkTopology.size() > 0))
            return false;


        int columnNumber = networkTopology.get(0).length;
        if (!(networkTopology.stream().allMatch(x -> x.length == columnNumber &&
                Arrays.stream(x).allMatch(y -> y <= 1 && y >= -1)))
        )
            return false;

        return true;
    }

}