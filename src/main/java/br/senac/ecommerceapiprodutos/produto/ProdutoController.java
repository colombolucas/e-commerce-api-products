package br.senac.ecommerceapiprodutos.produto;

import br.senac.ecommerceapiprodutos.categoria.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import br.senac.ecommerceapiprodutos.util.Paginacao;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/produto")
@AllArgsConstructor
public class ProdutoController {

    private ProdutoService produtoService;
    private final CategoriaService categoriaService;
    private ProdutoRepository produtoRepository;

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
    public ResponseEntity<Paginacao> buscarTodos(


        @QuerydslPredicate(root = Produto.class) Predicate filtroProduto,
        @RequestParam(name = "paginaSelecionada", required = false, defaultValue = "0") Integer paginaSelecionada,
        @RequestParam(name = "tamanhoPagina", defaultValue = "20") Integer tamanhoPagina) {



        BooleanExpression filter = Objects.isNull(filtroProduto) ? QProduto.produto.status.eq(Produto.Status.ATIVO) :
                QProduto.produto.status.eq(Produto.Status.ATIVO).and(filtroProduto);

        Pageable pageRequest = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Produto> produtoList = this.produtoRepository.findAll(filter, pageRequest);

        Paginacao paginacao = Paginacao.builder()
                .conteudo(ProdutoRepresentation.Lista.from(produtoList.getContent()))
                .paginaSelecionada(paginaSelecionada)
                .tamanhoPagina(tamanhoPagina)
                .proximaPagina(produtoList.hasNext())
                .build();


        return ResponseEntity.ok(paginacao);
    /*
         return ResponseEntity.ok(ProdutoRepresentation.Lista
                    .from(this.produtoService.buscarTodos(filter)));
*/
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
