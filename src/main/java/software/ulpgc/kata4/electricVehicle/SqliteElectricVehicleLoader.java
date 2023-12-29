package software.ulpgc.kata4.electricVehicle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqliteElectricVehicleLoader implements ElectricVehicleLoader{

    private final Connection connection;
    private final static String queryAllSql =
            "SELECT County, City, ModelYear, Make, Model\n" +
            "FROM Electric_Vehicles;\n";

    public SqliteElectricVehicleLoader(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ElectricVehicle> load() {
        try {
            return load(resultSetOf(queryAllSql));
        } catch (SQLException e){
            return Collections.emptyList();
        }
    }

    private List<ElectricVehicle> load(ResultSet resultSet) throws SQLException {
        List<ElectricVehicle> list= new ArrayList<>();
        while (resultSet.next()) list.add(vehicleFrom(resultSet));
        return  list;
    }

    private ElectricVehicle vehicleFrom(ResultSet resultSet) throws SQLException {
        return new ElectricVehicle(
                resultSet.getString("County"),
                resultSet.getString("City"),
                resultSet.getString("ModelYear"),
                resultSet.getString("Make"),
                resultSet.getString("Model")
        );
    }

    private ResultSet resultSetOf(String queryAllSql) throws SQLException {
        return connection.createStatement().executeQuery(queryAllSql);
    }
}
