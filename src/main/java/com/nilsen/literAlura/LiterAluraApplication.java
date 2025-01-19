package com.nilsen.literAlura;

import com.nilsen.literAlura.principal.Principal;
import com.nilsen.literAlura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository libroRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository);

		System.out.println("\n***** Holaa!!! :D Bienvenidos a LiterAlura *****\n");

		while (true) {
			principal.menu();
			int opcion = principal.ingresarOpcionNumerica();
			if(opcion == 6){
				System.out.println("Vuelva pronto :)");
				break;
			}
			principal.seleccionOpcion(opcion);
		}
	}
}


