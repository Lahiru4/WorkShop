package controller.home;

import dto.DaySalesQty;
import dto.WekLineChartData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import model.OrderDetailModel;
import model.OrderModel;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class DashBoardFromController {
    public AnchorPane sallesCartA;
    public ComboBox salesTimeCombox;
    public CategoryAxis xAxis;
    public NumberAxis yAxis;
    public BarChart salesQtybarChart;
    @FXML
    private ScrollPane sman;

    @FXML
    private AnchorPane man;

    @FXML
    private LineChart<?, ?> lineChartSalles;

    public void initialize() {
        setBarChartDataWekSallesQty();
        iniLineChartDay();
        salesTimeComboxSetData();
    }

    private void setBarChartDataWekSallesQty() {
        System.out.println("00 nul ekta klin 00");
        List<DaySalesQty> sellsQty = OrderDetailModel.getSellsQty();
        System.out.println(sellsQty.size()+"0000");
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(sellsQty.get(6).getDate(), sellsQty.get(5).getDate(),
                sellsQty.get(4).getDate(), sellsQty.get(3).getDate(),sellsQty.get(2).getDate(),sellsQty.get(1).getDate(),sellsQty.get(0).getDate()
        )));
        xAxis.setLabel("Date");
        yAxis.setLabel("QTY");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Fiat");
        series1.getData().add(new XYChart.Data<>(sellsQty.get(6).getDate(), sellsQty.get(6).getQtyTot()));
        series1.getData().add(new XYChart.Data<>(sellsQty.get(6).getDate(), sellsQty.get(5).getQtyTot()));
        series1.getData().add(new XYChart.Data<>(sellsQty.get(4).getDate(), sellsQty.get(4).getQtyTot()));
        series1.getData().add(new XYChart.Data<>(sellsQty.get(3).getDate(), sellsQty.get(3).getQtyTot()));
        series1.getData().add(new XYChart.Data<>(sellsQty.get(2).getDate(), sellsQty.get(2).getQtyTot()));
        series1.getData().add(new XYChart.Data<>(sellsQty.get(1).getDate(), sellsQty.get(1).getQtyTot()));
        series1.getData().add(new XYChart.Data<>(sellsQty.get(0).getDate(), sellsQty.get(0).getQtyTot()));

        salesQtybarChart.getData().addAll(series1);
    }

    private void salesTimeComboxSetData() {
        ObservableList<String> data = FXCollections.observableArrayList();
        data.add("Last week");
        data.add("Last month");
        data.add("Last year");
        salesTimeCombox.setItems(data);
    }

    private void iniLineChartDay() {
        List<WekLineChartData> sellsLineChartData = OrderModel.getSellsLineChartData();
        System.out.println(sellsLineChartData.size());

        XYChart.Series series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(sellsLineChartData.get(6).getDate(), sellsLineChartData.get(6).getTot()));
        series.getData().add(new XYChart.Data<>(sellsLineChartData.get(5).getDate(), sellsLineChartData.get(5).getTot()));
        series.getData().add(new XYChart.Data<>(sellsLineChartData.get(4).getDate(), sellsLineChartData.get(4).getTot()));
        series.getData().add(new XYChart.Data<>(sellsLineChartData.get(3).getDate(), sellsLineChartData.get(3).getTot()));
        series.getData().add(new XYChart.Data<>(sellsLineChartData.get(2).getDate(), sellsLineChartData.get(2).getTot()));
        series.getData().add(new XYChart.Data<>(sellsLineChartData.get(1).getDate(), sellsLineChartData.get(1).getTot()));
        series.getData().add(new XYChart.Data<>(sellsLineChartData.get(0).getDate(), sellsLineChartData.get(0).getTot()));
        lineChartSalles.getData().addAll(series);
        lineChartSalles.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
    }

}
