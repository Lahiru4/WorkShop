package controller.home;

import dto.WekLineChartData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import model.OrderModel;

import java.awt.*;
import java.util.List;

public class DashBoardFromController {
    public AnchorPane sallesCartA;
    public ComboBox salesTimeCombox;
    @FXML
    private ScrollPane sman;

    @FXML
    private AnchorPane man;

    @FXML
    private LineChart<?, ?> lineChartSalles;

    public void initialize() {
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
        List<WekLineChartData> sellsLineChartData = OrderModel.getSellsLineChartData();
        System.out.println(sellsLineChartData.size());

        XYChart.Series series=new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(sellsLineChartData.get(6).getDate(),sellsLineChartData.get(6).getTot()));
        series.getData().add(new XYChart.Data<>(sellsLineChartData.get(5).getDate(),sellsLineChartData.get(5).getTot()));
        series.getData().add(new XYChart.Data<>(sellsLineChartData.get(4).getDate(),sellsLineChartData.get(4).getTot()));
        series.getData().add(new XYChart.Data<>(sellsLineChartData.get(3).getDate(),sellsLineChartData.get(3).getTot()));
        series.getData().add(new XYChart.Data<>(sellsLineChartData.get(2).getDate(),sellsLineChartData.get(2).getTot()));
        series.getData().add(new XYChart.Data<>(sellsLineChartData.get(1).getDate(),sellsLineChartData.get(1).getTot()));
        series.getData().add(new XYChart.Data<>(sellsLineChartData.get(0).getDate(),sellsLineChartData.get(0).getTot()));
        lineChartSalles.getData().addAll(series);
        lineChartSalles.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
    }

}
