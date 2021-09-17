package br.senac.ecommerceapiprodutos.categoria;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor

public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public Categoria salvar(CategoriaRepresentation.CreateCategoria createCategoria) {

        return this.categoriaRepository.save(Categoria.builder()
                .descricao(createCategoria.getDescricao())
                .status(Categoria.Status.Ativo)
                .build());


    }

public List<Categoria> getAllCategoria(Predicate filter){
        return this.categoriaRepository.findAll(filter);

}

    public void deleteCategoria(Long id) {
        Categoria categoria = this.categoriaRepository.findById(id).get();

        categoria.setStatus(Categoria.Status.Inativo);
        this.categoriaRepository.save(categoria);

    }
}
