package com.malaga;

import java.util.Scanner;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.malaga.constants.ConstantsAdmin;
import com.malaga.controller.CommandControllerImpl;
import com.malaga.utils.ValidatorUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@EnableJpaRepositories(basePackages = { "com.malaga" })
@ComponentScan(basePackages = { "com.malaga" })
public class AdministratorApplication {

	private static CommandControllerImpl commands;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AdministratorApplication.class, args);

		// Get from the Spring Context, the controller
		commands = context.getBean(CommandControllerImpl.class);

		// run the main method
		runConsole();
	}

	public static void runConsole() {

		String command = ConstantsAdmin.INIT;

		try (Scanner scanner = new Scanner(System.in)) {

			log.info(ConstantsAdmin.WELCOME);

			while (!command.equals(ConstantsAdmin.EXIT)) {

				command = scanner.nextLine();

				String[] args = ValidatorUtils.trimCommands(ValidatorUtils.parseArgs(command));

				if (ValidatorUtils.isValid(args)) {

					if (commands.validateCommand(args)) {
						log.info(commands.executeCommand(args));
					}
				}

			}
		}

		log.info(ConstantsAdmin.BYE);

		System.exit(0);

	}

	@Bean
	public DozerBeanMapper mapper() {
		return new DozerBeanMapper();
	}

}
