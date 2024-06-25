package FYGuide2.FYGuide2.model.PatronObserver;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Turista;
import FYGuide2.FYGuide2.model.User;
import FYGuide2.FYGuide2.repository.GuiaRepository;
import FYGuide2.FYGuide2.repository.TuristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ObserverTrofeo implements IObserver {


    @Override
    public String notificar(User user, int rol) {
        if(rol == 1) {
            if(user.getCantReseñas() >= 10 && user.getTrofeo() == null) {
                user.setTrofeo("Trofeo a la reseña");
                return "Felicidades, has conseguido el trofeo: Trofeo a la reseña!!!";
            }
            return null;
        }
        if (rol == 2) {
            Guia guia = (Guia) user;
            if(user.getCantReseñas() >= 10) {
                if(guia.getPuntuacionTotal() >= 4.5) {
                    user.setTrofeo("Trofeo al éxito");
                    return "Felicidades, has conseguido el trofeo: Trofeo al éxito!!!";
                } else {
                    user.setTrofeo(null);
                }
                return null;
            }
            return null;
        }
        return null;
    }
}
