package model;

import dto.Supplier;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {
    public static List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        List<Supplier> data =new ArrayList<>();
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM supplier");
        while (resultSet.next()){
            data.add(new Supplier(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));
        }
    return data;
    }
}
