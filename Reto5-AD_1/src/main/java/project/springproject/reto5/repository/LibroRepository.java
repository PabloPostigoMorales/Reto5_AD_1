package project.springproject.reto5.repository;
import org.springframework.data.repository.query.Param;
import project.springproject.reto5.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    boolean existsByCategoria(@Param("Categoria") String Categoria);
    List<Libro> findByCategoria(@Param("Categoria") String Categoria);
    boolean existsByAutor(@Param("Autor") String Autor);
    List<Libro> findByAutor(@Param("Autor") String Autor);
}
