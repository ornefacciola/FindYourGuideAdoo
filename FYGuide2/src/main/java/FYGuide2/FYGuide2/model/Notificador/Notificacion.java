package FYGuide2.FYGuide2.model.Notificador;

import FYGuide2.FYGuide2.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class Notificacion {
    private String mensaje;
    private Date fecha;
    private Long userDestino;

}
