package system;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import static org.junit.jupiter.api.Assertions.*;


public class MinfESTest {

    @BeforeAll
    public static void setAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver_win32/chromedriver.exe");

    }

    @Test
    @DisplayName("Abrir Página")
    public void steamPage() {
        WebDriver webdriver = new ChromeDriver();
        webdriver.manage().window().maximize();
        webdriver.get("https://store.steampowered.com/?l=portuguese");
        assertEquals("https://store.steampowered.com/?l=portuguese", webdriver.getCurrentUrl());
        webdriver.close();

    }

    @Test
    @DisplayName("Procurar gênero RPG")
    public void abrirPaginaRPG() {
        WebDriver webdriver = new ChromeDriver();
        webdriver.manage().window().maximize();
        webdriver.get("https://store.steampowered.com/?l=portuguese");
        WebElement rpg = webdriver.findElement(By.linkText("RPG"));
        rpg.click();
        assertEquals("https://store.steampowered.com/tags/pt/RPG/", webdriver.getCurrentUrl());
        webdriver.close();
    }

    @Test
    @DisplayName("Procurar por elder scrolls")
    public void digitarNomeDoJogo() {
        WebDriver webdriver = new ChromeDriver();
        webdriver.manage().window().maximize();
        webdriver.get("https://store.steampowered.com/tags/pt/RPG/");
        WebElement search = webdriver.findElement(By.name("term"));
        search.sendKeys("elder scrolls");
        search.submit();
        WebElement eso = webdriver.findElement(By.xpath("//*[@id=\"search_resultsRows\"]/a[1]"));
        eso.click();
        assertEquals("https://store.steampowered.com/app/306130/The_Elder_Scrolls_Online/", webdriver.getCurrentUrl());
        webdriver.close();
    }

    @Test
    @DisplayName("Mover para Iniciar Sessão")
    public void iniciarSessão() {
        WebDriver webdriver = new ChromeDriver();
        Actions act = new Actions(webdriver);
        webdriver.manage().window().maximize();
        webdriver.get("https://store.steampowered.com/?l=portuguese");
        WebElement iniciarSessao = webdriver.findElement(By.xpath("//*[@id=\"responsive_page_template_content\"]/div[1]/div[2]/div[8]/div/div/div/a[1]/span"));
        act.moveToElement(iniciarSessao).perform();
        iniciarSessao.click();
        assertEquals("https://store.steampowered.com/login/?snr=1_4_4__more-content-login", webdriver.getCurrentUrl());
        webdriver.close();
    }

    @Test
    @DisplayName("Falha no Login")
    public void failLogin() {
        WebDriver webdriver = new ChromeDriver();
        webdriver.manage().window().maximize();
        webdriver.get("https://store.steampowered.com/login/?snr=1_4_4__more-content-login");
        WebElement login = webdriver.findElement(By.id("input_username"));
        login.sendKeys("login");
        WebElement password = webdriver.findElement(By.id("input_password"));
        password.sendKeys("password");
        WebElement button = webdriver.findElement(By.id("login_btn_signin"));
        button.click();
        WebElement error = webdriver.findElement(By.id("error_display"));
        assertTrue(error.isEnabled());
        webdriver.close();

    }
}
