package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MyExtentReport {

    public static ExtentReports generateExtentReport() throws IOException {
        // Set up the report path using the correct separator for the OS
        String reportPath = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "ExtendReport" + File.separator + "extent.html";

        // Create the report directory if it doesn't exist
        File reportDir = new File(reportPath).getParentFile();
        if (!reportDir.exists()) {
            reportDir.mkdirs();  // Creates directories if they don't exist
        }

        // Create an ExtentReports object
        ExtentReports extentReport = new ExtentReports();

        // Set up the SparkReporter for generating the HTML report
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("RoofRocketAI Report");
        sparkReporter.config().setReportName("RoofRocketAI Test Report");
        sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");
        sparkReporter.config().setEncoding("utf-8");

        // Attach the reporter to the ExtentReports instance
        extentReport.attachReporter(sparkReporter);

        // Load the configuration properties
        Properties config = new Properties();
        File configFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator + "org" + File.separator + "myproject" + File.separator + "qa" + File.separator + "config" + File.separator + "config.properties");

        try (FileInputStream fis = new FileInputStream(configFile)) {
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add system info to the report
        extentReport.setSystemInfo("Application URL", config.getProperty("url"));
        extentReport.setSystemInfo("Browser Name", config.getProperty("browserName"));
        extentReport.setSystemInfo("Username", config.getProperty("validEmail"));
        extentReport.setSystemInfo("OS", System.getProperty("os.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReport.setSystemInfo("OS Version", System.getProperty("os.version"));

        // Return the ExtentReports object for use in your tests
        return extentReport;
    }

    public static void main(String[] args) {
        try {
            // Generate the report
            ExtentReports extentReport = generateExtentReport();

            // Finalize the report
            extentReport.flush();
            System.out.println("Extent report generated successfully!");
        } catch (IOException e) {
            System.err.println("Failed to generate the extent report: " + e.getMessage());
        }
    }
}
