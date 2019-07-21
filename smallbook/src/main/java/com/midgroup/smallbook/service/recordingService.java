package com.midgroup.smallbook.service;

import com.midgroup.smallbook.mapper.recordingMapper;
import com.midgroup.smallbook.pojo.Recording;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface recordingService {
    int addRecording(Recording recording);
    int UpdateRecordingByDate(Recording recording,String date);
    Recording getRecordingByDate(String date);
    ArrayList<Recording> getRecordingByYear(String year);
    ArrayList<Recording> getRecordingByMonth(String year,String month);
}
