package de.danielprinz.hskl.verteiltesysteme.MathOpSrv.math;

import java.io.PrintStream;

public class MathResult {

    private ResultType resultType;
    private boolean resultBoolean;
    private int resultInteger;

    public MathResult(boolean resultBoolean) {
        this.resultType = ResultType.BOOLEAN;
        this.resultBoolean = resultBoolean;
    }

    public MathResult(int resultInteger) {
        this.resultType = ResultType.INTEGER;
        this.resultInteger = resultInteger;
    }


    public void printResult(PrintStream printStream) {
        if(resultType == ResultType.BOOLEAN) {
            printStream.println(resultBoolean);
        } else {
            printStream.println(resultInteger);
        }
    }


    public boolean isResultBoolean() {
        return resultBoolean;
    }

    public int getResultInteger() {
        return resultInteger;
    }

    private enum ResultType {
        BOOLEAN,
        INTEGER
    }

}
