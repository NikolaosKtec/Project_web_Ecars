package eaj.br.web_loja.project_web_ecars.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Prod_automobilis {

    @Id
    long id;

    @Column(length = 36)
    @NotBlank(message = "nome do produto não pode ser vazio!")
    @Size(max = 36, message ="campo máximo permitido é 36!")
    String produto;

    @Column(length = 75)
    @NotBlank(message = "descrição não pode ser vazio!")
    @Size(max = 75, message ="campo máximo permitido é 75!")
    String decricao;

    //@NotBlank(message = "preço não pode ser vazio!")
    @Column(columnDefinition = "NUMERIC(8,2)")
    @Min(value = 0, message = "valor não pode ser negativo!")
    double preco;

    //@NotBlank(message = "quantidade não pode ser vazio!")
    @Min(value = 0, message = "valor não pode ser negativo!")
    Integer quantidade;

    String imageUri;

    @Column(columnDefinition = "boolean DEFAULT false")
    boolean deleted;
}
