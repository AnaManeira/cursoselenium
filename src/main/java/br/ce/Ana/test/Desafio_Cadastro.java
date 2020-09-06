package br.ce.Ana.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Desafio_Cadastro {

	private WebDriver driver;

	@Before
	public void Inicializa() {
		System.setProperty("webdriver.gecko.driver", "C:\\Ana\\drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}

	@After
	public void Finaliza() {
		driver.quit();
	}

	@Test
	public void testeTextField() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Ana Paula");
// acima, o driverfindelement foi pra achar o campo, e sendkeys foi responsavel por escrever no campo.
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sanches");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Superior");
// aqui, usei o driverfindelement para achar onde estava o negocio, o selecto foi pra selecionar o texto visivel ''superior''
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Natacao");
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Karate");
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Corrida");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
// acima, usamos o findelement para achar o negocio do cadastro, e click pra clicar em cima dele. Cada linha é de um passo diferente no site.

// A partir daqui começam os testes de confirmação do que foi foi realmente escrito, com o que deveria estar escrito.	    
		Assert.assertTrue(driver.findElement(By.id("Resultado")).getText().startsWith("Cadastrado"));
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Ana Paula"));
		Assert.assertEquals("Sobrenome: Sanches", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Feminino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Carne", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Natacao Corrida Karate", driver.findElement(By.id("descEsportes")).getText());

		// driver.findElement(By.id("confirm")).click();
		// Assert.assertEquals("Confirm Simples", );
	}
}
