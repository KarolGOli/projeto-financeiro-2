package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Financas;

@Repository
public interface FinancasRepositorio extends JpaRepository<Financas, Long > {}
