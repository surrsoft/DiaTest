package ru.surrsoft.baaz;

/**
 * ИМЯ: [[w285w]]
 * <p>
 * ВЕРСИЯ 1 05-07-2016 (stored)
 */
public class SomeException extends RuntimeException {
    public SomeException() {
    }

    public SomeException(String detailMessage) {
        super(detailMessage);
    }

    public SomeException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public SomeException(Throwable throwable) {
        super(throwable);
    }
}
