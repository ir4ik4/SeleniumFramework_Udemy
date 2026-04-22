package tarakanova.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public List<HashMap<String,String>> getJsonDataToMap() throws IOException {
        String jsonContent = FileUtils.readFileToString(FileUtils.getFile(System.getProperty("user.dir")
                + "/src/test/java/tarakanova/data/PurchaseOrder.json"), "UTF-8");


    ObjectMapper objectMapper = new ObjectMapper();
    List<HashMap<String,String>> data = objectMapper
            .readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
        return data;


    }
}
