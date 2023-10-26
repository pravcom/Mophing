package com.example.javafxcleancodedrawerver2.controller;

import com.example.javafxcleancodedrawerver2.model.MainModel;

public class MainController {
    MainModel mainModel;
    PointController pointController;
    ShapeController shapeController;
    MenuBarController menuBarController;
    public MainController(MainModel mainModel) {
        this.mainModel = mainModel;
        this.pointController = new PointController(mainModel.getPointModel());
        this.shapeController = new ShapeController(mainModel.getShapeModel());
        this.menuBarController = new MenuBarController(mainModel.getMenuBarModel());
    }

    public PointController getPointController() {
        return pointController;
    }

    public ShapeController getShapeController() {
        return shapeController;
    }

    public MenuBarController getMenuBarController() {
        return menuBarController;
    }

}
