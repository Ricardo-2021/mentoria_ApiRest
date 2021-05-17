package dbservermentoria.Teste.Service;

import dbservermentoria.Teste.Exceptions.IdNaoEncontradoNoBancoDeDadosException;
import dbservermentoria.Teste.Model.Carros;
import dbservermentoria.Teste.Model.Categorias;
import dbservermentoria.Teste.Repository.CategoriasRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CategoriasService {

    private CategoriasRepository categoriasRepository;

    @Autowired
    public CategoriasService(CategoriasRepository categoriasRepository) {
        this.categoriasRepository = categoriasRepository;
    }

    public Categorias findById(Integer categoriaId) {
        return categoriasRepository.findById(categoriaId).orElseThrow(() -> new IdNaoEncontradoNoBancoDeDadosException(""));
    }

    public Categorias updateCategory(Categorias categoria, Integer categoriaId) {
        Categorias categoriaBancoDeDados = findById(categoriaId);
        BeanUtils.copyProperties(categoria, categoriaBancoDeDados, "id");
        return categoriasRepository.save(categoriaBancoDeDados);
    }

    public Categorias updateByFieldCategory(Categorias categoria, Integer categoriaId) {
        Categorias categoriaBancodeDados = findById(categoriaId);
        BeanUtils.copyProperties(fieldComplete(categoria, categoriaBancodeDados), categoriaBancodeDados, "categoriaId");
        return categoriasRepository.save(categoriaBancodeDados);
    }

    public Categorias fieldComplete(Categorias categoria, Categorias categoriaBancoDeDados) {
        Stream<Field> fields = Stream.of(categoriaBancoDeDados.getClass().getDeclaredFields());
        fields.forEach(field -> {
            try {
                field.setAccessible(true);
                if (field.get(categoria) == null)
                    field.set(categoria, field.get(categoriaBancoDeDados));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return categoria;
    }
}
