package domain.model;

import java.util.ArrayList;
import java.util.List;

public class DatosCurriculares {
    private List<String> lugaresEstudio = new ArrayList<>();
    private List<String> lugaresTrabajo = new ArrayList<>();

    public List<String> getLugaresEstudio() { return lugaresEstudio; }
    public void setLugaresEstudio(List<String> lugaresEstudio) {
        this.lugaresEstudio = (lugaresEstudio != null) ? lugaresEstudio : new ArrayList<>();
    }

    public List<String> getLugaresTrabajo() { return lugaresTrabajo; }
    public void setLugaresTrabajo(List<String> lugaresTrabajo) {
        this.lugaresTrabajo = (lugaresTrabajo != null) ? lugaresTrabajo : new ArrayList<>();
    }
}
