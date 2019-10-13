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

				String[] args = trimCommands(parseArgs(command));

				if (isValid(args)) {

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

	private static boolean isValid(String[] args) {
		boolean valid = true;
		if (args != null && args.length == 0) {
			valid = false;
		}
		if (args != null && args.length == 1) {
			if (args[0].equals(System.lineSeparator()) || args[0].equals("")) {

				valid = false;
			}
		}
		return valid;

	}

	/**
	 * parse the Arguments
	 * 
	 * @param arg
	 * @return
	 */
	private static String[] parseArgs(String arg) {

		String[] args = null;
		String[] newArgs = null;
		String[] argsJson = arg.split("-json");

		// No contains the parameter -json
		if (argsJson != null && argsJson.length == 1) {
			newArgs = arg.split(" ");
		} else {
			if (argsJson != null) {
				args = argsJson[0].split(" ");

				newArgs = new String[args.length + 1];

				for (int i = 0; i < args.length; i++) {
					newArgs[i] = args[i].trim();
				}
				newArgs[args.length] = argsJson[1];
			}
		}

		return newArgs;

	}

	/**
	 * trim the spaces
	 * 
	 * @param args
	 * @return
	 */
	private static String[] trimCommands(String[] args) {

		String[] newArgs = null;

		if (args != null) {

			int gaps = 0;
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("")) {
					gaps++;
				}
			}
			newArgs = new String[args.length - gaps];

			int j = 0;
			for (int i = 0; i < args.length; i++) {
				if (!args[i].equals("")) {
					newArgs[j] = args[i].trim();
					++j;
				}
			}
		}

		return newArgs;

	}

}
