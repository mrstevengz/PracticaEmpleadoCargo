package run;

import config.JPAUtil;
import entities.Cargo;
import entities.Empleado;
import jakarta.persistence.EntityManager;
import repository.dao.CargoDAO;
import repository.dao.EmpleadoDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        Scanner sc = new Scanner(System.in);
        CargoDAO daoCargo = new CargoDAO(em);
        EmpleadoDAO dao = new EmpleadoDAO(em);

        int op;
        do {
            System.out.println("1. Crear persona");
            System.out.println("2. Listar personas");
            System.out.println("3. Actualizar persona");
            System.out.println("4. Eliminar persona");
            System.out.println("5. Agregar cargos");
            System.out.println("6. Listar cargos");
            System.out.println("7. Actualizar cargos");
            System.out.println("8. Eliminar cargos");
            System.out.println("9. Salir");
            System.out.print("Opción: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    //Se crea un nuevo empleado
                    Empleado empleado = new Empleado();
                    System.out.println("Escriba el nombre de empleado: ");
                    empleado.setNombre(sc.nextLine());
                    System.out.println("Escriba el apellido de empleado: ");
                    empleado.setApellido(sc.nextLine());

                    //Se listan los cargos existentes en la DB para seleccionar la ID
                    List<Cargo> cargosList = daoCargo.listarCargo();
                    System.out.println("Cargos disponibles:");
                    for (Cargo c : cargosList) {
                        System.out.println("ID: " + c.getId_cargo() + " - Nombre: " + c.getNombreCargo());
                    }

                    System.out.println("Ingrese el ID del cargo que desea asignar al empleado: ");
                    Long id = sc.nextLong();
                    sc.nextLine();
                    Cargo cargoempleado = em.find(Cargo.class, id);

                    //Validacion para detectar si el cargo es valido o no. Si no existe, se asigna valor NULL
                    if (cargoempleado != null) {
                        empleado.setCargo(cargoempleado);
                    } else {
                        System.out.println("Cargo no encontrado");
                        empleado.setCargo(null);
                    }
                    System.out.println(dao.guardar(empleado));
                    break;

                case 2:
                    List<Empleado> empleados = dao.listar();
                    for (Empleado e : empleados) {
                        System.out.println(e);
                    }
                    break;

                case 3:

                    System.out.println("Ingrese el ID del empleado a actualizar: ");
                    Long idEmp = sc.nextLong();
                    sc.nextLine();
                    Empleado temp = em.find(Empleado.class, idEmp); //Se busca el ID del empleado que se quiere editar
                    if (temp != null) { //Si el ID no es nulo, se pide los datos del empleado (Nombre, apellido e ID del cargo)
                        System.out.println("Nuevo nombre: ");
                        temp.setNombre(sc.nextLine());
                        System.out.println("Nuevo apellido: ");
                        temp.setApellido(sc.nextLine());
                        System.out.println("Nuevo ID de cargo: ");
                        Long newCargoId = sc.nextLong();
                        sc.nextLine();
                        Cargo newCargo = em.find(Cargo.class, newCargoId);
                        if (newCargo != null) {
                            temp.setCargo(newCargo);
                        } else {
                            System.out.println("Cargo no encontrado, se mantiene el actual.");
                        }
                        System.out.println(dao.guardar(temp));
                    } else {
                        System.out.println("Empleado no encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Ingrese el ID del empleado a eliminar: ");
                    Long idEmpDel = sc.nextLong();
                    sc.nextLine();

                    //Validacion para comprobar que el ID existe en la DB
                    Empleado empleadoDel = em.find(Empleado.class, idEmpDel);
                    if(empleadoDel != null){
                        dao.eliminarEmpleado(idEmpDel);
                    } else {
                        System.out.println("Empleado no encontrado");
                    }

                    break;

                case 5:
                    Cargo cargo = new Cargo();
                    System.out.println("Escriba el nombre de cargo: ");
                    cargo.setNombreCargo(sc.nextLine());
                    System.out.println(daoCargo.guardar(cargo));
                    break;

                case 6:
                    List<Cargo> cargos = daoCargo.listarCargo();
                    for (Cargo c : cargos) {
                        System.out.println(c);
                    }
                    break;

                case 7:
                    System.out.println("Ingrese el ID del cargo a actualizar: ");
                    Long idCargo = sc.nextLong();
                    sc.nextLine();
                    Cargo nuevoCargo = em.find(Cargo.class, idCargo);
                    if (nuevoCargo != null) {
                        System.out.println("Nuevo nombre de cargo: ");
                        nuevoCargo.setNombreCargo(sc.nextLine());
                        System.out.println(daoCargo.guardar(nuevoCargo));
                    } else {
                        System.out.println("Cargo no encontrado.");
                    }
                    break;

                case 8:
                    System.out.println("Ingrese el ID del cargo a eliminar: ");
                    Long idCargoDel = sc.nextLong();
                    sc.nextLine();

                    //Validacion para comprobar que el cargo existe en la DB
                    Cargo cargoBorrar = em.find(Cargo.class, idCargoDel);
                    if (cargoBorrar != null) {
                        daoCargo.eliminarCargo(idCargoDel);
                    } else {
                        System.out.println("Cargo no encontrado");
                    }
                    break;

                case 9:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (op != 9);


    }
}
