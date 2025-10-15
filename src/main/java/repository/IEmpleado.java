package repository;

import entities.Empleado;

import java.util.List;

public interface IEmpleado {
    //Metodos CRUD para empleado
    public Empleado guardar(Empleado empleado);
    public List<Empleado> listar();
    public void eliminarEmpleado(Long id);
    public Empleado editarEmpleado(Long id, Empleado empleado);
}
