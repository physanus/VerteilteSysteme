package de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.function.Function;

public class MathResult<T> implements Serializable {

    private final Status status;
    private final T data;


    public MathResult(Status status, T data) {
        this.status = status;
        this.data = data;
    }


    public T getResult() throws MathException {
        if(status == Status.SUCCESS)
            return data;
        else
            throw new MathException(data.toString());
    }


    public void printResult(PrintStream printStream) throws MathException {
        printStream.println(getResult());
    }


    public void printResult(PrintStream printStream, Function<String, String> function) throws MathException {
        printStream.println(function.apply(getResult().toString()));
    }


    @Override
    public String toString() {
        return "MathResult{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }

    public enum Status {
        SUCCESS,
        ERROR
    }

}
