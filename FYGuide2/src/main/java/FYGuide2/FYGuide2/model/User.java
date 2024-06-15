package FYGuide2.FYGuide2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity //tabla user
//mapea objetos a una tabla
//cada atributo una columna

public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(name="name", nullable = false, length = 512)
        private String name;

        public User (String name){
                this.name = name;
        }
}
