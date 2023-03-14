import java.util.*;

public class ContactApp {

    StringBuilder printContactList;
    boolean isRunning;
    List<Contact> contactList;
    Scanner sc;
    int currentMenu;

    final int MAINMENU = 0;
    final int ADDMENU = 1;
    final int SEARCHMENU = 2;
    final int SHOWMENU = 3;
    final int SHOWSUBMENU = 4;
    final String ASCENDING = "ascending";
    final String DESCENDING = "descending";

    public ContactApp() {

        printContactList = new StringBuilder();
        isRunning = true;
        contactList = new ArrayList<>();
        sc = new Scanner(System.in);
        currentMenu = MAINMENU;

        System.out.println(
                "***************************************\n"
                + "***************************************\n"
                + "***                                 ***\n"
                + "***        APPLI CONTACTS           ***\n"
                + "***                                 ***\n"
                + "***************************************\n"
                + "***************************************\n"
        );

        do
            displayMainMenu();
        while (isRunning);

        sc.close();
        System.exit(0);

    }

    private void displayMainMenu() {

        System.out.println(
                "---------------------------------------\n"
                + " 1 -> Ajouter un contact\n"
                + " 2 -> Rechercher un contact\n"
                + " 3 -> Afficher tous les contacts\n"
                + " 4 -> Quitter\n"
                + ":"
        );

        retrieveUserInputForMenu();
    }

    private void displayAddSection() {

        System.out.println(
                "---------------------------------------\n"
                + " Ajouter un nouveau contact\n"
        );
        retrieveNewContactInfos();

    }

    private void displayAddMenu() {

        currentMenu = ADDMENU;
        System.out.println(
                "---------------------------------------\n"
                + " 1 -> Entrez un nouveau contact\n"
                + " 2 -> Retour au menu principal\n"
                + ":"
        );
        retrieveUserInputForMenu();

    }

    private void displaySearchSection() {

        System.out.println(
                "---------------------------------------\n"
                + " Rechercher un contact\n"
        );
        retrieveInputForContactSearch();

    }

    private void displaySearchMenu() {

        currentMenu = SEARCHMENU;
        System.out.println(
                "---------------------------------------\n"
                + " 1 -> Rechercher un nouveau contact\n"
                + " 2 -> Retour au menu principal\n"
                + ":"
        );
        retrieveUserInputForMenu();

    }

    private void displayShowContactsSection() {

        if (contactList.isEmpty())
            System.out.println(" => Il n'y a aucun contact à afficher !");
        else {
            System.out.println(
                    "---------------------------------------\n"
                    + " Liste des contacts\n"
                    + "---------------------------------------\n"
            );
            displayShowContactsMenu();
        }

    }

    private void displayShowContactsMenu() {

        currentMenu = SHOWMENU;
        System.out.println(
                " 1 -> Par ordre croissant\n"
                + " 2 -> Par ordre décroissant\n"
                + ":"
        );
        retrieveUserInputForMenu();

    }

    private void displayShowContactsSubMenu() {

        currentMenu = SHOWSUBMENU;
        System.out.println(
                " 3 -> Lister les contacts à nouveau\n"
                + " 4 -> Retour au menu principal\n"
                + ":"
        );
        retrieveUserInputForMenu();

    }

    private void retrieveUserInputForMenu() {

        String userChoiceStr = "";

        do {
            if (!userChoiceStr.isEmpty())
                System.out.println("Veuillez renseigner un chiffre :");
            userChoiceStr = sc.nextLine();
        } while (!userChoiceStr.matches("\\d+"));

        handleUserChoiceForMenus(Integer.parseInt(userChoiceStr));

    }

