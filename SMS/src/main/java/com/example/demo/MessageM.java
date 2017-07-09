/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Nada
 */
public class MessageM {
    
    @NotNull
    String Num ;
    @NotNull
    String Msg ;
 
    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String Num) {
        this.Num = Num;
    }
    
}
