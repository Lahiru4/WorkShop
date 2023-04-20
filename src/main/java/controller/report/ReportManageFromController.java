package controller.report;

import db.DbConnection;
import javafx.event.ActionEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.nio.file.FileSystems;
import java.sql.SQLException;

public class ReportManageFromController {
    public void inventoryreportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        String billPath = "D:\\frist project\\WorkShop\\src\\main\\resources\\report\\stockReport.jrxml";
        String sql="select * from items";
        String path = FileSystems.getDefault().getPath("/report/stockReport.jrxml").toAbsolutePath().toString();
        JasperDesign jasdi = JRXmlLoader.load(billPath);
        JRDesignQuery newQuery = new JRDesignQuery();
        newQuery.setText(sql);
        jasdi.setQuery(newQuery);
        JasperReport js = JasperCompileManager.compileReport(jasdi);
        JasperPrint jp = JasperFillManager.fillReport(js, null, DbConnection.getInstance().getConnection());
        JasperViewer viewer = new JasperViewer(jp, false);
        viewer.show();

        /*String sql="select * from items where id = '"+id+"'";*/
    }

    public void empReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        String billPath = "D:\\frist project\\WorkShop\\src\\main\\resources\\report\\empReport.jrxml";
        String sql="select * from employee";
        String path = FileSystems.getDefault().getPath("/report/empReport.jrxml").toAbsolutePath().toString();
        JasperDesign jasdi = JRXmlLoader.load(billPath);
        JRDesignQuery newQuery = new JRDesignQuery();
        newQuery.setText(sql);
        jasdi.setQuery(newQuery);
        JasperReport js = JasperCompileManager.compileReport(jasdi);
        JasperPrint jp = JasperFillManager.fillReport(js, null, DbConnection.getInstance().getConnection());
        JasperViewer viewer = new JasperViewer(jp, false);
        viewer.show();

    }
    public void setuiReport(){
    }
   /* PrintJob job = PrintJob.createPrinterJob();
if( job != null ){
        job.printPage(anchorplane);
    job.endJob();
    }*/
}
