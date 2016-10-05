package com.github.jbourlakos.terminalutils.prompts;

import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Prompter {

    private PrintStream out;
    private InputStream in;
    private PrintStream err;
    private PrintStream log;
    private Symbols sym;
    private Messages mes;
    private Scanner s;

    /**
     *
     */
    public Prompter() {
        this.out = System.out;
        this.in = System.in;
        this.err = System.err;
        this.log = System.err;
        this.sym = new Symbols();
        this.mes = new Messages();
    }


    public Prompter(Messages m, Symbols s) {
        this.out = System.out;
        this.in = System.in;
        this.err = System.err;
        this.log = System.err;
        this.sym = s;
        this.mes = m;
    }

    private String defineTypeName(Class c) throws IllegalParseTypeException {
        String result = c.getCanonicalName();
        if (c.equals(String.class)) {
        // String -> Line
            result = "Line";
        } else if (c.isPrimitive()) {
            // primitives -> first letter upper-case
            result = c.getSimpleName();
            result = new StringBuffer().append(Character.toUpperCase(result.charAt(0))).append(result.substring(1, result.length())).toString();
        } else if (c.equals(Integer.class)) {
            // Integer -> Int
            result = "Int";
        } else if (c.equals(AtomicInteger.class) || (c.equals(AtomicLong.class))) {
            // some subtypes of Number that are not acceptable
            throw new IllegalParseTypeException("Tried to parse a " + c.getCanonicalName());
        } else if (c.getSuperclass().equals(Number.class) || c.equals(Boolean.class)) {
            // the rest of Number subtypes + boolean
            result = c.getSimpleName();
        } else {
            // every other Object
            throw new IllegalParseTypeException("Tried to parse a " + c.getCanonicalName());
        }
        return result;
    }

    private <T> T dispatchCallToScanner(T var) throws IllegalParseArgumentException, IllegalParseTypeException {
        T result = null;
        Method m = null;
        String typeName = null;
        typeName = defineTypeName(var.getClass());
        try {
            m = this.s.getClass().getDeclaredMethod("next" + typeName);
        } catch (NoSuchMethodException ex) {
            throw new IllegalParseTypeException(ex);
        } catch (SecurityException ex) {
            throw new GenericError(ex);
        }
        try {
            result = (T) m.invoke(this.s);
        } catch (IllegalAccessException ex) {
            throw new GenericError(ex);
        } catch (InvocationTargetException ex) {
            throw new IllegalParseTypeException(ex);
        } catch (IllegalArgumentException ex) {
            throw new IllegalParseArgumentException(ex);
        }
        // TODO--- this.out.println("OK!");
        return result;
    }

    public <T> T read(T var, String msg) {
        return read(var,msg,false,null);
    }

    public <T> T read(T var, String msg, Validator<T> v) {
        return read(var,msg,false,v);
    }
    
    public <T> T read(T var, String msg, boolean withNewLine, Validator<T> v) {
        T result = null;
        boolean errorOccured = false;
        do {
            errorOccured = false;
            writeInput(msg,withNewLine);
            this.s = new Scanner(in);
            try {
                result = dispatchCallToScanner(var);
            } catch (IllegalParseArgumentException ex) {
                throw new NullPointerException();
            } catch (IllegalParseTypeException ex) {
                errorOccured = true;
                this.writeError(this.mes.unparsedValue);
            }
            if ((!errorOccured) && (v != null) && (!v.validate(result))) {
                errorOccured = true;
                this.writeError(this.mes.invalidInput);
                this.writeInfo(v.getErrorMessage());
            }
        } while (errorOccured);
        return result;
    }

    /**
     *
     * @param msg
     * @return
     */
    public String readString(String msg) {
        String result = "";
        result = this.read(result, msg, null);
        return result;
    }

    /**
     *
     * @param msg
     * @return
     */
    public int readInt(String msg) {
        int result = 0;
        result = read(result, msg, null);
        return result;
    }

    /**
     *
     * @param msg
     * @param withNewLine
     */
    public void writeError(String msg, boolean withNewLine) {
        String nl = (withNewLine) ? sym.nl : "";
        out.print(sym.error + ' ' + msg + nl);
    }

    /**
     *
     * @param msg
     */
    public void writeError(String msg) {
        writeError(msg, true);
    }

    /**
     *
     * @param msg
     * @param withNewLine
     */
    public void writeInfo(String msg, boolean withNewLine) {
        String nl = (withNewLine) ? sym.nl : "";
        out.print(sym.info + ' ' + msg + nl);
    }

    /**
     *
     * @param msg
     */
    public void writeInfo(String msg) {
        writeInfo(msg, true);
    }

    public void writeInput(String msg, boolean withNewLine) {
        String nl = (withNewLine) ? sym.nl : "";
        out.print(sym.input + ' ' + msg + sym.inputTail + ' ');
    }
    
    public void writeInput(String msg) {
        writeInput(msg,false);
    }

    public void writeLine(String line) {
        out.println(line);
    }

    public void writeLine() {
        this.writeLine("");
    }
    
    public PrintStream getOut() {
        return this.out;
    }
}
