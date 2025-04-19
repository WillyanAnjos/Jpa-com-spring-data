package com.willyan.rev_jpa.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.willyan.rev_jpa.dao.AutorDao;
import com.willyan.rev_jpa.entity.Autor;
import com.willyan.rev_jpa.entity.InfoAutor;
import com.willyan.rev_jpa.projection.AutorInfoProjection;

@RestController
@RequestMapping("autores")
public class AutorController {

	@Autowired
	private AutorDao dao;

	@PostMapping
	public Autor register(@RequestBody Autor autor) {
		dao.save(autor);
		return autor;
	}

	@PutMapping
	public Autor update(@RequestBody Autor autor) {
		dao.update(autor);
		return autor;
	}

	@DeleteMapping("/{id}")
	public String remove(@PathVariable Long id) {
		dao.delete(id);
		return "Autor removido com sucesso!";
	}

	@GetMapping("/{id}")
	public Autor findById(@PathVariable Long id) {
		return dao.findById(id);
	}

	@GetMapping
	public List<Autor> findAll() {
		return dao.findAll();
	}
	
	@GetMapping("nomeOrSobrenome")
	public List<Autor> findAllByNomeOrSobrenome(@RequestParam String termo) {
		return dao.findAllByNomeOrSobrenome(termo);
	}
	
	@GetMapping("total")
	public Long getTotal() {
		return dao.getTotalElements();
	}
	
	@PutMapping("{id}/info")
	public Autor salvarInfoAutor(@PathVariable Long id, @RequestBody InfoAutor infoAutor) {
		return dao.saveInfoAutor(infoAutor, id);
	}

	@GetMapping("info")
	public List<Autor> findByCargo(@RequestParam String cargo) {
		return dao.findByCargo(cargo);
	}
	
	@GetMapping("autor-info")
	public AutorInfoProjection findByAutorInfoById(@RequestParam Long id) {
		return dao.findAutorInfoById(id);
	}

}