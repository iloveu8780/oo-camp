package com.github.oocamp.exercise;

import java.math.BigDecimal;

public class RandomIndependentEvent {

    private static final BigDecimal FULL_PROBABILITY = new BigDecimal(1);
    private static final BigDecimal ZERO_PROBABILITY = new BigDecimal(0);

    private double probability;

    public RandomIndependentEvent(double probability) {
        checkProbability(probability);
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

    private void checkProbability(double probability) {
        BigDecimal decimal = new BigDecimal(probability);
        if(FULL_PROBABILITY.compareTo(decimal) < 0 || ZERO_PROBABILITY.compareTo(decimal) > 0) {
            throw new IllegalArgumentException("事件概率应在[0,1]区间内");
        }
    }
}
