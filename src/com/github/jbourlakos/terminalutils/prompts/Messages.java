package com.github.jbourlakos.terminalutils.prompts;

public class Messages {

    private static final String DEFAULT_INVALID_INPUT = "The value is not valid.";
    private static final String DEFAULT_UNPARSED_VALUE = "The value you typed is not acceptable";

    public String invalidInput;
    public String unparsedValue;

    public Messages() {
        this.invalidInput = DEFAULT_INVALID_INPUT;
        this.unparsedValue = DEFAULT_UNPARSED_VALUE;
    }

    
}
