package repository;

import entities.Cargo;

import java.util.List;

public interface ICargo {
    //Metodos CRUD para Cargos
    public Cargo guardar(Cargo cargo);
    public List<Cargo> listarCargo();
    public void eliminarCargo(Long id);
    public Cargo editarCargo(Long id, Cargo cargo);
}
