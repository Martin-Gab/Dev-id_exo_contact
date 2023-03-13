public class Contact {

    private String lastname, firstname, phoneNumber;

    public Contact(String lastname, String firstname, String phoneNumber) {

        this.lastname = lastname.toUpperCase();
        this.firstname = firstname.substring(0,1).toUpperCase() + firstname.substring(1).toLowerCase();
        this.phoneNumber = phoneNumber;

    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
