package ages.pucrs.hackathon.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO <T>{

    @Schema(description = "Total de elementos na página")
    private Long totalElements;
    @Schema(description = "Quantidade total de paginas")
    private Integer totalPages;
    @Schema(description = "Página solicitada (começa por zero)")
    private Integer page;
    @Schema(description = "Quantidade de registros por página")
    private Integer size;
    @Schema(description = "Lista de registros da página")
    private List<?> content;




}
