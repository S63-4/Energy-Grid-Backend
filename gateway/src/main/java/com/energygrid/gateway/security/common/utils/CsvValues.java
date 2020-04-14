package com.energygrid.gateway.security.common.utils;

public enum CsvValues {
    NET_PROVIDER(0),
    STREET(1),
    HOUSE_NUMBER(2),
    ZIPCODE(3),
    CITY(4),
    CONSUME(5),
    PRODUCE(6);

    private final int value;

    private CsvValues(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
