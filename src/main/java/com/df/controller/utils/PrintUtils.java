package com.df.controller.utils;

import com.df.domain.Detail;
import com.df.domain.SendList;
import com.df.domain.Ticket;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.UUID;

/**
 * Created by Jone on 2018-04-08.
 */
public class PrintUtils {

    public static void printOpenTicket(Ticket ticket,String sheetName) throws Exception {
        ExcelUtil.opneFileBySheet(TicketProperties.PATH,
                sheetName);

        ExcelUtil.writeToExcel(TicketProperties.TIME_LINE, TicketProperties.TIME,ticket.getDateValue());

        ExcelUtil.writeToExcel(TicketProperties.GOODS_ID_LINE, TicketProperties.GOODS_ID,ticket.getGoodId());

        ExcelUtil.writeToExcel(TicketProperties.FROM_NAME_LINE, TicketProperties.FROM_NAME,ticket.getFromName());

        ExcelUtil.writeToExcel(TicketProperties.FROM_PHONE_LINE, TicketProperties.FROM_PHONE,ticket.getFromName());

        ExcelUtil.writeToExcel(TicketProperties.FROM_PLACE_LINE, TicketProperties.FROM_PLACE,ticket.getFromPlace());

        ExcelUtil.writeToExcel(TicketProperties.TO_NAME_LINE, TicketProperties.TO_NAME,ticket.getToName());

        ExcelUtil.writeToExcel(TicketProperties.TO_LACE_LINE, TicketProperties.TO_PLACE,ticket.getToPlace());

        ExcelUtil.writeToExcel(TicketProperties.TO_PHONE_LINE, TicketProperties.TO_PHONE,ticket.getToPhone());

        ExcelUtil.writeToExcel(TicketProperties.GOODS_NAME_LINE, TicketProperties.GOODS_NAME,ticket.getGoodsName());

        ExcelUtil.writeToExcel(TicketProperties.GOODS_COUNT_LINE, TicketProperties.GOODS_COUNT,ticket.getGoodsCount()==null?"":ticket.getGoodsCount().toString());

        ExcelUtil.writeToExcel(TicketProperties.GOODS_CUBE_LINE, TicketProperties.GOODS_CUBE,ticket.getGoodsCube()==null?"":ticket.getGoodsCube().toString());

        ExcelUtil.writeToExcel(TicketProperties.GOODS_WEIGHT_LINE, TicketProperties.GOODS_WEIGHT,ticket.getGoodsWeight()==null?"":ticket.getGoodsWeight().toString());

        ExcelUtil.writeToExcel(TicketProperties.GOODS_UNITPRICE_LINE, TicketProperties.GOODS_UNITPRICE,ticket.getGoodsUnitprice()==null?"":ticket.getGoodsUnitprice().toString());

        ExcelUtil.writeToExcel(TicketProperties.SEND_TYPE_LINE, TicketProperties.SEND_TYPE,ticket.getSendType()==1?"送货":"自提");

        ExcelUtil.writeToExcel(TicketProperties.WRITE_BACK_LINE, TicketProperties.WRITE_BACK,ticket.getWriteBack()==1?"是":"");

        ExcelUtil.writeToExcel(TicketProperties.PAY_TYPE_LINE, TicketProperties.PAY_TYPE,ticket.getPayType()==1?"已付":(ticket.getPayType()==2?"提付":"回付"));

        ExcelUtil.writeToExcel(TicketProperties.PAY_COUNT_LINE, TicketProperties.PAY_COUNT,ticket.getPayCount()==null?"":ticket.getPayCount().toString());

        ExcelUtil.writeToExcel(TicketProperties.COLLECTION_LINE, TicketProperties.COLLECTION_BIG,ticket.getCollectionValue());

        ExcelUtil.writeToExcel(TicketProperties.COLLECTION_LINE, TicketProperties.COLLECTION_SMALL,ticket.getCollection()==null?"":ticket.getCollection().toString());

        ExcelUtil.writeToExcel(TicketProperties.CONSIGNEE_LINE, TicketProperties.CONSIGNEE,ticket.getConsignee());

        ExcelUtil.writeToExcel(TicketProperties.CONSIGNEE_PHONE_LINE, TicketProperties.CONSIGNEE_PHONE,ticket.getConsigneePhone());


        ExcelUtil.closeFile();

    }

