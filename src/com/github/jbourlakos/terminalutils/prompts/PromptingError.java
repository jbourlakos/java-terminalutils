/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.jbourlakos.terminalutils.prompts;

/**
 *
 * @author jbourlakos
 */
public class PromptingError extends GenericError {

    /**
     * Creates a new instance of <code>PromptingError</code> without detail message.
     */
    public PromptingError() {
    }


    /**
     * Constructs an instance of <code>PromptingError</code> with the specified detail message.
     * @param msg the detail message.
     */
    public PromptingError(String msg) {
        super(msg);
    }

    public PromptingError(Throwable ex) {
        super(ex);
    }
}
