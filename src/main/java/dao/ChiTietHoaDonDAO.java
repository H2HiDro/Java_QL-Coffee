package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.MatHang;

public class ChiTietHoaDonDAO {
	private Connection con;
	private MatHangDAO matHangDao;
	private HoaDonDAO hoaDonDao;

	public ChiTietHoaDonDAO() {
		con = connectDb.ConnectDB.getInstance().getConnection();
		matHangDao = new MatHangDAO();
		hoaDonDao = new HoaDonDAO();
	}

	public ChiTietHoaDon timChiTietHoaDon(int hoaDon, String matHang) throws Exception {
		String sql = "select * from ChiTietHoaDon where maHoaDon = ?  and maMatHang=? ";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, hoaDon);
			stmt.setString(2, matHang);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new ChiTietHoaDon(matHangDao.timMatHang(rs.getString("maMatHang")), rs.getInt("soLuong"),
						hoaDonDao.timHoaDon(rs.getInt("maHoaDon")));
			}
		}
		return null;
	}

	public boolean themChiTietHoaDon(ChiTietHoaDon cthd) throws Exception {
		String sql = "INSERT INTO [dbo].[ChiTietHoaDon]\r\n" + "           ([maMatHang]\r\n"
				+ "           ,[soLuong]\r\n" + "           ,[maHoaDon])\r\n" + "     VALUES\r\n"
				+ "           (?,?,?)";
		int n = 0;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, cthd.getMaMatHang().getMaMatHang());
			stmt.setInt(2, cthd.getSoLuong());
			stmt.setInt(3, cthd.getMaHoaDon().getMaHoaDon());
			n = stmt.executeUpdate();
		}
		return n > 0;
	}

	public boolean capNhanChiTietHoaDon(ChiTietHoaDon cthd) throws Exception {
		String sql = "UPDATE [dbo].[ChiTietHoaDon]\r\n" + "   SET [soLuong] = ?\r\n"
				+ " WHERE maHoaDon = ? and maMatHang =?";
		int n = 0;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(3, cthd.getMaMatHang().getMaMatHang());
			stmt.setInt(1, cthd.getSoLuong());
			stmt.setInt(2, cthd.getMaHoaDon().getMaHoaDon());
			n = stmt.executeUpdate();
		}
		return n > 0;
	}

	public boolean xoaChiTietHoaDon(ChiTietHoaDon cthd) throws Exception {
		int n = 0;
		
		String sql = "DELETE FROM [dbo].[ChiTietHoaDon]" + "      WHERE maHoaDon = ? and maMatHang = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, cthd.getMaHoaDon().getMaHoaDon());
			stmt.setString(2, cthd.getMaMatHang().getMaMatHang());
			n = stmt.executeUpdate();
		}
		return n > 0;
	}

	public List<ChiTietHoaDon> getAllChiTietHoaDons() throws Exception {
		List<ChiTietHoaDon> dscthd = new ArrayList<>();
		String sql = "select * from ChiTietHoaDon";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			dscthd.add(new ChiTietHoaDon(matHangDao.timMatHang(rs.getString("maMatHang")), rs.getInt("soLuong"),
					hoaDonDao.timHoaDon(rs.getInt("maHoaDon"))));
		}
		return dscthd;
	}

	public List<ChiTietHoaDon> getChiTietHoaDonTheoMaHD(int maHD) throws Exception {
		List<ChiTietHoaDon> dscthd = new ArrayList<>();
		String sql = "select * from ChiTietHoaDon where maHoaDon = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, maHD);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			dscthd.add(new ChiTietHoaDon(matHangDao.timMatHang(rs.getString("maMatHang")), rs.getInt("soLuong"),
					hoaDonDao.timHoaDon(rs.getInt("maHoaDon"))));
		}
		return dscthd;
	}

	public List<HoaDon> getHoaDon(int maHoaDon) throws Exception {
		List<HoaDon> dscthd = new ArrayList<>();
		String sql = "select * from ChiTietHoaDon where maHoaDon = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, maHoaDon);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ChiTietHoaDon hd = new ChiTietHoaDon(matHangDao.timMatHang(rs.getString("maMatHang")), rs.getInt("soLuong"),
					hoaDonDao.timHoaDon(rs.getInt("maHoaDon")));

		}
		return dscthd;
	}

	public List<String> xuatHoaDon(int maHD) throws Exception {
		String sql = "select tenmathang,soluong,dongia from chiTietHoadon ct join mathang mh on ct.mamathang = mh.mamathang where mahoadon =?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, maHD);
		ResultSet rs = stmt.executeQuery();
		List<String> ds = new ArrayList<>();
		while(rs.next()) {
			int sl = rs.getInt("soluong");
			double dg = rs.getDouble("dongia");
			String s = String.format("|%-30s|%-10d|%-20.2f|%-20.2f|",rs.getString("tenmathang"),sl,dg,sl*dg);
			ds.add(s);
		}
		return ds;
		
	}
}
