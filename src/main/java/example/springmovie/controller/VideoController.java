package example.springmovie.controller;

import cn.dev33.satoken.stp.StpUtil;
import example.springmovie.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/moviePlay/{videoId}")
    public boolean canPlayVideo(@PathVariable Long videoId) {
        String videoType = videoService.getVideoType(videoId);

        // 假设只有VIP用户可以观看付费视频
        return videoType.equals("free") || videoType.equals("vip") || StpUtil.hasRole("ROLE_VIP");
    }
    @GetMapping("test")
    public String test(){
        return "test-hello-word";
    }

}