import java.util.HashMap;
import java.util.Scanner;

public class AppContact {

    private String responseFinal, response;
    private Scanner sc;
    private HashMap<String, Contact> contacts;

    public AppContact() {
        responseFinal = "";
        response = "";
        sc = new Scanner(System.in);
        contacts = new HashMap<>();
    }

    public void launch() {

        System.out.println(
                "***************************************\n"
                + "***************************************\n"
                + "***                                 ***\n"
                + "***        APPLI CONTACTS           ***\n"
                + "***                                 ***\n"
                + "***************************************\n"
                + "***************************************\n"
        );

        do {
            System.out.println(
                    "---------------------------------------\n"
                    + " 1 -> Ajouter un contact\n"
                    + " 2 -> Rechercher un contact\n"
                    + " 3 -> Afficher tous les contacts\n"
                    + " 4 -> Quitter\n"
                    + ":"
            );

            switch (sc.nextLine().trim()) {
                case "1":
                    //addContact();
                    break;
                case "2":
                    //searchContact();
                    break;
                case "3":
                    //listContacts();
                    break;
                case "4":
                    responseFinal = "n";
                    break;
                default:
                    System.out.println("\nVeuillez recommencez !");
                    responseFinal = "o";
                    break;
            }
        } while (responseFinal.equalsIgnoreCase("o"));

    }
}
