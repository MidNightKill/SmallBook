package com.midgroup.smallbook.pojo;

import lombok.Data;
/**
 * Author:ZZY
 * Date:2019-7-21
 * Target:账目记录Bean
 */
@Data
public class Recording {

    private String date;
    private String ordering;
    private String menber;
    private String dine;
    private String takeaway;
    private String income;
    private String pay;
    private String profit;
    private String year;
    private String month;
    private String day;
    private String olddate;


    public void FormatData()
    {
        income=""+(Float.parseFloat(ordering)+Float.parseFloat(menber)+Float.parseFloat(dine)+Float.parseFloat(takeaway));
        profit=""+(Float.parseFloat(income)-Float.parseFloat(pay));
        year=date.substring(0,4);
        month=date.substring(4,6);
        day=date.substring(6);
    }

    public void FormatNULL(String date)
    {
        this.date=date;
        ordering="0";
        menber="0";
        dine="0";
        takeaway="0";
        pay="0";
        FormatData();
        this.date=day;
    }
    @Override
    public String toString()
    {
//        FormatData();
        return "日期："+date+"\n"
                +"预定："+ordering+"\n"
                +"会员："+menber+"\n"
                +"堂食："+dine+"\n"
                +"外供："+takeaway+"\n"
                +"总收入：:"+income+"\n"
                +"支出："+pay+"\n"
                +"利润："+profit+"\n"
                +"年份："+year+"\n"
                +"月份："+month+"\n"
                +"日："+day+"\n";
    }
}
