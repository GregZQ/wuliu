package com.web.utils;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by Jone on 2018-04-08.
 */
@Component
public class ExcelUtil {


    /**
     * 打开excel文件
     * 返回XSSFWorkbook
     * @param filePath  文件路径
     * @return
     * @throws Exception
     */
    public static final XSSFWorkbook openFileByWorkBook(String filePath) throws Exception {
        if (!FileUtil.fileExist(filePath)){
            throw new Exception("文件不存在");
        }
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(filePath)));

        return workbook;
    }

    /**
     * 打开excel文件
     * 返回XSSFSheet
     * @param filePath 文件路径
     * @param sheetName SheetName
     * @throws Exception
     */
    public static final XSSFSheet  opneFileBySheet(String filePath,String sheetName) throws Exception {
        XSSFWorkbook workbook=openFileByWorkBook(filePath);
        XSSFSheet sheet=workbook.getSheet(sheetName);
        return sheet;
    }

    /**
     *  向指定表格写入数据
     * @param sheet 哪个表
     * @param rowNumber 哪一行
     * @param columnNumber 哪一列
     * @param value 写入的值
     * @throws Exception
     */
    public static void writeToExcel(XSSFSheet sheet,
                                    Integer rowNumber,Integer columnNumber,String value) throws Exception {

        XSSFRow row=sheet.getRow(rowNumber);
        row.getCell(columnNumber).setCellValue(value==null?"":value);
    }

    /**
     * 创建一行表格（指定行高）
     * @param rowNumber
     * @param lineHegiht
     */
    public static XSSFRow  createRow(XSSFSheet xssfSheet,Integer rowNumber,Integer column,float lineHegiht){

        XSSFRow row=xssfSheet.createRow(rowNumber);
        XSSFWorkbook xssfWorkbook = xssfSheet.getWorkbook();
        row.setHeightInPoints(lineHegiht);
        createCell(xssfWorkbook,row,column);

        return row;
    }

    /**
     *  创建一行当中的表格
     * @param xssfWorkbook
     * @param xssfRow
     * @param column
     */
    private static void createCell(XSSFWorkbook xssfWorkbook,XSSFRow xssfRow,Integer column) {
        XSSFCell xssfCell= xssfRow.createCell(0);
        setCellStyle(xssfWorkbook,xssfCell,true,"黑体");
        for (int i=1;i<column;i++){
            xssfCell= xssfRow.createCell(i);
            setCellStyle(xssfWorkbook,xssfCell,false,"Tahoma");
        }
    }

    /**
     * 设置单元格样式
     * @param xssfWorkbook
     * @param cell
     * @param flag
     * @param fontStype
     */
    public static void setCellStyle(XSSFWorkbook xssfWorkbook,XSSFCell cell,Boolean flag,String fontStype){


        XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle(); // 单元格样式
        Font fontStyle = xssfWorkbook.createFont(); // 字体样式
        fontStyle.setBold(flag); // 加粗
        fontStyle.setFontName(fontStype); // 字体
        fontStyle.setFontHeightInPoints((short) 12); // 大小
        // 将字体样式添加到单元格样式中
        cellStyle.setFont(fontStyle);
        // 边框，居中
        XSSFDataFormat xssfDataFormat=xssfWorkbook.createDataFormat();
        //设置自动换行
        cellStyle.setWrapText(true);
        //全部设置为文本
        cellStyle.setDataFormat(xssfDataFormat.getFormat("@"));
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cell.setCellStyle(cellStyle);
    }

    /**
     * 单元格合并
     * @param xssfSheet
     * @param startRow 开始的行
     * @param endRow   结束的行
     * @param starCol  开始的列
     * @param endCol   结束的列
     */
    public static void meage(XSSFSheet xssfSheet,int startRow,int endRow,int starCol,int endCol){
        CellRangeAddress cellRangeAddress=new CellRangeAddress(startRow,
                endRow,starCol,endCol);
        xssfSheet.addMergedRegion(cellRangeAddress);
    }

    /**
     * 将excel写入到指定输出流当中
     * @param fileOutputStream
     * @throws IOException
     */
    private static void writeToFile(XSSFWorkbook xssfWorkbook,FileOutputStream fileOutputStream)throws IOException {
        if (fileOutputStream!=null&&xssfWorkbook!=null){
            xssfWorkbook.write(fileOutputStream);
        }
    }


    /**
     * 行复制
     * @param originPath
     * @param filePath
     * @param desSheetName
     * @param workbook
     * @param sheetName
     * @param before
     * @param to
     * @param height
     * @param nums
     * @throws Exception
     */
    public static void copyRow(XSSFWorkbook workbook,String sheetName,
                               int before,int to,float height,int nums) throws Exception {
        XSSFSheet temp=workbook.getSheet(sheetName);
        XSSFSheet sheet = null;//opneFileBySheet(originPath,filePath,desSheetName);
        sheet.createRow(to);

        copyRow(sheet,temp.getRow(before),sheet.getRow(to),height,nums);
    }
    public static void copyRow(XSSFSheet xssfSheet,XSSFRow fromRow,XSSFRow toRow,float height,int nums){
        toRow.setHeightInPoints(height);
        for (Iterator cellIt = fromRow.cellIterator(); cellIt.hasNext();) {
            XSSFCell tmpCell = (XSSFCell) cellIt.next();
            XSSFCell newCell = toRow.createCell(tmpCell.getColumnIndex());
            copyCell(xssfSheet,tmpCell, newCell);
        }
        /*
            * 以下的内容是调整单元格式的，只针对当前定制好的表格有用
            * */
        int start=toRow.getRowNum();
        switch (nums){
            case 0:
                meage(xssfSheet,start,start,0,1);
                meage(xssfSheet,start,start,3,4);
                meage(xssfSheet,start,start,5,6);
                meage(xssfSheet,start,start,7,8);
                meage(xssfSheet,start,start,9,11);
                meage(xssfSheet,start,start,13,15);
                break;
            case 1:
                meage(xssfSheet,start,start,0,1);
                meage(xssfSheet,start,start,3,4);
                meage(xssfSheet,start,start,5,7);
                meage(xssfSheet,start,start,9,11);
                meage(xssfSheet,start,start,13,15);
                break;
            case 2:
                meage(xssfSheet,start,start,0,1);
                meage(xssfSheet,start,start,2,4);
                meage(xssfSheet,start,start,5,6);
                meage(xssfSheet,start,start,7,8);
                meage(xssfSheet,start,start,10,11);
                meage(xssfSheet,start,start,13,15);
                meage(xssfSheet,start-2,start,12,12);
                break;
        }
        nums++;
    }

    /**
     * 复制单元格格式
     * @param fromStyle 被复制的单元格格式
     * @param toStyle   新的单元格格式
     */
    public static void copyCellStyle(XSSFCellStyle fromStyle,
                                     XSSFCellStyle toStyle) {
        toStyle.cloneStyleFrom(fromStyle);

    }

    /**
     * 复制单元格
     * @param sheet  最后需要的单元格
     * @param srcCell  源单元格
     * @param distCell 目的单元格
     */
    public static void copyCell(XSSFSheet sheet,XSSFCell srcCell, XSSFCell distCell) {

        XSSFWorkbook workbook = sheet.getWorkbook();
        XSSFCellStyle newstyle=workbook.createCellStyle();
        copyCellStyle(srcCell.getCellStyle(), newstyle);
        //样式
        distCell.setCellStyle(newstyle);
        //评论
        if (srcCell.getStringCellValue() != null) {
            distCell.setCellValue(srcCell.getStringCellValue());
        }
    }

    public static void copyFile(String originSrcFile,String srcFile,String originDesFile,String desFile) throws IOException {

        File src=new File(originSrcFile,srcFile);

        FileInputStream inputStream=new FileInputStream(src);

        File toFile=new File(originDesFile,desFile);
        FileOutputStream fileOutputStream=new FileOutputStream(toFile);
        byte[] bytes=new byte[1024];
        int length;
        while((length=inputStream.read(bytes))>0){
            if (length>0){
                fileOutputStream.write(bytes,0,length);
            }
        }
        inputStream.close();

        fileOutputStream.close();

    }

    public static void deleteFile(String originPath,String fileName){
        File file=new File(originPath,fileName);
        file.delete();
    }

    /**
     * 生成excel文件名称
     * @param tempFileName
     */
    public static void generateExcelName(StringBuilder tempFileName) {

        tempFileName.append(UUID.randomUUID().
                toString().replaceAll("-",""));
        tempFileName.append(".xlsx");
    }
    public static String generateExcelName(){
        String fileName = UUID.randomUUID().toString().replace("-","");
        fileName+=".xlsx";
        return fileName;
    }

    public static void closeSheet(XSSFSheet xssfSheet,String filePath) throws IOException {
        XSSFWorkbook xssfWorkbook = xssfSheet.getWorkbook();
        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
        xssfWorkbook.write(fileOutputStream);
        xssfWorkbook.close();
    }
}
