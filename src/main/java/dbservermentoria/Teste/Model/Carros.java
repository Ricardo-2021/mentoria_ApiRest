package dbservermentoria.Teste.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "car")
public class Carros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    @NotNull
    private String nome;
    @Column(name = "description")
    @NotNull
    private String descricao;
    @Column(name = "available")
    @NotNull
    private boolean acessivel;
    @Column(name = "brand")
    @NotNull
    private String marca;
    @Column(name = "created_at")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate criadoEm;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate updated_at;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private Categorias categoryId;

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAcessivel() {
        return acessivel;
    }

    public void setAcessivel(boolean acessivel) {
        this.acessivel = acessivel;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public LocalDate getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDate criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Categorias getCategoriaId() {
        return categoryId;
    }

    public void setCategoriaId(Categorias categoriaId) {
        this.categoryId = categoriaId;
    }
}
