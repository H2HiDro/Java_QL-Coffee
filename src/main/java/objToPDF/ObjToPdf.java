//package objToPDF;
//
//
//import com.spire.doc.Document;
//import com.spire.doc.FileFormat;
//import com.spire.doc.Table;
//import com.spire.doc.fields.Field;
//
//public class ObjToPdf {
//	private  void addRows(Table table, int rowNum) {
//	    for (int i = 0; i < rowNum; i++) {
//	        //insert specific number of rows by cloning the second row
//	        table.getRows().insert(2 + i, table.getRows().get(1).deepClone());
//	        //update formulas for Total
//	        for (Object object : table.getRows().get(2 + i).getCells().get(3).getParagraphs().get(0).getChildObjects()
//	        ) {
//	            if (object instanceof Field) {
//	                Field field = (Field) object;
//	                field.setCode(String.format("=B%d*C%d\\# \"0.00\"", 3 + i,3 + i));
//	            }
//	            break;
//	        }
//	    }
////	    //update formula for Total Tax
////	    for (Object object : table.getRows().get(2 + rowNum).getCells().get(3).getParagraphs().get(0).getChildObjects()
////	    ) {
////	        if (object instanceof Field) {
////	            Field field = (Field) object;
////	            field.setCode(String.format("=D%d*0.05\\# \"0.00\"", 3 + rowNum));
////	        }
////	        break;
////	    }
//	    //update formula for Balance Due
////	    for (Object object : table.getRows().get(5 + rowNum).getCells().get(3).getParagraphs().get(0).getChildObjects()
////	    ) {
////	        if (object instanceof Field) {
////	            Field field = (Field) object;
////	            field.setCode(String.format("=D%d+D%d\\# \"$#,##0.00\"", 3 + rowNum, 5 + rowNum));
////	        }
////	        break;
////	    }
//	}
//	private  void fillTableWithData(Table table, String[][] data) {
//	    for (int r = 0; r < data.length; r++) {
//	        for (int c = 0; c < data[r].length; c++) {
//	            //fill data in cells
//	            table.getRows().get(r + 1).getCells().get(c).getParagraphs().get(0).setText(data[r][c]);
//	        }
//	    }
//	}
//	public  void writeDataToDocument(Document doc, String[][] purchaseData) {
//	    //get the third table
//	    Table table = doc.getSections().get(0).getTables().get(2);
//	    //determine if it needs to add rows
//	    if (purchaseData.length > 1) {
//	        addRows(table, purchaseData.length - 1);
//	    }
//	    fillTableWithData(table, purchaseData);
//	}
////	public  void main(String[] args) {
////
////		// create a document instance
////		Document doc = new Document();
////
////		// load the template file
////		doc.loadFromFile("D:\\GiaLapThi\\test\\docx\\Invoice-Template.docx");
////
//////		// replace text in the document
////		doc.replace("#InvoiceNum", "17854", true, true);
////		doc.replace("#CompanyName", "Y Company", true, true);
////		doc.replace("#CompanyAddress", "122 4th Ave", true, true);
////		doc.replace("#CityStateZip", "New York, NY 10011", true, true);
////		doc.replace("#Country", "United States", true, true);
////		doc.replace("#Tel1", "111-222-333", true, true);
////		doc.replace("#ContactPerson", "John Smith", true, true);
////		doc.replace("#ShippingAddress", "122 4th Ave", true, true);
////		doc.replace("#Tel2", "111-222-334", true, true);
////
////		// define purchase data
////		String[][] purchaseData = { new String[] { "Product A", "5", "22.8" },
////				new String[] { "Product B", "4", "35.3" }, new String[] { "Product C", "2", "52.9" },
////				new String[] { "Product D", "3", "25" }, };
//////		System.out.println(purchaseData[9][0]);
////		// write the purchase data to the document
////		writeDataToDocument(doc, purchaseData);
////
//
////	}
//}
