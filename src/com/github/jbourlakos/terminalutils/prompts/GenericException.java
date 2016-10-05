/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.jbourlakos.terminalutils.prompts;

/**
 *
 * @author jbourlakos
 */
public class GenericException extends Exception {

    public GenericException(String msg, Throwable t) {
        super(msg,t);
    }

    public GenericException(Throwable t) {
        this(null,t);
    }

    public GenericException(String msg) {
        this(msg,null);
    }

    public GenericException() {
        this(null,null);
    }

}
