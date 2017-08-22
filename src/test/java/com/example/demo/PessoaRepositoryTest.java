package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

public class PessoaRepositoryTest extends TddApplicationTests {

	@Autowired private PessoaService pessoaService;
	@Autowired private JdbcTemplate jdbcTemplate;
	
	@After
	public void tearDown() {
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "pessoa");
	}
	
	@Test
	public void findByNomeRetornaPessoa() {
		Pessoa alex = new Pessoa("Alex");
		pessoaService.save(alex);
		
		Pessoa encontrado = pessoaService.findByNome(alex.getNome());
		
		assertThat(encontrado.getNome()).isEqualTo(alex.getNome());
	}
}
