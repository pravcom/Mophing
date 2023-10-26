package com.example.javafxcleancodedrawerver2.model;

import javafx.scene.control.Slider;

public class MainModel {
    PointModel pointModel;
    ShapeModel shapeModel;
    MenuBarModel menuBarModel;

    public MainModel() {
        this.pointModel = new PointModel();
        this.shapeModel = new ShapeModel();
        this.menuBarModel = new MenuBarModel();
    }

    public PointModel getPointModel() {
        return pointModel;
    }

    public ShapeModel getShapeModel() {
        return shapeModel;
    }

    public MenuBarModel getMenuBarModel() {
        return menuBarModel;
    }


}
