package com.returnp.admin.view;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.returnp.admin.dto.command.GiftCardIssueCommand;
import com.returnp.admin.utils.Util;

public class ListExcelDownload extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
     
        ArrayList<GiftCardIssueCommand> listExcel = (ArrayList<GiftCardIssueCommand>) model.get("giftCardIssuseList");
        String excelName = listExcel.get(0).getOrderNumber() +"_"+ listExcel.get(0).getOrdererName() + "_상품권 발행 리스트.xlsx";
        CellStyle style = workbook.createCellStyle(); // 셀 스타일을 위한 변수
        style.setAlignment(CellStyle.ALIGN_CENTER); // 글 위치를 중앙으로 설정
         
        Sheet worksheet = null;
        Row row = null;
        worksheet = workbook.createSheet("상품권 발행 목록");
        // 가장 첫번째 줄에 제목을 만든다.
        row = (Row)worksheet.createRow(0);
         
        // 칼럼 길이 설정
   /*     int columnIndex = 0;
        while (columnIndex < 7) {
             
            if(columnIndex == 0) {
                worksheet.setColumnWidth(columnIndex, 3000);
            } else if (columnIndex == 1) {
                worksheet.setColumnWidth(columnIndex, 4000);
            } else if (columnIndex == 2) {
                worksheet.setColumnWidth(columnIndex, 6000);
            } else if (columnIndex == 3) {
                worksheet.setColumnWidth(columnIndex, 4000);
            } else if (columnIndex == 4) {
                worksheet.setColumnWidth(columnIndex, 7000);
            } else if (columnIndex == 5) {
                worksheet.setColumnWidth(columnIndex, 3000);
            } else if (columnIndex == 6) {
                worksheet.setColumnWidth(columnIndex, 3000);
            }
            columnIndex++;
        }*/
         
        // 헤더 설정
        row = worksheet.createRow(0);
        row.createCell(0).setCellValue("발행 등록 번호");
        row.createCell(1).setCellValue("주문 번호");
        row.createCell(2).setCellValue("상품권 번호");
        row.createCell(3).setCellValue("상품권 이름");
        row.createCell(4).setCellValue("주문 명");
        row.createCell(5).setCellValue("주문 번호");
        row.createCell(6).setCellValue("주문자 이름");
        row.createCell(7).setCellValue("주문자 이메일");
        row.createCell(8).setCellValue("주문자 핸드폰");
        row.createCell(9).setCellValue("핀 번호");
        row.createCell(10).setCellValue("적립 가능");
        row.createCell(11).setCellValue("결제 가능");
        row.createCell(12).setCellValue("상품권 상태");
        row.createCell(13).setCellValue("상품권 타입");
        row.createCell(14).setCellValue("상품권 금액");
        row.createCell(15).setCellValue("상품권 판매 금액");
        row.createCell(16).setCellValue("적립 QR 데이타");
        row.createCell(17).setCellValue("결제 QR 데이타");
        row.createCell(18).setCellValue("적립 QR 스캐너");
        row.createCell(19).setCellValue("결제 QR 스캐너");
        row.createCell(20).setCellValue("적립 QR 스캔 일자");
        row.createCell(21).setCellValue("결제 QR 스캔 일자");
        row.createCell(22).setCellValue("발행 일자");
        row.createCell(23).setCellValue("만료 일자");
        row.createCell(24).setCellValue("등록일");
        row.createCell(25).setCellValue("수정일");
         
        int rowIndex = 1;
         
        // 각 해당하는 셀에 값과 스타일을 넣음
        for(GiftCardIssueCommand  command: listExcel) {
            row = worksheet.createRow(rowIndex);
            row.createCell(0).setCellValue(command.getGiftCardIssueNo());
            row.createCell(1).setCellValue(command.getGiftCardOrderNo());
            row.createCell(2).setCellValue(command.getGiftCardNo());
            row.createCell(3).setCellValue(Util.nullToString(command.getGiftCardName()));
            row.createCell(4).setCellValue(Util.nullToString(command.getOrderName()));
            
            row.createCell(5).setCellValue(Util.nullToString(command.getOrderNumber()));
            row.createCell(6).setCellValue(Util.nullToString(command.getOrdererName()));
            row.createCell(7).setCellValue(Util.nullToString(command.getOrdererEmail()));
            row.createCell(8).setCellValue(Util.nullToString(command.getOrdererPhone()));
            row.createCell(9).setCellValue(command.getPinNumber());
            row.createCell(10).setCellValue(command.getAccableStatus());
            row.createCell(11).setCellValue(command.getPayableStatus());
            row.createCell(12).setCellValue(command.getGiftCardStatus());
            row.createCell(13).setCellValue(command.getGiftCardType());
            row.createCell(14).setCellValue(command.getGiftCardAmount());
            row.createCell(15).setCellValue(command.getGiftCardSalePrice());
            row.createCell(16).setCellValue(command.getAccQrData());
            row.createCell(17).setCellValue(command.getPayQrData());
            row.createCell(18).setCellValue(command.getAccQrScanner());
            row.createCell(19).setCellValue(command.getPayQrScanner());
            row.createCell(20).setCellValue(Util.dateToString(command.getAccQrScanTime()));
            row.createCell(21).setCellValue(Util.dateToString(command.getPayQrScanTime()));
            row.createCell(22).setCellValue(Util.dateToString(command.getIssueTime()));
            row.createCell(24).setCellValue(Util.dateToString(command.getExpirationTime()));
            row.createCell(24).setCellValue(Util.dateToString(command.getCreateTime()));
            row.createCell(25).setCellValue(Util.dateToString(command.getUpdateTime()));
            rowIndex++;
        }
        try {
            response.setHeader("Content-Disposition", "attachement; filename=\"" + java.net.URLEncoder.encode(excelName, "UTF-8") + "\";charset=\"UTF-8\"");
            workbook.write(response.getOutputStream()); 
            workbook.close();
            response.getOutputStream().flush();
            response.getOutputStream().close();

          } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        


	}

}
