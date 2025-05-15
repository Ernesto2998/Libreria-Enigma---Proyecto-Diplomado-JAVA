package dgtic.core.service.ReportesPdf;

import dgtic.core.model.Venta;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ReportesPdfService {
    public ByteArrayInputStream generarReporteVentas(List<Venta> ventas);
}
