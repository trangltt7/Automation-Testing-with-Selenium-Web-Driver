/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author admin
 */
public class TestDatabaseNGTest {

    private Connection cnn;
    private static Statement statement;
    private static ResultSet rs;

    @BeforeClass
    public void setUp() {
        System.out.println("Create 10 teachers");
    }

    @Test
    public void testautomation() throws InterruptedException {
        
        String databaseURL = "jdbc:mysql://localhost:3000/createteachers";
        String user = "root";
        String password = "0710";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to Database...");
            cnn = DriverManager.getConnection(databaseURL, user, password);
            if (cnn != null) {
                System.out.println("Connected to the Database...");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        List first_name = new ArrayList();
        List last_name = new ArrayList();
        List phonenumber = new ArrayList();
        List mail = new ArrayList();


        
        try {
            String query = "select * from record1";
            statement = cnn.createStatement();
            rs = statement.executeQuery(query);
            
            int count=0;

            while (rs.next()) {
                String first_name2 = rs.getString("first_name");
                String last_name2 = rs.getString("last_name");
                String phonenumber2 = rs.getString("phonenumber");
                String mail2 = rs.getString("mail");
                first_name.add(first_name2);
                last_name.add(last_name2);
                phonenumber.add( phonenumber2);
                mail.add(mail2);
                count++;

            }
        //System.out.println(Integer.toString(3) + "\t" + first_name.get(3) + "\t" + last_name.get(3) + "\t" + phonenumber.get(3) + "\t" + mail.get(3));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (cnn != null) {
            try {
                System.out.println("Closing Database Connection...");
                cnn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        driver.get("https://myparkhangseo.herokuapp.com/login"); // đường dẫn trang web
        
        
        // Điền input
        // Tài khoản nhân viên
        driver.findElement(By.name("username")).sendKeys("ntmphuc");
        driver.findElement(By.name("password")).sendKeys("ntmphuc");

        // Nhấn nút đăng nhập
        WebElement bNext = driver.findElement(By.className("btn-default"));
        bNext.click();
        Thread.sleep(2000);
     
        /*// Nhấn thẻ Teachers
        List<WebElement> bNext2 = driver.findElements(By.tagName("a"));
        bNext2.get(5).click();
        Thread.sleep(2000);
     
        for (int i=0; i<1; i++){
            
            // Nhấn nút Add
            List<WebElement> bNext3 = driver.findElements(By.className("btn-round"));
            bNext3.get(0).click();
            Thread.sleep(1000);
            
            // Điền input
            driver.findElement(By.name("first_name")).sendKeys(first_name.get(i).toString());
            driver.findElement(By.name("last_name")).sendKeys(last_name.get(i).toString());
            driver.findElement(By.name("email")).sendKeys(mail.get(i).toString());
            driver.findElement(By.name("phone")).sendKeys(phonenumber.get(i).toString());
        
            // Nhấn nút Add
            List<WebElement> bNext4 = driver.findElements(By.className("btn-success"));
            bNext4.get(0).click();
            Thread.sleep(1000);
            // Nhấn nút Close
            WebElement bNext5 = driver.findElement(By.className("close"));
            bNext5.click();
            Thread.sleep(1000);
        }   */
         
        // Nhấn thẻ Courses
        List<WebElement> bNext2 = driver.findElements(By.tagName("a"));
        bNext2.get(9).click();
        Thread.sleep(2000);
        
        for (int i = 10; i < 20; i++) {

            // Nhấn nút Add
            WebElement bNext3 = driver.findElement(By.className("btn-round"));
            bNext3.click();
            Thread.sleep(2000);

            // Điền input
            driver.findElement(By.name("new_class_name")).sendKeys(Integer.toString(i) + "CTT");

            // Thêm file
            driver.findElement(By.name("students")).sendKeys("e:\\Book1.xlsx");
            Thread.sleep(1000);

            // Nhấn nút Add
            List<WebElement> bNext4 = driver.findElements(By.className("btn-success"));
            bNext4.get(1).click();
            Thread.sleep(1000);
        }
    }

    @AfterClass
    public void tearDown() {
        if (cnn != null) {
            try {
                System.out.println("Done!");
                cnn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
