package bancodados;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Material;

public class MaterialDAOTest {
	@BeforeClass
	public static void inserirMaterial() {
		boolean res;
		Material material = new Material("1234","Bisturi","10");
		MaterialDAO materialDAO = new MaterialDAO();
		res = materialDAO.inserirMaterial(material);
		assertEquals(true, res);
	}
	
	@Test
	public void selecionarMaterialCodigo() {
		MaterialDAO materialDAO = new MaterialDAO();
		Material material = materialDAO.selecionarMaterialCodigo("1234");
		assertEquals("Bisturi", material.getDescricao());
	}
	
	@Test
	public void selecionarMaterialDescricao() {
		MaterialDAO materialDAO = new MaterialDAO();
		Material material = materialDAO.selecionarMaterialDescricao("Bisturi");
		assertEquals("1234", material.getCodigo());
	}
	
	@Test
	public void alterarMaterial() {
		boolean res;
		MaterialDAO materialDAO = new MaterialDAO();
		res = materialDAO.alterarMaterial("2415", "Descricao", "Faca");
		assertEquals(true, res);
		
		res = materialDAO.alterarMaterial("2415", "Descricao", "Bisturi");
		assertEquals(true, res);
		
		res = materialDAO.alterarMaterial("2415", "Valor", "20");
		assertEquals(true, res);
		
		res = materialDAO.alterarMaterial("2415", "Valor", "10");
		assertEquals(true, res);
	}
}
