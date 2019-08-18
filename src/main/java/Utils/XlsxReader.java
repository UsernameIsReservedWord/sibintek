package Utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;
public class XlsxReader {

    private XSSFWorkbook workbook;
    private List<Map> mapList = new ArrayList<>();

    public XlsxReader(String fileName) {
        try {
            URL res = getClass().getClassLoader().getResource(fileName);
            File file = Paths.get(res.toURI()).toFile();
            InputStream is = new FileInputStream(file);
            workbook = new XSSFWorkbook(is);
            is.close();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private String getFromCell(int i, Row row) {
        return Optional.ofNullable(row.getCell(i).getStringCellValue()).orElse("");
    }

    private void read() {
        XSSFSheet sheet = workbook.getSheet("SimpleTest");
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Map<String, String> map = new HashMap<>();
            Row row = rowIterator.next();
            map.put("action", getFromCell(0, row));
            map.put("type", getFromCell(1, row));
            map.put("category", getFromCell(2, row));
            map.put("quarter", getFromCell(3, row));
            map.put("priceLimit", String.valueOf(row.getCell(4).getNumericCellValue()));
            map.put("location", getFromCell(5, row));
            mapList.add(map);
        }
    }

    public List<Map> getMapList() {
        read();
        return mapList;
    }
}
