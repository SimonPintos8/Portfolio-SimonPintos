package com.dh.catalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data // Cuando se utiliza @Data, Lombok agrega automáticamente métodos como getters, setters, equals(), hashCode() y toString().
@Document(collection = "Movies") // Esta anotación marca a la clase Movie como un documento que se almacenará en una colección.
@AllArgsConstructor //Cuando se utiliza esta anotación Lombok genera automáticamente un constructor que toma como parámetros todos los campos de la clase.
@RequiredArgsConstructor
public class Movie{
    //Este Movie es un DTO

    @Id
    private Long id;
    private String name;
    private String genre;
    private String urlStream;

}
