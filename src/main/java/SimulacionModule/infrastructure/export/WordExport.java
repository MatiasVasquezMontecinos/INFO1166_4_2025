package SimulacionModule.infrastructure.export;

import SimulacionModule.entity.Usuario;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class WordExport implements CvExport {

    @Override
    public void exportarCv(Usuario usuario, String path) throws IOException {
        XWPFDocument document = new XWPFDocument();

        // ===== Título =====
        XWPFParagraph titulo = document.createParagraph();
        titulo.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun runTitulo = titulo.createRun();
        runTitulo.setText("Curriculum Vitae");
        runTitulo.setBold(true);
        runTitulo.setFontSize(20);

        // ===== Nombre =====
        XWPFParagraph pNombre = document.createParagraph();
        XWPFRun runNombre = pNombre.createRun();
        runNombre.setText("Nombre: " + usuario.getNombre());

        // ===== Condición laboral =====
        if (usuario.getCondicionLaboral() != null) {
            var cond = usuario.getCondicionLaboral();

            XWPFParagraph pCond = document.createParagraph();
            XWPFRun runCond = pCond.createRun();
            runCond.setText("Situación laboral: " + cond.getSituacionLaboral());

            if (cond.getUltimaActividad() != null) {
                XWPFParagraph pAct = document.createParagraph();
                XWPFRun runAct = pAct.createRun();
                runAct.setText("Última actividad: " + cond.getUltimaActividad());
            }

            if (cond.getUltimoSalarioLiquido() != null) {
                XWPFParagraph pSalario = document.createParagraph();
                XWPFRun runSalario = pSalario.createRun();
                runSalario.setText("Último salario líquido: " + cond.getUltimoSalarioLiquido());
            }
        }

        // ===== Resumen de perfil =====
        if (usuario.getResumenPerfil() != null && usuario.getResumenPerfil().getDescripcion() != null) {
            XWPFParagraph pResumen = document.createParagraph();
            XWPFRun runResumenTitulo = pResumen.createRun();
            runResumenTitulo.setBold(true);
            runResumenTitulo.setText("Resumen de perfil:");

            XWPFParagraph pResumenDesc = document.createParagraph();
            XWPFRun runResumen = pResumenDesc.createRun();
            runResumen.setText(usuario.getResumenPerfil().getDescripcion());
        }

        // ===== Experiencia laboral =====
        if (usuario.getExperiencias() != null && !usuario.getExperiencias().isEmpty()) {
            XWPFParagraph pExpTitulo = document.createParagraph();
            XWPFRun runExpTitulo = pExpTitulo.createRun();
            runExpTitulo.setBold(true);
            runExpTitulo.setText("Experiencia laboral:");

            for (var exp : usuario.getExperiencias()) {
                XWPFParagraph pExp = document.createParagraph();
                XWPFRun runExp = pExp.createRun();
                runExp.setText("Ocupación: " + exp.getOcupacion());

                XWPFParagraph pEmp = document.createParagraph();
                XWPFRun runEmp = pEmp.createRun();
                runEmp.setText("Empresa: " + exp.getEmpresa());

                XWPFParagraph pDur = document.createParagraph();
                XWPFRun runDur = pDur.createRun();
                runDur.setText("Duración: " + exp.getExperienciaMeses() + " meses");

                if (exp.getDescripcion() != null) {
                    XWPFParagraph pDesc = document.createParagraph();
                    XWPFRun runDesc = pDesc.createRun();
                    runDesc.setText("Descripción: " + exp.getDescripcion());
                }

                XWPFParagraph pSep = document.createParagraph();
                XWPFRun runSep = pSep.createRun();
                runSep.setText("----------------------------");
            }
        }

        // ===== Referencias =====
        if (usuario.getReferencias() != null && !usuario.getReferencias().isEmpty()) {
            XWPFParagraph pRefTitulo = document.createParagraph();
            XWPFRun runRefTitulo = pRefTitulo.createRun();
            runRefTitulo.setBold(true);
            runRefTitulo.setText("Referencias:");

            for (var ref : usuario.getReferencias()) {
                XWPFParagraph pRef = document.createParagraph();
                XWPFRun runRef = pRef.createRun();
                runRef.setText(ref.getNombreCompleto() + " - " + ref.getPuesto());

                XWPFParagraph pDet = document.createParagraph();
                XWPFRun runDet = pDet.createRun();
                runDet.setText("Empresa: " + ref.getEmpresa() + ", Tel: " + ref.getTelefono() + ", Email: " + ref.getEmail());

                XWPFParagraph pSep = document.createParagraph();
                XWPFRun runSep = pSep.createRun();
                runSep.setText("----------------------------");
            }
        }

        // ===== Educación =====
        if (usuario.getEducacion() != null && !usuario.getEducacion().isEmpty()) {
            XWPFParagraph pEduTitulo = document.createParagraph();
            XWPFRun runEduTitulo = pEduTitulo.createRun();
            runEduTitulo.setBold(true);
            runEduTitulo.setText("Educación:");

            for (var edu : usuario.getEducacion()) {
                XWPFParagraph pEdu = document.createParagraph();
                XWPFRun runEdu = pEdu.createRun();
                runEdu.setText(edu.getTitulo() + " - " + edu.getInstitucion());

                if (edu.getObservaciones() != null) {
                    XWPFParagraph pObs = document.createParagraph();
                    XWPFRun runObs = pObs.createRun();
                    runObs.setText("Obs: " + edu.getObservaciones());
                }

                XWPFParagraph pSep = document.createParagraph();
                XWPFRun runSep = pSep.createRun();
                runSep.setText("----------------------------");
            }
        }

        // ===== Capacitaciones =====
        if (usuario.getCapacitaciones() != null && !usuario.getCapacitaciones().isEmpty()) {
            XWPFParagraph pCapTitulo = document.createParagraph();
            XWPFRun runCapTitulo = pCapTitulo.createRun();
            runCapTitulo.setBold(true);
            runCapTitulo.setText("Capacitaciones:");

            for (var cap : usuario.getCapacitaciones()) {
                XWPFParagraph pCap = document.createParagraph();
                XWPFRun runCap = pCap.createRun();
                runCap.setText(cap.getNombreCurso() + " - " + cap.getInstitucion() + " (" + cap.getDuracionHoras() + " horas)");
            }
        }

        // ===== Idiomas =====
        if (usuario.getIdiomas() != null && !usuario.getIdiomas().isEmpty()) {
            XWPFParagraph pIdiomasTitulo = document.createParagraph();
            XWPFRun runIdiomasTitulo = pIdiomasTitulo.createRun();
            runIdiomasTitulo.setBold(true);
            runIdiomasTitulo.setText("Idiomas:");

            for (var idioma : usuario.getIdiomas()) {
                XWPFParagraph pIdioma = document.createParagraph();
                XWPFRun runIdioma = pIdioma.createRun();
                runIdioma.setText(idioma.getIdioma() + " (Lectura: " + idioma.getNivelLectura() +
                        ", Escritura: " + idioma.getNivelEscritura() +
                        ", Habla: " + idioma.getNivelHabla() + ")");
            }
        }

        // ===== Licencias =====
        if (usuario.getLicencias() != null && !usuario.getLicencias().isEmpty()) {
            XWPFParagraph pLicTitulo = document.createParagraph();
            XWPFRun runLicTitulo = pLicTitulo.createRun();
            runLicTitulo.setBold(true);
            runLicTitulo.setText("Licencias:");

            for (var lic : usuario.getLicencias()) {
                XWPFParagraph pLic = document.createParagraph();
                XWPFRun runLic = pLic.createRun();
                runLic.setText("- " + lic.getTipoLicencia());
            }
        }

        // ===== Vehículos =====
        if (usuario.getVehiculos() != null && !usuario.getVehiculos().isEmpty()) {
            XWPFParagraph pVehTitulo = document.createParagraph();
            XWPFRun runVehTitulo = pVehTitulo.createRun();
            runVehTitulo.setBold(true);
            runVehTitulo.setText("Vehículos:");

            for (var veh : usuario.getVehiculos()) {
                XWPFParagraph pVeh = document.createParagraph();
                XWPFRun runVeh = pVeh.createRun();
                runVeh.setText("- " + veh.getTipoVehiculo());
            }
        }

        // ===== Competencias =====
        if (usuario.getCompetencias() != null && !usuario.getCompetencias().isEmpty()) {
            XWPFParagraph pCompTitulo = document.createParagraph();
            XWPFRun runCompTitulo = pCompTitulo.createRun();
            runCompTitulo.setBold(true);
            runCompTitulo.setText("Competencias:");

            for (var comp : usuario.getCompetencias()) {
                XWPFParagraph pComp = document.createParagraph();
                XWPFRun runComp = pComp.createRun();
                runComp.setText("- " + comp.getDescripcion());
            }
        }

        // ===== Expectativa laboral =====
        if (usuario.getExpectativa() != null) {
            var exp = usuario.getExpectativa();
            XWPFParagraph pExpTitulo = document.createParagraph();
            XWPFRun runExpTitulo = pExpTitulo.createRun();
            runExpTitulo.setBold(true);
            runExpTitulo.setText("Expectativa laboral:");

            if (exp.getOcupacionDeseada() != null) {
                XWPFParagraph pExp1 = document.createParagraph();
                XWPFRun runExp1 = pExp1.createRun();
                runExp1.setText("Ocupación deseada: " + exp.getOcupacionDeseada());
            }
            if (exp.getNivelCargo() != null) {
                XWPFParagraph pExp2 = document.createParagraph();
                XWPFRun runExp2 = pExp2.createRun();
                runExp2.setText("Nivel de cargo: " + exp.getNivelCargo());
            }
            if (exp.getSalarioDeseado() != null) {
                XWPFParagraph pExp3 = document.createParagraph();
                XWPFRun runExp3 = pExp3.createRun();
                runExp3.setText("Salario deseado: " + exp.getSalarioDeseado());
            }
            if (exp.getJornada() != null) {
                XWPFParagraph pExp4 = document.createParagraph();
                XWPFRun runExp4 = pExp4.createRun();
                runExp4.setText("Jornada: " + exp.getJornada());
            }
            if (exp.getRegionComuna() != null) {
                XWPFParagraph pExp5 = document.createParagraph();
                XWPFRun runExp5 = pExp5.createRun();
                runExp5.setText("Ubicación: " + exp.getRegionComuna());
            }
            if (exp.getTipoContrato() != null) {
                XWPFParagraph pExp6 = document.createParagraph();
                XWPFRun runExp6 = pExp6.createRun();
                runExp6.setText("Contrato: " + exp.getTipoContrato());
            }
            if (exp.getTurnos() != null) {
                XWPFParagraph pExp7 = document.createParagraph();
                XWPFRun runExp7 = pExp7.createRun();
                runExp7.setText("Turnos: " + exp.getTurnos());
            }
        }

        // ===== Guardar documento =====
        try (FileOutputStream out = new FileOutputStream(path)) {
            document.write(out);
        }

        document.close();
    }
}
