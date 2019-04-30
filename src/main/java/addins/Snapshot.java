package addins;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Snapshot {

    private final RemoteWebDriver driver;
    private final String baseDir;

    public Snapshot(RemoteWebDriver driver, String baseDir) {
        this.driver = driver;
        this.baseDir = baseDir;
    }

    public void takeFullPageSnapshot(String filename) throws IOException {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(), "PNG", new File(filePath(filename)));
    }

    private String filePath(String filename) {
        return String.format("%s%s%s.png", baseDir, File.separator, filename);
    }

}
