package com.recordshop.catalog.domain.record;

public class InvalidRecordFilterException extends RuntimeException {
    public InvalidRecordFilterException(String msg) {
        super(msg);
    }
}
