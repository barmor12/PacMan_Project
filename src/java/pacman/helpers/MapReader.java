package pacman.helpers;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Arrays;

/**
 * Class to read and parse map data from a CSV file.
 */
public class MapReader {

    /**
     * Parse the map data from the specified file URI.
     *
     * @param fileUri The URI of the CSV file containing the map data.
     * @return A list of lists containing the parsed map data.
     */
    public List<List<String>> parseMap(URI fileUri) {
        List<List<String>> csvData = null;
        try (Stream<String> lines = Files.lines(Paths.get(fileUri))) {
            csvData = lines.map(line -> Arrays.asList(line.split(";")))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return csvData;
    }
}
