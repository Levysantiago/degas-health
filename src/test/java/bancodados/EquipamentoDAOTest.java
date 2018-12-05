package bancodados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Equipamento;

public class EquipamentoDAOTest {
	@BeforeClass
	public static void inserirEquipamento() {
		boolean res;
		EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
		Equipamento equipamento = new Equipamento("123", "Equipamento", "200");
		res = equipamentoDAO.inserirEquipamento(equipamento);
		assertEquals(true, res);
	}
	
	@Test
	public void selecionarEquipamentoCodigo() {
		EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
		Equipamento equipamento = equipamentoDAO.selecionarEquipamentoCodigo("123");
		assertEquals("Equipamento", equipamento.getDescricao());
	}
	
	@Test
	public void selecionarEquipamentoDescricao() {
		EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
		Equipamento equipamento = equipamentoDAO.selecionarEquipamentoDescricao("Equipamento");
		assertEquals("123", equipamento.getCodigo());
	}
	
	@Test
	public void alterarEquipamento() {
		boolean res;
		EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
		res = equipamentoDAO.alterarEquipamento("123", "Descricao", "Equip");
		assertEquals(true, res);
		
		res = equipamentoDAO.alterarEquipamento("123", "Descricao", "Equipamento");
		assertEquals(true, res);
		
		res = equipamentoDAO.alterarEquipamento("123", "Valor", "400");
		assertEquals(true, res);
		
		res = equipamentoDAO.alterarEquipamento("123", "Valor", "200");
		assertEquals(true, res);
	}
}
