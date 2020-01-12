package list;

import java.util.ArrayList;

public class GuestsList {

	private final int availableSeats;
	private ArrayList<Guest> participantsList;
	private ArrayList<Guest> waitingList;
	
	public GuestsList(int availableSeats) {
		this.availableSeats = availableSeats;
		this.participantsList = new ArrayList<Guest>();
		this.waitingList = new ArrayList<Guest>();
	}
	
	//returns:
	//	 => -1 if the person was not found
	//	 =>	 x if the person was found 
	private int searchParticipantsByLastNameAndFirstName(String lastName, String firstName) {
		if(participantsList.size() == 0) {
			return -1;
		}
		for(int i=0; i<participantsList.size(); i++) {
			if(participantsList.get(i).getLastName().equals(lastName) &&
					participantsList.get(i).getFirstName().equals(firstName)) {
				return i;
			}
		}
		
		return -1;
	}
	
	private int searchWaitingsByLastNameAndFirstName(String lastName, String firstName) {
		if(waitingList.size() == 0) {
			return -1;
		}
		for(int i=0; i<waitingList.size(); i++) {
			if(waitingList.get(i).getLastName().equals(lastName) &&
					waitingList.get(i).getFirstName().equals(firstName)) {
				return i;
			}
		}
		
		return -1;
	}
	
	//returns:
	//	 => -1 if the person was not found
	//	 =>	 x if the person was found 
	private int searchParticipantsByEmail(String email) {
		if(participantsList.size() == 0) {
			return -1;
		}
		for(int i=0; i<participantsList.size(); i++) {
			if(participantsList.get(i).getEmail().equals(email)) {
				return i;
			}
		}
	
		return -1;
	}
	
	private int searchWaitingsByEmail(String email) {
		if(waitingList.size() == 0) {
			return -1;
		}
		for(int i=0; i<waitingList.size(); i++) {
			if(waitingList.get(i).getEmail().equals(email)) {
				return i;
			}
		}
		
		return -1;
	}
	
	//returns:
	//	 => -1 if the person was not found
	//	 =>	 x if the person was found 
	private int searchParticipantsByPhoneNumber(String phoneNumber) {
		if(participantsList.size() == 0) {
			return -1;
		}
		for(int i=0; i<participantsList.size(); i++) {
			if(participantsList.get(i).getPhoneNumber().equals(phoneNumber)) {
				return i;
			}
		}
		
		return -1;
	}
	
	private int searchWaitingsByPhoneNumber(String phoneNumber) {
		if(waitingList.size() == 0) {
			return -1;
		}
		for(int i=0; i<waitingList.size(); i++) {
			if(waitingList.get(i).getPhoneNumber().equals(phoneNumber)) {
				return i;
			}
		}
		
		return -1;
	}
	
	public int addNewPerson(Guest guest) {
		if(searchParticipantsByEmail(guest.getEmail()) >= 0 || searchWaitingsByEmail(guest.getEmail()) >= 0) {
			return -1;
		}
		if(searchParticipantsByEmail(guest.getEmail()) == -1){
			if(participantsList.size() < this.availableSeats) {
				participantsList.add(guest);
				System.out.println("\t [" + guest.getLastName() + " " + guest.getFirstName() + "]"
									+ " Felicitari! Locul tau la eveniment este confirmat. Te asteptam! \n");
				return 0;
			} 
		}
		if(searchWaitingsByEmail(guest.getEmail()) == -1) {
			waitingList.add(guest);
			System.out.println("\t [" + guest.getLastName() + " " + guest.getFirstName() + "]"
								+ "Te-ai inscris cu succes in lista de asteptare si ai primit numarul \n"
								+ "\t de ordine <" + waitingList.size() +  ">. Te vom notifica daca un loc devine disponibil. \n");
			return waitingList.size();
		}
		
		return -1;
	}
	
	//checkOption:							[]	option1 = lastName ||		
	// 1 => search by last & first name;	[]
	// 2 => search by e-mail address;		[]
	// 3 => search by phone number.			[]
	public int checkPerson(int checkOption, String option1, String option2) {
		if(checkOption == 1) {  // option 1: search by lastName and firstName
//System.out.println(searchParticipantsByLastNameAndFirstName(option1, option2) + " + " +
//		+ searchWaitingsByLastNameAndFirstName(option1, option2));
			return (searchParticipantsByLastNameAndFirstName(option1, option2) 
					+ searchWaitingsByLastNameAndFirstName(option1, option2));
		}else if(checkOption == 2) {  // option 2: search by e-mail address
			return (searchParticipantsByEmail(option1) + searchWaitingsByEmail(option1)); 
		}else {// option 3: search by phone number
//System.out.println("checkPerson" + checkOption + "   " + option1);
//System.out.println((searchParticipantsByPhoneNumber(option1) + " + " + searchWaitingsByPhoneNumber(option1)));
			return (searchParticipantsByPhoneNumber(option1) + searchWaitingsByPhoneNumber(option1));
		}
	}
	
