package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class PessoaService implements CrudService<Pessoa> {

	@Autowired private PessoaRepository pessoaRepository;
	
	public Pessoa findByNome(String nome) {
		return pessoaRepository.findByNome(nome);
	}
	
	@Override
	public <T> List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	@Override
	public <T> Object findOne(Integer id) {
		return pessoaRepository.findOne(id);
	}

	@Override
	public <T> Object save(Pessoa entity) {
		return pessoaRepository.save(entity);
	}

	@Override
	public void delete(Pessoa entity) {
		pessoaRepository.delete(entity);
	}

}
