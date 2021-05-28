package edu.eci.arep.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Result Class For Mapping Values and Other Operations On A Set of Numbers.
 * Author: Daniel Felipe Walteros Trujillo
 */
public class Result {
    private List<Double> values;
    private double average;
    private double sum;

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    public void setValuesFromString(List<String> data) {
        List<Double> newValues = new ArrayList<>();
        for (String value : data) {
            newValues.add(Double.parseDouble(value));
        }
        this.values = newValues;
    }
}
