package dbservermentoria.Teste.Repository;


import dbservermentoria.Teste.Model.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {

}
