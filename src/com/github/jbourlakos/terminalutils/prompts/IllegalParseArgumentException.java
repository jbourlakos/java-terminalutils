/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.jbourlakos.terminalutils.prompts;

/**
 *
 * @author jbourlakos
 */
public class IllegalParseArgumentException extends GenericException {

    /**
     * Creates a new instance of <code>IllegalParseTypeException</code> without detail message.
     */
    public IllegalParseArgumentException() {
        this(null,null);
    }

    public IllegalParseArgumentException(String msg, Throwable t) {
        super(msg, t);
    }



    /**
     * Constructs an instance of <code>IllegalParseTypeException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public IllegalParseArgumentException(String msg) {
        this(msg,null);
    }

    public IllegalParseArgumentException(Throwable ex) {
        this(null,ex);
    }
}
