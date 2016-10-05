/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.jbourlakos.terminalutils.menus;

/**
 *
 * @author jbourlakos
 */
public class MenuItem implements Action {
    private String title;
    private Menu parent;
    private Action action;

    public MenuItem(Menu parent, String title, Action action) {
        this.parent = parent;
        this.title = title;
        this.action = action;
    }

    public MenuItem(String title, Action action) {
        this(null,title,action);
    }

    public MenuItem(Menu parent, String title) {
        this(parent,title,null);
    }

    public void run() {
        if (action != null) {
            action.run();
        }
    }

    public void setAction(Action action) {
        this.action = action;
    }


    public void setParent(Menu menu) {
        this.parent = menu;
    }

    @Override
    public String toString() {
        return this.title;
    }

    public String getTitle() {
        return this.title;
    }

    public Menu getParent() {
        return this.parent;
    }

}
