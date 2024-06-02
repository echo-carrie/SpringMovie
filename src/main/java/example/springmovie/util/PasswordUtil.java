//package example.springmovie.util;
//
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//public class PasswordUtil {
//
//    public static String encryptPassword(String password) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder.encode(password);
//    }
//
//    public static boolean matchPassword(String rawPassword, String encodedPassword) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }
//}