    public static void printSendList(SendList sendList,List<Detail> list, String sheetName) throws Exception {
        int length=list.size();
        int count=0;
        int pages=0;

        StringBuilder tempFileName=new StringBuilder();
        //默认每行最多35个
        for (int i=0;i<length;i++){
            if (count==DetailListProperties.PER_NUMS){
                ExcelUtil.closeFile();
                ExcelUtil.deleteFile(tempFileName.toString());
                tempFileName.delete(0,tempFileName.length());
                count=0;
            }
            if (count==0){
                pages++;
                ExcelUtil.createFile(tempFileName);
                ExcelUtil.copyFile(DetailListProperties.PATH,tempFileName.toString());
                ExcelUtil.opneFileBySheet(tempFileName.toString(),sheetName);
                ExcelUtil.writeToExcel(DetailListProperties.CARID_LINE,DetailListProperties.CARID,sendList.getCarid());
                ExcelUtil.writeToExcel(DetailListProperties.BIG_PLACE_LINE,DetailListProperties.BIG_PLACE,"聊城---"+sendList.getPlace()+"货物运输清单");
                ExcelUtil.writeToExcel(DetailListProperties.LOAD_TIME_LINE,DetailListProperties.LOAD_TIME,sendList.getLoadTimeValue());
                ExcelUtil.writeToExcel(DetailListProperties.PAGELINE,DetailListProperties.PAGE,"第"+pages+"页");
            }
            Detail temp=list.get(i);
            ExcelUtil.createRow(5+count,DetailListProperties.COLUMN_LINE, (float) 35.25);
            ExcelUtil.writeToExcel(5+count,DetailListProperties.NUM,i+1+"");
            ExcelUtil.writeToExcel(5+count,DetailListProperties.GOOD_ID,temp.getGoodId());
            ExcelUtil.writeToExcel(5+count,DetailListProperties.GOOD_NAME,temp.getGoodsName());
            ExcelUtil.writeToExcel(5+count,DetailListProperties.GOOD_COUNT,
                                    temp.getGoodsCount()==null?"":temp.getGoodsCount().toString());
            ExcelUtil.writeToExcel(5+count,DetailListProperties.GOOD_WEIGHT,
                                    temp.getGoodsWeight()==null?"":temp.getGoodsWeight().toString());
            ExcelUtil.writeToExcel(5+count,DetailListProperties.GOOD_CUBE,
                                    temp.getGoodsCube()==null?"":temp.getGoodsCube().toString());
            ExcelUtil.writeToExcel(5+count,DetailListProperties.PAY_COUNT,
                                    temp.getPayType()==2?(temp.getPayCount()==null?
                                            "":temp.getPayType().toString()
                                    ):"");
            ExcelUtil.writeToExcel(5+count,DetailListProperties.PAY_TYPE,
                                    temp.getPayType()==1?"已付":(temp.getPayType()==2?"提付":"回付"));
            ExcelUtil.writeToExcel(5+count,DetailListProperties.COLLECTION,
                                    temp.getCollection()==null?"":temp.getCollection().toString());
            ExcelUtil.writeToExcel(5+count,DetailListProperties.SEND_TYPE,
                                    temp.getSendType()==1?"送货":"自提");
            ExcelUtil.writeToExcel(5+count,DetailListProperties.WRITE_BACK,
                                    temp.getWriteBack()==null?"":"是");
            ExcelUtil.writeToExcel(5+count,DetailListProperties.TO_NAME,
                                    temp.getToName());
            ExcelUtil.writeToExcel(5+count,DetailListProperties.TO_PLACE,
                                    temp.getToPlace());
            ExcelUtil.writeToExcel(5+count,DetailListProperties.TO_PHONE,
                                    temp.getToPhone());

            count++;
        }
        /**
         * 打印结尾
         */

        FileInputStream fileInputStream=new FileInputStream(ExcelUtil.path+DetailListProperties.endPath);
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(fileInputStream);
        for (int i=0;i<3;i++){
            if (count==DetailListProperties.PER_NUMS){
                ExcelUtil.closeFile();
                ExcelUtil.deleteFile(tempFileName.toString());
                tempFileName.delete(0,tempFileName.length());
                count=0;
            }
            if (count==0){
                pages++;
                ExcelUtil.createFile(tempFileName);
                ExcelUtil.copyFile(DetailListProperties.PATH,tempFileName.toString());
                ExcelUtil.opneFileBySheet(tempFileName.toString(),sheetName);
                ExcelUtil.writeToExcel(DetailListProperties.CARID_LINE,DetailListProperties.CARID,sendList.getCarid());
                ExcelUtil.writeToExcel(DetailListProperties.BIG_PLACE_LINE,DetailListProperties.BIG_PLACE,"聊城---"+sendList.getPlace()+"货物运输清单");
                ExcelUtil.writeToExcel(DetailListProperties.LOAD_TIME_LINE,DetailListProperties.LOAD_TIME,sendList.getLoadTimeValue());
                ExcelUtil.writeToExcel(DetailListProperties.PAGELINE,DetailListProperties.PAGE,"第"+pages+"页");
            }
            ExcelUtil.copyRow(xssfWorkbook,"Sheet1",i,count+5,(float)35.25,i);
            switch (i){
                case 0:
                    ExcelUtil.writeToExcel(count+5,DetailListProperties.PAGE_COUNT,
                                        sendList.getTotalPages()+"");
                    ExcelUtil.writeToExcel(count+5,DetailListProperties.MONEY_HE,
                                            sendList.getMoneysHe()==null?"":sendList.getMoneysHe().toString());

                    ExcelUtil.writeToExcel(count+5,DetailListProperties.MONEYS_ME,
                                            sendList.getMoneysMe()==null?"":sendList.getMoneysMe().toString());
                    break;
                case 2:
                    ExcelUtil.writeToExcel(count+5,DetailListProperties.XIEHUO,sendList.getPutPlace());
                    ExcelUtil.writeToExcel(count+5,DetailListProperties.LINKPHONE,sendList.getLinkPhone());
                    break;
            }
            count++;
        }
        fileInputStream.close();
        xssfWorkbook.close();
        if (count!=0) {
            ExcelUtil.closeFile();
            ExcelUtil.deleteFile(tempFileName.toString());
        }
    }

}
