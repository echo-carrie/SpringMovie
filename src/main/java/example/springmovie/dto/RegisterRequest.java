package example.springmovie.dto;

//dto层，相当于一个封装数据包，前后端通过这个数据包进行信息交换，前端一般使用json格式。
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private Boolean isVip; // 'vip' or 'non-vip'

    // Getters and setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsVip() {
        return isVip;
    }

    public void setIsVip(Boolean isVip) {
        this.isVip = isVip;
    }
}
