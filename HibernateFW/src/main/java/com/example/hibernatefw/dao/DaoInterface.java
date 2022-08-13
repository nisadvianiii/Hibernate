package com.example.hibernatefw.dao;

import javafx.collections.ObservableList;

public interface DaoInterface<T> {
    ObservableList<T> getData();
    void addData(T data);
    void setData(T data);
    void delData(T data);
}

