package br.com.micaelps.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.micaelps.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

}