	public boolean removePerson(int checkOption, String option1, String option2) {
		if(checkOption == 1) {  // option 1: search by lastName and firstName
			int searchResult = searchParticipantsByLastNameAndFirstName(option1, option2);
			if(searchResult >= 0) {
				String guestName = participantsList.get(searchResult).getLastName() + " "
								+ participantsList.get(searchResult).getFirstName();
				participantsList.remove(searchResult);
				System.out.println("\t [" + guestName + "] a fost stears din lista participantilor! \n");
				
				participantsList.add(waitingList.get(0));
				String waitingName = waitingList.get(0).getLastName() + " " + waitingList.get(0).getFirstName();
				System.out.println("\t [" + waitingName + "] Felicitari! Locul tau la eveniment este confirmat. Te asteptam! \n");
				waitingList.remove(0);
				System.out.println("\t [" + waitingName + "] a fost stears din lista de asteptare! \n");
				return true;
			} else {
				searchResult = searchWaitingsByLastNameAndFirstName(option1, option2);
				if(searchResult >= 0) {
					String waitingName = waitingList.get(searchResult).getLastName() + " "
							+ waitingList.get(searchResult).getFirstName();
					waitingList.remove(searchResult);
					System.out.println("\t [" + waitingName + "] a fost stears din lista participantilor! \n");
					return true;
				} else {
					return false;
				}
			}
		} else if(checkOption == 2) {  // option 2: search by e-mail address
			int searchResult = searchParticipantsByEmail(option1);
			if(searchResult >= 0) {
				String guestName = participantsList.get(searchResult).getLastName() + " "
						+ participantsList.get(searchResult).getFirstName();
				participantsList.remove(searchResult);
				System.out.println("\t [" + guestName + "] a fost stears din lista participantilor! \n");
				
				participantsList.add(waitingList.get(0));
				String waitingName = waitingList.get(0).getLastName() + " " + waitingList.get(0).getFirstName();
				System.out.println("\t [" + waitingName + "] Felicitari! Locul tau la eveniment este confirmat. Te asteptam! \n");
				waitingList.remove(0);
				System.out.println("\t [" + waitingName + "] a fost stears din lista de asteptare! \n");
				
				return true;
			} else {
				searchResult = searchWaitingsByEmail(option1);
				if(searchResult >= 0) {
					String waitingName = waitingList.get(searchResult).getLastName() + " "
							+ waitingList.get(searchResult).getFirstName();
					waitingList.remove(searchResult);
					System.out.println("\t [" + waitingName + "] a fost stears din lista participantilor! \n");
					return true;
				} else {
					return false;
				}
			}				
		} else {// option 3: search by phone number
			int searchResult = searchParticipantsByPhoneNumber(option1);
			if(searchResult >= 0) {
				String guestName = participantsList.get(searchResult).getLastName() + " "
						+ participantsList.get(searchResult).getFirstName();
				participantsList.remove(searchResult);
				System.out.println("\t [" + guestName + "] a fost stears din lista participantilor! \n");
				
				participantsList.add(waitingList.get(0));
				String waitingName = waitingList.get(0).getLastName() + " " + waitingList.get(0).getFirstName();
				System.out.println("\t [" + waitingName + "] Felicitari! Locul tau la eveniment este confirmat. Te asteptam! \n");
				waitingList.remove(0);
				System.out.println("\t [" + waitingName + "] a fost stears din lista de asteptare! \n");
				
				return true;
			} else {
				searchResult = searchWaitingsByPhoneNumber(option1);
				if(searchResult >= 0) {
					String waitingName = waitingList.get(searchResult).getLastName() + " "
							+ waitingList.get(searchResult).getFirstName();
					waitingList.remove(searchResult);
					System.out.println("\t [" + waitingName + "] a fost stears din lista participantilor! \n");
					return true;
				} else {
					return false;
				}
			}	
		}
	}
	
	private void updateField(ArrayList<Guest> arrayList, int index, int updateOption, String updateValue) {
		if(updateOption == 1) {
			arrayList.get(index).setLastName(updateValue);
		} else if(updateOption == 2) {
			arrayList.get(index).setFirstName(updateValue);
		} else if(updateOption == 3) {
			arrayList.get(index).setEmail(updateValue);
		} else {
			arrayList.get(index).setPhoneNumber(updateValue);
		}
	}
	
