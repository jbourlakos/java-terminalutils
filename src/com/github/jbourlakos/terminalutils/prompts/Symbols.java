package com.github.jbourlakos.terminalutils.prompts;

public class Symbols {

    private static final String DEFAULT_NL = System.getProperty("line.separator");

    private static final String DEFAULT_INPUT_SYMBOL = "|>|";
    private static final String DEFAULT_INPUT_PERIOD = ":";
    private static final String DEFAULT_QUESTION_PERIOD = "?";
    private static final String DEFAULT_INFO_SYMBOL = "|i|";
    private static final String DEFAULT_INFO_PERIOD = ".";
    private static final String DEFAULT_ERROR_SYM = "|x|";
    
    public String input;
    public String inputTail;
    public String nl;
    public String info;
    public String error;

    public Symbols() {
        this.input = DEFAULT_INPUT_SYMBOL;
        this.inputTail = DEFAULT_INPUT_PERIOD;
        this.nl = DEFAULT_NL;
        this.info = DEFAULT_INFO_SYMBOL;
        this.error = DEFAULT_ERROR_SYM;
    }
}
