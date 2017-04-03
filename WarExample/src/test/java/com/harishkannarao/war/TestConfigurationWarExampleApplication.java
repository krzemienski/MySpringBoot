package com.harishkannarao.war;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@TestConfiguration
public class TestConfigurationWarExampleApplication {

    private static final String FIREFOX_DRIVER = "firefox";
    private static final String PHANTOMJS_DRIVER = "phantomjs";

    @Autowired
    @Value("${warExample.testDriver}")
    private String warExampleTestDriver;

    @Bean(destroyMethod = "quit")
    public WebDriver getWebDriver() {
        WebDriver driver = null;
        if (FIREFOX_DRIVER.equals(warExampleTestDriver)) {
            FirefoxProfile fp = new FirefoxProfile();
            fp.setPreference("browser.startup.homepage", "about:blank");
            fp.setPreference("startup.homepage_welcome_url", "about:blank");
            fp.setPreference("startup.homepage_welcome_url.additional", "about:blank");
            driver = new FirefoxDriver(fp);
            driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        } else if (PHANTOMJS_DRIVER.equals(warExampleTestDriver)) {
            DesiredCapabilities phantomjsCapabilities = DesiredCapabilities.phantomjs();
            String [] phantomJsArgs = {"--ignore-ssl-errors=true","--ssl-protocol=any"};
            phantomjsCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomJsArgs);
            driver = new PhantomJSDriver(phantomjsCapabilities);
            driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        }

        return driver;
    }
}
