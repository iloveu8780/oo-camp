package com.github.oocamp.exercise;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

public class RandomIndependentEvent {

    @DecimalMin(value = "0")
    @DecimalMax(value = "1.0")
    private double probability;

    public RandomIndependentEvent(double probability) {
        this.probability = probability;
    }

    public double getProbability() {
        return probability;
    }

    public RandomIndependentEvent not() {
        return new RandomIndependentEvent(1 - probability);
    }

    public RandomIndependentEvent and(RandomIndependentEvent anotherEvent) {
        return new RandomIndependentEvent(probability * anotherEvent.getProbability());
    }

    public RandomIndependentEvent or(RandomIndependentEvent anotherEvent) {
        double anotherProbability = anotherEvent.getProbability();
        return new RandomIndependentEvent(probability + anotherProbability - probability * anotherProbability);
    }
}
