package com.devsuperior.gscommerce.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public record ProductRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
        String name,

        @NotBlank(message = "Descrição é obrigatória")
        @Size(min = 10, message = "Descrição deve ter no mínimo 10 caracteres")
        String description,

        @NotNull(message = "Preço é obrigatório")
        @Positive(message = "Preço deve ser positivo")
        Double price,

        @URL(message = "URL da imagem inválida")
        String imgUrl

) {}
