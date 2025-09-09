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

        // Título principal
        document.add(new Paragraph("Curriculum Vitae")
                .setBold()
                .setFontSize(20));

        // Nombre
        if (usuario.getNombre() != null) {
            document.add(new Paragraph("Nombre: " + usuario.getNombre()));
        }


        // Resumen de perfil
        if (usuario.getResumenPerfil() != null && usuario.getResumenPerfil().getDescripcion() != null) {
            document.add(new Paragraph("\nResumen de perfil:"));
            document.add(new Paragraph(usuario.getResumenPerfil().getDescripcion()));
        }

        // Experiencia laboral
        if (usuario.getExperiencias() != null && !usuario.getExperiencias().isEmpty()) {
            document.add(new Paragraph("\nExperiencia laboral:").setBold());
            usuario.getExperiencias().forEach(exp -> {
                document.add(new Paragraph("Ocupación: " + exp.getOcupacion()));
                document.add(new Paragraph("Empresa: " + exp.getEmpresa()));
                document.add(new Paragraph("Duración: " + exp.getExperienciaMeses() + " meses"));
                if (exp.getDescripcion() != null) {
                    document.add(new Paragraph("Descripción: " + exp.getDescripcion()));
                }
                document.add(new Paragraph("----------------------------"));
            });
        }

        // Referencias
        if (usuario.getReferencias() != null && !usuario.getReferencias().isEmpty()) {
            document.add(new Paragraph("\nReferencias laborales:").setBold());
            usuario.getReferencias().forEach(ref -> {
                document.add(new Paragraph(ref.getNombreCompleto() + " - " + ref.getPuesto()));
                document.add(new Paragraph("Empresa: " + ref.getEmpresa()));
                document.add(new Paragraph("Tel: " + ref.getTelefono() + " | Email: " + ref.getEmail()));
                document.add(new Paragraph("----------------------------"));
            });
        }

        // Educación
        if (usuario.getEducacion() != null && !usuario.getEducacion().isEmpty()) {
            document.add(new Paragraph("\nEducación:").setBold());
            usuario.getEducacion().forEach(edu -> {
                document.add(new Paragraph(edu.getTitulo() + " - " + edu.getInstitucion()));
                if (edu.getObservaciones() != null) {
                    document.add(new Paragraph("Obs: " + edu.getObservaciones()));
                }
                document.add(new Paragraph("----------------------------"));
            });
        }

        // Capacitaciones
        if (usuario.getCapacitaciones() != null && !usuario.getCapacitaciones().isEmpty()) {
            document.add(new Paragraph("\nCapacitaciones:").setBold());
            usuario.getCapacitaciones().forEach(cap -> {
                document.add(new Paragraph(cap.getNombreCurso() + " - " + cap.getInstitucion()));
                document.add(new Paragraph("Duración: " + cap.getDuracionHoras() + " horas"));
                document.add(new Paragraph("----------------------------"));
            });
        }

        // Idiomas
        if (usuario.getIdiomas() != null && !usuario.getIdiomas().isEmpty()) {
            document.add(new Paragraph("\nIdiomas:").setBold());
            usuario.getIdiomas().forEach(ido -> {
                document.add(new Paragraph(
                        ido.getIdioma() + " (Lectura: " + ido.getNivelLectura() +
                                ", Escritura: " + ido.getNivelEscritura() +
                                ", Habla: " + ido.getNivelHabla() + ")"
                ));
            });
        }

        // Licencias
        if (usuario.getLicencias() != null && !usuario.getLicencias().isEmpty()) {
            document.add(new Paragraph("\nLicencias de conducir:").setBold());
            usuario.getLicencias().forEach(lic ->
                    document.add(new Paragraph("- " + lic.getTipoLicencia()))
            );
        }


        // Competencias
        if (usuario.getCompetencias() != null && !usuario.getCompetencias().isEmpty()) {
            document.add(new Paragraph("\nCompetencias:").setBold());
            usuario.getCompetencias().forEach(comp ->
                    document.add(new Paragraph("- " + comp.getDescripcion()))
            );
        }

        // Expectativa laboral
        if (usuario.getExpectativa() != null) {
            var exp = usuario.getExpectativa();
            document.add(new Paragraph("\nExpectativa laboral:").setBold());
            if (exp.getOcupacionDeseada() != null)
                document.add(new Paragraph("Ocupación deseada: " + exp.getOcupacionDeseada()));
            if (exp.getNivelCargo() != null)
                document.add(new Paragraph("Nivel de cargo: " + exp.getNivelCargo()));
            if (exp.getSalarioDeseado() != null)
                document.add(new Paragraph("Salario deseado: " + exp.getSalarioDeseado()));
            if (exp.getJornada() != null)
                document.add(new Paragraph("Jornada: " + exp.getJornada()));
            if (exp.getRegionComuna() != null)
                document.add(new Paragraph("Ubicación: " + exp.getRegionComuna()));
            if (exp.getTipoContrato() != null)
                document.add(new Paragraph("Contrato: " + exp.getTipoContrato()));
            if (exp.getTurnos() != null)
                document.add(new Paragraph("Turnos: " + exp.getTurnos()));
        }

        document.close();
    }
}
