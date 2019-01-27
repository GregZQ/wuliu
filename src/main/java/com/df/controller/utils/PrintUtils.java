package com.df.controller.utils;

import com.df.domain.Detail;
import com.df.domain.Sends;
import com.df.domain.Ticket;
import com.df.domain.User;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 定制化的文件excel文件导出
 * Created by Jone on 2018-04-08.
 */
public class PrintUtils {
    /**
     * 生成专用票据
     * @param srcFilePath 源文件路径
     * @param filePath
     * @param sheetName
     * @param user
     * @throws Exception
     */
    public static void generateTicketFile(String srcFilePath,String filePath,String sheetName,User user) throws Exception {
        //复制ticket
        String realTicketPath  = FileUtil.getFilePath(filePath,user.getTicket());
        FileUtil.copyFile(srcFilePath,realTicketPath);
        XSSFSheet xssfSheet = ExcelUtil.opneFileBySheet(realTicketPath,sheetName);
        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.COMPANY_LINE, TicketProperties.COMPANY,
                user.getCompany()+"专用票据");

        ExcelUtil.closeSheet(xssfSheet,realTicketPath);
    }

    /**
     * 生成专用fuben
     * @param srcFilePath
     * @param filePath
     * @param sheetName
     * @param user
     * @throws Exception
     */
    public static void generateFubenFile(String srcFilePath,String filePath,String sheetName,User user) throws Exception {

        String realFubenPath  = FileUtil.getFilePath(filePath,user.getFuben());
        FileUtil.copyFile(srcFilePath,realFubenPath);

        XSSFSheet xssfSheet = ExcelUtil.opneFileBySheet(realFubenPath,sheetName);
        ExcelUtil.writeToExcel(xssfSheet,DetailListProperties.COMPANY_LINE, DetailListProperties.COMPANY,
                user.getCompany()+"专用票据");

        ExcelUtil.closeSheet(xssfSheet,realFubenPath);
    }

    /**
     * 生成专用end
     * @param srcFilePath
     * @param filePath
     * @param sheetName
     * @param user
     * @throws IOException
     */
    public static void generateEndFile(String srcFilePath,String filePath,String sheetName,User user) throws IOException {
        String realEndPath = FileUtil.getFilePath(filePath,user.getEnd());
        FileUtil.copyFile(srcFilePath,realEndPath);
    }

    public static void saveOpenTicket(String filePath,Ticket ticket,String sheetName) throws Exception {
        XSSFSheet xssfSheet = ExcelUtil.opneFileBySheet(filePath,sheetName);

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.TIME_LINE, TicketProperties.TIME,ticket.getDateValue());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.GOODS_ID_LINE, TicketProperties.GOODS_ID,ticket.getGoodId());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.FROM_NAME_LINE, TicketProperties.FROM_NAME,ticket.getFromName());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.FROM_PHONE_LINE, TicketProperties.FROM_PHONE,ticket.getFromName());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.FROM_PLACE_LINE, TicketProperties.FROM_PLACE,ticket.getFromPlace());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.TO_NAME_LINE, TicketProperties.TO_NAME,ticket.getToName());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.TO_LACE_LINE, TicketProperties.TO_PLACE,ticket.getToPlace());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.TO_PHONE_LINE, TicketProperties.TO_PHONE,ticket.getToPhone());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.GOODS_NAME_LINE, TicketProperties.GOODS_NAME,ticket.getGoodsName());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.GOODS_COUNT_LINE, TicketProperties.GOODS_COUNT,ticket.getGoodsCount()==null?"":ticket.getGoodsCount().toString());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.GOODS_CUBE_LINE, TicketProperties.GOODS_CUBE,ticket.getGoodsCube()==null?"":ticket.getGoodsCube().toString());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.GOODS_WEIGHT_LINE, TicketProperties.GOODS_WEIGHT,ticket.getGoodsWeight()==null?"":ticket.getGoodsWeight().toString());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.GOODS_UNITPRICE_LINE, TicketProperties.GOODS_UNITPRICE,ticket.getGoodsUnitprice()==null?"":ticket.getGoodsUnitprice().toString());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.SEND_TYPE_LINE, TicketProperties.SEND_TYPE,ticket.getSendType()==1?"送货":"自提");

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.WRITE_BACK_LINE, TicketProperties.WRITE_BACK,ticket.getWriteBack()==1?"是":"");

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.PAY_TYPE_LINE, TicketProperties.PAY_TYPE,ticket.getPayType()==1?"已付":(ticket.getPayType()==2?"提付":"回付"));

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.PAY_COUNT_LINE, TicketProperties.PAY_COUNT,ticket.getPayCount()==null?"":ticket.getPayCount().toString());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.COLLECTION_LINE, TicketProperties.COLLECTION_BIG,ticket.getCollectionValue());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.COLLECTION_LINE, TicketProperties.COLLECTION_SMALL,ticket.getCollection()==null?"":ticket.getCollection().toString());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.CONSIGNEE_LINE, TicketProperties.CONSIGNEE,ticket.getConsignee());

        ExcelUtil.writeToExcel(xssfSheet,TicketProperties.CONSIGNEE_PHONE_LINE, TicketProperties.CONSIGNEE_PHONE,ticket.getConsigneePhone());

        ExcelUtil.closeSheet(xssfSheet,filePath);
    }

    public static void saveSendList(String filePath, String sheetName,
                                    Sends sends, List<Detail> list, User user) throws Exception {
        int length = list.size();
        XSSFSheet xssfSheet = ExcelUtil.opneFileBySheet(filePath,sheetName);

        ExcelUtil.writeToExcel(xssfSheet, DetailListProperties.COMPANY_LINE,DetailListProperties.COMPANY, user.getCompany()+"运输清单");
        ExcelUtil.writeToExcel(xssfSheet, DetailListProperties.CARID_LINE, DetailListProperties.CARID, sends.getCarid());
        ExcelUtil.writeToExcel(xssfSheet, DetailListProperties.BIG_PLACE_LINE, DetailListProperties.BIG_PLACE, "聊城---" + sends.getPlace() + "货物运输清单");
        ExcelUtil.writeToExcel(xssfSheet, DetailListProperties.LOAD_TIME_LINE, DetailListProperties.LOAD_TIME, sends.getLoadTimeValue());
        ExcelUtil.writeToExcel(xssfSheet, DetailListProperties.PAGELINE, DetailListProperties.PAGE, "第1页");

        for (int i=0;i<length ; i++){
            Detail temp =list.get(i);
            ExcelUtil.createRow(xssfSheet,5+i,DetailListProperties.COLUMN_LINE, (float) 35.25);
            ExcelUtil.writeToExcel(xssfSheet,5+i,DetailListProperties.NUM,i+1+"");
            ExcelUtil.writeToExcel(xssfSheet,5+i,DetailListProperties.GOOD_ID,temp.getGoodId());
            ExcelUtil.writeToExcel(xssfSheet,5+i,DetailListProperties.GOOD_NAME,temp.getGoodsName());
            ExcelUtil.writeToExcel(xssfSheet,5+i,DetailListProperties.GOOD_COUNT,
                    temp.getGoodsCount()==null?"":temp.getGoodsCount().toString());
            ExcelUtil.writeToExcel(xssfSheet,5+i,DetailListProperties.GOOD_WEIGHT,
                    temp.getGoodsWeight()==null?"":temp.getGoodsWeight().toString());
            ExcelUtil.writeToExcel(xssfSheet,5+i,DetailListProperties.GOOD_CUBE,
                    temp.getGoodsCube()==null?"":temp.getGoodsCube().toString());
            ExcelUtil.writeToExcel(xssfSheet,5+i,DetailListProperties.PAY_COUNT,
                    temp.getPayType()==2?(temp.getPayCount()==null?
                            "":temp.getPayType().toString()
                    ):"");
            ExcelUtil.writeToExcel(xssfSheet,5+i,DetailListProperties.PAY_TYPE,
                    temp.getPayType()==1?"已付":(temp.getPayType()==2?"提付":"回付"));
            ExcelUtil.writeToExcel(xssfSheet,5+i,DetailListProperties.COLLECTION,
                    temp.getCollection()==null?"":temp.getCollection().toString());
            ExcelUtil.writeToExcel(xssfSheet,5+i,DetailListProperties.SEND_TYPE,
                    temp.getSendType()==1?"送货":"自提");
            ExcelUtil.writeToExcel(xssfSheet,5+i,DetailListProperties.WRITE_BACK,
                    temp.getWriteBack()==null?"":"是");
            ExcelUtil.writeToExcel(xssfSheet,5+i,DetailListProperties.TO_NAME,
                    temp.getToName());
            ExcelUtil.writeToExcel(xssfSheet,5+i,DetailListProperties.TO_PLACE,
                    temp.getToPlace());
            ExcelUtil.writeToExcel(xssfSheet,5+i,DetailListProperties.TO_PHONE,
                    temp.getToPhone());
        }
        ExcelUtil.closeSheet(xssfSheet,filePath);

    }

    /**
     * 下载文件
     * @param response
     * @param filePath
     * @throws IOException
     */
    public static void print(HttpServletResponse response,String filePath) throws IOException {

        File file = new File(filePath);

        try(FileInputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream()) {

            response.setContentType("application/x-download;charset=utf-8");
            response.setCharacterEncoding("UTF-8");

            String fileName = file.getName();

            response.addHeader("Content-Disposition", "attachment; filename*=utf-8''" + fileName);
            FileCopyUtils.copy(inputStream,outputStream);
        }catch (IOException e) {
            throw e;
        }
    }
}
