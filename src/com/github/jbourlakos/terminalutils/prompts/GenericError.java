/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.jbourlakos.terminalutils.prompts;

/**
 *
 * @author jbourlakos
 */
public class GenericError extends Error {

    public GenericError(String msg, Throwable t) {
        super(msg,t);
    }

    public GenericError(Throwable t) {
        this(null,t);
    }

    public GenericError(String msg) {
        this(msg,null);
    }

    public GenericError() {
        this(null,null);
    }

}
