package dbservermentoria.Teste.Controller;

import dbservermentoria.Teste.Exceptions.IdNaoEncontradoNoBancoDeDadosException;
import dbservermentoria.Teste.Model.Categorias;
import dbservermentoria.Teste.Repository.CategoriasRepository;
import dbservermentoria.Teste.Service.CategoriasService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriasController {

    private CategoriasRepository categoriasRepository;
    private ArrayList<Categorias> categoriasArrayList = new ArrayList<>();
    private CategoriasService categoriasService;

    @Autowired
    public CategoriasController(CategoriasRepository categoriasRepository, CategoriasService categoriasService) {
        this.categoriasRepository = categoriasRepository;
        this.categoriasService = categoriasService;
    }

    @PostMapping
    public ResponseEntity<Categorias> saveCategory(@RequestBody @Valid Categorias categorias) {
        categoriasRepository.save(categorias);
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllCategory() {
        List<Categorias> listaDeCategorias = categoriasRepository.findAll();
        if (!listaDeCategorias.isEmpty()) {
            return new ResponseEntity(listaDeCategorias, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("Lista Vazia", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{categoriaId}")
    public ResponseEntity<?> findByIdCategory(@PathVariable Integer categoriaId) throws IdNaoEncontradoNoBancoDeDadosException {
        Optional<Categorias> categoriaPorId = categoriasRepository.findById(categoriaId);
        if (categoriaPorId.isPresent()) {
            return new ResponseEntity(categoriaPorId, HttpStatus.FOUND);
        }
        throw new IdNaoEncontradoNoBancoDeDadosException("");
    }

    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<?> deletecategory(@PathVariable Integer categoriaId) throws IdNaoEncontradoNoBancoDeDadosException {

        Optional<Categorias> categoriaDeletada = categoriasRepository.findById(categoriaId);
        if (categoriaDeletada.isPresent()) {
            categoriasRepository.deleteById(categoriaId);
            return new ResponseEntity<>("Categoria excluida com sucesso.", HttpStatus.FOUND);
        }
        throw new IdNaoEncontradoNoBancoDeDadosException("");
    }

    @PutMapping("/{categoriaId}")
    public ResponseEntity<Object> updateCategory(@RequestBody @Valid Categorias categoria, @PathVariable Integer categoriaId) throws IdNaoEncontradoNoBancoDeDadosException {
        return new ResponseEntity<>(categoriasService.updateCategory(categoria, categoriaId), HttpStatus.FOUND);
    }

    @PatchMapping("/{categoriaId}")
    public ResponseEntity<?> updateByFieldCategory(@RequestBody @Valid Categorias categoria, @PathVariable Integer categoriaId) throws IdNaoEncontradoNoBancoDeDadosException {
        return new ResponseEntity<>(categoriasService.updateByFieldCategory(categoria, categoriaId), HttpStatus.FOUND);
    }
}
