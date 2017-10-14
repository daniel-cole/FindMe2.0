package test.findme;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.findme.exception.DuplicateRecordException;
import com.findme.exception.ResourceNotFoundException;
import com.findme.service.UserService;
import com.intuit.karate.junit4.Karate;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import test.ServerStart;

/**
 *
 * @author pthomas3
 */

@RunWith(Karate.class)
public abstract class TestBase {

	private static ServerStart server;

	@BeforeClass
	public static void beforeClass() throws Exception {
		server = new ServerStart();
		server.start(new String[] { "--server.port=8090" }, false);
		System.setProperty("findme.server.port", server.getPort() + "");
		createTestData();
	}

	private static void createTestData() {
		UserService userService = server.getContext().getBean(UserService.class);
		try {
			userService.createUser("testuser1", "testuser1password", "testuser1@test.com");
			userService.createUser("test_disabled", "test123", "disabled@test.com");
			userService.disableUser("test_disabled");
		} catch (DuplicateRecordException e) {
			throw new RuntimeException("failed to create test data");
		} catch (ResourceNotFoundException e) {
			throw new RuntimeException("failed to disable user for tests");
		}
	}

	@AfterClass
	public static void afterClass() {
		server.stop();
		// generateReport("target/surefire-reports");
	}

	private static void generateReport(String karateOutputPath) {
		Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] { "json" }, true);
		List<String> jsonPaths = new ArrayList<String>(jsonFiles.size());
		for (File file : jsonFiles) {
			jsonPaths.add(file.getAbsolutePath());
		}
		Configuration config = new Configuration(new File("target"), "findme");
		ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
		reportBuilder.generateReports();
	}

}