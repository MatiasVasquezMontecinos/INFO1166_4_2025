package SimulacionModule.infrastructure.export;

public class CvExportFactory {

    public static CvExport exportarCv(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "pdf" -> new PdfExport();
            case "word" -> new WordExport();
            default -> throw new IllegalArgumentException("Formato no soportado: " + tipo);
        };
    }
}
