package br.ce.Ana.core;

import static br.ce.Ana.core.DriverFactory.killDriver;

import org.junit.After;

public class BaseTest {

	@After
	public void Finaliza() {
		killDriver();
	}
}
