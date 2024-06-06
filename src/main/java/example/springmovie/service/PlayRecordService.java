package example.springmovie.service;

import example.springmovie.entity.PlayRecord;
import example.springmovie.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayRecordService {

    @Autowired
    RecordMapper recordMapper;
    public void savePlayRecord(PlayRecord playRecord) {
        recordMapper.insertRecord(playRecord);
    }
}
