package controller.cashier;

import javafx.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public class PlayBillController {
    public void viewBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        /*String billPath = "D:\\frist project\\WorkShop\\src\\main\\java\\report\\stockReport.jrxml";
        String sql="select i.description as name ,od.qty as qty ,i.selling_price as price , od.qty*i.selling_price as sub_total from orderdetails od inner join items i on od.item_code = i.code; WHERE order_Id=?";
        String path = FileSystems.getDefault().getPath("/report/stock.jrxml").toAbsolutePath().toString();
        JasperDesign jasdi = JRXmlLoader.load(billPath);
        JRDesignQuery newQuery = new JRDesignQuery();
        newQuery.setText(sql);
        jasdi.setQuery(newQuery);
        JasperReport js = JasperCompileManager.compileReport(jasdi);
        JasperPrint jp = JasperFillManager.fillReport(js, null, DbConnection.getInstance().getConnection());
        JasperViewer viewer = new JasperViewer(jp, false);
        viewer.show();*/
    }
}
