package com.example.javafxcleancodedrawerver2.model;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuBarModel extends MenuBar {
    List<Menu> menuList = new ArrayList<>();

    public MenuBarModel() {
        menuList.add(new Menu("Event" ));
        MenuItem morphing = new MenuItem  ("Morphing");
        MenuItem draw = new MenuItem  ("Draw");
        MenuItem clear = new MenuItem  ("Clear");

        menuList.forEach(m->{
            m.getItems().add(morphing);
            m.getItems().add(draw);
            m.getItems().add(clear);

            getMenus().add(m);
        });
    }

    public List<Menu> getMenuList() {
        return menuList;
    }
}
