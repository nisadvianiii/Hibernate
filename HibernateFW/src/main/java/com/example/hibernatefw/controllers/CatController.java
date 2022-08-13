package com.example.hibernatefw.controllers;

import com.example.hibernatefw.dao.CategoryDao;
import com.example.hibernatefw.model.CategoryEntity;
import com.example.hibernatefw.model.ItemsEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class CatController {
    public TextField txtID;
    public TextField txtName;
    public Button btnSave;
    public TableView<CategoryEntity> table1;
    public TableColumn<CategoryEntity, Integer> kolom1;
    public TableColumn<CategoryEntity, String> kolom2;
    private ObservableList<CategoryEntity> clist;
    private CategoryDao categoryDao;

    private MainController mainController;

    public void initialize() {
        categoryDao = new CategoryDao();
        kolom1.setCellValueFactory(new PropertyValueFactory<CategoryEntity, Integer>("id"));
        kolom2.setCellValueFactory(new PropertyValueFactory<CategoryEntity, String>("name"));
    }

    public void setClist(ObservableList<CategoryEntity> clist){
        clist = categoryDao.getData();
        table1.setItems(clist);
    }

    public void setMainController(MainController controller) {
        mainController = controller;
    }

    private boolean check() {
        boolean result = true;
        if (txtID.getText().isEmpty() || txtName.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Isi semua field");
            alert.show();
            result = false;
        }
        return result;
    }

    public void reset() {
        txtID.setText("");
        txtName.setText("");
    }

    public void pilih(MouseEvent mouseEvent) {
        if (!table1.getSelectionModel().getSelectedCells().isEmpty()) {
            CategoryEntity c = table1.getSelectionModel().getSelectedItem();
            txtID.setText(c.getId().toString());
            txtName.setText(c.getName());
        }
    }
    public void onTambah(ActionEvent actionEvent) {
        if (check()) {
            CategoryEntity category = new CategoryEntity(
                    Integer.parseInt(txtID.getText()),
                    txtName.getText()
            );

            categoryDao.addData(category);
            clist = categoryDao.getData();
            table1.setItems(clist);

            reset();
        }
    }
    public void onEdit(ActionEvent actionEvent) {
        if (check()) {
            CategoryEntity c = table1.getSelectionModel().getSelectedItem();

            c.setId(Integer.parseInt(txtID.getText()));
            c.setName(txtName.getText());

            categoryDao.setData(c);
            clist = categoryDao.getData();
            table1.setItems(clist);
            reset();
        }
    }

    public void onHapus (ActionEvent actionEvent) {
        if (check()) {
            CategoryEntity c = table1.getSelectionModel().getSelectedItem();

            categoryDao.delData(c);
            clist = categoryDao.getData();
            table1.setItems(clist);
            reset();
        }
    }
}
