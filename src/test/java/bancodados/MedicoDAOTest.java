package bancodados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Medico;

public class MedicoDAOTest {
	@BeforeClass
	public static void inserirMedico() {
		boolean res;
		MedicoDAO medicoDAO = new MedicoDAO();
		Medico medico = new Medico("Carlos Antonio","M","12345","Brasil","26/08/1977","29/06/2012","28/01/2007");
		res = medicoDAO.inserirMedico(medico);
		assertEquals(true, res);
	}
	
	@Test
	public void selecionarMedicoCrm() {
		MedicoDAO medicoDAO = new MedicoDAO();
		Medico medico = medicoDAO.selecionarMedicoCrm("12345");
		assertEquals("Carlos Antonio", medico.getNome());
	}
	
	@Test
	public void selecionarMedicoNome() {
		MedicoDAO medicoDAO = new MedicoDAO();
		Medico medico = medicoDAO.selecionarMedicoNome("Carlos Antonio");
		assertEquals("12345", medico.getCrm());
	}
	
	@Test
	public void alterarMedico() {
		boolean res;
		MedicoDAO medicoDAO = new MedicoDAO();
		res = medicoDAO.alterarMedico("12345", "Nome", "Carlos");
		assertEquals(true, res);
		
		res = medicoDAO.alterarMedico("12345", "Nome", "Carlos Antonio");
		assertEquals(true, res);
		
		res = medicoDAO.alterarMedico("12345", "Nacionalidade", "Inglaterra");
		assertEquals(true, res);
		
		res = medicoDAO.alterarMedico("12345", "Nacionalidade", "Brasil");
		assertEquals(true, res);
	}
}
