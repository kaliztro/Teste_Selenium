import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MeuPrimeiroScriptAutomacaoJava {
    WebDriver driver = new ChromeDriver();
    String email = "malenia@eldenlord.com";
    String nome = "Malenia";
    String senha = "MaleniaEspadaDeMiquella";

    String email2 = "miquella@eldenlord.com";
    String nome2 = "Miquella";
    String senha2 = "miquellaCriadoraDaArvoreSacra";

    @BeforeEach
    public void beforeTest() {
        driver.manage().window().maximize();
    }

    @AfterEach
    public void afterTest() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
    }

    private String conta;
    private String conta2;
    private String digito;
    private String digitoConta2;

//    @Test
//    public void acessarGoogle() {
//        driver.get("https://www.google.com/");
//        driver.findElement(By.id("APjFqb")).sendKeys("Unicred", Keys.ENTER);
//    }



    @Test
    public void criarConta() {

        driver.get("https://bugbank.netlify.app/");
        driver.findElement(By.xpath("//button[normalize-space()='Registrar']")).click();
        driver.findElement((By.xpath("(//input[@placeholder='Informe seu e-mail'])[2]"))).sendKeys(email);
        driver.findElement(By.name("name")).sendKeys(nome);
        driver.findElement(By.xpath("(//input[@placeholder='Informe sua senha'])[2]")).sendKeys(senha);
        driver.findElement(By.name("passwordConfirmation")).sendKeys(senha);

        driver.findElement((By.id("toggleAddBalance"))).click(); //conta com saldo
        driver.findElement(By.xpath("//button[normalize-space()='Cadastrar']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10000)); //espera 2 segundos
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalText"))); //aguarda os 2 segundos até carregar a modal

        String modalText = element.getText(); //pega o conteudo do modal

        conta = modalText.replaceAll("[^0-9]", ""); // Remove tudo que não for numero
        System.out.println("Conta 1: " + conta);

        char ultimoCaractere = conta.charAt(conta.length() - 1); //pega apenas o ultimo caractere
        digito = String.valueOf(ultimoCaractere); //variavel contendo o digito
        System.out.println("Ultimo numero: " + digito);

        driver.findElement(By.id("btnCloseModal")).click(); //botao fechar modal

    }

    @Test
    public void acessarConta() {

        driver.get("https://bugbank.netlify.app/");

        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(senha);

        driver.findElement(By.xpath(" //button[normalize-space()='Acessar']")).click();
    }

    @Test
    public void criarContaDois() {

        driver.get("https://bugbank.netlify.app/");
        driver.findElement(By.xpath("//button[normalize-space()='Registrar']")).click();
        driver.findElement((By.xpath("(//input[@placeholder='Informe seu e-mail'])[2]"))).sendKeys(email2);
        driver.findElement(By.name("name")).sendKeys(nome2);
        driver.findElement(By.xpath("(//input[@placeholder='Informe sua senha'])[2]")).sendKeys(senha2);
        driver.findElement(By.name("passwordConfirmation")).sendKeys(senha2);

        driver.findElement((By.id("toggleAddBalance"))).click(); //conta com saldo
        driver.findElement(By.xpath("//button[normalize-space()='Cadastrar']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10000)); //espera 2 segundos
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalText"))); //aguarda os 2 segundos até carregar a modal

        String modalText2 = element.getText(); //pega o conteudo do modal

        conta2 = modalText2.replaceAll("[^0-9]", ""); // Remove tudo que não for numero
        System.out.println("Conta 2: " + conta2);

        char ultimoCaractere = conta2.charAt(conta2.length() - 1); //pega apenas o ultimo caractere
        digitoConta2 = String.valueOf(ultimoCaractere); //variavel contendo o digito
        System.out.println("Ultimo numero: " + digitoConta2);

        driver.findElement(By.id("btnCloseModal")).click(); //botao fechar modal

    }

    @Test
    public void RealizarTransferencia(){
        criarConta();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        acessarConta();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[@id='btn-TRANSFERÊNCIA']")).click();

//        driver.findElement(By.id("btn-TRANSFERÊNCIA")).click(); //acessa o menu transferencia
//        driver.findElement(By.name("accountNumber")).sendKeys(conta2);
//        driver.findElement(By.name("digit")).sendKeys(digitoConta2);
//        driver.findElement(By.name("transferValue")).sendKeys("500");
//        driver.findElement(By.name("description")).sendKeys("Aqui está o dinheiro da Térvore");
//
//        driver.findElement(By.xpath("//button[normalize-space()='Transferir agora']")).click();
    }


    @Test
    public void ExecutarFluxo(){
        criarConta();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        criarContaDois();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        acessarConta();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        RealizarTransferencia();
    }

}
