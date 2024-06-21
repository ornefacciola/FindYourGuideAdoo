package FYGuide2.FYGuide2;

import FYGuide2.FYGuide2.model.Guia;
import FYGuide2.FYGuide2.model.Turista;
import FYGuide2.FYGuide2.model.User;
import FYGuide2.FYGuide2.repository.GuiaRepository;
import FYGuide2.FYGuide2.repository.TuristaRepository;
import FYGuide2.FYGuide2.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FyGuide2Application {

	public static void main(String[] args) {

		SpringApplication.run(FyGuide2Application.class, args);


	}
}
