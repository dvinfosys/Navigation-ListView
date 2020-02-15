package com.dvinfosys.model;

public class ChildModel {
    String title;
    boolean isSelected;

    public ChildModel(String title){
        this.title = title;
    }

    public ChildModel(String title, boolean isSelected){
        this.title = title;
        this.isSelected = isSelected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
