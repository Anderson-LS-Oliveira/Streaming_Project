package StreamingProject.demo.repository;

import StreamingProject.demo.entity.Categoria;
import StreamingProject.demo.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findByTituloContainingOrderByTituloAsc(String titulo);

    List<Video> findByCategoriaOrderByTituloAsc(Categoria categoria);

    @Query("SELECT v FROM Video v JOIN v.avaliacoes a GROUP BY v ORDER BY AVG(a.nota) DESC")
    List<Video> findTop10MaisBemAvaliados(org.springframework.data.domain.Pageable pageable);

    @Query("SELECT v FROM Video v JOIN v.visualizacoes vis GROUP BY v ORDER BY COUNT(vis) DESC")
    List<Video> findTop10MaisAssistidos(org.springframework.data.domain.Pageable pageable);

}
