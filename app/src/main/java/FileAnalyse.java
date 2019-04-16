import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by duoshilin on 2019/3/6.
 */
public class FileAnalyse {

    public static void main(String[] args) {
        readFile("/Users/duoshilin/Desktop/a.txt");
    }

    public static Map<String, Integer> readFile(String filename) {
        Map<String, Integer> map = new HashMap<>();
        try {
            FileReader reader = new FileReader(filename);
            BufferedReader buffer = new BufferedReader(reader);
            String line;
            while ((line = buffer.readLine()) != null) {
                line = line.substring(line.indexOf("- "));
//                System.out.println(line);
                Integer count = map.get(line);
                map.put(line, count == null ? 1 : ++count);
            }
            forEachMap(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void forEachMap(Map<String, Integer> map) {
        for (String key : map.keySet()) {
            System.out.println(map.get(key) + "\t" + key);
        }
    }
}
