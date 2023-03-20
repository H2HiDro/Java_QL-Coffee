package entity;

import java.util.Objects;

public class ChiTietHoaDon {
	private MatHang maMatHang;
	private int soLuong;
	private HoaDon maHoaDon;
	public MatHang getMaMatHang() {
		return maMatHang;
	}
	public void setMaMatHang(MatHang maMatHang) {
		this.maMatHang = maMatHang;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public HoaDon getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(HoaDon maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public ChiTietHoaDon(MatHang maMatHang, int soLuong, HoaDon maHoaDon) {
		super();
		this.maMatHang = maMatHang;
		this.soLuong = soLuong;
		this.maHoaDon = maHoaDon;
	}
	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [maMatHang=" + maMatHang + ", soLuong=" + soLuong + ", maHoaDon=" + maHoaDon + "]";
	}
	
}
