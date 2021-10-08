package br.senac.ecommerceapiprodutos.categoria;

import com.querydsl.core.types.Predicate;
import lombok.Getter;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Service
public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Long>,
        QuerydslPredicateExecutor<Categoria> {

  List<Categoria> findAll(Predicate filter);

}



