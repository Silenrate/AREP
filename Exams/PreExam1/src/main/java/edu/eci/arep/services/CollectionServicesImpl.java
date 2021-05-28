package edu.eci.arep.services;

import edu.eci.arep.model.Result;
import edu.eci.arep.sorts.BubbleSort;

import java.util.List;
import java.util.OptionalDouble;

/**
 * Class that implements the Services Required for the App in the collection.
 * Author: Daniel Felipe Walteros Trujillo
 */
public class CollectionServicesImpl implements CollectionServices {

    /**
     * Calculate the average of a result object.
     *
     * @param result The object with tha set of values
     */
    @Override
    public void calculateAverage(Result result) {
        List<Double> values = result.getValues();
        OptionalDouble average = values.stream()
                .mapToDouble(Double::doubleValue).average();
        double avg = 0.0;
        if (average.isPresent()) {
            avg = average.getAsDouble();
        }
        result.setAverage(avg);
    }

    /**
     * Calculate the sum of a result object.
     *
     * @param result The object with tha set of values
     */
    @Override
    public void calculateSum(Result result) {
        List<Double> values = result.getValues();
        Double sum = values.stream()
                .reduce(0.0, Double::sum);
        result.setSum(sum);
    }

    /**
     * Orders the values of a result object.
     *
     * @param result The object with tha set of values
     */
    @Override
    public void sort(Result result) {
        BubbleSort<Double> bubbleSorter = new BubbleSort<>();
        List<Double> orderedValues = bubbleSorter.bubbleSort(result.getValues());
        result.setValues(orderedValues);
    }
}
