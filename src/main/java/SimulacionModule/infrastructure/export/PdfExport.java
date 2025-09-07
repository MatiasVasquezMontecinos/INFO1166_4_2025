package SimulacionModule.infrastructure.export;


import SimulacionModule.entity.Usuario;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


public class PdfExport implements CvExport {
    @Override
    public void exportarCv(Usuario usuario, String path) throws Exception {
        PdfWriter writer = new PdfWriter(path);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Curriculum Vitae").setBold().setFontSize(20));
        document.add(new Paragraph("Nombre" + usuario.getNombre()));

        if (usuario.getResumenPerfil() != null) {
            document.add(new Paragraph("Resumen perfil: \n" + usuario.getResumenPerfil()));
        }

        if (usuario.getExperiencias() != null && !usuario.getExperiencias().isEmpty()) {
            document.add(new Paragraph("Experiencia laboral:").setBold());
            usuario.getExperiencias().forEach(exp ->
                    document.add(new Paragraph(" - " + exp.toString()))
            );
        }

        if (usuario.getReferencias() != null && !usuario.getReferencias().isEmpty()) {
            document.add(new Paragraph("Referencias:").setBold());
            usuario.getReferencias().forEach(ref ->
                    document.add(new Paragraph(" - " + ref.toString()))
            );
        }

        if (usuario.getEducacion() != null && !usuario.getEducacion().isEmpty()) {
            document.add(new Paragraph("Educacion:").setBold());
            usuario.getEducacion().forEach(edu ->
                    document.add(new Paragraph(" - " + edu.toString()))
            );
        }

        if (usuario.getCapacitaciones() != null && !usuario.getCapacitaciones().isEmpty()) {
            document.add(new Paragraph("Capacitaciones:").setBold());
            usuario.getCapacitaciones().forEach(cap ->
                    document.add(new Paragraph(" - " + cap.toString()))
            );
        }

        if (usuario.getIdiomas() != null && !usuario.getIdiomas().isEmpty()) {
            document.add(new Paragraph("Idiomas:").setBold());
            usuario.getIdiomas().forEach(ido ->
                    document.add(new Paragraph(" - " + ido.toString()))
            );
        }

        if (usuario.getLicencias() != null && !usuario.getLicencias().isEmpty()) {
            document.add(new Paragraph("Licencias:").setBold());
            usuario.getLicencias().forEach(lic ->
                    document.add(new Paragraph(" - " + lic.toString()))
            );
        }

        if (usuario.getVehiculos() != null && !usuario.getVehiculos().isEmpty()) {
            document.add(new Paragraph("Vehiculos:").setBold());
            usuario.getVehiculos().forEach(veh ->
                    document.add(new Paragraph(" - " + veh.toString()))
            );
        }

        if (usuario.getCompetencias() != null && !usuario.getCompetencias().isEmpty()) {
            document.add(new Paragraph("Competencias:").setBold());
            usuario.getCompetencias().forEach(comp ->
                    document.add(new Paragraph(" - " + comp.toString()))
            );

            if (usuario.getExpectativa() != null) {
                document.add(new Paragraph("Resumen perfil: \n" + usuario.getExpectativa()));
            }

        }

        document.close();

    }
}
