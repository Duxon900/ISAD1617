package ISAD1617.control.ui;

import ISAD1617.control.Datu;
import ISAD1617.control.db.DBKudeatzaile;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainUIKud implements Initializable {

    @FXML
    private TableView<Datu> taula1;

    @FXML
    private TableView<?> taula2;

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
    private TableColumn<?, ?> zut1Argazki;

    @FXML
    private TableColumn<?, ?> zut2Izena;

    @FXML
    private TableColumn<?, ?> zut2Kirola;

    @FXML
    private TableColumn<?, ?> zut2Urte;

    @FXML
    private TableColumn<?, ?> zut2Vegetta777;

    @FXML
    private TableColumn<?, ?> zut2Argazki;

    ObservableList<Datu> emaitza1= FXCollections.observableArrayList();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResultSet resultSet= DBKudeatzaile.getInstantzia().execSQL("select * from datuak");

        try {
            datuakSartu(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        zut1Izena.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        zut1Abizena.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        zut1Kirola.setCellValueFactory(new PropertyValueFactory<>("sport"));
        zut1Urte.setCellValueFactory(new PropertyValueFactory<>("numyears"));
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
        //zut1Argazki.setCellValueFactory(new PropertyValueFactory<>("argazkia"));
    }

    private void datuakSartu(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {
            Datu datu=new Datu();
            datu.setFirstname(resultSet.getString("firstname"));
            datu.setLastname(resultSet.getString("lastname"));
            datu.setNumyears(resultSet.getInt("numyears"));
            datu.setVegetarian(resultSet.getInt("vegetarian")==1? true : false);
            emaitza1.add(datu);
        }
        taula1.setItems(emaitza1);

    }
}
