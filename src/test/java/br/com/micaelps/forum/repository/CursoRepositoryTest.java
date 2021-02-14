package br.com.micaelps.forum.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.micaelps.forum.modelo.Curso;

@RunWith(SpringRunner.class)
@DataJpaTest

public class CursoRepositoryTest {
    
    @Autowired
    private CursoRepository repo;

    @Autowired
    private TestEntityManager em;

    @Test
    public void DeveriaCarregarUmCursoAoBuscarPeloNome(){
        String nomeCurso = "HTML 5";

        Curso html5 = new Curso();
        html5.setNome(nomeCurso);
        em.persist(html5);
        Curso curso = repo.findByNome(nomeCurso);
        Assert.assertNotNull(curso);
        Assert.assertEquals(nomeCurso, curso.getNome());
    }
}