    private void handleUserChoiceForMenus(int userChoice) {

        switch (userChoice){

            case 1:
                if (currentMenu == MAINMENU)
                    displayAddSection();
                else if (currentMenu == ADDMENU)
                    retrieveNewContactInfos();
                else if (currentMenu == SEARCHMENU)
                    retrieveInputForContactSearch();
                else if (currentMenu == SHOWMENU)
                    sortContacts(ASCENDING);
                break;

            case 2:
                if (currentMenu == MAINMENU)
                    displaySearchSection();
                else if (currentMenu == ADDMENU)
                    currentMenu = MAINMENU;
                else if (currentMenu == SEARCHMENU)
                    currentMenu = MAINMENU;
                else if (currentMenu == SHOWMENU)
                    sortContacts(DESCENDING);
                break;

            case 3:
                if (currentMenu == MAINMENU)
                    displayShowContactsSection();
                else if (currentMenu == SHOWSUBMENU)
                    displayShowContactsMenu();
                break;

            case 4:
                if (currentMenu == MAINMENU)
                    isRunning = false;
                else if (currentMenu == SHOWSUBMENU)
                    currentMenu = MAINMENU;
                break;

        }

    }

    private void retrieveNewContactInfos() {

        String lastname = "";
        String firstname = "";
        String phoneNumber = "";

        do {

            if (!lastname.isEmpty())
                System.out.println("Veuillez saisir uniquement des lettres ! Réessayez");

            System.out.println("Entrez son nom :");
            lastname = sc.nextLine();

        } while (!lastname.trim().matches("[a-zA-Z]+"));

        do {

            if (!firstname.isEmpty())
                System.out.println("Veuillez saisir uniquement des lettres ! Réessayez");

            System.out.println("Entrez son prénom :");
            firstname = sc.nextLine();

        } while (!firstname.trim().matches("[a-zA-Z]+"));

        do {

            if (!phoneNumber.isEmpty())
                System.out.println("Veuillez saisir uniquement des numéros ! Réessayez");

            System.out.println("Entrez son numéro de téléphone :");
            phoneNumber = sc.nextLine();

        } while (!phoneNumber.trim().matches("[0-9]+"));

        createNewContactAndAddToList(lastname, firstname, phoneNumber);

    }

    private void createNewContactAndAddToList(String lastname, String firstname, String phoneNumber) {

        contactList.add(new Contact(lastname, firstname, phoneNumber));
        displayAddMenu();

    }

    private void retrieveInputForContactSearch() {

        System.out.println("Entrez le nom ou le prénom du contact à rechercher :");
        String searchStrToLowerCase = sc.nextLine().toLowerCase();
        searchForContacts(searchStrToLowerCase);

    }

    private void searchForContacts(String searchStrToLowerCase) {

        List<Contact> contactsFound = new ArrayList<>();

        for (Contact c : contactList)
            if (
                c.getLastname().toLowerCase().contains(searchStrToLowerCase)
                ||
                c.getFirstname().toLowerCase().contains(searchStrToLowerCase)
            )
                contactsFound.add(c);

        if (contactsFound.isEmpty())
            System.out.println(" => Aucune correspondance trouvée ! (sur " + contactList.size() + " contacts(s))");
        else
            printList(contactsFound);

        displaySearchMenu();

    }

    private void sortContacts(String direction) {

        List <Contact> sortedContacts = new ArrayList<>();

        if (direction == ASCENDING)
            contactList
                    .stream()
                    .sorted((c1, c2) -> c1.getLastname().compareTo(c2.getLastname()))
                    .forEach(contact -> sortedContacts.add(contact));
        else if (direction == DESCENDING)
            contactList
                    .stream()
                    .sorted((c1, c2) -> c2.getLastname().compareTo(c1.getLastname()))
                    .forEach(contact -> sortedContacts.add(contact));

        printList(sortedContacts);
        displayShowContactsSubMenu();

    }

    private void printList(List<Contact> contactsToPrint) {

        for (Contact c : contactsToPrint)
            printContactList
                    .append(" -> ")
                    .append(c.getFirstname())
                    .append(" ")
                    .append(c.getLastname())
                    .append(" : ")
                    .append(c.getPhoneNumber())
                    .append("\n");

        System.out.println(printContactList);
        printContactList.setLength(0);

    }

}
