package edu.ifes.ci.si.les.scv.config;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import edu.ifes.ci.si.les.scv.services._DBService;

/**Classe para configuração do ambiente de Teste
* Neste caso, estamos armazenando no banco de dados H2
* @author Rafael Vargas Mesquita
* @version 1.00
*/
@Configuration
@Profile("test")
public class ConfigurationDBTest {

	@Autowired
	private _DBService dbService;
	
	@Bean
	public boolean instatiateDatabase() throws ParseException, IOException {
		dbService.instantiateTestDatabase();
		return true;
	}
	
}
