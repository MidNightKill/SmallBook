package com.midgroup.smallbook.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.midgroup.smallbook.pojo.Recording;
import com.midgroup.smallbook.service.recordingService;
import com.midgroup.smallbook.util.LayuiUtil;
import com.midgroup.smallbook.util.RecordinglistUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Month;
import java.util.ArrayList;

@Controller
public class MainController {

    @Qualifier("recodingImp")
    @Autowired
    private recordingService recordingservice;
    @RequestMapping(value = "/getsearchresult")
    @ResponseBody
    public JSONObject getsearchresult(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        if(session.getAttribute("recordingdate")!=null)
        {
            Recording recording=recordingservice.getRecordingByDate((String)(session.getAttribute("recordingdate")));
            if (recording==null)return LayuiUtil.ToLayJson(null);
            ArrayList<Recording> recordings=new ArrayList<>();
            recordings.add(recording);
            return LayuiUtil.ToLayJson(recordings);
        }
        else if(session.getAttribute("recordingmonth")!=null)
        {
            return LayuiUtil.ToLayJson(recordingservice.getRecordingByMonth((String)session.getAttribute("recordingyear"),(String)session.getAttribute("recordingmonth")));
        }
        else if(session.getAttribute("recordingyear")!=null)
        {
            return LayuiUtil.ToLayJson(recordingservice.getRecordingByYear((String)session.getAttribute("recordingyear")));
        }
        return LayuiUtil.ToLayJson(null);
    }
    @RequestMapping(value = "/searchrecording")
    @ResponseBody
    public String searchrecording(HttpServletRequest request,String date)
    {
        HttpSession session=request.getSession();

        if(date.length()==4)
        {
            session.setAttribute("recordingdate",null);
            session.setAttribute("recordingmonth",null);
            session.setAttribute("recordingyear",date);
            return "true";
        }
        else if(date.length()==6)
        {
            session.setAttribute("recordingdate",null);
            session.setAttribute("recordingyear",date.substring(0,4));
            session.setAttribute("recordingmonth",date.substring(4,6));
            return "true";
        }
        else if(date.length()==8)
        {
            session.setAttribute("recordingdate",date);
            return "true";
        }
        session.setAttribute("recordingdate",null);
        session.setAttribute("recordingyear",null);
        session.setAttribute("recordingmonth",null);
        return "false";
    }

    @RequestMapping(value = "/searchrecordingbymap")
    @ResponseBody
    public String searchrecordingbymap(HttpServletRequest request)
    {
        HttpSession session=request.getSession();
         if(session.getAttribute("recordingmonth")!=null)
        {
            String year=(String)session.getAttribute("recordingyear");
            String month=(String)session.getAttribute("recordingmonth");
            ArrayList<Recording> recordings=recordingservice.getRecordingByMonth(year,month);
            ArrayList<Recording> recordingstmp=new ArrayList<Recording>();
            Recording recording=new Recording();
            recording.setYear(year);
            recording.setMonth(month);
            recordingstmp.add(recording);
            for(int i=1;i<=31;++i)
            {
                String day="";
                if(i<10)day+="0";
                day+=i;
                int index=RecordinglistUtil.existday(recordings,day);
                if(index!=-1)
                {
                    Recording recording1=recordings.get(index);
                    recording1.setDate(day);
                    recordingstmp.add(recording1);

                }
                else
                {
                    recording=new Recording();
                    String date=year+month+day;
                    recording.FormatNULL(date);
                    recordingstmp.add(recording);
                }
            }

            return JSON.toJSONString(recordingstmp);
        }
        else if(session.getAttribute("recordingyear")!=null)
        {
            String year=(String)session.getAttribute("recordingyear");
            ArrayList<Recording> recordingstmp=new ArrayList<Recording>();
            Recording recording=new Recording();
            recording.setYear(year);
            recordingstmp.add(recording);
            for(int i=1;i<=12;++i)
            {
                String month="";
                if(i<10)month+="0";
                month+=i;
                ArrayList<Recording> recordings=recordingservice.getRecordingByMonth(year,month);
                recording=RecordinglistUtil.getMonthSum(recordings,year,month);
                recording.setDate(recording.getMonth());
                recordingstmp.add(recording);
            }
            return JSON.toJSONString(recordingstmp);
        }
        return "false";
    }
    @RequestMapping(value = "/modifyrecording")
    @ResponseBody
    public String modifyrecording(HttpServletRequest request,Recording recording)
    {
        HttpSession session=request.getSession();
        if(session.getAttribute("modifydate")==null)
        {
            return "false";
        }
        recording.FormatData();
        int flag=recordingservice.UpdateRecordingByDate(recording,(String)session.getAttribute("modifydate"));
        if(flag>0) return "true";
        return "false";
    }

    @RequestMapping(value = "/addrecording")
    @ResponseBody
    public String addrecording(Recording recording)
    {
        recording.FormatData();
//        System.out.println(recording.toString());
        if(recordingservice.getRecordingByDate(recording.getDate())!=null)
        {
            return "exist";
        }
        int flag=recordingservice.addRecording(recording);
        System.out.println(flag);
        if(flag>0) return "true";
        return "false";
    }
    @RequestMapping(value = "/getrecordingbyday")
    @ResponseBody
    public String getrecordingbyday(HttpServletRequest request,String date)
    {
        if(date==null||date.length()!=8)
        {
            return "false";
        }
        Recording recording=recordingservice.getRecordingByDate(date);
        if(recording==null)
        {
            return "false";
        }
        HttpSession session=request.getSession();
        session.setAttribute("modifydate",date);
        System.out.println(JSON.toJSONString(recording));
        return JSON.toJSONString(recording);
    }

    @RequestMapping(value = "/getsearchyear")
    @ResponseBody
    public String getsearchyear(HttpServletRequest request,String date)
    {
        HttpSession session=request.getSession();
        if(session.getAttribute("recordingyear")!=null)
        return (String)session.getAttribute("recordingyear");
        return null;
    }

    @RequestMapping(value = "/getsearchmonth")
    @ResponseBody
    public String getsearchmonth(HttpServletRequest request,String date)
    {
        HttpSession session=request.getSession();
        if(session.getAttribute("recordingyear")!=null&&session.getAttribute("recordingyear")!=null)
            return (String)session.getAttribute("recordingyear")+(String)session.getAttribute("recordingyear");
        return null;
    }
}
