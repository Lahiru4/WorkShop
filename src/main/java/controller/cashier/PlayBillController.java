package controller.cashier;

import db.DbConnection;
import javafx.event.ActionEvent;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.nio.file.FileSystems;
import java.sql.SQLException;
import java.util.HashMap;

public class PlayBillController {
    private static String orId;

    private static double tot;
    public static void setTot(double tot) {
        PlayBillController.tot = tot;
    }

    public static void setOrId(String orId) {
        PlayBillController.orId = orId;
    }
    public void viewBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        Thread t1 = new Thread(){
          @SneakyThrows
          public void run(){
              String billPath = "C:\\Users\\Lahiru\\JaspersoftWorkspace\\MyReports\\mainBill.jrxml";
              //String sql="SELECT i.description,od.qty,o.item_cost FROM orders o INNER JOIN orderdetails od ON o.Id=od.order_Id INNER JOIN items i ON i.code=od.item_code WHERE o.Id="+orId+"";
              String path = FileSystems.getDefault().getPath("/report/MainBill.jrxml").toAbsolutePath().toString();
              JasperDesign jasdi = JRXmlLoader.load(billPath);
              //JRDesignQuery newQuery = new JRDesignQuery();
              //newQuery.setText(sql);
              //jasdi.setQuery(newQuery);
              HashMap<String,Object> hm = new HashMap<>();
              hm.put("orderId",orId);
              hm.put("total",String.valueOf(tot));
              JasperReport js = JasperCompileManager.compileReport(jasdi);
              JasperPrint jp = JasperFillManager.fillReport(js, hm, DbConnection.getInstance().getConnection());
              JasperViewer viewer = new JasperViewer(jp, false);
              viewer.show();
          }
        };


        t1.start();
    }
}
