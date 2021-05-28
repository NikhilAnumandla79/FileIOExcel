package com.challenge.fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Operations {
	public void writeNotepad() {
		File file = new File("NikhilA.xlsx");
		File file2 = new File("nikhil.txt");
		Writer fw=null;
		try {
			fw = new FileWriter(file2);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			Workbook  workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			while(rows.hasNext()) {
				Row row = rows.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case STRING:
						fw.write(String.valueOf(cell.getStringCellValue()));
						System.out.print(cell.getStringCellValue());
						break;
					case BOOLEAN:
						fw.write(String.valueOf(cell.getBooleanCellValue()));
						System.out.print(cell.getBooleanCellValue());
						break;
					case NUMERIC:
						fw.write(String.valueOf(cell.getNumericCellValue()));
						System.out.print(cell.getNumericCellValue());
						break;
					default:
						break;
					} 
					fw.write("     ");
					System.out.print("     ");
				}
				
				fw.write("\n");
				System.out.println("\n");
			}
			workbook.close();
			fw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void getExpenses(String name) {
		FileInputStream fis =null ;
		Workbook  workbook = null;
		try {
			fis = new FileInputStream("NikhilA.xlsx");
			workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			while(rows.hasNext()) {
				Row row= rows.next();
				if(row.getCell(1).getStringCellValue().equalsIgnoreCase(name)) {
					System.out.println(row.getCell(2));
				}
				
			}
			workbook.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(fis!=null) {
					fis.close();
				}
				if(workbook!=null) {
					workbook.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	public void updateExpenses(String name, double expenses) {
		FileInputStream fis =null ;
		Workbook  workbook = null;
		try {
			fis = new FileInputStream("NikhilA.xlsx");
			workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			rows.next();
			while(rows.hasNext()) {
				Row row= rows.next();
				if(row.getCell(1).getStringCellValue().equalsIgnoreCase(name)) {
					Cell cell = row.getCell(2);
					cell.setCellValue(expenses);
				}
				
			}
			workbook.write(new FileOutputStream("NikhilA.xlsx"));
			workbook.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(fis!=null) {
					fis.close();
				}
				if(workbook!=null) {
					workbook.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	public void creatTotal() {
		FileInputStream fis =null ;
		Workbook  workbook = null;
		FileOutputStream fw =null;
		try {
			fis = new FileInputStream("NikhilA.xlsx");
			workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			int total = 0;
			rows.next();
			int count=1;
			while(rows.hasNext()) {
				count++;
				Row row= rows.next();
				total+=row.getCell(2).getNumericCellValue();
				
			}
			XSSFRow createRow = (XSSFRow) sheet.createRow(count);
			Cell cell = createRow.createCell(0);
			cell.setCellValue("Total Expenses");
			System.out.println("worked");
			Cell cell2 = createRow.createCell(1);
			cell2.setCellValue(total);
			fw= new FileOutputStream("NikhilA.xlsx");
			workbook.write(fw);
			workbook.close();
			System.out.println(total);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(fis!=null) {
					fis.close();
				}
				if(fw!=null) {
					fw.close();
				}
				if(workbook!=null) {
					workbook.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
