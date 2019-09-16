import java.util.*;
import java.util.stream.IntStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import com.opencsv.CSVReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.sun.tools.javac.code.TypeMetadata.Entry;

public class ValleyBikeSim {
	
	/*
	 * Fields related to stations.
	 */
	public static List<Station> stationsList;
	public static TreeMap<Integer, Station> stationsMap;
	public static List<String[]> allStationEntries;
	public static List<Station> stationWithAvailableDocks;
	public static List<Integer> stationId;
	
	/*
	 * Fields related to rides.
	 */
	public static List<Ride> ridesList;
	//public static List<Rides> ridesList2;
	public static TreeMap<Integer, Ride> ridesMap;
	//public static TreeMap<Integer, Rides> ridesMap2;
	public static List<String[]> allRidesEntries;
	//public static List<String[]> allridesEntries2;
	
	
	/**
	 * Read in all the data files and store them in appropriate data structures
	 */
	public static void readData() {
		try {
			String stationData = "data-files/station-data.csv";

			
			CSVReader stationDataReader = new CSVReader(new FileReader(stationData));
			
			
			stationsList = new ArrayList<>();
			stationsMap = new TreeMap<>();
			
			// how to read the CSV data row wise:
			allStationEntries = stationDataReader.readAll();
			System.out.println("");
			int counter = 0;
			for(String[] array : allStationEntries) {
				if(counter == 0) {
					
				} else {
					stationsList.add(new Station((Integer.parseInt(array[0])), array[1], Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4]),
						Integer.parseInt(array[5]), Integer.parseInt(array[6]), toBool(array[7]), array[8]));
					
				}
				counter++;
				
			}
		
