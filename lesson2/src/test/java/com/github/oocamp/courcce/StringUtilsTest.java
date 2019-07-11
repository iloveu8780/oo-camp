package com.github.oocamp.courcce;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilsTest {

    private StringUtils stringUtils;

    @Before
    public void setUp() {
        stringUtils = new StringUtils("");
    }

    @Test
    public void should_be_empty_given_a_null_value() {
        assertThat(stringUtils.isEmpty(null)).isTrue();
    }

    @Test
    public void should_be_empty_when_length_is_zero() {
        assertThat(stringUtils.isEmpty("")).isTrue();
    }

    @Test
    public void should_be_blank_given_a_null_value() {
        assertThat(stringUtils.isBlank(null)).isTrue();
    }

    @Test
    public void should_be_blank_when_length_is_zero() {
        assertThat(stringUtils.isBlank("")).isTrue();
    }
    @Test
    public void should_be_blank_given_string_that_only_contains_white_spaces() {
        assertThat(stringUtils.isBlank("     ")).isTrue();
    }



    @Test
    public void should_not_be_blank_given_string_that_only_contains_non_blank_char() {
        assertThat(stringUtils.isBlank("   A  ")).isFalse();
    }

    @Test
    public void should_not_be_alpha_given_an_empty_string() {
        assertThat(stringUtils.isAlpha("")).isFalse();
    }

    @Test
    public void should_not_be_alpha() {
        assertThat(stringUtils.isAlpha("######")).isFalse();
    }

    @Test
    public void should_be_alpha() {
        assertThat(stringUtils.isAlpha("abc")).isTrue();
    }

}
