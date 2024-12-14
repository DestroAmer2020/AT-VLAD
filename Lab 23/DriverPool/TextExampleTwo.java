@Test(dataProvider = "browserProvider", parallel = true)
public void parallelTest(String browser) {
    WebDriver driver = DriverPool.getDriver(browser);
    driver.get("https://example.com");
    System.out.println(browser + " Title: " + driver.getTitle());
    DriverPool.quitDriver();
}