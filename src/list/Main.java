package list;

import java.util.Scanner;

public class Main {

	public static void addMethod(Scanner sc, GuestsList guestList) {
		System.out.println("\t Introduceti numele, urmat de tasta ENTER \n");
		String lastName = sc.next();
		System.out.println("\t Introduceti prenumele, urmat de tasta ENTER \n");
		String firstName = sc.next();
		System.out.println("\t Introduceti e-mail, urmat de tasta ENTER \n");
		String email = sc.next();
		System.out.println("\t Introduceti numarul de telefon, urmat de tasta ENTER \n");
		String phoneNumber = sc.next();

		Guest guest = new Guest(lastName, firstName, email, phoneNumber);
		guestList.addNewPerson(guest);
	}
	
	public static int searchOptionInput(Scanner sc) {
		System.out.println("\t Alegeti numarul aferent criteriului de cautare: \n"
				+ "\t\t 1  ->  dupa nume si prenume; \n"
				+ "\t\t 2  ->  dupa adresa de e-mail; \n"
				+ "\t\t 3  ->  dupa numarul de telefon. \n");
		int checkOption = sc.nextInt();
		
		while(!validateSearchOption(checkOption)) {
			System.out.println("\t Introduceti o valoare valida!");
			checkOption = sc.nextInt();
		}
		
//System.out.println("searchOptionInput" + checkOption);
		return checkOption;
	}
	
	public static boolean validateSearchOption(int checkOption) {
		if(checkOption == 1 || checkOption == 2 || checkOption == 3) {
			return true;
		}else {
			return false;
		}
	}
		
	public static int checkMethod(Scanner sc, GuestsList guestList) {
		//	 => returns -2 if the person was not found
		//	 =>	returns a value grater than -1 if the person was found
		int checkOption = searchOptionInput(sc);
//System.out.println("checkMethod" + checkOption + "\n");
		int result = -10;
		switch (checkOption) {
		case 1:
//System.out.println("checkMethod + case 1" + checkOption + "\n");
			System.out.println("\t Introduceti numele si prenumele, urmate de ENTER. \n");
			result = guestList.checkPerson(checkOption, sc.next(), sc.next());
			break;
		case 2:
			System.out.println("\t Introduceti adresa de e-mail, urmata de ENTER. \n");
			result = guestList.checkPerson(checkOption, sc.next(), "");					
			break;
		case 3:
//System.out.println("checkMethod + case 3" + checkOption);
			System.out.println("\t Introduceti numarul de telefon, urmat de ENTER. \n");
			result = guestList.checkPerson(checkOption, sc.next(), "");					
			break;
		default:
			break;
		}
		
		return result;
	}
		
	public static boolean removeMethod(Scanner sc, GuestsList guestList) {
		int checkOption = searchOptionInput(sc);

		switch (checkOption) {
		case 1:
			System.out.println("\t Introduceti numele si prenumele, urmate de ENTER. \n");
			return guestList.removePerson(checkOption, sc.next(), sc.next());
		case 2:
			System.out.println("\t Introduceti adresa de e-mail, urmata de ENTER. \n");
			return guestList.removePerson(checkOption, sc.next(), "");
		case 3:
			System.out.println("\t Introduceti numarul de telefon, urmat de ENTER. \n");
			return guestList.removePerson(checkOption, sc.next(), "");
		default:
			return false;
		}
	}
	
	public static int updateOptionInput(Scanner sc) {
		System.out.println("\t Alegeti numarul aferent campului de actualizat: \n"
				+ "\t\t 1  ->  actualizare nume; \n"
				+ "\t\t 2  ->  actualizare prenume; \n"
				+ "\t\t 3  ->  actualizare e-mail. \n"
				+ "\t\t 4  ->  actualizare numar de telefon. \n");
		int checkOption = sc.nextInt();
		
		while(!validateUpdateOption(checkOption)) {
			System.out.println("\t Introduceti o valoare valida!");
			checkOption = sc.nextInt();
		}
		
		return checkOption;
	}
	
	public static boolean validateUpdateOption(int checkOption) {
		if(checkOption == 1 || checkOption == 2 || checkOption == 3 || checkOption == 4) {
			return true;
		}else {
			return false;
		}
	}
		
	public static boolean updateMethod(Scanner sc, GuestsList guestList) {
		int updateOption = updateOptionInput(sc);		
		System.out.println("\t Introduceti noua valoare! \n");
		
		String updateValue = sc.next();
		
		int searchOption = searchOptionInput(sc);
		
		switch (searchOption) {
		case 1:
			System.out.println("\t Introduceti numele si prenumele, urmate de ENTER. \n");
			return guestList.updatePerson(searchOption, sc.next(), sc.next(), updateOption, updateValue);
		case 3:
			System.out.println("\t Introduceti adresa de e-mail, urmata de ENTER. \n");
			return guestList.updatePerson(searchOption, sc.next(), "", updateOption, updateValue);
		case 4:
			System.out.println("\t Introduceti numarul de telefon, urmat de ENTER. \n");
			return guestList.updatePerson(searchOption, sc.next(), "", updateOption, updateValue);
		default:
			return false;
		}		
	}
	
