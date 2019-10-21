package de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math;

public class MathException extends Exception {

    public MathException(String message) {
        super(message);
    }

    public MathException(String message, Throwable cause) {
        super(message, cause);
    }

    public MathException(Throwable cause) {
        super(cause);
    }
}
