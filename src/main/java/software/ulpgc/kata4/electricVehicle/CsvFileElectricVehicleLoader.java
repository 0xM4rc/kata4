package software.ulpgc.kata4.electricVehicle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsvFileElectricVehicleLoader implements ElectricVehicleLoader {
    private final File file;

    private CsvFileElectricVehicleLoader(File file) {
        this.file=file;
    }

    public static ElectricVehicleLoader with(File file) {
        return new CsvFileElectricVehicleLoader(file);
    }

    @Override
    public List<ElectricVehicle> load(){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            return load(reader);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private List<ElectricVehicle> load(BufferedReader reader) throws IOException {
        List<ElectricVehicle> result = new ArrayList<>();
        reader.readLine();
        while (true){
            String line = reader.readLine();
            if (line == null) {
                return result;
            }
            result.add(toElectricVehicle(line));
        }
    }

    private ElectricVehicle toElectricVehicle(String line) {
        return toElectricVehicle(line.split(","));
    }

    private ElectricVehicle toElectricVehicle(String[] fields) {
        return new ElectricVehicle(
          fields[0],
          fields[1],
          fields[2],
          fields[3],
          fields[4]
        );
    }
}
