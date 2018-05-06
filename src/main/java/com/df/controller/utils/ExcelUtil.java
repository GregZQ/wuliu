package com.df.controller.utils;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by Jone on 2018-04-08.
 */
public class ExcelUtil {

    public static  String path=null;
    //操作的excel
    private static XSSFWorkbook workbook = null;
    private static File file=null;
    //操作的sheet
    private static XSSFSheet sheet=null;
    //操作的文件路径
    private static String filePath;
    //操作的row
    private static XSSFRow row;
    static {
        path="E:\\ideaProject\\dfwuliu\\src\\main\\resources\\resultfile\\";
    }
    /**
     * 判断文件是否存在
     * @param filePath
     * @return
     */
    public static boolean fileExist(String filePath){
        boolean flag=false;
        file=new File(filePath);
        ExcelUtil.filePath=filePath;
        flag=file.exists();
        return flag;
    }

    /**
     * 操作文件之前先打开文件
     * @param filePath
     * @param sheetName
     * @throws Exception
     */
    public static final void  opneFileBySheet(String filePath,String sheetName) throws Exception {
        String realPath=ExcelUtil.path+filePath;
        if (!fileExist(realPath)){
            throw new Exception("文件不存在");
        }
        workbook=new XSSFWorkbook(new FileInputStream(realPath));
        sheet=workbook.getSheet(sheetName);
    }

    public static Sheet getSheet(){
        return ExcelUtil.sheet;
    }

    /**
     * 写入到excel中
     * @param rowNumber 哪一行
     * @param columnNumber 哪一列
     * @param value 写入value的值
     * @throws Exception
     */
    public static void writeToExcel(Integer rowNumber,Integer columnNumber,String value) throws Exception {
        if (workbook==null){
            throw new Exception("文件未打开");
        }
        XSSFRow row=sheet.getRow(rowNumber);
        row.getCell(columnNumber).setCellValue(value==null?"":value);
    }

    /**
     * 设置行高
     * @param rowNumber
     * @param lineHegiht
     */
    public static void  createRow(Integer rowNumber,Integer column,float lineHegiht){

        row=sheet.createRow(rowNumber);
        row.setHeightInPoints(lineHegiht);
        createCell(column);
    }

    private static void createCell(Integer column) {
        XSSFCell xssfCell= row.createCell(0);
        setCellStyle(xssfCell,true,"黑体");
        for (int i=1;i<column;i++){
           xssfCell= row.createCell(i);
            setCellStyle(xssfCell,false,"Tahoma");
        }
    }


