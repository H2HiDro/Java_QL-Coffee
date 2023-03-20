package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDb.ConnectDB;
import entity.HoaDon;

public class BanHoaDonDao {
	private Connection con;
	private HoaDonDAO hoaDonDao;
	public BanHoaDonDao() {
		
		con = ConnectDB.getInstance().getConnection();
	}
	public boolean themBanHoaDon(HoaDon hd) throws Exception {
		int n=0;
		String sql = "INSERT INTO [dbo].[Ban_HoaDon]\r\n"
				+ "           ([maBan]\r\n"
				+ "           ,[maHoaDon])\r\n"
				+ "     VALUES\r\n"
				+ "           (?,?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, hd.getBan().getMaBan());
			stmt.setInt(2, hd.getMaHoaDon());
			n = stmt.executeUpdate();
		}
		return n >0;
	}
	public boolean xoaBanHoaDon(HoaDon hd) throws Exception {
		int n=0;
		String sql = "DELETE FROM [dbo].[Ban_HoaDon] WHERE maBan = ? and maHoaDon = ?";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, hd.getBan().getMaBan());
			stmt.setInt(2, hd.getMaHoaDon());
			n = stmt.executeUpdate();
		}
		return n >0;
	}
	public HoaDon timHoaDonTheoBan(int maBanNow) throws Exception {
		hoaDonDao = new HoaDonDAO();
		String sql = "select hd.maHoaDon ,\r\n"
				+ "	ngayTao ,\r\n"
				+ "	maKhachHang,\r\n"
				+ "	maNhanVien,\r\n"
				+ "	thanhTien,\r\n"
				+ "	tienKhachTra,\r\n"
				+ "	b.maBan from HoaDon hd join Ban_HoaDon bhd on hd.maHoaDon=bhd.maHoaDon join  Ban b on b.maBan= bhd.maBan where b.maban = ? ";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, maBanNow);
			ResultSet rs = stmt.executeQuery();
			KhachHangDAO khachHangDao = new KhachHangDAO();
			NhanVienDAO nhanVienDao = new NhanVienDAO();
			BanDAO banDao = new BanDAO();
			if (rs.next()) {

				return new  HoaDon(rs.getInt("maHoaDon"), rs.getDate("ngayTao"),khachHangDao.timKhachHang(rs.getString("maKhachHang")),nhanVienDao.timNhanVien(rs.getString("maNhanVien")), rs.getDouble("thanhTien"), rs.getDouble("tienKhachTra"), banDao.timBan(Integer.parseInt(rs.getString("maBan"))));
			}
		}
		return null;
	}
}
