package dgtic.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SucursalDto {

    private Integer id;
    private String calle;
    private Integer numeroExterior;
    private String numeroInterior;
    private String colonia;
    private String municipio;
    private Integer codigoPostal;
}
