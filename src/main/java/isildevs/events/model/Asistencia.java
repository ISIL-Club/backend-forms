package isildevs.events.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

 @Entity
 @Table(name = "Asistencia")

public class Asistencia {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(name = "nombre", nullable = false, length = 255)
     private String nombre;

     @Column(name = "correo", nullable = false, length = 255)
     private String correo;

     @Column(name = "telefono", length = 50)
     private String telefono;


     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "evento_id", nullable = false)
     private Evento evento;
     public Asistencia() {}

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public Evento getEvento() {
         return evento;
     }

     public void setEvento(Evento evento) {
         this.evento = evento;
     }

     public String getTelefono() {
         return telefono;
     }

     public void setTelefono(String telefono) {
         this.telefono = telefono;
     }

     public String getCorreo() {
         return correo;
     }

     public void setCorreo(String correo) {
         this.correo = correo;
     }

     public String getNombre() {
         return nombre;
     }

     public void setNombre(String nombre) {
         this.nombre = nombre;
     }
 }
