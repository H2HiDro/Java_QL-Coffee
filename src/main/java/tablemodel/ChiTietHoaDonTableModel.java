package tablemodel;

import java.text.DecimalFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.ChiTietHoaDon;

public class ChiTietHoaDonTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DONGIA = 2;
	private static final int TENMATHANG = 0;
	private static final int SOLUONG = 1;
	private static final int THANHTIEN = 3;
	private String[] headers;
	private List<ChiTietHoaDon> dsCTHD;
	
	public ChiTietHoaDonTableModel(String[] headers, List<ChiTietHoaDon> dsCTHD) {
		super();
		this.headers = headers;
		this.dsCTHD = dsCTHD;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return dsCTHD.size();
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return headers.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ChiTietHoaDon cthd = dsCTHD.get(rowIndex);
		DecimalFormat decimalFormat = new DecimalFormat("###,###,### đ");
		switch (columnIndex) {
		case DONGIA:		
			return decimalFormat.format(cthd.getMaMatHang().getDonGia());
		case TENMATHANG:
			return cthd.getMaMatHang().getTenMatHang();
		case SOLUONG:
			return cthd.getSoLuong();
		case THANHTIEN:
			return decimalFormat.format(cthd.getMaMatHang().getDonGia()*cthd.getSoLuong());

		default:
			return cthd;
		}
	}
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return headers[column];
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		if (columnIndex== SOLUONG)
			return Integer.class;
		return String.class;
	}
	
}
