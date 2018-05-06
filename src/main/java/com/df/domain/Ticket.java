package com.df.domain;

import com.df.controller.utils.TicketProperties;
import com.df.controller.utils.TimeTransfer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jone on 2018-04-05.
 */
public class Ticket {

    private Integer id;

    private String dateValue;

    private Date date;

    private Date sendTime;
    private String goodId;

    private String fromName;

    private String fromPlace;

    private String fromPhone;

    private String toName;

    private String toPlace;

    private String toPhone;

    private String goodsName;


    private BigDecimal goodsCount;

    private BigDecimal goodsCube;

    private BigDecimal goodsWeight;

    private BigDecimal goodsUnitprice;

    private Integer sendType;

    private Integer writeBack;

    private Integer payType;

    private BigDecimal payCount;

    private BigDecimal collection;

    private String collectionValue;

    private String consignee;

    private String consigneePhone;

    private Integer flag;

    private Date toTime;
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateValue() {

        return dateValue;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        String dateValue=TimeTransfer.dateToString(date,"yyyy-MM-dd");
        if (this.dateValue==null)
        this.setDateValue(dateValue);
    }
    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
        try {
            Date date=TimeTransfer.stringToDate(this.dateValue, "yyyy-MM-dd");
            if (this.date==null)
            this.setDate(date);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getFromPhone() {
        return fromPhone;
    }

    public void setFromPhone(String fromPhone) {
        this.fromPhone = fromPhone;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(BigDecimal goodsCount) {
        this.goodsCount = goodsCount;
    }

    public BigDecimal getGoodsCube() {
        return goodsCube;
    }

    public void setGoodsCube(BigDecimal goodsCube) {
        this.goodsCube = goodsCube;
    }

    public BigDecimal getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(BigDecimal goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public BigDecimal getGoodsUnitprice() {
        return goodsUnitprice;
    }

    public void setGoodsUnitprice(BigDecimal goodsUnitprice) {
        this.goodsUnitprice = goodsUnitprice;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public Integer getWriteBack() {
        return writeBack;
    }

    public void setWriteBack(Integer writeBack) {
        this.writeBack = writeBack;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getPayCount() {
        return payCount;
    }

    public void setPayCount(BigDecimal payCount) {
        this.payCount = payCount;
    }

    public BigDecimal getCollection() {
        return collection;
    }

    public void setCollection(BigDecimal collection) {
        this.collection = collection;
        if (this.collection!=null) {
            String result = toBigValue(collection.intValue(), 0, 0, new StringBuilder());
            this.setCollectionValue(result);
        }

    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getCollectionValue() {
        return collectionValue;
    }

    public void setCollectionValue(String collectionValue) {
        this.collectionValue = collectionValue;
    }

    /**
     *
     * @param value  当前值
     * @param tag   当前位数
     * @param flag  是否显示数字，0表示不显示
     * @param stringBuilder
     * @return
     */
    private String toBigValue(Integer value,Integer tag,Integer flag,StringBuilder stringBuilder) {
        if (value==0){
            stringBuilder.append("元");
            return stringBuilder.toString();
        }
        Integer end=value%10;

        if (end>0||flag==1){
            if (tag>=5){
                stringBuilder.insert(0, TicketProperties.jin[tag-4]);
            }
            else{
                stringBuilder.insert(0, TicketProperties.jin[tag]+"   ");//表示进制
            }
            stringBuilder.insert(0, TicketProperties.pu[end]);//表示位数
            flag=1;
        }
        value/=10;
        tag++;
        return toBigValue(value,tag,flag,stringBuilder);
    }

    public static void main(String args[]){
        Ticket ticket=new Ticket();

        ticket.setCollection(new BigDecimal(25602.00));

        System.out.println(ticket.getCollectionValue());
    }
}