	public boolean updatePerson(int checkOption, String option1, String option2, int updateOption, String updateValue) {
		if(checkOption == 1) {  // option 1: search by lastName and firstName; returns the index
			int searchResult = searchParticipantsByLastNameAndFirstName(option1, option2);
			if(searchResult >= 0) {
				updateField(participantsList, searchResult, updateOption, updateValue);
				return true;
			} else {
				searchResult = searchWaitingsByLastNameAndFirstName(option1, option2);
				if(searchResult >= 0) {
					updateField(waitingList, searchResult, updateOption, updateValue);
					return true;
				} else {
					return false;
				}
			}
		} else if(checkOption == 2) {  // option 2: search by e-mail address
			int searchResult = searchParticipantsByEmail(option1);
			if(searchResult >= 0) {
				updateField(participantsList, searchResult, updateOption, updateValue);
				return true;
			} else {
				searchResult = searchWaitingsByEmail(option1);
				if(searchResult >= 0) {
					updateField(waitingList, searchResult, updateOption, updateValue);
					return true;
				} else {
					return false;
				}
			}				
		} else {// option 3: search by phone number
			int searchResult = searchParticipantsByPhoneNumber(option1);
			if(searchResult >= 0) {
				updateField(participantsList, searchResult, updateOption, updateValue);
				return true;
			} else {
				searchResult = searchWaitingsByPhoneNumber(option1);
				if(searchResult >= 0) {
					updateField(waitingList, searchResult, updateOption, updateValue);
					return true;
				} else {
					return false;
				}
			}	
		}
	}
	
	public void getParticipantsList() {
		if(participantsList.size() == 0) {
			System.out.println("\t Lista de participanti este goala... \n");
		} else {		
			System.out.println("\t ==================== \n");
			for(int i=0; i<participantsList.size(); i++) {
				System.out.println(participantsList.get(i));
			}
			System.out.println("\t ==================== \n");
		}
	}
	
	public void getWaitingList() {
		if(waitingList.size() == 0) {
			System.out.println("\t Lista de asteptare este goala... \n");
		} else {		
			System.out.println("\t ==================== \n");
			for(int i=0; i<waitingList.size(); i++) {
				System.out.println(waitingList.get(i));
			}
			System.out.println("\t ==================== \n");
		}
	}
	
	public void getAvailableNo() {
		System.out.println("\t Numarul de locuri disponibile: " + (this.availableSeats - participantsList.size()) + "\n");
	}
	
	public void getParticipantsNo() {
		System.out.println("\t Numarul de participanti: " + participantsList.size() + "\n");
	}
	
	public void getWaitingPersonsNo() {
		System.out.println("\t Numarul de persoane in asteptare: " + waitingList.size() + "\n");
	}
	
	public void getTotalPersonsNo() {
		System.out.println("\t Numarul total de inscrisi: " + (participantsList.size() + waitingList.size()) + "\n");
	}
	
	public void searchEachFieldOfGuest(String s) {
		for(int i=0; i<participantsList.size(); i++) {
			String lastName = participantsList.get(i).getLastName();
			String firstName = participantsList.get(i).getFirstName();
			String email = participantsList.get(i).getEmail();
			String phoneNumber = participantsList.get(i).getPhoneNumber();

			if(lastName.contains(s)) {
				System.out.println("\t Prenumele: " + lastName + "\n");
			}
			
			if(firstName.contains(s)) {
				System.out.println("\t Numele: " + firstName + "\n");
			}
			
			if(email.contains(s)) {
				System.out.println("\t Email: " + email + "\n");
			}
			
			if(phoneNumber.contains(s)) {
				System.out.println("\t Telefon: " + phoneNumber + "\n");
			}
		}
		
		for(int i=0; i<waitingList.size(); i++) {
			String lastName = waitingList.get(i).getLastName();
			String firstName = waitingList.get(i).getFirstName();
			String email = waitingList.get(i).getEmail();
			String phoneNumber = waitingList.get(i).getPhoneNumber();

			if(lastName.contains(s)) {
				System.out.println("\t Prenumele: " + lastName + "\n");
			}
			
			if(firstName.contains(s)) {
				System.out.println("\t Numele: " + firstName + "\n");
			}
			
			if(email.contains(s)) {
				System.out.println("\t Email: " + email + "\n");
			}
			
			if(phoneNumber.contains(s)) {
				System.out.println("\t Telefon: " + phoneNumber + "\n");
			}
		}
	}
}