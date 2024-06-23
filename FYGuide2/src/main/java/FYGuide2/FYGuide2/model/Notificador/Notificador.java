package FYGuide2.FYGuide2.model.Notificador;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class Notificador {
    public Notificacion notificar(Notificacion notificacion) {
        return notificacion;
    }
}