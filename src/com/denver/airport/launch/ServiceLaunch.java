package com.denver.airport.launch;

import com.denver.airport.exception.AirportServiceException;
import com.denver.airport.exception.InputException;
import com.denver.airport.service.AirportService;
import com.denver.airport.service.impl.DenverAirportService;
import com.denver.airport.util.Section;
import com.denver.airport.util.StringUtils;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class ServiceLaunch {
	private Scanner scanner;
	private String processingSection;
	private AirportService airportService;
	private InputHandlerFactory handlerFactory;
	
	public ServiceLaunch() {
		this(System.in, new DenverAirportService());
	}

	public ServiceLaunch(InputStream stream, AirportService airportService) {
		scanner = new Scanner(stream);
		this.airportService = airportService;
		handlerFactory = new InputHandlerFactory();
	}

	public final String getProcessingSection() {
		return processingSection;
	}

	public final void setProcessingSection(String processingSection) {
		this.processingSection = processingSection;
	}

	private void readInput() {
		System.out.println("\nPlease start entering Section inputs :");
		while (scanner.hasNextLine()) {
			String nextLine = scanner.nextLine();
			if (StringUtils.isEmpty(nextLine)) {
				break;
			}
			if (nextLine.toLowerCase().startsWith(Section.SECTION_PREFIX.toLowerCase())) {
				setProcessingSection(StringUtils.getSubstring(Section.SECTION_PREFIX, nextLine));
				continue;
			}
			try {
				processSectionInput(nextLine);
			} catch (InputException | AirportServiceException e) {
				System.out.println("Error : " + e.getMessage());
			}
		}
	}

	private void printBaggageOptimalPath() {
		try {
			if (airportService.hasValidData()) {
				List<String> shortPaths = airportService.getAllBaggageShortestPath();
				System.out.println("Baggage optimal route\n");
				shortPaths.forEach((path) -> System.out.println(path));
			} else {
				System.out.println("No valid data found to calculate optimal route");
			}
		} catch (AirportServiceException e) {
			System.out.println(" Error : " + e.getMessage());
		}
	}

	private boolean isValidSection() {
		if (null != getProcessingSection()) {
			String section = getProcessingSection().trim();
			if (section.equalsIgnoreCase(Section.BAGS_SECTION)
					|| section.equalsIgnoreCase(Section.CONVEYOR_SYSTEM_SECTION)
					|| section.equalsIgnoreCase(Section.DEPARTURES_SECTION)) {
				return true;
			}
		}
		return false;
	}

	private void processSectionInput(String input) throws InputException, AirportServiceException {
		if (StringUtils.isEmpty(getProcessingSection()) || !isValidSection()) {
			System.out.println("Not valid section found, ignoring input");
			return;
		}
		InputHandler handler = handlerFactory.getInputHandler(getProcessingSection().trim());
		if(handler != null)
			handler.handle(input, airportService);
		else
			System.out.println("No valid input handler found");
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to Denver Airport Baggage Service\n");
		System.out.println("Please Enter each section in below format\n");
		System.out.println("# Section: Conveyor System" + "\n" + "SourceNode DestinationNode TravelTime");
		System.out.println("# Section: Departures" + "\n" + "FlightId FlightGate Destination FligthTime");
		System.out.println("# Section: Bags" + "\n" + "BagNumber EntryPoint FlightId");
		System.out.println("\nNote : Please hit Enter(Empty line) at last to start processing");
		ServiceLaunch launch = new ServiceLaunch(System.in, new DenverAirportService());
		launch.readInput();
		launch.printBaggageOptimalPath();
	}
}
