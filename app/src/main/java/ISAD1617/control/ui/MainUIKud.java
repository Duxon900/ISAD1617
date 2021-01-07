package ISAD1617.control.ui;

import ISAD1617.control.Datu;
import ISAD1617.control.db.DBKudeatzaile;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainUIKud implements Initializable {

    @FXML
    private TableView<Datu> taula1;

    @FXML
    private TableView<Datu> taula2;

    @FXML
    private TableColumn<?, ?> zut1Izena;

    @FXML
    private TableColumn<?, ?> zut1Abizena;

    @FXML
    private TableColumn<?, ?> zut1Kirola;

    @FXML
    private TableColumn<?, ?> zut1Urte;

    @FXML
    private TableColumn<Datu, CheckBox> zut1Vegetta;

    @FXML
    private TableColumn<Datu, Image> zut1Argazki;

    @FXML
    private TableColumn<?, ?> zut2Izena;

    @FXML
    private TableColumn<?, ?> zut2Abizena;

    @FXML
    private TableColumn<?, ?> zut2Kirola;

    @FXML
    private TableColumn<?, ?> zut2Urte;

    @FXML
    private TableColumn<Datu, CheckBox> zut2Vegetta777;

    @FXML
    private TableColumn<Datu, Image> zut2Argazki;

    ObservableList<Datu> emaitza1= FXCollections.observableArrayList();
    ObservableList<Datu> emaitza2= FXCollections.observableArrayList();




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResultSet resultSet= DBKudeatzaile.getInstantzia().execSQL("select * from datuak");

        try {
            datuakSartu(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        kargatuTaula1();
        kargatuTaula2();


        taula2.setItems(emaitza2);

    }


    private void kargatuTaula1() {
        zut1Izena.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        zut1Abizena.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        zut1Kirola.setCellValueFactory(new PropertyValueFactory<>("sport"));
        zut1Urte.setCellValueFactory(new PropertyValueFactory<>("numyears"));

        //kargatu checkbox
        zut1Vegetta.setCellValueFactory(new PropertyValueFactory<Datu, CheckBox>("vegetarian"));
        zut1Vegetta.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Datu, CheckBox>, ObservableValue<CheckBox>>(){

            @Override
            public ObservableValue<CheckBox> call(
                    TableColumn.CellDataFeatures<Datu, CheckBox> arg0) {
                Datu datu = arg0.getValue();

                CheckBox checkBox = new CheckBox();

                checkBox.selectedProperty().setValue(datu.isVegetarian());



                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> ov,
                                        Boolean old_val, Boolean new_val) {

                        datu.setVegetarian(new_val);

                    }
                });

                return new SimpleObjectProperty<CheckBox>(checkBox);

            }
        });

        //Argazki zutabea kargatu
        zut1Argazki.setCellValueFactory(new PropertyValueFactory<>("argazkia"));

        zut1Argazki.setCellFactory(p -> new TableCell<>() {
            public void updateItem(Image image, boolean empty) {
                if (image != null && !empty){
                    final ImageView imageview = new ImageView();
                    imageview.setFitHeight(30);
                    imageview.setFitWidth(50);
                    imageview.setImage(image);
                    setGraphic(imageview);
                    setAlignment(Pos.CENTER);
                }else{
                    setGraphic(null);
                    setText(null);
                }
            };
        });
    }

    private void kargatuTaula2() {
        zut2Izena.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        zut2Abizena.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        zut2Kirola.setCellValueFactory(new PropertyValueFactory<>("sport"));
        zut2Urte.setCellValueFactory(new PropertyValueFactory<>("numyears"));

        //kargatu checkbox
        zut2Vegetta777.setCellValueFactory(new PropertyValueFactory<Datu, CheckBox>("vegetarian"));
        zut2Vegetta777.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Datu, CheckBox>, ObservableValue<CheckBox>>(){

            @Override
            public ObservableValue<CheckBox> call(
                    TableColumn.CellDataFeatures<Datu, CheckBox> arg0) {
                Datu datu = arg0.getValue();

                CheckBox checkBox = new CheckBox();

                checkBox.selectedProperty().setValue(datu.isVegetarian());



                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> ov,
                                        Boolean old_val, Boolean new_val) {

                        datu.setVegetarian(new_val);

                    }
                });

                return new SimpleObjectProperty<CheckBox>(checkBox);

            }
        });

        //Argazki zutabea kargatu
        zut2Argazki.setCellValueFactory(new PropertyValueFactory<>("argazkia"));

        zut2Argazki.setCellFactory(p -> new TableCell<>() {
            public void updateItem(Image image, boolean empty) {
                if (image != null && !empty){
                    final ImageView imageview = new ImageView();
                    imageview.setFitHeight(30);
                    imageview.setFitWidth(50);
                    imageview.setImage(image);
                    setGraphic(imageview);
                    setAlignment(Pos.CENTER);
                }else{
                    setGraphic(null);
                    setText(null);
                }
            };
        });
    }


    private void datuakSartu(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {
            Datu datu=new Datu();
            datu.setFirstname(resultSet.getString("firstname"));
            datu.setLastname(resultSet.getString("lastname"));
            datu.setSport(resultSet.getString("sport"));
            datu.setNumyears(resultSet.getInt("numyears"));
            datu.setVegetarian(resultSet.getInt("vegetarian")==1? true : false);
            datu.setArgazkia(new Image("isad1617.png"));
            emaitza1.add(datu);
        }
        taula1.setItems(emaitza1);
    }

    @FXML
    void onClickGorde(ActionEvent event) {

    }

    @FXML
    void onClickKendu(ActionEvent event) {

    }

    @FXML
    void onClickSartu(ActionEvent event) {

        Datu datu=taula1.getSelectionModel().getSelectedItem();

        if(datu==null) {
            datu=taula1.getItems().get(0);
            emaitza2.add(datu);
            emaitza1.remove(0);
        }
        else{
            emaitza2.add(datu);
            emaitza1.remove(datu);
        }


    }
}
