package application.Controller;

import application.CRUD.VehiculoCRUD;

// he creado este controller padre para instanciar el crud una sola vez y crear la conexion solo una vez la he creado abstracto y el resto de controladores la extiende
public abstract class SuperController{
    VehiculoCRUD vehiculoCRUD;

    public SuperController() {
        this.vehiculoCRUD = new VehiculoCRUD();
    }
}
