//package example.springmovie.entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Table;
//import org.springframework.data.annotation.Id;
//
//@Entity
//@Table(name = "videos")
//
//public class Video {
//    @jakarta.persistence.Id
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String title;           // 视频标题
//    private String type;            // 视频类型，例如 "free", "vip", "paid"
//    private String description;     // 视频描述
//    private String video_url;       // 视频文件的URL或路径
//
//    // 构造函数、getter和setter方法
//
//    public Video() {
//        // 空构造函数
//    }
//
//    // ID的getter和setter
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    // 标题的getter和setter
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    // 类型的getter和setter
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    // 描述的getter和setter
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    // 视频URL的getter和setter
//    public String getVideoUrl() {
//        return video_url;
//    }
//
//    public void setVideoUrl(String video_url) {
//        this.video_url = video_url;
//    }
//
//    // toString方法，用于调试
//    @Override
//    public String toString() {
//        return "Video{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", type='" + type + '\'' +
//                ", description='" + description + '\'' +
//                ", video_url='" + video_url + '\'' +
//                '}';
//    }
//}