package br.com.notna.projetoempresas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.notna.projetoempresas.model.Company;

@Repository
public interface CompanyRepository  extends JpaRepository<Company, Long>  {

}