			for(Station station : stationsList) {
				stationsMap.put(station.getID(), station);
			}	
		
		} 
		
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void processRideData(String ridesFileName) {
		String rideData = "/Users/nukhbahmajid/Desktop/Smith_College/Junior_Year/CSC223/CSC223-ValleyBikeSim/data-files/" + ridesFileName;
		
		try {
			CSVReader rideDataReader = new CSVReader(new FileReader(rideData));
			
			ridesList = new ArrayList<>();
			ridesMap = new TreeMap<>();
			
			allRidesEntries = rideDataReader.readAll();
			System.out.println("");
			int counter = 0;
			for(String[] array : allRidesEntries) {
				if(counter == 0) {
					
				} else {
					ridesList.add(new Ride(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]), toDate(array[3]), toDate(array[4])));
				}
	
			}
			
			
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Convert a string to Date java object.
	 * @throws ParseException 
	 */
	private static Date toDate(String s) throws ParseException {
		Date dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
		return dateTime;
	}
	
	
	/**
	 * Helper function to pass the String values of "0" and "1" as arguments
	 * and return boolean values of true and false respectively. 
	 */
	private static boolean toBool(String s) {
		if(s.equals("0")) {
			return false;
		} else {
			return true;
		}
	}
	
    public static void menu() {
		
		System.out.println("\n" + "Please choose from one of the following menu options:\n" + 
				           "0. Quit Program.\n" + 
				           "1. View station list.\n" + 
				           "2. Add station.\n" + 
				           "3. Save station list.\n" + 
				           "4. Record ride.\n" + 
				           "5. Resolve ride data.\n" + 
				           "6. Equalize stations.");
		
	}
    
    public static void quit() {
		System.out.println("\n" + "You've quit the program.");
		System.exit(0);
	}
    
    public static void viewStationList() {
    	
    	System.out.println("ID" + "\t" + "Bikes" + "\t" + "Pedelec" + "\t" + "AvDocs"
    			+ "\t" + "MainReq" + "\t" + "Cap" + "\t" + "Kiosk" + "\t" + "Name - Address");
    			
        for(Map.Entry<Integer, Station> entry: stationsMap.entrySet()) {
        	entry.getValue().printStation();
        	}
    }
    
    public static void addStation() {
    	int ID = 0;
    	int bikes = 0;
    	int pedelacs = 0;
    	int aDocks = 0;
    	int mReq = 0;
    	boolean hasKiosk = false;
    	boolean error = true;
    	stationId = stationID();
		Scanner input = new Scanner(System.in);
		
		while (error) {
			System.out.println("Please enter the ID number of the new station: ");
			try {
				ID = Integer.parseInt(input.nextLine());
				if (ID < 0) {
					System.out.println("Please enter a non-negative number.");
					continue;
				}	
				else if (stationId.contains(ID)) {
					System.out.println("The station id has already existed. Please enter another one.");
					continue;
				}
				
				error = false;
			}
			catch (NumberFormatException nfe) {
				System.out.println("Please enter a valid integer.");
			}
		}
		
		System.out.println("Please enter the name of the new station: ");
		String name = input.nextLine();
		
		error = true;
		while(error) {
			System.out.println("Please enter the number of bikes at the new station: ");
		    try {
		    	bikes = Integer.parseInt(input.nextLine());
		    	if (bikes < 0) {
		    		System.out.println("Please enter a non-negative number.");
		    		continue;
		    	}
		    	error = false;
		    }
		    catch (NumberFormatException nfe) {
		    	System.out.println("Please enter a valid integer.");
		    }
			
		}
		
		error = true;
		while(error) {
			System.out.println("Please enter the number of pedelecs at the new station: ");
		    try {
		    	pedelacs = Integer.parseInt(input.nextLine());
		    	if (pedelacs < 0) {
		    		System.out.println("Please enter a non-negative number.");
		    		continue;
		    	}
		    	error = false;
		    }
		    catch (NumberFormatException nfe) {
		    	System.out.println("Please enter a valid integer.");
		    }
			
		}
		
		error = true;
		while(error) {
			System.out.println("Please enter the number of available docks at the new station: ");
		    try {
		    	aDocks = Integer.parseInt(input.nextLine());
		    	if (aDocks < 0) {
		    		System.out.println("Please enter a non-negative number.");
		    		continue;
		    	}
		    	error = false;
		    }
		    catch (NumberFormatException nfe) {
		    	System.out.println("Please enter a valid integer.");
		    }
			
		}

		int capacity = aDocks + bikes + pedelacs;
		
		System.out.println("Please enter the address of the new station: ");
		String address = input.nextLine();
		
		System.out.println("Please indicate whether the new station has a kiosk or not (true or false): ");
		hasKiosk = Boolean.parseBoolean(input.nextLine());
			
		
		Station newStation = new Station(ID, name, bikes, pedelacs, aDocks, mReq, capacity, hasKiosk, address);
		stationsList.add(newStation);	
		stationsMap.put(newStation.getID(), newStation);
		System.out.println("Station successfully added. Choose 'View station list' in menu to view the station.");
	}
	
	public static void saveStationList() {
		
	}
	
	public static List<Integer> stationID() {
		stationId = new ArrayList<>();
		
		for (Station station : stationsList) {
			stationId.add(station.getID());
		}
		
		return stationId;
	}
	
	public static List<Station> availableStation() {
		stationWithAvailableDocks = new ArrayList<>();
		
		for (Station station : stationsList) {
			if (station.getAvailableDocks() > 0) {
				stationWithAvailableDocks.add(station);
			}
		}
		
		return stationWithAvailableDocks;
	}
	
	/**
	 * Record a ride that a user has currently taken
	 * Required information such as the start station ID, transportation, destination ID
	 * Throw out exceptions when the user didn't enter appropriate input
	 */
	public static void recordRide() {
		Scanner input = new Scanner(System.in);
		
		//a boolean to determine whether the user input is appropriate or not
		boolean error = true;
		int start = 0;
		Station startStation = new Station(0, null, 0, 0, 0, 0, 0, false, null);
		String transportation = null;
		int end = 0;
		Station endStation = new Station(0, null, 0, 0, 0, 0, 0, false, null);
		stationId = stationID();
		
		//ask for the start station id
		//throw out exception if the user didn't enter a non-negative integer or existing station ID
		while (error) {
			System.out.println("Which station did you start from (station ID)? ");
			try {
				start = Integer.parseInt(input.nextLine());
				error = false;
			}
			catch (NumberFormatException nfe) {
				System.out.println("Please enter a valid integer ID.");
			}
		
		}
		
		while (! stationId.contains(start)) {
			try {
				System.out.println("Please enter an existing station ID: ");
				start = Integer.parseInt(input.nextLine());
			}
			catch (NumberFormatException nfe) {
				System.out.println(nfe.getMessage());
			}
		}
		
		startStation = stationsMap.get(start);
		
		//ask for the transportation
		//throw out exception if the user neither enter 'bike' nor 'pedelec' or 
		//the user entered a wrong transportation tool
		error = true;
		while (error) {
			System.out.println("Which transportation did you use (bike or pedelec)? ");
			transportation = input.nextLine();
			
			if (transportation.toLowerCase().equals("bike")) {
				if (startStation.getBikes() <= 0) {
					System.out.println("There's no bike at the start station. Please start over and enter the correct start station ID and the transportation.");
					while (error) {
						System.out.println("Which station did you start from (station ID)? ");
						try {
							start = Integer.parseInt(input.nextLine());
							error = false;
						}
						catch (NumberFormatException nfe) {
							System.out.println("Please enter a valid integer ID.");
						}
					
					}
					
					while (! stationId.contains(start)) {
						try {
							System.out.println("Please enter an existing station ID: ");
							start = Integer.parseInt(input.nextLine());
						}
						catch (NumberFormatException nfe) {
							System.out.println(nfe.getMessage());
						}
					}
					
					startStation = stationsMap.get(start);
					error = true;
					continue;
				}
				startStation.setBikes(startStation.getBikes()-1);
				startStation.setAvailableDocks(startStation.getAvailableDocks()+1);
				error = false;
			}
			
			else if (transportation.toLowerCase().equals("pedelec")) {
				if (startStation.getPedelacs() <= 0) {
					System.out.println("There's no pedelec at the start station. Please start over and enter the correct start station ID and the transportation you used.");
					while (error) {
						System.out.println("Which station did you start from (station ID)? ");
						try {
							start = Integer.parseInt(input.nextLine());
							error = false;
						}
						catch (NumberFormatException nfe) {
							System.out.println("Please enter a valid integer ID.");
						}
					
					}
					
					while (! stationId.contains(start)) {
						try {
							System.out.println("Please enter an existing station ID: ");
							start = Integer.parseInt(input.nextLine());
						}
						catch (NumberFormatException nfe) {
							System.out.println(nfe.getMessage());
						}
					}
					
					startStation = stationsMap.get(start);
					error = true;
					continue;
				}
				startStation.setPedelacs(startStation.getPedelacs() - 1);
				startStation.setAvailableDocks(startStation.getAvailableDocks()+1);
				error = false;
			}
			
			else {
				System.out.println("Please enter either 'bike' or 'pedelec.'");
			}
		}
		
		//ask for the destination
		//throw out exception if the user didn't enter a non-negative integer or existing station ID or
		//the destination has no available docks
		error = true;
		while (error) {
			System.out.println("Where's your destination (station ID)? ");
			try {
				end = Integer.parseInt(input.nextLine());
				error = false;
			}
			catch (NumberFormatException nfe) {
				System.out.println("Please enter a valid integer ID.");
			}
			
		}
		
		while (! stationId.contains(end)) {
			try {
				System.out.println("Please enter an existing station ID: ");
				end = Integer.parseInt(input.nextLine());
			}
			catch (NumberFormatException nfe) {
				System.out.println(nfe.getMessage());
			}
		}
		
		endStation = stationsMap.get(end);
		
		//when the destination has no available docks, print out stations that are available 
		//and ask the user to change destination
		while (endStation.getAvailableDocks() <= 0) {
			System.out.println("Sorry, there's no available dock for you to return.");
			System.out.println("\n" + "Here's a list of stations that have available docks: ");
			System.out.println("ID" + "\t" + "Bikes" + "\t" + "Pedelec" + "\t" + "AvDocs"
	    			+ "\t" + "MainReq" + "\t" + "Cap" + "\t" + "Kiosk" + "\t" + "Name - Address");
			
			stationWithAvailableDocks = availableStation();
			for (Station station : stationWithAvailableDocks) {
				station.printStation();
			}
			
			error = true;
			while (error) {
				System.out.println("Please choose an available station to return (station ID): ");
				try {
					end = Integer.parseInt(input.nextLine());
					error = false;
				}
				catch (NumberFormatException nfe) {
					System.out.println("Please enter a valid integer ID.");
				}
			}
			
			while (! stationId.contains(end)) {
				try {
					System.out.println("Please enter an existing station ID: ");
					end = Integer.parseInt(input.nextLine());
				}
				catch (NumberFormatException nfe) {
					System.out.println(nfe.getMessage());
				}
			}
			
			endStation = stationsMap.get(end);
		}
		
		System.out.println("You've recorded your ride successfully!");
		if (transportation.toLowerCase().equals("bike")) {
			endStation.setBikes(endStation.getBikes()+1);
		} else {
			endStation.setPedelacs(endStation.getPedelacs()+1);
		}
		endStation.setAvailableDocks(endStation.getAvailableDocks()-1);
		
	}
	
	/**
	 * We resolve the ride data but we are not able to check whether the transaction
	 * is valid or not since we don't know the exact station info when a ride happened
	 * at a specific time
	 */
	public static void resolveRideData() {
		
	}
	
	/**
	 * Equalize the station based on the percentages of bike/pedelecs per doc capacity
	 */
	public static void equalizeStation() {	
        int totalBikes = 0;
		for (Station station : stationsList) {
			totalBikes += station.getBikes();
		}
		
        int totalPedelecs = 0;
		for (Station station : stationsList) {
			totalPedelecs += station.getPedelacs();
		}
		
        int totalCapacity = 0;		
		for (Station station : stationsList) {
			totalCapacity += station.getCapacity();
		}
		
		for (Station station : stationsList) {
			int bikes = station.getBikes();
			int capacity = station.getCapacity();
			bikes = Math.round(capacity * totalBikes / totalCapacity);
			station.setBikes(bikes);
		}
		
		for (Station station : stationsList) {
			int pedelecs = station.getPedelacs();
			int capacity = station.getCapacity();
			pedelecs = Math.round(capacity * totalPedelecs / totalCapacity);
			station.setPedelacs(pedelecs);
		}
		
		//what if after equalizing, the current total number of bikes doesn't match the precious total number
		//we assign the bikes left by the equalization randomly to some other stations
		int nowTotalBikes = 0;
		for (Station station : stationsList) {
			nowTotalBikes += station.getBikes();
		}
		
		if (nowTotalBikes != totalBikes) {
			int difference = totalBikes - nowTotalBikes;
			for (int i = 0; i < difference; i++) {
				int bikes = stationsList.get(i).getBikes() + 1;
				stationsList.get(i).setBikes(bikes);
			}
		}
		
		//what if after equalizing, the current total number of pedelecs doesn't match the precious total number
		//we assign the pedelecs left by the equalization randomly to some other stations
		int nowTotalPedelecs = 0;
		for (Station station : stationsList) {
			nowTotalPedelecs += station.getPedelacs();
		}
		
		if (nowTotalPedelecs != totalPedelecs) {
			int difference = totalPedelecs - nowTotalPedelecs;
			for (int i = 0; i < difference; i++) {
				int pedelecs = stationsList.get(i).getPedelacs() + 1;
				stationsList.get(i).setPedelacs(pedelecs);
			}
		}
		
		for (Station station : stationsList) {
			int aDocs = station.getAvailableDocks();
			aDocs = station.getCapacity() - station.getBikes() - station.getPedelacs();
			station.setAvailableDocks(aDocs);
		}
		
		System.out.println("\n" + "Equalization completed! Choose 'View station list' in menu to view the station.");
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to the ValleyBike Simulator.\n");
		
        menu();
		
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter your selection (0-6):");
		int number = input.nextInt();
		
		readData();
		
		while (number != 0) {
			if (number == 1) {
				viewStationList();
			}
			
			else if (number == 2) {
				addStation();
			}
			
			else if (number == 3) {
				saveStationList();
			}
			
			else if (number == 4) {
				recordRide();
			}
			
			else if (number == 5) {
				resolveRideData();
			}
			
			else if (number == 6) {
				equalizeStation();
			}
			
			menu();
			
	    	System.out.print("Please enter your selection (0-6):");
			number = input.nextInt();
		}
		
		if (number == 0) {
			quit();
		}
			
	}//endMain
	

}
