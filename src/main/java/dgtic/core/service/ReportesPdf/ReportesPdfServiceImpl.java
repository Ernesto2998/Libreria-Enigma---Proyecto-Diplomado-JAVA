package dgtic.core.service.ReportesPdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import dgtic.core.model.Inventario;
import dgtic.core.model.Venta;
import dgtic.core.model.VentaLibro;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ReportesPdfServiceImpl implements ReportesPdfService{

    @Override
    public ByteArrayInputStream generarReporteVentas(List<Venta> ventas) {
        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font cabeceraFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font textoFont = FontFactory.getFont(FontFactory.HELVETICA, 11);

            Paragraph titulo = new Paragraph("Reporte de Ventas", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(15f);
            document.add(titulo);

            // Ahora 9 columnas: ID, Fecha, Empleado, Libro, Cantidad, Precio, Descuento, Método de pago, Total
            PdfPTable table = new PdfPTable(9);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1, 2, 3, 4, 1.5f, 2, 2, 2, 2});
            table.setSpacingBefore(10f);

            Stream.of("ID", "Fecha", "Empleado", "Libro", "Cantidad", "Precio", "Descuento", "Método Pago", "Total")
                    .forEach(col -> {
                        PdfPCell header = new PdfPCell(new Phrase(col, cabeceraFont));
                        header.setBackgroundColor(Color.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setPadding(5f);
                        table.addCell(header);
                    });

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            for (Venta venta : ventas) {
                String idVenta = venta.getNumVenta().toString();
                String fecha = venta.getFechaVenta().format(formatter);
                String empleado = venta.getEmpleado().getNombre() + " " + venta.getEmpleado().getApellido1();
                String metodoPago = venta.getMetodoPago().name();

                for (VentaLibro ventaLibro : venta.getVentaLibros()) {
                    String libro = ventaLibro.getLibro().getTitulo();
                    String cantidad = String.valueOf(ventaLibro.getCantidadLibros());
                    String precio = "$" + ventaLibro.getLibro().getPrecio();
                    String descuento = ventaLibro.getLibro().getDescuento() != null ?
                            ventaLibro.getLibro().getDescuento() + "%" : "0%";
                    String totalVenta = "$" + venta.getTotal().toPlainString();

                    table.addCell(new PdfPCell(new Phrase(idVenta, textoFont)));
                    table.addCell(new PdfPCell(new Phrase(fecha, textoFont)));
                    table.addCell(new PdfPCell(new Phrase(empleado, textoFont)));
                    table.addCell(new PdfPCell(new Phrase(libro, textoFont)));
                    table.addCell(new PdfPCell(new Phrase(cantidad, textoFont)));
                    table.addCell(new PdfPCell(new Phrase(precio, textoFont)));
                    table.addCell(new PdfPCell(new Phrase(descuento, textoFont)));
                    table.addCell(new PdfPCell(new Phrase(metodoPago, textoFont)));
                    table.addCell(new PdfPCell(new Phrase(totalVenta, textoFont)));
                }
            }

            document.add(table);
            document.close();

        } catch (DocumentException e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream generarReporteInventario(List<Inventario> inventarios) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font cabeceraFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font textoFont = FontFactory.getFont(FontFactory.HELVETICA, 11);

            Paragraph titulo = new Paragraph("Reporte de Inventario", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(15f);
            document.add(titulo);

            PdfPTable table = new PdfPTable(4); // 4 columnas
            table.setWidthPercentage(100);
            table.setWidths(new float[]{0.5f, 4.5f, 4, 1f});
            table.setSpacingBefore(10f);

            Stream.of("ID", "Libro", "Sucursal", "Stock")
                    .forEach(col -> {
                        PdfPCell header = new PdfPCell(new Phrase(col, cabeceraFont));
                        header.setBackgroundColor(Color.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setPadding(5f);
                        table.addCell(header);
                    });

            for (Inventario inv : inventarios) {
                String sucursal = inv.getSucursal().getCalle() + " "
                        + inv.getSucursal().getNumeroExterior() + " "
                        + inv.getSucursal().getNumeroInterior() + ", "
                        + inv.getSucursal().getColonia() + ", "
                        + inv.getSucursal().getMunicipio() + ", CP: "
                        + inv.getSucursal().getCodigoPostal();
                String libro = inv.getLibro().getTitulo() + ", "
                        + inv.getLibro().getEditorial().getEditorialName() + ", Pasta: "
                        + inv.getLibro().getTipoPasta();


                table.addCell(new PdfPCell(new Phrase(inv.getId().toString(), textoFont)));
                table.addCell(new PdfPCell(new Phrase(libro, textoFont)));
                table.addCell(new PdfPCell(new Phrase(sucursal, textoFont)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(inv.getStock()), textoFont)));
            }

            document.add(table);
            document.close();

        } catch (DocumentException e) {
            throw new RuntimeException("Error al generar el PDF de inventario", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
