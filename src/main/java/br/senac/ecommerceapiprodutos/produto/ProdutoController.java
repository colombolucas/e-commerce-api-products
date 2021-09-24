package br.senac.ecommerceapiprodutos.produto;

import br.senac.ecommerceapiprodutos.categoria.Categoria;
import br.senac.ecommerceapiprodutos.categoria.CategoriaRepresentation;
import br.senac.ecommerceapiprodutos.categoria.CategoriaService;
import br.senac.ecommerceapiprodutos.categoria.QCategoria;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produto")
@AllArgsConstructor
public class ProdutoController {

    private ProdutoService produtoService;
    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<ProdutoRepresentation.Detalhes> cadastrarProduto(
            @Valid @RequestBody ProdutoRepresentation.CreateOrUpdate createOrUpdate) {

        Categoria categoria = this.categoriaService.getCategoria(createOrUpdate.getCategoria());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProdutoRepresentation.Detalhes.from(this.produtoService.salvar(createOrUpdate, categoria)));


    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoRepresentation.Detalhes> atualizaProduto(@PathVariable("id") Long id,
                                                                          @Valid @RequestBody ProdutoRepresentation.CreateOrUpdate createOrUpdate) {

        Categoria categoria = this.categoriaService.getCategoria(createOrUpdate.getCategoria());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ProdutoRepresentation.Detalhes.from(this.produtoService.atualizar(id, createOrUpdate, categoria)));

    }

    @GetMapping("/")
    public ResponseEntity<List<ProdutoRepresentation.Lista>> buscarTodos() {

        BooleanExpression filter = QProduto.produto.status.eq(Produto.Status.ATIVO);

        return ResponseEntity.ok(ProdutoRepresentation.Lista
                .from(this.produtoService.buscarTodos(filter)));

    }



    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<ProdutoRepresentation.Detalhes> buscarUm (@PathVariable("id") Long id) {

        return ResponseEntity.ok(ProdutoRepresentation.Detalhes
                .from(this.produtoService.buscarUm(id)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduto(@PathVariable("id") Long id){
        this.produtoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
