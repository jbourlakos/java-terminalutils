/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jbourlakos.terminalutils.menus;

import java.util.Arrays;
import java.util.Hashtable;
import com.github.jbourlakos.terminalutils.prompts.Prompter;
import com.github.jbourlakos.terminalutils.prompts.Validator;

/**
 *
 * @author jbourlakos
 */
public class Menu<T extends Comparable> extends MenuItem implements Action {

    private Hashtable<T,MenuItem> map;
    
    private T quitKey;
    private String promptMsg;
    private Prompter prompter;
    private Validator validator;
    private MenuItem quitMenuItem;

    public Menu(Menu parent, String title, Prompter prompter, String promptMsg, String quitTitle, T quitKey) {
        super(parent, title);
        this.map = new Hashtable<T,MenuItem>();
        this.prompter = prompter;
        this.quitMenuItem = new MenuItem(this, quitTitle);
        this.quitKey = quitKey;
        this.promptMsg = promptMsg;
        this.initialize();
    }

    public Menu(Menu parent, String title, Prompter prompter, String promptMsg, T quitValue) {
        this(parent,title, prompter, promptMsg, (parent != null)?parent.toString():"", quitValue);
    }

    public Menu(String title, Prompter prompter, String promptMsg, String quitLabel, T quitValue) {
        this(null,title, prompter, promptMsg, quitLabel, quitValue);
    }


    private void initialize() {
        this.validator = new Validator<T>() {

            public boolean validate(T value) {
                if (value.equals(quitKey)) return true;
                if (map.keySet().contains(value)) return true;
                return false;
            }

            public String getErrorMessage() {
                return "Not an option.";
            }
        };
        
        this.setAction(new Action() {

            public void run() {
                T selection = null;
                while(true) {
                    prompter.writeLine();  // TODO: refine menu decoration
                    prompter.writeLine(getMenuPath());
                    prompter.writeLine();  // TODO: refine menu decoration
                    printMenu();
                    selection = (T)prompter.read(quitKey,promptMsg,validator);
                    if (selection.equals(quitKey)) break;
                    map.get(selection).run();
                    prompter.writeLine();  // TODO: refine menu decoration
                }
            }
        });
    }

    public void addMenuItem(T key, MenuItem item) {
        item.setParent(this);
        map.put(key, item);
    }

    public void addMenuItem(T key, String title, Action action) {
        this.addMenuItem(key, new MenuItem(title,action));
    }

    private void printMenu() {
        Object[] entries = map.keySet().toArray();
        Arrays.sort(entries);
        for (Object e : entries) {
            MenuItem mi = map.get(e);
            if ((mi != null)) {
                if (mi instanceof Menu) {
                    prompter.writeLine(e.toString()+' '+'['+mi.getTitle()+']');
                }
                else {
                    prompter.writeLine(e.toString()+' '+mi.getTitle());
                }
            }
        }
        prompter.writeLine(""+quitKey + ' ' + quitMenuItem.getTitle());
        
    }

    private String getMenuPath() {
        String result = new String();
        for (Menu currentMenu = this ; currentMenu != null; currentMenu = currentMenu.getParent()) {
            result = '>' + currentMenu.getTitle() + result;
        }
        return result.toString();
    }
}
