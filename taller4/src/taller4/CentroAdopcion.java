package taller4;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CentroAdopcion {
    private String nombre;
    private ArrayList<Perro> internos;
    private ArrayList<Dueño> clientes;

    public CentroAdopcion(String nombre) {
        this.nombre = nombre;
        this.internos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        cargarPerros();  // Cargar los perros al iniciar
    }

    // Método para cargar los perros desde el archivo
    public void cargarPerros() {
        // Verificar si el archivo existe antes de intentar cargarlo
        File archivo = new File("adopcion.bin");
    
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                internos = (ArrayList<Perro>) ois.readObject();  // Leer los perros almacenados en el archivo
                System.out.println("Perros cargados exitosamente desde adopcion.bin.");
                mostrarInternos();
            } catch (FileNotFoundException e) {
                System.out.println("No se encontró el archivo adopcion.bin. Creando uno nuevo.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar los perros desde el archivo: " + e.getMessage());
                registrarExcepcion(e);
            }
        } else {
            System.out.println("El archivo adopcion.bin no existe. Creando uno nuevo.");
        }
    }
    

    // Método para guardar los perros en el archivo
    public void guardarPerros() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("adopcion.bin"))) {
            oos.writeObject(internos);
            System.out.println("Perros guardados exitosamente en adopcion.bin.");
        } catch (IOException e) {
            System.out.println("Error al guardar los perros: " + e.getMessage());
            registrarExcepcion(e);
        }
    }

    public void mostrarInternos() {
        try {
            for (Perro perro : internos) {
                System.out.println("Perro: " + perro.getNombre() + ", Raza: " + perro.getRaza() + ", Edad: " + perro.calcularEdad() + ", Peso: " + perro.getPeso());
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar internos: " + e.getMessage());
            registrarExcepcion(e);
        }
    }

    public void registrarExcepcion(Exception e) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("excepciones.txt", true))) {

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String fechaHora = formatoFecha.format(new Date());

            // Escribir la excepción en el archivo con la fecha y hora
            writer.write("Excepción: " + e.getMessage() + " | Fecha y Hora: " + fechaHora + "\n");
            System.out.println("Hola mundo");
        } catch (IOException ex) {
            System.out.println("Error al registrar la excepción: " + ex.getMessage());
        }
    }

    public void rescatarMascota(Perro perro) {
        try {
            if (perro == null) {
                throw new IllegalArgumentException("El perro no puede ser nulo.");
            }
            internos.add(perro);
        } catch (Exception e) {
            System.out.println("Error al rescatar perro: " + e.getMessage());
            registrarExcepcion(e);
        }
    }

    public void darEnAdopcion(Perro perro, Dueño dueño) {
        try {
            if (internos.contains(perro)) {
                internos.remove(perro);
                if (dueño.buscarMascota(perro.getNombre()) == null) {
                    dueño.adoptarMascota(perro);
                    System.out.println("Perro " + perro.getNombre() + " ha sido adoptado por " + dueño.getNombre());
                } else {
                    System.out.println("El dueño ya tiene este perro.");
                }
            } else {
                System.out.println("El perro no está disponible para adopción.");
            }
        } catch (Exception e) {
            System.out.println("Error al dar en adopción: " + e.getMessage());
            registrarExcepcion(e);
        }
    }

    public Perro buscarPerro(String nombre) {
        try {
            for (Perro perro : internos) {
                if (perro.getNombre().equals(nombre)) {
                    return perro;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println("Error al buscar perro: " + e.getMessage());
            registrarExcepcion(e);
            return null;
        }
    }

    public void agregarCliente(Dueño cliente) {
        try {
            if (buscarCliente(cliente.getCedula()) == null) {
                clientes.add(cliente);
            } else {
                System.out.println("El cliente con cédula " + cliente.getCedula() + " ya está registrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
            registrarExcepcion(e);
        }
    }

    public Dueño buscarCliente(String cedula) {
        try {
            for (Dueño cliente : clientes) {
                if (cliente.getCedula().equals(cedula)) {
                    return cliente;
                }
            }
            return null; 
        } catch (Exception e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
            registrarExcepcion(e);
            return null;
        }
    }

    public void mostrarAdopciones() {
        try {
            for (Dueño cliente : clientes) {
                System.out.println("Cliente: " + cliente.getNombre() + ", Cédula: " + cliente.getCedula());
                cliente.mostrarMascotas();
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar adopciones: " + e.getMessage());
            registrarExcepcion(e);
        }
    }

    // Getters y Setters
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
}
