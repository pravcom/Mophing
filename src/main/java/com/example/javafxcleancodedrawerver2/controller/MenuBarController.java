package com.example.javafxcleancodedrawerver2.controller;

import com.example.javafxcleancodedrawerver2.model.MenuBarModel;

public class MenuBarController {
    MenuBarModel menuBarModel;

    public MenuBarController(MenuBarModel menuBarModel) {
        this.menuBarModel = menuBarModel;
    }

    public void pressedOnMorphing(){
        System.out.println("Морфинг нажат");
    }
    public void pressedOnClear()
    {

    }
}
