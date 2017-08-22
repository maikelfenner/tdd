package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalarioMinimoService implements CrudService<SalarioMinimo>{

	@Autowired private SalarioMinimoRepository salarioMinimoRepository;
	
	@Override
	public <T> List<SalarioMinimo> findAll() {
		return salarioMinimoRepository.findAll();
	}

	@Override
	public <T> Object findOne(Integer id) {
		return salarioMinimoRepository.findOne(id);
	}

	@Override
	public <T> Object save(SalarioMinimo entity) {
		return salarioMinimoRepository.save(entity);
	}

	@Override
	public void delete(SalarioMinimo entity) {
		salarioMinimoRepository.delete(entity);
	}
}
