package example.springmovie.dto;

public class RegisterRequest {
    private String username;
    private String password;
    private String type; // 'vip' or 'non-vip'


    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public String getType() {
        return type;
    }

}