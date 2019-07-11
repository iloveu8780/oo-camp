package com.github.oocamp.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomIndependentEventTest {

    @Test
    public void should_return_an_and_event() {
        RandomIndependentEvent eventA = new RandomIndependentEvent(0.5);
        RandomIndependentEvent eventB = new RandomIndependentEvent(0.4);

        RandomIndependentEvent andEvent = eventA.and(eventB);

        assertEquals(0.2, andEvent.getProbability(), 1e-4);

    }

    @Test
    public void should_return_an_or_event() {
        RandomIndependentEvent eventA = new RandomIndependentEvent(0.5);
        RandomIndependentEvent eventB = new RandomIndependentEvent(0.4);

        RandomIndependentEvent orEvent = eventA.or(eventB);

        assertEquals(0.7, orEvent.getProbability(), 1.0e-4);
    }

    @Test
    public void should_return_not_event() {
        RandomIndependentEvent eventA = new RandomIndependentEvent(0.4);

        RandomIndependentEvent notEvent = eventA.not();

        assertEquals(0.6, notEvent.getProbability(), 1.0e-4);
    }

    @Test
    public void should_throw_exception_when_probability_is_smaller_than_0() {
        assertThrows(IllegalArgumentException.class,
                () -> new RandomIndependentEvent(-1),
                "probability should be in [0,1]");
    }

    @Test
    public void should_throw_exception_when_probability_is_great_than_1() {
        assertThrows(IllegalArgumentException.class,
            () -> new RandomIndependentEvent(2),
            "probability should be in [0,1]");
    }





}