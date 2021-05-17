package dbservermentoria.Teste.Controller;

import dbservermentoria.Teste.Exceptions.IdNaoEncontradoNoBancoDeDadosException;
import dbservermentoria.Teste.Model.Carros;
import dbservermentoria.Teste.Repository.CarrosRepository;
import dbservermentoria.Teste.Service.CarrosService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/v1/carros")
public class CarrosController {

    private CarrosRepository carrosRepository;
    private ArrayList<Carros> carrosArrayList = new ArrayList<>();
    private CarrosService carrosService;

    @Autowired
    public CarrosController(  CarrosRepository carrosRepository, CarrosService carrosService ) {
        this.carrosRepository = carrosRepository;
        this.carrosService = carrosService;
    }

    @PostMapping
    public ResponseEntity<Carros> saveCar(@RequestBody @Valid Carros carros) {
        carrosRepository.save(carros);
        return new ResponseEntity<>(carros, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllCar() {
        List<Carros> listaDeCarros = carrosRepository.findAll();
        if (!listaDeCarros.isEmpty()) {
            return new ResponseEntity<>(listaDeCarros, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("Lista Vazia", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdCar(@PathVariable  Integer id) throws IdNaoEncontradoNoBancoDeDadosException {
        Optional<Carros> carroPorId = carrosRepository.findById(id);
        if (carroPorId.isPresent()) {
            return new ResponseEntity<>(carroPorId, HttpStatus.FOUND);
        }
        throw new IdNaoEncontradoNoBancoDeDadosException("");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id) throws IdNaoEncontradoNoBancoDeDadosException {
        Optional<Carros> carroDelete = carrosRepository.findById(id);
        if (carroDelete.isPresent()) {
            carrosRepository.deleteById(id);
            return new ResponseEntity<>("Veiculo excluido com sucesso.", HttpStatus.FOUND);
        }
        throw new IdNaoEncontradoNoBancoDeDadosException("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCar(@RequestBody @Valid Carros carro, @PathVariable Integer id) throws IdNaoEncontradoNoBancoDeDadosException {
        return new ResponseEntity<>(carrosService.updateCar(carro, id), HttpStatus.FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateByFieldCar(@RequestBody  Carros carrosBody, @PathVariable Integer id) throws IdNaoEncontradoNoBancoDeDadosException {
        return new ResponseEntity<>(carrosService.updateByFieldCar(carrosBody, id), HttpStatus.FOUND);
    }
}