	public static void search(Scanner sc, GuestsList guestList) {
		System.out.println("\t Introduceti sirul de caractere, urmat de ENTER. \n");
		String s = sc.next();
		
		guestList.searchEachFieldOfGuest(s);
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\t Bine ati venit! \n"
							+ "\t Introduceti numarul de locuri disponibile: ");
		int availableSeats = sc.nextInt();
		GuestsList guestList = new GuestsList(availableSeats);
		
		System.out.println("\t Va rugam sa introduceti comanda \"help\" urmata de tasta \"ENTER\", "
							+ "pentru a obtine lista de comenzi disponibile");
		String userCommand = sc.next();
		boolean repeat = true;
		
		while(repeat) {
			switch (userCommand) {
			case "help":
			case "Help":
			case "HELP":
				System.out.println("\t help         - Afiseaza aceasta lista de comenzi \n"
								 + "\t add          - Adauga o noua persoana (inscriere) \n"
								 + "\t check        - Verifica daca o persoana este inscrisa la eveniment \n"
								 + "\t remove       - Sterge o persoana existenta din lista \n"
								 + "\t update       - Actualizeaza detaliile unei persoane \n"
								 + "\t guests       - Lista de persoane care participa la eveniment \n"
								 + "\t waitlist     - Persoanele din lista de asteptare \n"
								 + "\t available    - Numarul de locuri libere \n"
								 + "\t guests_no    - Numarul de persoane care participa la eveniment \n"
								 + "\t waitlist_no  - Numarul de persoane din lista de asteptare \n"
								 + "\t subscribe_no - Numarul total de persoane inscrise \n"
								 + "\t search       - Cauta toti invitatii conform sirului de caractere introdus \n"
								 + "\t quit         - Inchide aplicatia \n");
				break;
				
			case "add":
			case "Add":
			case "ADD":
				addMethod(sc, guestList);
				//System.out.println("\t Tastati \"help\", urmat de ENTER pentru a vedea comenzile disponibile!");
				break;
				
			case "check":
			case "Check":
			case "CHECK":
				if(checkMethod(sc, guestList) >= -1) {
					System.out.println("\t Persoana cautata este inscrisa! \n");
				} else {
					System.out.println("\t Persoana cautata NU este inscrisa! \n");
				}
				break;
				
			case "remove":
			case "Remove":
			case "REMOVE":
				removeMethod(sc, guestList);
				break;
				
			case "update":
			case "Update":
			case "UPDATE":
				if(updateMethod(sc, guestList)) {
					System.out.println("\t Persoana a fost actualizata! \n");
				} else {
					System.out.println("\t Persoana nu a fost gasita pentru a fi actualizata! \n");
				}
				break;
	
			case "guests":
			case "Guests":
			case "GUESTS":
				guestList.getParticipantsList();
				break;
				
			case "waitlist":
			case "Waitlist":
			case "WAITLIST":
				guestList.getWaitingList();
				break;
				
			case "available":
			case "Available":
			case "AVAILABLE":
				guestList.getAvailableNo();
				break;
				
			case "guests_no":
			case "Guests_no":
			case "GUESTS_NO":
				guestList.getParticipantsNo();
				break;
				
			case "waitlist_no":
			case "Waitlist_no":
			case "WAITLIST_NO":
				guestList.getWaitingPersonsNo();
				break;
				
			case "subscribe_no":
			case "Subscribe_no":
			case "SUBSCRIBE_NO":
				guestList.getTotalPersonsNo();
				break;
				
			case "search":
			case "Search":
			case "SEARCH":
				search(sc, guestList);
				break;
				
			case "quit":
			case "Quit":
			case "QUIT":
				System.out.println("\t Aplicatia se va inchide! \n "
						+ "\t\t O zi buna! \n");
				repeat = false;
				break;
				
			default:
				System.out.println("\t Va rugam sa introduceti o comanda valida! \n");
				repeat = true;
				break;
			}
			
			if(repeat) {
				System.out.println("\t Va rugam sa introduceti  o noua comanda sau "
								+ "comanda \"help\" urmata de tasta \"ENTER\","
								+ " pentru a obtine lista de comenzi disponibile");
				userCommand = sc.next();	
			} else {
				break;
			}
		}		
	}

}
