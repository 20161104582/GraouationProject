package com.web.sys.utils;

import org.apache.commons.io.FileExistsException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Excel {

    public static HSSFCellStyle createCellStyle(@NotNull HSSFWorkbook workbook){

        HSSFCellStyle style = workbook.createCellStyle();

        //�������������ĸ��߿���
        style.setBorderTop(HSSFBorderFormatting.BORDER_THIN);
        style.setBorderBottom(HSSFBorderFormatting.BORDER_THIN);
        style.setBorderLeft(HSSFBorderFormatting.BORDER_THIN);
        style.setBorderRight(HSSFBorderFormatting.BORDER_THIN);

        //�������������ĸ��߿���ɫ
//        style.setTopBorderColor(HSSFColor.RED.index);
//        style.setBottomBorderColor(HSSFColor.RED.index);
//        style.setLeftBorderColor(HSSFColor.RED.index);
//        style.setRightBorderColor(HSSFColor.RED.index);

        //���õ�Ԫ�񱳾�ɫ
//        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //���������ʽ
        HSSFFont font = workbook.createFont();
        font.setFontName("����");
        font.setFontHeightInPoints((short)9);
        font.setFontHeight((short) 300);
//        font.setColor(HSSFColor.YELLOW.index);
        font.setBoldweight(font.BOLDWEIGHT_BOLD);
//        font.setItalic(true);
//        font.setStrikeout(true);
//        font.setUnderline((byte)1);
        //�������ʽ���õ�HSSFCellStyle��
        style.setFont(font);

        // ����������뷽ʽ
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//ˮƽ����
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//��ֱ����

        return style;

    }

    public static boolean createExcel(String fileName, Object[][] data)throws FileExistsException {return createExcel(fileName,data,null);}

    public static boolean createExcel(String fileName, Object[][] data,String sheetName)throws  FileExistsException { return createExcel(fileName, data, sheetName,true); }

    public static boolean createExcel(String fileName,Object[][] data,String sheetName,boolean overwrite)throws FileExistsException{
        //����excel�ļ�
        if(FileUtil.fileExist(fileName)){
            if(overwrite){
                FileUtil.deleteFile(fileName);
            }else{
                throw new FileExistsException("�ļ��Ѵ���");
            }
        }
        if(data == null || data.length < 1) return false;
        //����excel������
        HSSFWorkbook workbook=new HSSFWorkbook();
        //����������sheet
        HSSFSheet sheet = null;
        if(T.isNullOrWhite(sheetName)){
            sheet = workbook.createSheet();
        }else{
            sheet = workbook.createSheet(sheetName);
        }
        HSSFCellStyle style = createCellStyle(workbook);
        double[] colWidths = new double[data[0].length];
        //д������
        for (int i=0;i<data.length;i++){
            HSSFRow nrow=sheet.createRow(i);
            nrow.setHeight((short)(15.625*25));
//            nrow.setHeightInPoints((float)40);
            for(int j =0;j<data[i].length;j++){
                String v = "";
                if(data[i][j] != null){
                    v = data[i][j].toString();
                }
                double len =0;
                for(String s:v.split("")){
                    if(T.isChinese(s)) len += 2.1;
                    else len += 1;
                }
                colWidths[j] = Math.max(colWidths[j],len);
                HSSFCell ncell=nrow.createCell(j);
                ncell.setCellValue(v==null?"":v.toString());
                ncell.setCellStyle(style);
            }
        }
        //�����п�
        for(int i=0;i<colWidths.length;i++){
            sheet.setColumnWidth(i, Math.max((int)colWidths[i],4)*450);
        }
        File file=new File(fileName);
        try {
            file.createNewFile();
            FileOutputStream stream= FileUtil.openOutputStream(file);
            workbook.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static String[][] readExcel(String fileName,int ignoreRows)throws Exception{
        if(!FileUtil.fileExist(fileName)){
            throw new Exception("file not exists.");
        }
        File file = new File(fileName);
        List<String[]> result = new ArrayList<String[]>();

        int rowSize = 0;

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

        // ��HSSFWorkbook
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFCell cell = null;

        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            HSSFSheet st = wb.getSheetAt(sheetIndex);

            // ��һ��Ϊ���⣬��ȡ
            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
                HSSFRow row = st.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                int tempRowSize = row.getLastCellNum() + 1;
                if (tempRowSize > rowSize) {
                    rowSize = tempRowSize;
                }

                String[] values = new String[rowSize];
                Arrays.fill(values, "");
                boolean hasValue = false;

                for (int columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                    String value = "";
                    cell = row.getCell(columnIndex);
                    if (cell != null) {
                        // ע�⣺һ��Ҫ��������������ܻ��������

//                        cell.setEncoding(HSSFCell.ENCODING_UTF_16);

                        switch (cell.getCellType()) {

                            case HSSFCell.CELL_TYPE_STRING:
                                value = cell.getStringCellValue();
                                break;

                            case HSSFCell.CELL_TYPE_NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    Date date = cell.getDateCellValue();
                                    if (date != null) {
                                        value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                                .format(date);
                                    } else {
                                        value = "";
                                    }
                                } else {
                                    value = new DecimalFormat("0").format(cell
                                            .getNumericCellValue());
                                }
                                break;

                            case HSSFCell.CELL_TYPE_FORMULA:
                                // ����ʱ���Ϊ��ʽ���ɵ���������ֵ
                                if (!cell.getStringCellValue().equals("")) {
                                    value = cell.getStringCellValue();
                                } else {
                                    value = cell.getNumericCellValue() + "";
                                }
                                break;

                            case HSSFCell.CELL_TYPE_BLANK:
                                break;

                            case HSSFCell.CELL_TYPE_ERROR:
                                value = "";
                                break;

                            case HSSFCell.CELL_TYPE_BOOLEAN:
                                value = (cell.getBooleanCellValue() == true ? "Y"
                                        : "N");
                                break;

                            default:
                                value = "";
                        }

                    }

                    if (columnIndex == 0 && value.trim().equals("")) {
//                        break;
                    }

                    values[columnIndex] = T.rightTrim(value);
                    hasValue = true;
                    System.out.print(value + "     ");
                }
                T.print();
                if (hasValue) {
                    result.add(values);
                }
            }
        }

        in.close();

        String[][] returnArray = new String[result.size()][rowSize];

        for (int i = 0; i < returnArray.length; i++) {

            returnArray[i] = (String[]) result.get(i);

        }

        return returnArray;
    }

    public static void main(String[] args) throws Exception,IOException {
        Object[][] data = {
                {"URL","������TLKSDJF","������123","1","123","123456789887654","DFSDFDFSFSDF","123","124567893","123","123"},
                {"URL","������TLKSDJF","������123","1","123","123456789887654","DFSDFDFSFSDF","123","123","123","123"},
                {"URL","������TLKSDJF","������123","1","123","123456789887654","DFSDFDFSFSDF","123","123","123","123"},
                {"URL","������TLKSDJF","������123","1","123","123456789887654","DFSDFDFSFSDF","123","123","123","123"},
                {"URL","������TLKSDJF","������123","1","123","123456789887654","DFSDFDFSFSDF","123","123","123","123"},
        };
//        createExcel("123.xls",data,"����");
        // T.print("123");
        String[][] dread = readExcel("123.xls",0);
        for(Object[] item :dread){
            // T.print(Arrays.toString(item));
        }
        // T.print("123");
    }
}