package com.dvinfosys.model;

import android.graphics.Color;

import androidx.annotation.ColorInt;

import java.util.ArrayList;
import java.util.List;

public class HeaderModel {
    String title;
    int resource = -1;
    int indicatorResource = -1;
    boolean isNew = false;
    boolean isCartBudget = false;
    int cartBudgetTextColor = Color.WHITE;
    boolean hasChild = false;
    boolean isSelected = false;

    List<ChildModel> childModelList = new ArrayList<>();

    public HeaderModel(String title) {
        this.title = title;
    }

    public HeaderModel(String title, int resource) {
        this.title = title;
        this.resource = resource;
    }

    public HeaderModel(String title, int resource, boolean hasChild) {
        this.title = title;
        this.resource = resource;
        this.hasChild = hasChild;
    }

    public HeaderModel(String title, int resource, boolean hasChild, boolean isNew, boolean isSelected) {
        this.title = title;
        this.resource = resource;
        this.isNew = isNew;
        this.hasChild = hasChild;
        this.isSelected = isSelected;
    }

    public HeaderModel(String title, int indicatorResource, boolean isCartBudget, boolean isNew, boolean hasChild, @ColorInt int CartTextColor) {
        this.title = title;
        this.indicatorResource = indicatorResource;
        this.isCartBudget = isCartBudget;
        this.cartBudgetTextColor = CartTextColor;
        this.isNew = isNew;
        this.hasChild = hasChild;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public int getCartBudgetTextColor() {
        return cartBudgetTextColor;
    }

    public void setCartBudgetTextColor(@ColorInt int cartBudgetTextColor) {
        this.cartBudgetTextColor = cartBudgetTextColor;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getIndicatorResource() {
        return indicatorResource;
    }

    public void setIndicatorResource(int indicatorResource) {
        this.indicatorResource = indicatorResource;
    }

    public HeaderModel addChildModel(ChildModel childModel) {
        this.childModelList.add(childModel);

        return this;
    }

    public boolean isCartBudget() {
        return isCartBudget;
    }

    public void setCartBudget(boolean cartBudget) {
        isCartBudget = cartBudget;
    }

    public List<ChildModel> getChildModelList() {
        return childModelList;
    }

    public void setChildModelList(List<ChildModel> childModelList) {
        this.childModelList = childModelList;
    }
}
