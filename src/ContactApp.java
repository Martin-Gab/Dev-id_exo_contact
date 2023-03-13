import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ContactApp {

    String print;
    boolean isRunning;
    List<Contact> contactList;

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

        System.out.println(print);

        displayMainMenu();
        /*do {

            displayMainMenu();

        } while (isRunning);*/

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

    private void retrieveUserInputForMenu() {

        Scanner sc = new Scanner(System.in);
        try {
            int userChoice = sc.nextInt();
            handleUserChoiceForMainMenu(userChoice);
        } catch (InputMismatchException ime) {
            print = "Veuillez renseigner un chiffre !";
            System.out.println(print);
            displayMainMenu();
        }
        sc.close();

    }

    private void handleUserChoiceForMainMenu(int userChoice) {

        switch (userChoice){

            case 1:
                System.out.println("Ajouter");
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
}
