package br.senac.ecommerceapiprodutos.categoria;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity (name = "CATEGORIA")



public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotNull(message = "O campo descrição não pode ser nulo")
    @Column (name = "DESCRICAO")
    @Size (max = 30, min = 1, message = "A validação deve ter no mínimo 30 caracteres")
    private String descricao;

    @Column(name = "STATUS")
    private Status status;

    public enum Status{
        Ativo,
        Inativo
    }

}
