package com.hardisgroup.common.model;

public class Error {

    /**
     * Line.
     */
    private int line;

    /**
     * Message.
     */
    private String message;

    /**
     * Value.
     */
    private String value;

    /**
     * Default constructor.
     */
    public Error() {
    }

    /**
     * Constructor.
     *
     * @param line    line.
     * @param message message.
     * @param value   value.
     */
    public Error(int line, String message, String value) {
        this.line = line;
        this.message = message;
        this.value = value;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
