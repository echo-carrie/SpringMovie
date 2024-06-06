package example.springmovie.controller;

import example.springmovie.entity.PlayRecord;
import example.springmovie.service.PlayRecordService;
import org.springframework.ui.Model;
import example.springmovie.entity.Movie;
import example.springmovie.entity.User;
import example.springmovie.service.MovieService;
import example.springmovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.sql.Timestamp;
import java.util.Date;


@Controller
@RequestMapping
public class PlayController {
    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;
    @Autowired
    private PlayRecordService playRecordService;

    @GetMapping("/plays")
    public String plays( Long videoId, Model model,Long userId) {
        Boolean currentUser_isVip = userService.isVipUser(userId);
        Movie movie = movieService.getVideoById(videoId);

        if (movie.isVip() && !currentUser_isVip) {
            // 如果视频是VIP视频且用户不是VIP用户，则重定向到错误页面
            model.addAttribute("message", "该视频仅VIP用户可观看。");
            return "error";
        }

        // 用户有权限观看视频，跳转到视频播放页面
        model.addAttribute("movie", movie);
        return "play";
    }

    @GetMapping("/log_add")
    public String logAdd(Long userId, Long videoId) {
        // 在这里添加播放记录逻辑
        // 创建播放记录对象
        PlayRecord playRecord = new PlayRecord();

        playRecord.setUserId(userId);
        playRecord.setVideoId(videoId);
        playRecord.setCreated_at(new Timestamp(System.currentTimeMillis()));// 记录当前时间为播放时间
        playRecord.setPlayTime();
        // 保存播放记录到数据库

        playRecordService.savePlayRecord(playRecord);

        return "redirect:/plays"; // 重定向到播放页面
    }
}