    public static void setCellStyle(XSSFCell cell,Boolean flag,String fontStype){


        XSSFCellStyle cellStyle = workbook.createCellStyle(); // 单元格样式
        Font fontStyle = workbook.createFont(); // 字体样式
        fontStyle.setBold(flag); // 加粗
        fontStyle.setFontName(fontStype); // 字体
        fontStyle.setFontHeightInPoints((short) 12); // 大小
        // 将字体样式添加到单元格样式中
        cellStyle.setFont(fontStyle);
        // 边框，居中
        XSSFDataFormat xssfDataFormat=workbook.createDataFormat();
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

    public static void meage(int startRow,int endRow,int starCol,int endCol){
        CellRangeAddress cellRangeAddress=new CellRangeAddress(startRow,
                endRow,starCol,endCol);
        sheet.addMergedRegion(cellRangeAddress);
    }

    /**
     * 关闭文件，关闭之前将值写入到文件当中
     * @param fileOutputStream
     * @throws IOException
     */
    private static void writeToFile(FileOutputStream fileOutputStream)throws IOException {
        if (fileOutputStream!=null&&workbook!=null){
            workbook.write(fileOutputStream);
        }
    }
    public static void closeFile() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filePath);
        writeToFile(fileOut);
        workbook.close();
        fileOut.close();
        printFile();
        workbook=null;
        file=null;
        filePath=null;
        sheet=null;
    }

    private static void printFile(){
        ComThread.InitSTA();
        ActiveXComponent wd=null;
        try {
            wd = new ActiveXComponent("Excel.Application");

            Dispatch.put(wd, "Visible", new Variant(false));

            Dispatch workbooks = wd.getProperty("Workbooks").toDispatch();
            Dispatch excel = Dispatch.call(workbooks, "Open", filePath).toDispatch();
            String printName=null;
            if (filePath.contains("ticket")) {
                printName="XP-80C";
            }else{
                printName="Hewlett-Packard HP LaserJet M1005";
            }
            //设置打印属性并打印
            Dispatch.callN(excel, "PrintOut", new Object[]{Variant.VT_MISSING, Variant.VT_MISSING, new Integer(1),//new Integer(1)，设置打印的份数
                    new Boolean(false),/*PRINT_NAME*/printName, new Boolean(true), Variant.VT_MISSING, ""});
            //关闭文档
            Dispatch.call(excel, "Close", new Variant(false));
        }catch (Exception e){
            e.getMessage();
        }finally {
            wd.invoke("Quit", new Variant[] {});
            ComThread.Release();
        }
    }

    public static void copyRow(XSSFWorkbook workbook,String sheetName,int before,int to,float height,int nums){
        XSSFSheet temp=workbook.getSheet(sheetName);
        sheet.createRow(to);
        copyRow(temp.getRow(before),sheet.getRow(to),height,nums);
    }
    public static void copyRow(XSSFRow fromRow,XSSFRow toRow,float height,int nums){
        toRow.setHeightInPoints(height);
        for (Iterator cellIt = fromRow.cellIterator(); cellIt.hasNext();) {
            XSSFCell tmpCell = (XSSFCell) cellIt.next();
            XSSFCell newCell = toRow.createCell(tmpCell.getColumnIndex());
            copyCell(workbook,tmpCell, newCell);
        }
        /*
            * 以下的内容是调整单元格式的，只针对当前定制好的表格有用
            * */
        int start=toRow.getRowNum();
        switch (nums){
            case 0:
                meage(start,start,0,1);
                meage(start,start,3,4);
                meage(start,start,5,6);
                meage(start,start,7,8);
                meage(start,start,9,11);
                meage(start,start,13,15);
                break;
            case 1:
                meage(start,start,0,1);
                meage(start,start,3,4);
                meage(start,start,5,7);
                meage(start,start,9,11);
                meage(start,start,13,15);
                break;
            case 2:
                meage(start,start,0,1);
                meage(start,start,2,4);
                meage(start,start,5,6);
                meage(start,start,7,8);
                meage(start,start,10,11);
                meage(start,start,13,15);
                meage(start-2,start,12,12);
                break;
        }
        nums++;
    }
    public static void copyCellStyle(XSSFCellStyle fromStyle,
                                     XSSFCellStyle toStyle) {
            toStyle.cloneStyleFrom(fromStyle);

    }

        public static void copyCell(XSSFWorkbook wb,XSSFCell srcCell, XSSFCell distCell) {
        XSSFCellStyle newstyle=wb.createCellStyle();
        copyCellStyle(srcCell.getCellStyle(), newstyle);
        //样式
        distCell.setCellStyle(newstyle);
        //评论
        if (srcCell.getStringCellValue() != null) {
            distCell.setCellValue(srcCell.getStringCellValue());
        }
    }

    public static void copyFile(String srcFile,String desFile) throws IOException {
        srcFile=ExcelUtil.path+srcFile;
        File file=new File(srcFile);

        FileInputStream inputStream=new FileInputStream(file);

        desFile=ExcelUtil.path+desFile;
        File toFile=new File(desFile);
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

    public static void deleteFile(String fileName){
        fileName=ExcelUtil.path+fileName;
        File file=new File(fileName);
        file.delete();
    }

    public static void createFile(StringBuilder tempFileName) {

        tempFileName.append(UUID.randomUUID().
                    toString().replaceAll("-",""));
        tempFileName.append(".xlsx");
    }
}
