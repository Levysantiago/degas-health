package bancodados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Especialidade;

public class EspecialidadeDAOTest {
	@BeforeClass
	public static void inserirEspecialidade() {
		boolean res;
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		Especialidade especialidade = new Especialidade("123", "Especialidade");
		res = especialidadeDAO.inserirEspecialidade(especialidade);
		assertEquals(true, res);
	}
	
	@Test
	public void selecionarEspecialidadeCodigo() {
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		Especialidade especialidade = especialidadeDAO.selecionarEspecialidadeCodigo("123");
		assertEquals("Especialidade", especialidade.getDescricao());
	}
	
	@Test
	public void selecionarEspecialidadeDescricao() {
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		Especialidade especialidade = especialidadeDAO.selecionarEspecialidadeDescricao("Especialidade");
		assertEquals("123", especialidade.getCodigo());
	}
	
	@Test
	public void alterarEspecialidade() {
		boolean res;
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		res = especialidadeDAO.alterarEspecialidade("123", "Descricao", "Especial");
		assertEquals(true, res);
		
		res = especialidadeDAO.alterarEspecialidade("123", "Descricao", "Especialidade");
		assertEquals(true, res);
	}
}
