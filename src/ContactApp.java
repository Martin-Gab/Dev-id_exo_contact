import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ContactApp {

    String print;
    boolean isRunning;
    List<Contact> contactList;
    Scanner sc;
    int currentMenu;

    final int MAINMENU = 0;
    final int ADDMENU = 1;
    final int SEARCHMENU = 2;
    final int DISPLAYMENU = 3;

    public ContactApp() {

        print = "***************************************\n"
                + "***************************************\n"
                + "***                                 ***\n"
                + "***        APPLI CONTACTS           ***\n"
                + "***                                 ***\n"
                + "***************************************\n"
                + "***************************************\n";
        isRunning = true;
        contactList = new ArrayList<>();
        sc = new Scanner(System.in);
        currentMenu = MAINMENU;

        System.out.println(print);

        do
            displayMainMenu();
        while (isRunning);

        System.exit(0);

    }

    private void displayMainMenu() {

        print = "---------------------------------------\n"
                + " 1 -> Ajouter un contact\n"
                + " 2 -> Rechercher un contact\n"
                + " 3 -> Afficher tous les contacts\n"
                + " 4 -> Quitter\n"
                + ":";

        System.out.println(print);

        retrieveUserInputForMenu();
    }

    private void displayCreateMenu() {
        currentMenu = ADDMENU;
        print = "---------------------------------------\n"
                + " 1 -> Entrez un nouveau contact\n"
                + " 2 -> Retour au menu principal\n"
                + ":";
        System.out.println(print);
        retrieveUserInputForMenu();
    }

    private void retrieveUserInputForMenu() {

        try {
            int userChoice = sc.nextInt();
            sc.nextLine();
            handleUserChoiceForMenus(userChoice);
        } catch (InputMismatchException ime) {
            print = "Veuillez renseigner un chiffre !";
            System.out.println(print);
            displayMainMenu();
        }

    }

    private void handleUserChoiceForMenus(int userChoice) {

        switch (userChoice){

            case 1:
                if (currentMenu == MAINMENU)
                    displayAddContactSection();
                else if (currentMenu == ADDMENU) {
                    retrieveNewContactInfos();
                }
                break;

            case 2:
                System.out.println("Rechercher");
                break;

            case 3:
                System.out.println("Afficher");
                break;

            case 4:
                isRunning = false;
                break;

            default:
                print = "Veuillez renseigner un chiffre entre 1 et 4";
                System.out.println(print);
                displayMainMenu();
                break;

        }

    }

    private void displayAddContactSection() {

        print = "---------------------------------------\n"
                + " Ajouter un nouveau contact\n";
        System.out.println(print);
        retrieveNewContactInfos();

    }

    private void retrieveNewContactInfos() {

        print = "Entrez son nom :";
        System.out.println(print);
        String lastname = sc.nextLine();

        print = "Entrez son prénom :";
        System.out.println(print);
        String firstname = sc.nextLine();

        print = "Entrez son numéro de téléphone :";
        System.out.println(print);
        String phoneNumber = sc.nextLine();

        createNewContactAndAddToList(lastname, firstname, phoneNumber);

    }

    private void createNewContactAndAddToList(String lastname, String firstname, String phoneNumber) {

        contactList.add(new Contact(lastname, firstname, phoneNumber));
        displayCreateMenu();

    }


}
