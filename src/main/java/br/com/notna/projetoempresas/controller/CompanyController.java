package br.com.notna.projetoempresas.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.notna.projetoempresas.model.Company;
import br.com.notna.projetoempresas.repository.CompanyRepository;

@RestController
@RequestMapping({ "/company" })
public class CompanyController {
	private CompanyRepository repository;

	public CompanyController(CompanyRepository repository) {
		super();
		this.repository = repository;
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAll() {
		Map<String, Object> response = new HashMap<String, Object>();
		Iterable<Company> companys = repository.findAll();

		response.put("status", true);
		response.put("data", companys);
		return ResponseEntity.ok().body(response);
	}

	@PostMapping(produces = "application/json", consumes= "application/json")
	public ResponseEntity<Map<String, Object>> create(@RequestBody Company company) {
		Map<String, Object> response = new HashMap<String, Object>();
		repository.save(company);
		response.put("status", true);
		response.put("data", company);
		return ResponseEntity.ok().body(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Map<String, Object>> update(@PathVariable("id") long id, @RequestBody Company company) {
		Map<String, Object> response = new HashMap<String, Object>();
		Optional<Company> oldCompany = repository.findById(id);
		if (oldCompany.isPresent()) {
			Company newCompany = company;
			repository.save(newCompany);
			response.put("status", true);
			response.put("data", newCompany);
			return ResponseEntity.ok().body(response);
		} else
			response.put("status", false);
		response.put("message", "COMPANY_NOT_FOUND");
		response.put("data", null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> remove(@PathVariable("id") long id) {
		Map<String, Object> response = new HashMap<String, Object>();

		response.put("status", false);
		response.put("data", null);

		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			response.putIfAbsent("status", true);
			response.putIfAbsent("data", record);

			return new ResponseEntity<>(response, HttpStatus.OK);
		}).orElse(new ResponseEntity<>(response, HttpStatus.NOT_FOUND));

	}

}
