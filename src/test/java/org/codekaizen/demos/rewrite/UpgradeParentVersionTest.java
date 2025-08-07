package org.codekaizen.demos.rewrite;

import org.junit.jupiter.api.Test;
import org.openrewrite.InMemoryExecutionContext;
import org.openrewrite.maven.MavenExecutionContextView;
import org.openrewrite.test.RewriteTest;
import org.openrewrite.test.SourceSpec;
import org.openrewrite.test.SourceSpecs;

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
          spec -> spec.recipeFromResources("org.codekaizen.demos.rewrite.UpgradeParentVersion")
            .expectedCyclesThatMakeChanges(1),
          pomXml(loadFile("parent-pom-upgrade-before.xml"),
            loadFile("parent-pom-upgrade-after.xml"))
        );
    }

    private String loadFile(String fileName) {
        try {
            Path path = Paths.get(getClass().getResource(fileName).toURI());
            return Files.readString(path);
        } catch (URISyntaxException | IOException e) {
            fail(e);
            return "";
        }
    }
}
