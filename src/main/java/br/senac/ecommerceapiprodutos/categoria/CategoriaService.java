package br.senac.ecommerceapiprodutos.categoria;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

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



}
