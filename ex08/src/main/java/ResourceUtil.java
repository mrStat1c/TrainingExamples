import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ResourceUtil {
    /**
     * @param path путь до файла от директории main/resources
     * @return содержимое файла
     */
    public static String readResourceToString(String path) {
        try (Reader in = new InputStreamReader(
                ResourceUtil.class.getResourceAsStream(path.replaceAll("\\\\", "/")),
                StandardCharsets.UTF_8)) {
            return new String(IOUtils.toByteArray(in));
        } catch (IOException e) {
            log.error("Can't read resource '{}'", path, e);
        }
        return null;
    }

    /**
     * @param dirPath путь до директории от директории main/resources
     * @return список путей всех найденных в директории файлов (пути начинаются от директории main/resources)
     */
    public static List<String> getFileListFromDirectory(String dirPath) {
        File[] files = new File(ResourceUtil.class.getResource(dirPath).getFile()).listFiles();
        List<String> fileList = new ArrayList<>();
        if (files != null && files.length > 0) {
            fileList = Arrays.stream(files).map(File::toString).collect(Collectors.toList());
            String pattern = "resources";
            for (int i = 0; i < fileList.size(); i++) {
                fileList.set(i, fileList.get(i).substring(fileList.get(i).indexOf(pattern) + pattern.length()).replaceAll("\\\\", "/"));
            }
        }
        return fileList;
    }
}
