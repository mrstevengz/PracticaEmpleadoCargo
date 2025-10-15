package repository.dao;

import entities.Empleado;
import jakarta.persistence.EntityManager;
import repository.IEmpleado;

import java.util.List;

public class EmpleadoDAO implements IEmpleado {
    private final EntityManager em;

    public EmpleadoDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        if(empleado.getId_empleado() == null) {
            em.getTransaction().begin();
            em.persist(empleado);
            em.getTransaction().commit();
            return empleado;
        }
        return em.merge(empleado);
    }

    @Override
    public List<Empleado> listar() {
        return em.createQuery("from Empleado", Empleado.class).getResultList();
    }

    @Override
    public void eliminarEmpleado(Long id) {
        Empleado empleado = em.find(Empleado.class, id);
        em.getTransaction().begin();
        em.remove(empleado);
        em.getTransaction().commit();
        System.out.println("El empleado con ID:" + empleado.getId_empleado() + " se ha eliminado");
    }

    @Override
    public Empleado editarEmpleado(Long id, Empleado empleado) {
        Empleado existente = em.find(Empleado.class, id);
        if (existente != null) {
            em.getTransaction().begin();
            existente.setNombre(empleado.getNombre());
            existente.setApellido(empleado.getApellido());
            em.getTransaction().commit();
            return existente;
        } return null;
    }
}
