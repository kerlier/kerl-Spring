package com.fashion.spring.file;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfMain {

    public static BaseFont bfCN=null;
    public static Font titleFont=null;
    public static Font titleFont2=null;
    public static Font normalFont=null;
    public static Font boldFont=null;
    public static Font smallBoldFont=null;
    public static float[] width100={100f};
    public static float minHeight=30f;
    static {
        try {
            bfCN=BaseFont.createFont("STSong-Light","UniGB-UCS2-H", false);
            titleFont=new Font(bfCN,24f,Font.BOLD);
            titleFont2=new Font(bfCN,18f,Font.BOLD);
            normalFont=new Font(bfCN,12f);
            boldFont=new Font(bfCN,12f,Font.BOLD);
            smallBoldFont=new Font(bfCN,10f,Font.BOLD);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 获取标题
     * @param value  标题内容
     * @param font  字体样式
     * @return
     */
    public static PdfPTable getPdfTitle(String value, Font font){
        float[] width={100f};
        PdfPTable table=new PdfPTable(width);
        Paragraph content=new Paragraph(value,font);
        PdfPCell cellN = new PdfPCell(content);
        cellN.setPadding(10f);
        cellN.setBorder(0);
        cellN.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cellN.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cellN.setMinimumHeight(30f);
        table.addCell(cellN);
        return table;
    }


    /**
     *
     * 获取单行表格
     * @param widthArr 单行表格内的单元格宽度
     * @param values 单行表格内的单元格内容
     * @param font 字体样式
     * @param height 单元格最小高度
     * @return
     */
    public static PdfPTable getExplain(float[] widthArr,String[] values,Font font,float height){
        PdfPTable table=new PdfPTable(widthArr);
        for(int i=0;i<widthArr.length;i++){
            table.addCell(getPdfCellNoBorder(values[i], font,height));
        }
        return table;
    }
    /**
     * 普通单元格
     * @param value  单元格内容
     * @param font 字体
     * @param height 单元格最小高度
     * @return
     */
    public static PdfPCell getNormalPdfCell(String value,Font font,float height){
        Paragraph p=new Paragraph(value,font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        PdfPCell cellN = new PdfPCell(p);
        cellN.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cellN.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cellN.setMinimumHeight(height);
        return cellN;
    }

    /**
     * 普通单元格
     * @param value  单元格内容
     * @param font 字体
     * @param height 单元格最小高度
     * @return
     */
    public static PdfPCell getPdfCellNoBorder(String value,Font font,float height){
        Paragraph p=new Paragraph(value,font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        PdfPCell cellN = new PdfPCell(p);
        cellN.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cellN.setMinimumHeight(height);
        cellN.setBorder(0);
        return cellN;
    }

    /**
     * 没有上下边框的单元格
     * @param value
     * @param font
     * @return
     */
    public static PdfPCell getPdfCellNoTB(String value,Font font){
        Paragraph p=new Paragraph(value,font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        PdfPCell cellN = new PdfPCell(p);
        cellN.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cellN.setMinimumHeight(30f);
        cellN.setBorderWidthBottom(0);
        cellN.setBorderWidthTop(0);
        return cellN;
    }
    /**
     * 没有上边框的单元格
     * @param value
     * @param font
     * @return
     */
    public static PdfPCell getPdfCellNoT(String value,Font font){
        Paragraph p=new Paragraph(value,font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        PdfPCell cellN = new PdfPCell(p);
        cellN.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cellN.setMinimumHeight(30f);
        cellN.setBorderWidthTop(0);
        return cellN;
    }
    /**
     * 没有上边框的单元格
     * @param value
     * @param font
     * @return
     */
    public static PdfPCell getPdfCellNoB(String value,Font font){
        Paragraph p=new Paragraph(value,font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        PdfPCell cellN = new PdfPCell(p);
        cellN.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cellN.setMinimumHeight(30f);
        cellN.setBorderWidthBottom(0);
        return cellN;
    }

    /**
     * 单元格内增加表格
     * @param width
     * @param content
     * @param height
     * @param border
     * @return
     * @throws Exception
     */
    public static PdfPTable getCellTable(float[] width,String[] content,float height,int border)throws Exception{
        PdfPTable table=new PdfPTable(width);
        for(int i=0;i<width.length;i++){
            Paragraph p=new Paragraph(content[i],normalFont);
            PdfPCell c=new PdfPCell(p);
            c.setMinimumHeight(height);
            c.setBorder(border);
            table.addCell(c);
        }
        return table;
    }

    /**
     * 生成跨行跨列的表格
     * @param f
     * @param values
     * @param rows
     * @param cols
     * @return
     */
    public static PdfPTable getRowColTable(float[] f,String[][] values,int[][] rows,int[][] cols,float height){
        PdfPTable table=new PdfPTable(f);
        for(int i=0;i<values.length;i++){
            String[] v=values[i];
            int[] r=rows[i];
            int[] c=cols[i];
            for(int j=0;j<v.length;j++){
                PdfPCell cell = getNormalPdfCell(v[j],normalFont,height);
                if(r[j]>1){
                    cell.setRowspan(r[j]);
                }
                if(c[j]>1){
                    cell.setColspan(c[j]);
                }
                table.addCell(cell);
            }
        }
        return table;
    }

    public static void  getPdf(String path) throws IOException, DocumentException{

        File file = new File(path);
        file.createNewFile();
        Document document=new Document(PageSize.A4.rotate(),-60,-60,0,0);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        document.add(getPdfTitle("xxxxxxxxxx申请表",titleFont2));
        String[] e1={"登记日期:2019年06月20日","","登记编号:20190905000000000"};
        float[] e={245f,480f,180f};
        document.add(getExplain(e,e1,smallBoldFont,20f));

        //这边将表格一共分了12格，并给定每个单元格的比例大小，然后根据跨行和跨列来分配单元格大小
        float[] f1={20f,35f,90f,70f,70f,70f,85f,70f,70f,70f,85f,70f};

        //单元格的值
        String[] v1={"个人信息","姓名","陈xx","性别","男","出生日期","2019-06-20","身份证号","333333333333333333"};
        String[] v2={"年龄","20","政治面貌","党员","文化程度","本科","民族","汉","健康状况","良好"};
        String[] v3={"手机号码","15555555555","固定电话","15555555555",};
        String[] v4={"户籍住址","xx省xx市xx街道"};

        //单元格跨行
        int[] r1={4,1,1,1,1,1,1,1,1};
        int[] r2={1,1,1,1,1,1,1,1,1,1};
        int[] r3={1,1,1,1};
        int[] r4={1,1};
        //单元格跨列
        int[] c1={2,1,1,1,1,1,1,1,3};
        int[] c2={1,1,1,1,1,1,1,1,1,1};
        int[] c3={1,3,1,5};
        int[] c4={1,9};
        String[][] values4={v1,v2,v3,v4};
        int[][] rows4={r1,r2,r3,r4};
        int[][] cols4={c1,c2,c3,c4};
        //四行一起添加
        document.add(getRowColTable(f1,values4,rows4,cols4,20f));

        String[] v5={"其\n他\n信\n息","信息1","1111","1","2222","2","3333","3","4444","4","55","5"};
        String[] v6={"信息22","1111","1","2222","2"};
        String[] v7={"3333","33","44444","4"};
        String[] v8={"5555","5","6666","8","7777","8","8888","8"};
        int[] r5={4,1,1,1,1,1,1,1,1,1,1,1};
        int[] c5={1,1,1,1,1,1,1,1,1,1,1,1};
        int[] r6={3,1,1,1,1};
        int[] c6={1,1,3,3,3};
        int[] r7={1,1,1,1};
        int[] c7={1,3,3,3};
        int[] r8={1,1,1,1,1,1,1,1};
        int[] c8={1,1,1,1,1,2,1,2};
        String[][] values8={v5,v6,v7,v8};
        int[][] rows8={r5,r6,r7,r8};
        int[][] cols8={c5,c6,c7,c8};
        document.add(getRowColTable(f1,values8,rows8,cols8,20f));

        String[] v9={"其\n他\n信\n息\n2","信\n息\n21","21"};
        int[] r9={5,3,1};
        int[] c9={1,1,10};
        String[] v10={"11111111111","222222222222"};
        int[] r10={1,1};
        int[] c10={4,6};
        String[] v11={"","","","","","","","","",""};
        int[] r11={1,1,1,1,1,1,1,1,1,1};
        int[] c11={1,1,1,1,1,1,1,1,1,1};
        String[] v12={"信息\n44","3333"};
        int[] r12={2,1};
        int[] c12={1,10};
        String[] v13={"22222222","33333333333333"};
        int[] r13={1,1};
        int[] c13={4,6};
        String[][] values13={v9,v10,v11,v12,v13};
        int[][] rows13={r9,r10,r11,r12,r13};
        int[][] cols13={c9,c10,c11,c12,c13};
        document.add(getRowColTable(f1,values13,rows13,cols13,20f));

        String[] v14={"xx\nxx","xxxxxxx"};
        int[] r14={2,1};
        int[] c14={2,10};
        String[] v15={"xxxxxxx：","","xxxxxxx","xxxxxxx","xxxxxxx",""};
        int[] r15={1,1,1,1,1,1};
        int[] c15={2,2,2,1,1,2};
        String[] v16={"xx\nxx","xxxxxxx"};
        int[] r16={2,1};
        int[] c16={2,10};
        String[] v17={"xxxxxxx：","","xxxxxxx","xxxxxxx"};
        int[] r17={1,1,1,1};
        int[] c17={2,2,2,4};
        String[][] values17={v14,v15,v16,v17};
        int[][] rows17={r14,r15,r16,r17};
        int[][] cols17={c14,c15,c16,c17};
        document.add(getRowColTable(f1,values17,rows17,cols17,20f));


        String[] e2={"联系单位：xxxxxxxxxxx","","联系电话：xxxxxxxxxxxxx"};
        float[] e3={300f,350f,255f};
        document.add(getExplain(e3,e2,smallBoldFont,20f));
        document.close();

    }


    public static void  getNewPdf(String path) throws IOException, DocumentException{
        File file = new File(path);
        file.createNewFile();
        Document document=new Document(PageSize.A4.rotate(),-60,-60,0,0);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        document.add(getPdfTitle("业务结算单",titleFont2));
        String[] e1={"登记日期:2019年06月20日","","登记编号:20190905000000000"};
        float[] e={245f,480f,180f};
        document.add(getExplain(e,e1,smallBoldFont,20f));

        //这边将表格一共分了12格，并给定每个单元格的比例大小，然后根据跨行和跨列来分配单元格大小
        float[] f1={20f,35f,90f,70f,70f,70f,85f,70f,70f,70f,85f,70f};

        //单元格的值
        String[] v1={"个人信息","姓名","陈xx","性别","男","出生日期","2019-06-20","身份证号","333333333333333333"};
        String[] v2={"年龄","20","政治面貌","党员","文化程度","本科","民族","汉","健康状况","良好"};
        String[] v3={"手机号码","15555555555","固定电话","15555555555",};
        String[] v4={"户籍住址","xx省xx市xx街道"};

        //单元格跨行
        int[] r1={4,1,1,1,1,1,1,1,1};
        int[] r2={1,1,1,1,1,1,1,1,1,1};
        int[] r3={1,1,1,1};
        int[] r4={1,1};
        //单元格跨列
        int[] c1={2,1,1,1,1,1,1,1,3};
        int[] c2={1,1,1,1,1,1,1,1,1,1};
        int[] c3={1,3,1,5};
        int[] c4={1,9};
        String[][] values4={v1,v2,v3,v4};
        int[][] rows4={r1,r2,r3,r4};
        int[][] cols4={c1,c2,c3,c4};
        //四行一起添加
        document.add(getRowColTable(f1,values4,rows4,cols4,20f));

        String[] v5={"其\n他\n信\n息","信息1","1111","1","2222","2","3333","3","4444","4","55","5"};
        String[] v6={"信息22","1111","1","2222","2"};
        String[] v7={"3333","33","44444","4"};
        String[] v8={"5555","5","6666","8","7777","8","8888","8"};
        int[] r5={4,1,1,1,1,1,1,1,1,1,1,1};
        int[] c5={1,1,1,1,1,1,1,1,1,1,1,1};
        int[] r6={3,1,1,1,1};
        int[] c6={1,1,3,3,3};
        int[] r7={1,1,1,1};
        int[] c7={1,3,3,3};
        int[] r8={1,1,1,1,1,1,1,1};
        int[] c8={1,1,1,1,1,2,1,2};
        String[][] values8={v5,v6,v7,v8};
        int[][] rows8={r5,r6,r7,r8};
        int[][] cols8={c5,c6,c7,c8};
        document.add(getRowColTable(f1,values8,rows8,cols8,20f));

        String[] v9={"其\n他\n信\n息\n2","信\n息\n21","21"};
        int[] r9={5,3,1};
        int[] c9={1,1,10};
        String[] v10={"11111111111","222222222222"};
        int[] r10={1,1};
        int[] c10={4,6};
        String[] v11={"","","","","","","","","",""};
        int[] r11={1,1,1,1,1,1,1,1,1,1};
        int[] c11={1,1,1,1,1,1,1,1,1,1};
        String[] v12={"信息\n44","3333"};
        int[] r12={2,1};
        int[] c12={1,10};
        String[] v13={"22222222","33333333333333"};
        int[] r13={1,1};
        int[] c13={4,6};
        String[][] values13={v9,v10,v11,v12,v13};
        int[][] rows13={r9,r10,r11,r12,r13};
        int[][] cols13={c9,c10,c11,c12,c13};
        document.add(getRowColTable(f1,values13,rows13,cols13,20f));

        String[] v14={"xx\nxx","xxxxxxx"};
        int[] r14={2,1};
        int[] c14={2,10};
        String[] v15={"xxxxxxx：","","xxxxxxx","xxxxxxx","xxxxxxx",""};
        int[] r15={1,1,1,1,1,1};
        int[] c15={2,2,2,1,1,2};
        String[] v16={"xx\nxx","xxxxxxx"};
        int[] r16={2,1};
        int[] c16={2,10};
        String[] v17={"xxxxxxx：","","xxxxxxx","xxxxxxx"};
        int[] r17={1,1,1,1};
        int[] c17={2,2,2,4};
        String[][] values17={v14,v15,v16,v17};
        int[][] rows17={r14,r15,r16,r17};
        int[][] cols17={c14,c15,c16,c17};
        document.add(getRowColTable(f1,values17,rows17,cols17,20f));


        String[] e2={"联系单位：xxxxxxxxxxx","","联系电话：xxxxxxxxxxxxx"};
        float[] e3={300f,350f,255f};
        document.add(getExplain(e3,e2,smallBoldFont,20f));
        document.close();

    }

    public static void main(String[] args) throws IOException, DocumentException {
        String path="/Users/kerl/pdf/test.pdf";
        getPdf(path);
    }
}
