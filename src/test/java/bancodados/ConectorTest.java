package bancodados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ConectorTest {
	@Before
	public void inicializarConexão() {
		boolean res;
		res = Conector.iniciarConexao();
		assertEquals(true, res);
	}
	
	@Test
	public void finalizarConexão() {
		boolean res;
		res = Conector.finalizarConeccao();
		assertEquals(true, res);
	}
}
