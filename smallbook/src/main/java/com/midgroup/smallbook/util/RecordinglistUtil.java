package com.midgroup.smallbook.util;

import com.midgroup.smallbook.pojo.Recording;

import java.util.ArrayList;

public class RecordinglistUtil {
    public static int existday(ArrayList<Recording> recordings,String day)
    {
        for (Recording recording :recordings )
        {
            if(recording.getDay().equals(day))return recordings.indexOf(recording);
        }
        return -1;
    }
    public static int existmonth(ArrayList<Recording> recordings,String month)
    {
        for (Recording recording :recordings )
        {
            if(recording.getMonth().equals(month))return recordings.indexOf(recording);
        }
        return -1;
    }

    public static Recording getMonthSum(ArrayList<Recording> recordings,String year,String month)
    {

        Recording recording=new Recording();
        float orderding=0;
        float menber=0;
        float dine=0;
        float takeaway=0;
        float income=0;
        float pay=0;
        float profit=0;
        recording.setMonth(month);
        recording.setYear(year);
        recording.setDate(year+month);
        for(Recording recording1:recordings)
        {
            orderding+=Float.parseFloat(recording1.getOrdering());
            menber+=Float.parseFloat(recording1.getMenber());
            dine+=Float.parseFloat(recording1.getDine());
            takeaway+=Float.parseFloat(recording1.getTakeaway());
            income+=Float.parseFloat(recording1.getIncome());
            pay+=Float.parseFloat(recording1.getPay());
            profit+=Float.parseFloat(recording1.getProfit());
        }
        recording.setOrdering(""+orderding);
        recording.setMenber(""+menber);
        recording.setDine(""+dine);
        recording.setTakeaway(""+takeaway);
        recording.setIncome(""+income);
        recording.setPay(""+pay);
        recording.setProfit(""+profit);
        return  recording;
    }
}
