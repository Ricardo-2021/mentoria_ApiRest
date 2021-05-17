package dbservermentoria.Teste.Service;

import dbservermentoria.Teste.Exceptions.IdNaoEncontradoNoBancoDeDadosException;
import dbservermentoria.Teste.Model.Carros;
import dbservermentoria.Teste.Repository.CarrosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CarrosService {

    private CarrosRepository carrosRepository;

    @Autowired
    public CarrosService(CarrosRepository carrosRepository) {
        this.carrosRepository = carrosRepository;
    }

    public Carros findById(Integer id) {
        return carrosRepository.findById(id).orElseThrow(() -> new IdNaoEncontradoNoBancoDeDadosException(""));
    }

    public Carros updateCar(Carros carro, Integer id) {
        Carros carroBancoDeDados = findById(id);
        BeanUtils.copyProperties(carro, carroBancoDeDados, "id");
        return carrosRepository.save(carroBancoDeDados);
    }

    public Carros updateByFieldCar(Carros carrosBody, Integer id) {
        Carros carroBancoDeDados = findById(id);
        BeanUtils.copyProperties(fieldComplete(carrosBody, carroBancoDeDados), carroBancoDeDados, "id");
        return carrosRepository.save(carroBancoDeDados);
    }

    public Carros fieldComplete(Carros carro, Carros carroBancoDeDados) {
        Stream<Field> fields = Stream.of(carroBancoDeDados.getClass().getDeclaredFields());
        fields.forEach(field -> {
            try {
                field.setAccessible(true);
                if (field.get(carro) == null)
                    field.set(carro, field.get(carroBancoDeDados));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return carro;
    }
}
