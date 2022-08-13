package com.example.hibernatefw.controllers;

import com.example.hibernatefw.HelloApplication;
import com.example.hibernatefw.dao.CategoryDao;
import com.example.hibernatefw.dao.ItemsDao;
import com.example.hibernatefw.model.CategoryEntity;
import com.example.hibernatefw.model.ItemsEntity;
import com.example.hibernatefw.utility.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public MenuItem tampil;
    public MenuItem tutup;
    public TextField txtId;
    public TextField txtName;
    public TextField txtPrice;
    public TextField txtDesc;
    public ComboBox<CategoryEntity> cmbCategory;
    public Button btnSave;
    public Button btnReset;
    public TableView<ItemsEntity> table1;
    public TableColumn<ItemsEntity, Integer> kolom1;
    public TableColumn<ItemsEntity,String> kolom2;
    public TableColumn<ItemsEntity, Double> kolom3;
    public TableColumn<ItemsEntity, CategoryEntity> kolom4;

    private ObservableList<ItemsEntity> ilist;

    private ObservableList<CategoryEntity> clist;

    private ItemsDao itemsDao;

    private CategoryDao categoryDao;

    private CatController catController;

    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection = MyConnection.getConnection();
        stage = new Stage();
        itemsDao = new ItemsDao();
        categoryDao = new CategoryDao();

        ilist = FXCollections.observableArrayList();
        clist = FXCollections.observableArrayList();

        ilist.addAll(itemsDao.getData());
        clist.addAll(categoryDao.getData());

        table1.setItems(ilist);
        kolom1.setCellValueFactory(new PropertyValueFactory<ItemsEntity, Integer>("id"));
        kolom2.setCellValueFactory(new PropertyValueFactory<ItemsEntity, String>("name"));
        kolom3.setCellValueFactory(new PropertyValueFactory<ItemsEntity, Double>("price"));
        kolom4.setCellValueFactory(new PropertyValueFactory<ItemsEntity, CategoryEntity>("category"));
        cmbCategory.setItems(clist);
        cmbCategory.getSelectionModel().selectFirst();

    }

    public void onShow(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("layout2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),623, 400);
        catController = fxmlLoader.getController();
        catController.setClist(clist);
        catController.setMainController(this);
        stage.setTitle("Category Management");
        stage.setScene(scene);
        stage.show();
    }
    private boolean check() {
        boolean result = true;
        if (txtId.getText().isEmpty() || txtName.getText().isEmpty()|| txtPrice.getText().isEmpty() || txtDesc.getText().isEmpty() || cmbCategory.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Isi Semua Field");
            alert.show();
            result = false;
        }
        return result;
    }

    public void reset() {
        txtId.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtDesc.setText("");
        cmbCategory.getSelectionModel().selectFirst();
    }

    public void pilih(MouseEvent mouseEvent) {
        if (!table1.getSelectionModel().getSelectedCells().isEmpty()) {
            ItemsEntity i = table1.getSelectionModel().getSelectedItem();
            txtId.setText(i.getId().toString());
            txtName.setText(i.getName());
            txtPrice.setText(i.getPrice().toString());
            txtDesc.setText(i.getDescription());
            cmbCategory.getSelectionModel().select(i.getCategoryByCategoryId());
        }
    }

    public void onTambah(ActionEvent actionEvent) {
        if (check()) {
            ItemsEntity i = new ItemsEntity(
                    Integer.parseInt(txtId.getText()),
                    txtName.getText(),
                    Double.parseDouble(txtPrice.getText()),
                    txtDesc.getText(),
                    cmbCategory.getValue()
            );
            itemsDao.addData(i);
            ilist = itemsDao.getData();
            table1.setItems(ilist);
            reset();
        }
    }

    public void onReset(ActionEvent actionEvent) {
        reset();
        table1.getSelectionModel().clearSelection();
    }

    public void onEdit(ActionEvent actionEvent) {
        if (check()) {
            ItemsEntity i = table1.getSelectionModel().getSelectedItem();

            i.setId(Integer.parseInt(txtId.getText()));
            i.setName(txtName.getText());
            i.setPrice(Double.parseDouble(txtPrice.getText()));
            i.setDescription(txtDesc.getText());
            i.setCategoryByCategoryId(cmbCategory.getValue());

            itemsDao.setData(i);
            ilist = itemsDao.getData();
            table1.setItems(ilist);
            reset();
        }
    }

    public void onHapus (ActionEvent actionEvent) {
        if (check()) {
            ItemsEntity i = table1.getSelectionModel().getSelectedItem();

            itemsDao.delData(i);
            ilist = itemsDao.getData();
            table1.setItems(ilist);
            reset();
        }
    }

    public void onSimple(ActionEvent actionEvent) {
//        throws JRException {
//        Map parameter = new HashMap();
//        JasperPrint jasperPrint = JasperFillManager.fillReport("report/SimpleReport.jasper", parameter, connection);
//        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//        jasperViewer.setVisible(true);
    }

        public void onGroup (ActionEvent actionEvent){
//        throws JRException {
//                Map parameter = new HashMap();
//                JasperPrint jasperPrint = JasperFillManager.fillReport("report/GroupReport.jasper", parameter, connection);
//                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//                jasperViewer.setVisible(true);
//            }
        }
    }