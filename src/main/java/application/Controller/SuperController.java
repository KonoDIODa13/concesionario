package application.Controller;

import application.CRUD.VehiculoCRUD;

public abstract class SuperController{
    VehiculoCRUD vehiculoCRUD;

    public SuperController() {
        this.vehiculoCRUD = new VehiculoCRUD();
    }
}
