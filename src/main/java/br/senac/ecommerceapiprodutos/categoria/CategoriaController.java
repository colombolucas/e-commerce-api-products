package br.senac.ecommerceapiprodutos.categoria;


import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.hibernate.query.criteria.internal.predicate.BooleanExpressionPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@AllArgsConstructor

public class CategoriaController {

    private CategoriaService categoriaService;

    @PostMapping
    @RequestMapping("/")
    public ResponseEntity<CategoriaRepresentation.Detail> createCategoria(
        @RequestBody CategoriaRepresentation.CreateCategoria createCategoria){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CategoriaRepresentation.Detail.from(this.categoriaService.salvar(createCategoria)));

    }

    @GetMapping
    @RequestMapping("/todos")
    public ResponseEntity<List<CategoriaRepresentation.Lista>> getAll() {

        BooleanExpression filter = QCategoria.categoria.status.eq(Categoria.Status.Ativo);

        return ResponseEntity.ok(CategoriaRepresentation.Lista
                .from(this.categoriaService.getAllCategoria(filter)));

    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity deleteCategoria(@PathVariable("id") Long id){
        this.categoriaService.deleteCategoria(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}


