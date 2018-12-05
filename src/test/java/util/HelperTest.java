package util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HelperTest {
	@Test
	public void testaGradAfterAdm() {
		boolean res;
		
		res = Helper.gradAfterAdm("04/04/2015", "03/04/2014");
		assertEquals(true, res);
		res = Helper.gradAfterAdm("01/02/2013", "03/12/2012");
		assertEquals(true, res);
		res = Helper.gradAfterAdm("03/02/2013", "03/12/2015");
		assertEquals(false, res);
		res = Helper.gradAfterAdm("03/02/2013", "03/02/2013");
		assertEquals(false, res);
	}
	
	@Test
	public void testaCaracterInvalido() {
		String res;
		
		res = Helper.caracterInvalido("ol@mundo");
		assertEquals("@", res);
		
		res = Helper.caracterInvalido("ol#mundo");
		assertEquals("#", res);
		
		res = Helper.caracterInvalido("ol%mundo");
		assertEquals("%", res);
		
		res = Helper.caracterInvalido("ola mundo");
		assertEquals(null, res);
		
		res = Helper.caracterInvalido("    %@");
		assertEquals("%", res);
	}
	
	@Test
	public void testaDataInvalida() {
		boolean res;
		
		res = Helper.dataInvalida("31/02/2018");
		assertEquals(true, res);
		
		res = Helper.dataInvalida("31/14/1992");
		assertEquals(true, res);
		
		res = Helper.dataInvalida("31/04/2017");
		assertEquals(true, res);
		
		res = Helper.dataInvalida("30-04-2018");
		assertEquals(true, res);
		
		res = Helper.dataInvalida("3/4/2018");
		assertEquals(false, res);
		
		res = Helper.dataInvalida("ab/04/2018");
		assertEquals(true, res);
		
		res = Helper.dataInvalida("02/04/as20");
		assertEquals(true, res);
		
		res = Helper.dataInvalida("02/04/b2ba");
		assertEquals(true, res);
		
		res = Helper.dataInvalida("02.04.2018");
		assertEquals(true, res);
	}
}
