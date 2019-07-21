package com.midgroup.smallbook.service.Imp;

import com.midgroup.smallbook.mapper.recordingMapper;
import com.midgroup.smallbook.pojo.Recording;
import com.midgroup.smallbook.service.recordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class recodingImp implements recordingService {
    @Autowired
    private recordingMapper recordingMapper;

    @Override
    public int addRecording(Recording recording) {
        recording.FormatData();
        return recordingMapper.addRecording(recording);
    }

    @Override
    public int UpdateRecordingByDate(Recording recording, String date) {
        recording.FormatData();
        recording.setOlddate(date);
        return recordingMapper.UpdateRecordingByDate(recording);
    }

    @Override
    public Recording getRecordingByDate(String date) {
        return recordingMapper.getRecordingByDate(date);
    }

    @Override
    public ArrayList<Recording> getRecordingByYear(String year) {
        return recordingMapper.getRecordingByYear(year);
    }

    @Override
    public ArrayList<Recording> getRecordingByMonth(String year, String month) {
        return recordingMapper.getRecordingByMonth(year,month);
    }
}
