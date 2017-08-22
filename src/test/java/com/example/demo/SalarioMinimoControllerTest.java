package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class SalarioMinimoControllerTest extends TddApplicationTests {

	private MockMvc mockMvc;
	
	@Autowired private SalarioMinimoController salarioMinimoController;
	@Autowired private SalarioMinimoService salarioMinimoService;
	@Autowired private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(salarioMinimoController).build();
	}
	
	@After
	public void tearDown() {
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "salario_minimo");
	}
	
	@Test
	public void testGETIndexSalarioMinimoController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/salarios")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGETSaveSalarioMinimoController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/salarios/novo"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("salarioMinimo"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testPOSTSaveSalarioMinimoController() throws Exception {
		this.mockMvc.perform(post("/salarios")
				.param("estado", "BR")
				.param("salario", "800")
				.param("dataInicio", "01/01/2017")
				.param("dataFim", "31/12/2016"))
		.andExpect(redirectedUrl("/salarios/novo"));
		
	}
	
	@Test
	public void testPUTSalarioMinimoController() throws Exception {
		SalarioMinimo salarioMinimo = (SalarioMinimo) salarioMinimoService.save(new SalarioMinimo(
				"AL", "800", new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2016"), new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2016"), "Decreto Test"));
		
		this.mockMvc.perform(put("/salarios/" + salarioMinimo.getId() + "/editar")).andExpect(redirectedUrl("/salarios"));
	}
}