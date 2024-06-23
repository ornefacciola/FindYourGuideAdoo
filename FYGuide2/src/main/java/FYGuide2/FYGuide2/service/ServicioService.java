package FYGuide2.FYGuide2.service;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Reserva.Reserva;
import FYGuide2.FYGuide2.model.Servicio;
import FYGuide2.FYGuide2.repository.ServicioRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }


    public Servicio getServiceById(Long idServicio) {
        return servicioRepository.findById(idServicio).orElse(null);
    }


    public boolean isServiceAvaible(Long idServicio, Date fechaInicio, String destino) {
        Servicio servicio = servicioRepository.findById(idServicio).orElse(null);
        Guia guia = servicio.getGuia();
        List<Reserva> reservas = guia.getReservas();

        List<String> locations = guia.getLocations();

        if (!locations.contains(destino)) {
            return false;
        }

        Calendar now = Calendar.getInstance();
        Calendar nextWeek = Calendar.getInstance();
        nextWeek.add(Calendar.WEEK_OF_YEAR, 1);

        Calendar fechaInicioCal = Calendar.getInstance();
        fechaInicioCal.setTime(fechaInicio);
        fechaInicioCal.set(Calendar.HOUR_OF_DAY, 0);
        fechaInicioCal.set(Calendar.MINUTE, 0);
        fechaInicioCal.set(Calendar.SECOND, 0);
        fechaInicioCal.set(Calendar.MILLISECOND, 0);
        fechaInicio = fechaInicioCal.getTime();

        if (fechaInicio.before(now.getTime()) || fechaInicio.before(nextWeek.getTime())) {
            return false;
        }

        for (Reserva reserva : reservas) {

            Calendar reservaFechaInicioCal = Calendar.getInstance();
            reservaFechaInicioCal.setTime(reserva.getFechaInicio());
            reservaFechaInicioCal.set(Calendar.HOUR_OF_DAY, 0);
            reservaFechaInicioCal.set(Calendar.MINUTE, 0);
            reservaFechaInicioCal.set(Calendar.SECOND, 0);
            reservaFechaInicioCal.set(Calendar.MILLISECOND, 0);
            Date reservaFechaInicio = reservaFechaInicioCal.getTime();

            if (reservaFechaInicio.equals(fechaInicio)) {
                return false;
            }
        }
        return true;
    }

}
