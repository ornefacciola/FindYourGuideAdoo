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
    private final GuiaService guiaService;

    public ServicioService(ServicioRepository servicioRepository, GuiaService guiaService) {
        this.servicioRepository = servicioRepository;
        this.guiaService = guiaService;
    }


    public Servicio getServiceById(Long idServicio) {
        return servicioRepository.findById(idServicio).orElse(null);
    }


    public Iterable<Servicio> getAllServices () {
        return servicioRepository.findAll();
    }




}
