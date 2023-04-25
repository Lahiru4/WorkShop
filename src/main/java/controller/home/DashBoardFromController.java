package controller.home;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardFromController implements Initializable {
    public AnchorPane sallesCartA;
    public ComboBox salesTimeCombox;
    @FXML
    private ScrollPane sman;

    @FXML
    private AnchorPane man;

    @FXML
    private LineChart<?, ?> lineChartSalles;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniLineChartDay();
        salesTimeComboxSetData();
    }

    private void salesTimeComboxSetData() {
        ObservableList<String> data= FXCollections.observableArrayList();
        data.add("Last week");
        data.add("Last month");
        data.add("Last year");
        salesTimeCombox.setItems(data);
    }

    private void iniLineChartDay(){
        XYChart.Series series=new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Monday",10));
        series.getData().add(new XYChart.Data<>("Tuesday",450));
        series.getData().add(new XYChart.Data<>("Wednesday",580));
        series.getData().add(new XYChart.Data<>("Thursday",523));
        series.getData().add(new XYChart.Data<>("Friday",790));
        series.getData().add(new XYChart.Data<>("Saturday",820));
        series.getData().add(new XYChart.Data<>("Sunday",120));
        lineChartSalles.getData().addAll(series);
        lineChartSalles.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
    }

}
