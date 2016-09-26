package com.tofi.shop.dao;

/**
 * Basic exception for {@link com.tofi.shop.dao} package.
 */
@SuppressWarnings("unused")
public class DAOException extends Exception {
    private static final long serialVersionUID = 9142271879034124314L;

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, Throwable cause,
                        boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}