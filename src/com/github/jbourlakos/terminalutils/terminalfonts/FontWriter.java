/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jbourlakos.terminalutils.terminalfonts;

import java.io.PrintStream;
import java.util.HashMap;

/**
 *
 * @author jbourlakos
 */
public class FontWriter {
    
    private PrintStream output;
    private HashMap<Character, Character[][]> mapper;
    
    public FontWriter(HashMap<Character, Character[][]> mapper, PrintStream output) {
        this.mapper = mapper;
    }
    
    public void print(String msg) {
        boolean finished = false;
        while (true) {
            if (finished) break;
            
        }
    }
    
}
