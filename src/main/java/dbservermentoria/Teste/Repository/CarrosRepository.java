package dbservermentoria.Teste.Repository;

import dbservermentoria.Teste.Model.Carros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarrosRepository extends JpaRepository<Carros, Integer> {

}


