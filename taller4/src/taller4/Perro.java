package taller4;

import java.util.Calendar;

/* _______________
< David Jácome >
 ---------------
          \
            \             .:---------:.
              \        .:               :.
                    .· __..~~       ~~..__ ·.
               ___________________________________
                |  :   ---     | |     ---   :  |
           __   | :   / @ \    | |    / @ \   : |
          /  \   \:   \___/   /   \   \___/   :/
  _  _  _ |  |    \          /     \          /
 / \/ \/ \|  |    : --------         -------- :
 |  |  | _|_ |    :    o   __________    o    :
 \_/\_/\|    |     :  。 0 |   ||   |  0  。 :
 |       \_  |      :-     \___/\___/      -:
 |   _____   |       .-                   -.
  \     /   /          .-               -.
   \______ /              :-----------: */

   
public class Perro {
    
    private String raza;
    private Calendar fechaNacimiento;
    private float peso;
    private String nombre;
    private Calendar fechaAdopcion;
    private Dueño dueño;

    
    public Perro(String raza, Calendar fechaNacimiento, float peso, String nombre) {
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.nombre = nombre;
        this.fechaAdopcion = null; 
        this.dueño = null; 
    }

    public int calcularEdad() {
        Calendar today = Calendar.getInstance();
        int edad = today.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < fechaNacimiento.get(Calendar.MONTH) || (today.get(Calendar.MONTH) == fechaNacimiento.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < fechaNacimiento.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }
        return edad;
    }

    // Getters y Setters
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public Dueño getDueño() {return dueño;}
    public void setDueño(Dueño dueño) {this.dueño = dueño;}
    public Calendar getFechaAdopcion() {return fechaAdopcion;}
    public void setFechaAdopcion(Calendar fechaAdopcion) {this.fechaAdopcion = fechaAdopcion;}
    public String getRaza() {return raza;}
    public void setRaza(String raza) {this.raza = raza;}
    public Calendar getFechaNacimiento() {return fechaNacimiento;}
    public void setFechaNacimiento(Calendar fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}
    public float getPeso() {return peso;}
    public void setPeso(float peso) {this.peso = peso;}
}