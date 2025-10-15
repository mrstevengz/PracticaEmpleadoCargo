package repository.dao;

import entities.Cargo;
import jakarta.persistence.EntityManager;
import repository.ICargo;

import java.util.List;

public class CargoDAO implements ICargo {
    private final EntityManager em;

    public CargoDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public Cargo guardar(Cargo cargo) {
        if (cargo.getId_cargo() == null){
            em.getTransaction().begin();
            em.persist(cargo);
            em.getTransaction().commit();
            return cargo;
        } return em.merge(cargo);
    }

    @Override
    public List<Cargo> listarCargo() {
        return em.createQuery("from Cargo", Cargo.class).getResultList();
    }

    @Override
    public void eliminarCargo(Long id) {
        Cargo cargo = em.find(Cargo.class, id);
        em.getTransaction().begin();
        em.remove(cargo);
        em.getTransaction().commit();
        System.out.println("El cargo con ID: " + cargo.getId_cargo() + "se ha eliminado correctamente");
    }

    @Override
    public Cargo editarCargo(Long id, Cargo cargo) {
        return null;
    }
}

