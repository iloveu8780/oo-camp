package com.github.oocamp.exercise


import spock.lang.Specification

class LocaleUtilsTest extends Specification {
    LocaleUtils localeUtils = new LocaleUtils()

    def "should return null given a null string"() {
        given:
        def str = null
        def expected = null

        when:
        def actual = localeUtils.toLocale(str)

        then:
        actual == expected
    }

    def "should return locale with empty country and empty language given an empty string"() {
        given:
        def str = ""
        def expected = new Locale("", "")

        when:
        def actual = localeUtils.toLocale(str)

        then:
        actual == expected
    }

    def "should throw Illegal argument exception given an invalid string"() {
        when:
        localeUtils.toLocale(str)

        then:
        def exception = thrown(IllegalArgumentException)
        exception.getMessage() == expectedExceptionMeaage

        where:
        str        | expectedExceptionMeaage
        "123#456"  | "Invalid locale format: 123#456"
        "L"        | "Invalid locale format: L"

        "_p"       | "Invalid locale format: _p"

        "_tddNo"   | "Invalid locale format: _tddNo"
        "_Tdd"     | "Invalid locale format: _Tdd"
        "_tDd"     | "Invalid locale format: _tDd"

        "_TDd"     | "Invalid locale format: _TDd"

        "_TDAF"    | "Invalid locale format: _TDAF"

        "ttTT"     | "Invalid locale format: ttTT"
        "ttuuii"   | "Invalid locale format: ttuuii"

        "TT_tt"    | "Invalid locale format: TT_tt"
        "t_tt"     | "Invalid locale format: t_tt"
        "tt_t"     | "Invalid locale format: tt_t"
        "tt_T"     | "Invalid locale format: tt_T"

        "Ty_y_y"   | "Invalid locale format: Ty_y_y"
        "y_yP_yP"  | "Invalid locale format: y_yP_yP"
        "yy_yP_yP" | "Invalid locale format: yy_yP_yP"
        "yy_123_"  | "Invalid locale format: yy_123_"
        "yy__"     | "Invalid locale format: yy__"
        "yy_AB_"   | "Invalid locale format: yy_AB_"
    }

    def "should return a locale with empty language and nonempty country when the length of string is 3"() {
        given:
        def str = "_TD"
        def expectedCountry = "TD"
        def expectedLanguage = ""

        when:
        def actualLocale = localeUtils.toLocale(str)

        then:
        actualLocale.country == expectedCountry
        actualLocale.language == expectedLanguage
    }

    def "should return a locale with nonempty country and empty language and variant"() {
        given:
        def str = "_TD_12345"
        def expectedCountry = "TD"
        def expectedLanguage = ""
        def expectedVariant = "12345"

        when:
        def actualLocale = localeUtils.toLocale(str)

        then:
        actualLocale.country == expectedCountry
        actualLocale.language == expectedLanguage
        actualLocale.variant == expectedVariant
    }

    def "should return a locale with ISO639 language code country and nonempty language and variant"() {
        when:
        localeUtils.toLocale(str)

        then:
        actualLocale.country == ""
        actualLocale.language == str
        actualLocale.variant == ""

        where:
        str   || actualLocale
        "ptp" || new Locale("ptp")
        "tt"  || new Locale("tt")
    }

    def "should return a locale with nonempty language and nonempty country"() {
        when:
        def actualLocale = localeUtils.toLocale(str)

        then:
        actualLocale.language == expectedLanguage
        actualLocale.country == expectedCounttry
        actualLocale.variant == ""

        where:
        str       | expectedLanguage | expectedCounttry
        "qq_AA"   | "qq"             | "AA"
        "qq_123"  | "qq"             | "123"
        "qqq_123" | "qqq"            | "123"
        "qqq_AA"  | "qqq"            | "AA"
    }

    def "should return a locale with nonempty language and nonempty country and nonempty variant"() {
        when:
        def actualLocale = localeUtils.toLocale(str)

        then:
        actualLocale.language == expectedLanguage
        actualLocale.country == expectedCounttry
        actualLocale.variant == expectedVariant

        where:
        str         | expectedLanguage | expectedCounttry | expectedVariant
        "qq_AA_123" | "qq"             | "AA"             | "123"
        "qq_123_123" | "qq"             | "123"             | "123"
        "qqq__123"  | "qqq"            | ""               | "123"
    }
}
