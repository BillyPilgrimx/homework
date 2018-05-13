// Name: Vadim Kobrinsky
// ID: 314257551

package hw2_VadimKobrinsky;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AfekaInstruments {

	private static Scanner consoleScanner;

	public static void main(String[] args) {
		ArrayList<MusicalInstrument> allInstruments = new ArrayList<MusicalInstrument>();
		File file = getInstrumentsFileFromUser();

		loadInstrumentsFromFile(file, allInstruments);

		if (allInstruments.size() == 0) {
			System.out.println("There are no instruments in the store currently");
			return;
		}

		printInstruments(allInstruments);

		int different = getNumOfDifferentElements(allInstruments);

		System.out.println("\n\nDifferent Instruments: " + different);

		MusicalInstrument mostExpensive = getMostExpensiveInstrument(allInstruments);

		System.out.println("\n\nMost Expensive Instrument:\n" + mostExpensive);

		startInventoryMenu(allInstruments);
	}

	public static File getInstrumentsFileFromUser() {
		boolean stopLoop = true;
		File file;
		consoleScanner = new Scanner(System.in);

		do {
			System.out.println("Please enter instruments file name / path:");
			String filepath = consoleScanner.nextLine();
			file = new File(filepath);
			stopLoop = file.exists() && file.canRead();

			if (!stopLoop)
				System.out.println("\nFile Error! Please try again\n\n");
		} while (!stopLoop);

		return file;
	}

	public static void loadInstrumentsFromFile(File file, ArrayList<MusicalInstrument> allInstruments) {
		Scanner scanner = null;

		try {

			scanner = new Scanner(file);

			addAllInstruments(allInstruments, loadGuitars(scanner));

			addAllInstruments(allInstruments, loadBassGuitars(scanner));

			addAllInstruments(allInstruments, loadFlutes(scanner));

			addAllInstruments(allInstruments, loadSaxophones(scanner));

		} catch (InputMismatchException | IllegalArgumentException ex) {
			System.err.println("\n" + ex.getMessage());
			System.exit(1);
		} catch (FileNotFoundException ex) {
			System.err.println("\nFile Error! File was not found");
			System.exit(2);
		} finally {
			scanner.close();
		}
		System.out.println("\nInstruments loaded from file successfully!\n");

	}

	public static ArrayList<Guitar> loadGuitars(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Guitar> guitars = new ArrayList<Guitar>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			guitars.add(new Guitar(scanner));

		return guitars;
	}

	public static ArrayList<Bass> loadBassGuitars(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Bass> bassGuitars = new ArrayList<Bass>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			bassGuitars.add(new Bass(scanner));

		return bassGuitars;
	}

	public static ArrayList<Flute> loadFlutes(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Flute> flutes = new ArrayList<Flute>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			flutes.add(new Flute(scanner));

		return flutes;
	}

	public static ArrayList<Saxophone> loadSaxophones(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Saxophone> saxophones = new ArrayList<Saxophone>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			saxophones.add(new Saxophone(scanner));

		return saxophones;
	}

	public static void addAllInstruments(ArrayList<MusicalInstrument> instruments,
			ArrayList<? extends MusicalInstrument> moreInstruments) {
		for (int i = 0; i < moreInstruments.size(); i++) {
			instruments.add(moreInstruments.get(i));
		}
	}

	public static void printInstruments(ArrayList<MusicalInstrument> instruments) {
		for (int i = 0; i < instruments.size(); i++)
			System.out.println(instruments.get(i));
	}

	public static int getNumOfDifferentElements(ArrayList<MusicalInstrument> instruments) {
		int numOfDifferentInstruments;
		ArrayList<MusicalInstrument> differentInstruments = new ArrayList<MusicalInstrument>();
		System.out.println();

		for (int i = 0; i < instruments.size(); i++) {
			if (!differentInstruments.contains((instruments.get(i)))) {
				differentInstruments.add(instruments.get(i));
			}
		}

		if (differentInstruments.size() == 1)
			numOfDifferentInstruments = 0;

		else
			numOfDifferentInstruments = differentInstruments.size();

		return numOfDifferentInstruments;
	}

	public static MusicalInstrument getMostExpensiveInstrument(ArrayList<MusicalInstrument> instruments) {
		Number maxPrice = 0;
		MusicalInstrument mostExpensive = (MusicalInstrument) instruments.get(0);

		for (int i = 0; i < instruments.size(); i++) {
			MusicalInstrument temp = (MusicalInstrument) instruments.get(i);

			if (temp.getPrice() instanceof Double && temp.getPrice().doubleValue() > maxPrice.doubleValue()) {
				maxPrice = temp.getPrice();
				mostExpensive = temp;
			} else if (temp.getPrice() instanceof Integer && temp.getPrice().intValue() > maxPrice.intValue()) {
				maxPrice = temp.getPrice();
				mostExpensive = temp;
			}
		}

		return mostExpensive;
	}

	/**
	 * A method that initiates the inventory and allows the user to execute
	 * operations on the inventory.
	 * 
	 * @param instruments
	 */
	public static void startInventoryMenu(ArrayList<MusicalInstrument> instruments) {
		int searchRes = -1;
		AfekaInventory inventory = new AfekaInventory();

		boolean flag = true;
		while (flag) {

			inventory.printInventoryMenu();
			String choice = consoleScanner.nextLine();

			switch (choice) {
			case "1":
				inventory.addAllStringInstruments(instruments, inventory.getInventoryList());
				break;

			case "2":
				inventory.addAllWindInstruments(instruments, inventory.getInventoryList());
				break;

			case "3":
				inventory.SortByBrandAndPrice(inventory.getInventoryList());
				break;

			case "4":
				searchRes = secondaryInputHelperPerformsBinarySearch(inventory, consoleScanner);
				break;

			case "5":
				removeInstrumentHelperFunc(inventory, consoleScanner, searchRes);
				searchRes = -1;
				break;

			case "6":
				removeAllHelperFunc(inventory, consoleScanner);
				break;

			case "7":
				inventory.printInstrumentsInInventory(inventory.getInventoryList());
				break;

			default:
				flag = false;
				consoleScanner.close();
				System.out.println("Finished!");
				break;

			}
		}

	}

	/**
	 * A method that executes the console dialog with a user, check input cases and
	 * performs a binary search
	 * 
	 * @param inventory
	 * @param consoleScanner
	 * @return binary search result index: returns the index of a specified
	 *         instrument - if the element not found - returns -1
	 */
	public static int secondaryInputHelperPerformsBinarySearch(AfekaInventory inventory, Scanner consoleScanner) {

		if (!inventory.getInventoryList().isEmpty()) {

			int searchRes;
			System.out.print("SEARCH INSTRUMENT:\nBrand: ");
			String brandToPass = consoleScanner.nextLine();
			System.out.print("Price: ");
			Number priceToPass;
			if (consoleScanner.hasNextDouble() || consoleScanner.hasNextInt()) {
				priceToPass = (Number) Double.parseDouble(consoleScanner.nextLine());
			} else {
				consoleScanner.nextLine();
				priceToPass = null;
			}
			searchRes = inventory.binnarySearchByBrandAndPrice(inventory.getInventoryList(), brandToPass, priceToPass);
			if (searchRes >= 0) {
				System.out.println("\nResult: " + inventory.getInventoryList().get(searchRes));
			} else {
				System.out.println("\nInstrument Not Found!");
			}

			return searchRes;
		} else {
			System.out.println("\nThe List Is Empty!");
			return -1;
		}
	}
	
	/**
	 * A method that helps to perform a deletion of an instrument after a 'sorting' method have been executed.
	 * 
	 * @param inventory
	 * @param consoleScanner
	 * @param searchRes
	 */
	public static void removeInstrumentHelperFunc(AfekaInventory inventory, Scanner consoleScanner, int searchRes) {

		if (inventory.isSorted()) {

			if (searchRes >= 0) {
				boolean innerFlag = true;
				while (innerFlag) {
					innerFlag = false;

					System.out.print("\nAre You Sure (Y/N)? ");

					switch (consoleScanner.nextLine()) {
					case "Y":
					case "y":
						inventory.removeInstrument(inventory.getInventoryList(),
								inventory.getInventoryList().get(searchRes));
						break;

					case "N":
					case "n":
						break;

					default:
						innerFlag = true;
						System.out.println("\nInvalid Input! Try Again...");
						break;

					}
				}
			} else {
				System.out.println(
						"\nNo Instrument In The Deletion Queue - Search For an Instrument You Would Like To Delete!");
			}
		} else {
			System.err.println("\nThe List Must Be Sorted (And Not Empty) Before a Binary Search Can Be Performed!");
		}
	}
	
	/**
	 * A method that helps to perform a deletion of all instruments.
	 * 
	 * @param inventory
	 * @param consoleScanner
	 * @param searchRes
	 */
	public static void removeAllHelperFunc(AfekaInventory inventory, Scanner consoleScanner) {
		boolean innerFlag2 = true;
		while (innerFlag2) {
			innerFlag2 = false;

			System.out.println("\nDELETE ALL INSTRUMENTS:");
			System.out.print("Are You Sure (Y/N)? ");

			switch (consoleScanner.nextLine()) {
			case "Y":
			case "y":
				inventory.removeAll(inventory.getInventoryList());
				break;

			case "N":
			case "n":
				break;

			default:
				innerFlag2 = true;
				System.out.println("\nInvalid Input! Try Again...");
				break;

			}
		}
	}
}
