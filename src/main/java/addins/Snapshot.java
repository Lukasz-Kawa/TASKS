package addins;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public class Snapshot {

    private final RemoteWebDriver driver;
    private final String baseDir;

    public Snapshot(RemoteWebDriver driver, String baseDir) {
        this.driver = driver;
        this.baseDir = baseDir;
    }

    public void takeSnapshot(String filename) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        File destination = new File(filePath(filename));
        FileUtils.copyFile(source, destination);
    }

    private String filePath(String filename) {
        return String.format("%s%s%s.png", baseDir, File.separator, filename);
    }

}
