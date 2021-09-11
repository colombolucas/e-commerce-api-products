package br.senac.ecommerceapiprodutos.categoria;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public interface CategoriaRepository extends CrudRepository<Categoria, UUID>,
        QuerydslPredicateExecutor<Categoria> {
}
