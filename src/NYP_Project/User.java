package NYP_Project;

public abstract class User {
    private String name;
    private String username;
    private String password;
    private String userType;

    public User(String name, String username, String password, String userType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public static void ekle(String name, String username, String password, String userType) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void ilanaBasvur(int ilanId) {
    }

    public boolean ilanaBasvurulmusMu(int ilanId) {
        return true;
    }
    public void ilanIptal(int ilanId){

    }
}
