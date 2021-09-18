package br.senac.ecommerceapiprodutos.categoria;


import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoria")
@AllArgsConstructor

public class CategoriaController {

    private CategoriaService categoriaService;

    @PostMapping("/")
    public ResponseEntity<CategoriaRepresentation.Detail> createOrUpdateCategoria(
            @Valid @RequestBody CategoriaRepresentation.CreateOrUpdateCategoria createOrUpdateCategoria) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CategoriaRepresentation.Detail.from(this.categoriaService.salvar(createOrUpdateCategoria)));

    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaRepresentation.Detail> atualizaCategoria(@PathVariable("id") Long id,
    @Valid @RequestBody CategoriaRepresentation.CreateOrUpdateCategoria createOrUpdateCategoria){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CategoriaRepresentation.Detail.from(this.categoriaService.update(id, createOrUpdateCategoria)));

    }

    @GetMapping("/")
    public ResponseEntity<List<CategoriaRepresentation.Lista>> getAll() {

        BooleanExpression filter = QCategoria.categoria.status.eq(Categoria.Status.Ativo);

        return ResponseEntity.ok(CategoriaRepresentation.Lista
                .from(this.categoriaService.getAllCategoria(filter)));

    }


    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<CategoriaRepresentation.Detail> getoneCategoria(@PathVariable("id") Long id) {

        return ResponseEntity.ok(CategoriaRepresentation.Detail
                .from(this.categoriaService.getCategoria(id)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategoria(@PathVariable("id") Long id){
        this.categoriaService.deleteCategoria(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}


