package software.ulpgc.kata4.electricVehicle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Implementación de ElectricVehicleLoader para cargar vehículos eléctricos desde SQLite
public class SqliteElectricvehicleLoader implements ElectricVehicleLoader {
    private final Connection connection; // Conexión a la base de datos

    // Constructor que recibe una conexión a la base de datos SQLite
    public SqliteElectricvehicleLoader(Connection connection) {
        this.connection = connection;
    }

    // Método para cargar la lista de vehículos eléctricos desde la base de datos
    @Override
    public List<ElectricVehicle> load() {
        try {
            // Intenta cargar los vehículos utilizando el método load() y la consulta definida
            return load(resultSetOf(queryAllSql));
        } catch (SQLException e) {
            // En caso de error, devuelve una lista vacía
            return Collections.emptyList();
        }
    }

    // Consulta SQL para seleccionar todos los vehículos eléctricos
    private final static String queryAllSql = "" +
            "SELECT County, City, Modelyear, Make, Model\n" +
            "FROM Electric_Vehicles;\n";

    // Método para cargar los vehículos desde un ResultSet y convertirlos en objetos ElectricVehicle
    private List<ElectricVehicle> load(ResultSet resultSet) throws SQLException {
        List<ElectricVehicle> list = new ArrayList<>();
        while (resultSet.next()) // Itera a través de los resultados del ResultSet
            list.add(vehicleFrom(resultSet)); // Crea un objeto ElectricVehicle y lo agrega a la lista
        return list; // Devuelve la lista de vehículos cargados
    }

    // Método para crear un objeto ElectricVehicle a partir de un ResultSet
    private ElectricVehicle vehicleFrom(ResultSet resultSet) throws SQLException {
        return new ElectricVehicle(
                resultSet.getString("County"),
                resultSet.getString("City"),
                String.valueOf(resultSet.getInt("ModelYear")),
                resultSet.getString("Make"),
                resultSet.getString("Model")
        );
    }

    // Método para obtener un ResultSet a partir de una consulta SQL
    private ResultSet resultSetOf(String sql) throws SQLException {
        return connection.createStatement().executeQuery(sql);
    }
}

