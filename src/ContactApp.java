import java.util.ArrayList;
import java.util.List;

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
            + "***************************************\n"
        ;
        isRunning = true;
        contactList = new ArrayList<>();

        System.out.println(print);

    }
}
