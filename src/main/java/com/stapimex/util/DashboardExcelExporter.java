package com.stapimex.util;

import com.stapimex.dao.DashboardDAO;
import com.stapimex.dao.impl.DashboardDAOImpl;
import com.stapimex.model.view.ThongKeNgayView;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

public class DashboardExcelExporter {
    private static final int DATA_ROW = 12; // Excel dòng 14

    private static final int COL_DE_NGHI_START = 6;   // G
    private static final int COL_CAP_PHAT_START = 38; // AM

    public static void export(
            int month,
            int year,
            File outputFile
    ) throws Exception {

        DashboardDAO dao =
                new DashboardDAOImpl();

        List<ThongKeNgayView> list =
                dao.thongKeTheoNgay(
                        month,
                        year
                );

        InputStream is =
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream(
                                "template/TD CAP PHAT THẺ RFID VPXN 2026.xlsx"
                        );

        if(is == null){
            throw new RuntimeException(
                    "Không tìm thấy file template"
            );
        }


        Workbook workbook =
                new XSSFWorkbook(is);

        String sheetName =
                String.format(
                        "T%02d",
                        month
                );

        Sheet sheet =
                workbook.getSheet(
                        sheetName
                );

        if (sheet == null) {

            throw new RuntimeException(
                    "Không tìm thấy sheet "
                            + sheetName
            );
        }

        Row rowTitle = sheet.getRow(4); // Excel dòng 4
        Cell cellTitle = rowTitle.getCell(1); // M

        cellTitle.setCellValue(
                String.format(
                        "THÁNG %02d/%d",
                        month,
                        year
                )
        );
        Row row =
                sheet.getRow(
                        DATA_ROW
                );

        if (row == null) {
            row = sheet.createRow(DATA_ROW);
        }
        for (ThongKeNgayView item : list) {

            int day = item.getNgay();
            if(day < 1 || day > 31){
                continue;
            }

            int colThuHoi =
                    COL_DE_NGHI_START
                            + day
                            - 1;

            int colCapPhat =
                    COL_CAP_PHAT_START
                            + day
                            - 1;

            Cell cellThuHoi =
                    row.getCell(
                            colThuHoi
                    );

            if(cellThuHoi == null){
                cellThuHoi =
                        row.createCell(
                                colThuHoi
                        );
            }

            if(item.getThuHoi() > 0){
                cellThuHoi.setCellValue(
                        item.getThuHoi()
                );
            }

            Cell cellCapPhat =
                    row.getCell(
                            colCapPhat
                    );

            if(cellCapPhat == null){
                cellCapPhat =
                        row.createCell(
                                colCapPhat
                        );
            }

            if(item.getCapPhat() > 0) {
                cellCapPhat.setCellValue(
                        item.getCapPhat()
                );
            }
        }

        FormulaEvaluator evaluator =
                workbook
                        .getCreationHelper()
                        .createFormulaEvaluator();

        evaluator.evaluateAll();

        FileOutputStream fos =
                new FileOutputStream(
                        outputFile
                );

        workbook.write(fos);

        fos.close();
        workbook.close();
    }
}
