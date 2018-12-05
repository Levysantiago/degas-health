package bancodados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Auxiliar;

public class AuxiliarDAOTest {
	@BeforeClass
	public static void inserirAuxiliar() {
		boolean res;
		AuxiliarDAO auxiliarDAO = new AuxiliarDAO();
		Auxiliar auxiliar = new Auxiliar("Carlos Antonio","M","12345","Brasil","26/08/1977","29/06/2012","28/01/2007");
		res = auxiliarDAO.inserirAuxiliar(auxiliar);
		assertEquals(true, res);
	}
	
	@Test
	public void selecionarAuxiliarCoren() {
		AuxiliarDAO auxiliarDAO = new AuxiliarDAO();
		Auxiliar auxiliar = auxiliarDAO.selecionarAuxiliarCoren("12345");
		assertEquals("Carlos Antonio", auxiliar.getNome());
	}
	
	@Test
	public void selecionarAuxiliarNome() {
		AuxiliarDAO auxiliarDAO = new AuxiliarDAO();
		Auxiliar auxiliar = auxiliarDAO.selecionarAuxiliarNome("Carlos Antonio");
		assertEquals("12345", auxiliar.getCoren());
	}
	
	@Test
	public void alterarAuxiliar() {
		boolean res;
		AuxiliarDAO auxiliarDAO = new AuxiliarDAO();
		res = auxiliarDAO.alterarAuxiliar("12345", "Nome", "Carlos");
		assertEquals(true, res);
		
		res = auxiliarDAO.alterarAuxiliar("12345", "Nome", "Carlos Antonio");
		assertEquals(true, res);
		
		res = auxiliarDAO.alterarAuxiliar("12345", "Nacionalidade", "Inglaterra");
		assertEquals(true, res);
		
		res = auxiliarDAO.alterarAuxiliar("12345", "Nacionalidade", "Brasil");
		assertEquals(true, res);
	}
}
