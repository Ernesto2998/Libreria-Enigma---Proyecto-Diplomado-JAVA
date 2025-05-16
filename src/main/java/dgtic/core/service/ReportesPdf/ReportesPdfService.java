package dgtic.core.service.ReportesPdf;

import dgtic.core.model.Inventario;
import dgtic.core.model.Venta;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ReportesPdfService {
    ByteArrayInputStream generarReporteVentas(List<Venta> ventas);

    ByteArrayInputStream generarReporteInventario(List<Inventario> inventarios);
}
