package taller4;

import java.util.ArrayList;

public class Dueño {

    private String nombre;
    private int edad;
    private String residencia;
    private String cedula;
    private ArrayList<Perro> mascotas;

    public Dueño(String nombre, int edad, String residencia, String cedula) {
        this.nombre = nombre;
        this.edad = edad;
        this.residencia = residencia;
        this.cedula = cedula;
        this.mascotas = new ArrayList<>();
    }
    
    public void adoptarMascota(Perro perro) {
        try {
            if (perro == null) {
                throw new IllegalArgumentException("El perro no puede ser nulo.");
            }
            if (!mascotas.contains(perro)) {
                mascotas.add(perro);
            } else {
                System.out.println("Este perro ya está adoptado.");
            }
        } catch (Exception e) {
            System.out.println("Error al adoptar mascota: " + e.getMessage());
        }
    }
    
    public Perro buscarMascota(String nombre) {
        try {
            for (Perro perro : mascotas) {
                if (perro.getNombre().equals(nombre)) {
                    return perro;
                }
            }
            return null; 
        } catch (Exception e) {
            System.out.println("Error al buscar mascota: " + e.getMessage());
            return null;
        }
    }
    
    public void cambiarNombreMascota(String nombreAnterior, String nombreNuevo) {
        try {
            Perro perro = buscarMascota(nombreAnterior);
            if (perro != null) {
                perro.setNombre(nombreNuevo);
            } else {
                throw new IllegalArgumentException("Perro no encontrado para cambiar nombre.");
            }
        } catch (Exception e) {
            System.out.println("Error al cambiar nombre de la mascota: " + e.getMessage());
        }
    }

    public void mostrarMascotas() {
        try {
            for (Perro perro : mascotas) {
                System.out.println("Nombre: " + perro.getNombre() + ", Raza: " + perro.getRaza() + ", Peso: " + perro.getPeso());
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar mascotas: " + e.getMessage());
        }
    }

    // Validación de la cédula
    public void validarCedula() throws IllegalArgumentException {
        if (cedula.length() != 10) {
            throw new IllegalArgumentException("Cédula no válida");
        }
    }

    // Getters y setters
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad = edad;}
    public String getResidencia() {return residencia;}
    public void setResidencia(String residencia) {this.residencia = residencia;}
    public String getCedula() {return cedula;}
    public void setCedula(String cedula) {this.cedula = cedula;}
}
