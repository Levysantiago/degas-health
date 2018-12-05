package bancodados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Procedimento;

public class ProcedimentoDAOTest {
	@BeforeClass
	public static void inserirProcedimento() {
		boolean res;
		Procedimento procedimento = new Procedimento("123456","Ponte de Safena","50000");
		ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
		res = procedimentoDAO.inserirProcedimento(procedimento);
		assertEquals(true, res);
	}
	
	@Test
	public void inserirMaterialProcedimento() {
		boolean res;
		ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
		res = procedimentoDAO.materialProcedimento("123456", "1234");
		assertEquals(true, res);
	}
	
	@Test
	public void inserirEquipamentoProcedimento() {
		boolean res;
		ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
		res = procedimentoDAO.equipamentoProcedimento("123456", "885");
		assertEquals(true, res);
	}
	
	@Test
	public void selecionarProcedimentoCodigo() {
		ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
		Procedimento procedimento = procedimentoDAO.selecionarProcedimentoCodigo("123456");
		assertEquals("Ponte de Safena", procedimento.getDescricao());
	}
	
	@Test
	public void selecionarProcedimentoDescricao() {
		ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
		Procedimento procedimento = procedimentoDAO.selecionarProcedimentoDescricao("Ponte de Safena");
		assertEquals("763236", procedimento.getCodigo());
	}
	
	@Test
	public void alterarProcedimento() {
		boolean res;
		ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
		res = procedimentoDAO.alterarProcedimento("123456", "Descricao", "Ponte");
		assertEquals(true, res);
		
		res = procedimentoDAO.alterarProcedimento("123456", "Descricao", "Ponte de Safena");
		assertEquals(true, res);
		
		res = procedimentoDAO.alterarProcedimento("123456", "Valor", "20000");
		assertEquals(true, res);
		
		res = procedimentoDAO.alterarProcedimento("123456", "Valor", "50000");
		assertEquals(true, res);
	}
}
