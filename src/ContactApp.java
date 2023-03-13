import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ContactApp {

    StringBuilder printContactList;
    boolean isRunning;
    List<Contact> contactList;
    Scanner sc;
    int currentMenu;

    final int MAINMENU = 0;
    final int ADDMENU = 1;
    final int SEARCHMENU = 2;
    final int DISPLAYMENU = 3;

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

    private void retrieveUserInputForMenu() {

        try {
            int userChoice = sc.nextInt();
            sc.nextLine();
            handleUserChoiceForMenus(userChoice);
        } catch (InputMismatchException ime) {
            System.out.println("Veuillez renseigner un chiffre !");
            displayMainMenu();
        }

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
                break;

            case 2:
                if (currentMenu == MAINMENU)
                    displaySearchSection();
                else if (currentMenu == ADDMENU)
                    currentMenu = MAINMENU;
                else if (currentMenu == SEARCHMENU)
                    currentMenu = MAINMENU;
                break;

            case 3:
                System.out.println("Afficher");
                break;

            case 4:
                isRunning = false;
                break;

            default:
                System.out.println("Veuillez renseigner un chiffre entre 1 et 4");
                displayMainMenu();
                break;

        }

    }

    private void retrieveNewContactInfos() {

        System.out.println("Entrez son nom :");
        String lastname = sc.nextLine();

        System.out.println("Entrez son prénom :");
        String firstname = sc.nextLine();

        System.out.println("Entrez son numéro de téléphone :");
        String phoneNumber = sc.nextLine();

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

    }

}
