package org.codekaizen.demos.rewrite;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RewriteTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Fail.fail;
import static org.openrewrite.maven.Assertions.pomXml;

public class UpgradeParentVersionTest implements RewriteTest {

    @Test
    void upgradesParentVersion() {
        rewriteRun(
          spec -> spec.recipeFromResources("org.codekaizen.demos.rewrite.UpgradeParentVersion"),
          pomXml(loadFile("parent-pom-upgrade-before.xml"),
            loadFile("parent-pom-upgrade-after.xml"))
        );
    }

    private String loadFile(String fileName) {
        try {
            Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
            return Files.readString(path);
        } catch (URISyntaxException | IOException e) {
            fail(e);
            return "";
        }
    }
}
