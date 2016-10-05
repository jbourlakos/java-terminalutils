/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.jbourlakos.terminalutils.prompts;

/**
 *
 * @author jbourlakos
 */
public class IllegalParseTypeException extends GenericException {

    /**
     * Creates a new instance of <code>IllegalParseTypeException</code> without detail message.
     */
    public IllegalParseTypeException() {
        this(null,null);
    }

    public IllegalParseTypeException(String msg, Throwable t) {
        super(msg, t);
    }



    /**
     * Constructs an instance of <code>IllegalParseTypeException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public IllegalParseTypeException(String msg) {
        this(msg,null);
    }

    public IllegalParseTypeException(Throwable ex) {
        this(null,ex);
    }
}
