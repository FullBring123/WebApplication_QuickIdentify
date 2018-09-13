/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.inject.Named;

/**
 *
 * @author Windows8.1
 */
@Named
public class Redirection {

    private int no;
    /**
     * Creates a new instance of page1
     */
    public Redirection() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
    
}
