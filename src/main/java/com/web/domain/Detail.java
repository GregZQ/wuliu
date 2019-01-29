package com.web.domain;

import com.web.utils.TimeTransfer;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by Jone on 2018-04-06.
 */
public class Detail extends Ticket{
    private Date sendTime;
    private String sendTimeValue;

    public String getSendTimeValue() {
        return sendTimeValue;
    }

    public void setSendTimeValue(String sendTimeValue) {
        this.sendTimeValue = sendTimeValue;
        if (this.sendTime==null){
            try {
                this.sendTime=TimeTransfer.stringToDate(sendTimeValue,"yyyy-MM-dd");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public Date getSendTime() {

        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
        if (this.sendTimeValue==null){
            this.sendTimeValue= TimeTransfer.dateToString(sendTime,"yyyy-MM-dd");
        }
    }
}
