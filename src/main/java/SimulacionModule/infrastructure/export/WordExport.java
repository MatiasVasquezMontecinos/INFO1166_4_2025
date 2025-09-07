package SimulacionModule.infrastructure.export;

import SimulacionModule.entity.Usuario;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class WordExport implements CvExport {

    @Override
    public void exportarCv(Usuario usuario, String path) throws IOException {
        XWPFDocument document = new XWPFDocument();


        XWPFParagraph titulo = document.createParagraph();
        titulo.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = titulo.createRun();
        run.setText("Curriculum Vitae");
        run.setBold(true);
        run.setFontSize(20);

        document.createParagraph().createRun().setText("Nombre: " + usuario.getNombre());

        if (usuario.getResumenPerfil() != null) {
            document.createParagraph().createRun()
                    .setText("Resumen perfil: " + usuario.getResumenPerfil().toString());
        }

        if (usuario.getExperiencias() != null && !usuario.getExperiencias().isEmpty()) {
            document.createParagraph().createRun().setText("Experiencia laboral:");
            for (var exp : usuario.getExperiencias()) {
                document.createParagraph().createRun().setText(" - " + exp.toString());
            }
        }

        if (usuario.getReferencias() != null && !usuario.getReferencias().isEmpty()) {
            document.createParagraph().createRun().setText("Referencias:");
            for (var ref : usuario.getReferencias()) {
                document.createParagraph().createRun().setText(" - " + ref.toString());
            }
        }

        if (usuario.getEducacion() != null && !usuario.getEducacion().isEmpty()) {
            document.createParagraph().createRun().setText("Educación:");
            for (var edu : usuario.getEducacion()) {
                document.createParagraph().createRun().setText(" - " + edu.toString());
            }
        }

        if (usuario.getCapacitaciones() != null && !usuario.getCapacitaciones().isEmpty()) {
            document.createParagraph().createRun().setText("Capacitaciones:");
            for (var cap : usuario.getCapacitaciones()) {
                document.createParagraph().createRun().setText(" - " + cap.toString());
            }
        }

        if (usuario.getIdiomas() != null && !usuario.getIdiomas().isEmpty()) {
            document.createParagraph().createRun().setText("Idiomas:");
            for (var idioma : usuario.getIdiomas()) {
                document.createParagraph().createRun().setText(" - " + idioma.toString());
            }
        }

        if (usuario.getLicencias() != null && !usuario.getLicencias().isEmpty()) {
            document.createParagraph().createRun().setText("Licencias:");
            for (var lic : usuario.getLicencias()) {
                document.createParagraph().createRun().setText(" - " + lic.toString());
            }
        }

        if (usuario.getVehiculos() != null && !usuario.getVehiculos().isEmpty()) {
            document.createParagraph().createRun().setText("Vehículos:");
            for (var veh : usuario.getVehiculos()) {
                document.createParagraph().createRun().setText(" - " + veh.toString());
            }
        }

        if (usuario.getCompetencias() != null && !usuario.getCompetencias().isEmpty()) {
            document.createParagraph().createRun().setText("Competencias:");
            for (var comp : usuario.getCompetencias()) {
                document.createParagraph().createRun().setText(" - " + comp.toString());
            }
        }

        if (usuario.getExpectativa() != null) {
            document.createParagraph().createRun()
                    .setText("Expectativa laboral: " + usuario.getExpectativa().toString());
        }

        try (FileOutputStream out = new FileOutputStream(path)) {
            document.write(out);
        }

        document.close();
    }
}
