package application.Controller;

import application.CRUD.VehiculoCRUD;
import application.Utils.CambioEscenas;

public abstract class SuperController{
    VehiculoCRUD vehiculoCRUD;

    public SuperController() {
        this.vehiculoCRUD = new VehiculoCRUD();
    }
}
