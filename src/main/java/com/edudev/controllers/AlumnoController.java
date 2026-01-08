package com.edudev.controllers;

import com.edudev.domain.Alumno;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
// Unificamos todos nuestros endpoints
@RequestMapping("/alumnos")
public class AlumnoController {

    //Creamos una lista de alumnos
    private List<Alumno> alumnos = new ArrayList<>(Arrays.asList(
            new Alumno(1001, "Julio Bernales","julio@gmail.com", 22 , "Desarrollo de Aplicaciones Web"),
            new Alumno(1002, "Edu Quispe","edu@gmail.com", 22 , "Desarrollo de aplicaciones Movil"),
            new Alumno(1003, "Emilio Castañeda","emilio@gmail.com", 23 , "Desarrollo de Servicios Web"),
            new Alumno(1004, "Gerard Guardales","gerard@gmail.com", 21 , "Desarrollo de Aplicaciones Web")
    ));

    // Metodo para Listar Alumnos
    @GetMapping
    public List<Alumno> mostrarAlumnos(){
        return alumnos;
    }

    // Metodo para consultar un alumno por su email
     @RequestMapping(value = "/{email}", method = RequestMethod.GET) //otra manera de trabajar ambas son correcta
    //@GetMapping({"/{email}"})
    public Alumno consultarAlumnoXEmail(@PathVariable String email){
        for( Alumno a : alumnos){
            if (a.getEmail().equalsIgnoreCase(email)){
                return a;
            }
        }
        return null;
    }

    // Metodo para crear un nuevo alumno
    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public Alumno crearAlumno(@RequestBody Alumno alumno){
        alumnos.add(alumno);
        return alumno;
    }


    // Metodo para Actualizar/Modificar un alumno
    @RequestMapping(method = RequestMethod.PUT)
    //@PutMapping
    public Alumno modificarAlumnoTotal(@RequestBody Alumno alumno){
        for (Alumno a : alumnos){
            if (a.getId() == alumno.getId()){
                a.setName(alumno.getName());
                a.setEmail(alumno.getEmail());
                a.setAge(alumno.getAge());
                a.setCourse(alumno.getCourse());

                return a;
            }
        }
        return null;
    }

    // Metodo para modificar de manera parcial un recurso/alumno
    @RequestMapping(method = RequestMethod.PATCH)
    public Alumno modificarAlumnoParcial(@RequestBody Alumno alumno){
        for(Alumno a : alumnos){
            if (a.getId() == alumno.getId()){

                if (alumno.getName() != null){
                    a.setName(alumno.getName());
                }

                if (alumno.getEmail() != null){
                    a.setEmail(alumno.getEmail());
                }

                if (alumno.getAge() != 0){
                    a.setAge(alumno.getAge());
                }

                if (alumno.getCourse() != null){
                    a.setCourse(alumno.getCourse());
                }
                return a;
            }
        }
        return null;
    }

    // Metodo para eliminar un alumno por su ID
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Alumno eliminarAlumnoPorID(@PathVariable int id){
        for (Alumno a : alumnos){
            if (a.getId() == id){
                alumnos.remove(a);
                return a;
            }
        }
        return null;
    }


}
