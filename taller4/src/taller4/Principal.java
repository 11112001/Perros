package taller4;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        CentroAdopcion centro = new CentroAdopcion("Centro Canino");
        
        // Menú de opciones
        int opcion;
        centro.cargarPerros();
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Rescatar un perro");
            System.out.println("2. Adoptar un perro");
            System.out.println("3. Cambiar nombre a una mascota");
            System.out.println("4. Ver adopciones");
            System.out.println("5. Guardar y salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            try {
                switch (opcion) {
                    case 1:
                        // Rescatar un perro
                        String nombrePerro = ""; 
                        do{
                            System.out.print("Ingrese el nombre del perro: ");
                            try {
                                nombrePerro = scanner.nextLine();
                                
                            } catch (Exception e) {
                                System.out.println("El perro no debe ser nulo o en blanco");
                                centro.registrarExcepcion(e);
                            }
                        }
                        while (nombrePerro == null || nombrePerro == "");                       
                        


                        String raza = "";
                        while(raza == null || raza == ""){
                            System.out.print("Ingrese la raza: ");
                            try {
                                raza = scanner.nextLine();
                                
                            } catch (Exception e) {
                                System.out.println("La raza no debe ser nulo o en blanco");
                                centro.registrarExcepcion(e);
                            }
                        }

                        float peso = -1;  // Declaración fuera del bloque try
                        
                        while (peso <= 0 ) {
                            System.out.print("Ingrese el peso: ");                       
                            try {
                                peso = scanner.nextFloat();  // Asignación del valor a la variable peso
                                if(peso <= 0)
                                {
                                    System.out.println("El peso debe ser mayor que 0");
                                }
                            } catch (Exception e) {
                                System.out.println("El peso debe ser un número. " + e.getMessage());
                                scanner.nextLine(); // Limpiar el buffer
                                peso = -123;
                                centro.registrarExcepcion(e);
                            }
                            
                        }

                        
                        
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
                        Calendar fechaNacimiento = Calendar.getInstance();
                        boolean bandera = true;
                        String fechaNacimientoString;      
                        scanner.nextLine(); //limpieza buffer
                        boolean r;

                        while (bandera) {
                            System.out.print("Ingrese el año de nacimiento (ejemplo: 12-03-2020): ");
                            try {
                                fechaNacimientoString = scanner.nextLine().trim();
                                fechaNacimiento.setTime(formatoFecha.parse(fechaNacimientoString));
                                
                                r = verificarFecha(fechaNacimientoString);

                                if(r == false){
                                    System.out.println("Error en el formato");
                                    bandera = true;
                                }else{
                                    bandera = false;
                                }
                        
                                
                            } catch (Exception e) {
                                System.out.println("Formato de fecha incorrecto. Usando la fecha actual.");
                                fechaNacimiento = Calendar.getInstance(); // Restablecer a la fecha actual si ocurre un error
                                centro.registrarExcepcion(e);
                            }
                        
                        }
                        
                        
                        Perro perroNuevo = new Perro(raza, fechaNacimiento, peso, nombrePerro);
                        centro.rescatarMascota(perroNuevo);
                        System.out.println("Perro " + perroNuevo.getNombre() + " rescatado exitosamente.");
                        break;

                    case 2:
                        String cedula = "";
                        while (cedula == null || cedula == "") {
                            System.out.println("Ingrese su cedula: ");
                            try {
                                cedula = scanner.nextLine();
                                if(cedula.length() < 11)
                                {
                                    throw new Excepciones("Cedula invalida");
                                }
                                
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                centro.registrarExcepcion(e);
                                cedula = "";    
                            }
                            
                        }

                        Dueño cliente = centro.buscarCliente(cedula);
                        if (cliente == null) {
                            String nombreCliente = "";
                            
                            while (nombreCliente.equals("")) {
                                System.out.print("Ingrese su nombre: ");
                                try {
                                    nombreCliente = scanner.nextLine().trim();
                                    if(nombreCliente.equals(""))
                                    {
                                        throw new Excepciones("Nombre no valido");
                                    }    
                                } catch (Exception e) {
                                    nombreCliente = "";
                                    System.out.println("Error en el ingreso del nombre; " + e.getMessage());
                                    centro.registrarExcepcion(e);
                                }
                            }

                            int edadCliente = 0;
                            while (edadCliente <= 0) {
                                try {
                                    System.out.print("Ingrese su edad: ");
                                    edadCliente = scanner.nextInt();
                                    if(edadCliente <= 0 && edadCliente >100)
                                    {
                                        throw new Excepciones("Edad no valida");
                                    }
                                    
                                } catch (Exception e) {
                                    System.out.println("Error en la edad: " + e.getMessage());
                                    edadCliente = 0;   
                                    centro.registrarExcepcion(e); 
                                }
                            }
                            scanner.nextLine(); 

                            String residencia = "";
                            while (residencia.equals("")) {
                                try {
                                    System.out.print("Ingrese su residencia: ");
                                    residencia = scanner.nextLine();
                                    if(residencia.equals(""))
                                    {
                                        throw new Excepciones("La residencia no debe ser nula");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Residencia incorrecta; " + e.getMessage());
                                    residencia = "";
                                    centro.registrarExcepcion(e);
                                }
                            }
                            cliente = new Dueño(nombreCliente, edadCliente, residencia, cedula);
                            //cliente.validarCedula(); // Validar cédula antes de agregar
                            centro.agregarCliente(cliente);
                        }

                        System.out.println("Perros disponibles para adopción:");
                        centro.mostrarInternos();
                        String nombreAdopcion = "";
                        while (nombreAdopcion == "") {
                            try {
                                System.out.print("Ingrese el nombre del perro que desea adoptar: ");
                                nombreAdopcion = scanner.nextLine();
                                if(nombreAdopcion == "")
                                {   
                                    throw new Excepciones("Nombre no válido");
                                }else{
                                    Perro perroAdoptado = centro.buscarPerro(nombreAdopcion);
                                    centro.darEnAdopcion(perroAdoptado, cliente);
                                }

                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                centro.registrarExcepcion(e);
                            }
                        }
                        break;
                        
                    case 3:
                        // Cambiar nombre de mascota
                        String cedulaDos = "";
                        while (cedulaDos.equals("")) {
                            try {
                                System.out.print("Ingrese su cédula: ");
                                cedulaDos = scanner.nextLine();
                                if(cedulaDos == "")
                                {
                                    throw new Excepciones("Cedula incorrecta");
                                }else{
                                    cliente = centro.buscarCliente(cedulaDos);
                                    cliente.mostrarMascotas();
                                    System.out.print("Ingrese el nombre actual del perro: ");
                                    String nombreActual = scanner.nextLine();
                                    if(cliente.buscarMascota(nombreActual) == null)
                                    {
                                        throw new Excepciones("Perro no existe :c");
                                    }
                                    System.out.print("Ingrese el nuevo nombre del perro: ");
                                    String nombreNuevo = scanner.nextLine();
                                    cliente.cambiarNombreMascota(nombreActual, nombreNuevo);
                                    System.out.println("El nombre de " + nombreActual + " ha sido cambiado a " + nombreNuevo);
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                centro.registrarExcepcion(e);
                            }
                            
                        }

                    case 4:
                        // Ver adopciones
                        System.out.println("Clientes y sus mascotas:");
                        centro.mostrarAdopciones();
                        break;

                    case 5:
                        // Guardar y salir
                        centro.guardarPerros();  // Guardar perros en archivo binario
                        System.out.println("¡Gracias por usar el sistema!");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
                centro.registrarExcepcion(e);  // Registrar la excepción en el archivo
            }
        } while (opcion != 5);

        scanner.close();
    }
    public static boolean verificarFecha(String f)
    {
        char[] vector = f.toCharArray();
        try {
            if(vNum(vector[0]) == false){return false;}
            if(vNum(vector[1]) == false){return false;}
            if(vNum(vector[3]) == false){return false;}
            if(vNum(vector[4]) == false){return false;}
            if(vNum(vector[6]) == false){return false;}
            if(vNum(vector[7]) == false){return false;}
            if(vNum(vector[8]) == false){return false;}
            if(vNum(vector[9]) == false){return false;}
            
        } catch (Exception e) {
            System.out.println("error en el formato de la fecha");
            return false;
        }
        int dia = Integer.parseInt(f.substring(0, 2));
        int mes = Integer.parseInt(f.substring(3, 5));
        int año = Integer.parseInt(f.substring(6, 10));
        Calendar a = Calendar.getInstance();
        int añoA = a.get(Calendar.YEAR);
        
        
        if(añoA - año >= 30)
        {
            return false;
        }
        
        if(mes > 0 && mes <13)
        {
            if((dia < 0 || dia >= 29) && (mes == 2)||
            ((dia < 0 || dia > 31) && (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes ==12))||
            ((dia < 0 || dia > 30) && (mes == 4 || mes == 6 || mes == 9 || mes == 11))){return false;}else{return true;}
            
        }else{
            return false;
        }
        
    }
    
    
    public static boolean vNum(char p){
        int dE = p - '0';
        if(dE < 0 || dE > 9 ){
            return false;
        }
        return true;
    }
}