package dgtic.core.model.dto;

import dgtic.core.model.Venta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaLibroDto {
    private Integer sucursalId;
    private Integer empleadoId;
    private Integer libroId;
    private LocalDate fechaVenta;
    private Float precio;
    private Integer descuento;
    private Integer cantidad;
    private Venta.MetodoPago metodoPago;
    private BigDecimal total;
}
