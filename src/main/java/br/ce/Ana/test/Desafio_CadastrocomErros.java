package br.ce.Ana.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Desafio_CadastrocomErros {

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
	public void DesafioSemNome() {
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alerta_Nome = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alerta_Nome.getText());
		alerta_Nome.accept();
	}

	@Test
	public void DesafioComNomeSemSobrenome() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Ana Paula");
		Assert.assertEquals("Ana Paula", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alerta_Sobrenome = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alerta_Sobrenome.getText());
		alerta_Sobrenome.accept();
	}

	@Test
	public void DesafioSexoNaoSelecionado() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Ana Paula");
		Assert.assertEquals("Ana Paula", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sanches");
		Assert.assertEquals("Sanches", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));

		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alerta_Sexo = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alerta_Sexo.getText());
		alerta_Sexo.accept();
	}

	@Test
	public void DesafioConflitoEntreComidas() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Ana Paula");
		Assert.assertEquals("Ana Paula", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sanches");
		Assert.assertEquals("Sanches", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		driver.findElement(By.id("elementosForm:sexo:1")).click();

		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alerta_Comida = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alerta_Comida.getText());
		alerta_Comida.accept();
	}

	@Test
	public void DesafioConflitoEntreEsportes() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Ana Paula");
		Assert.assertEquals("Ana Paula", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sanches");
		Assert.assertEquals("Sanches", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		driver.findElement(By.id("elementosForm:sexo:1")).click();

		WebElement Element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(Element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alerta_Esporte = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alerta_Esporte.getText());
		alerta_Esporte.accept();
	}
}
