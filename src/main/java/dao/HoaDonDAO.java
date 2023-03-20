package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import entity.HoaDon;

public class HoaDonDAO {
	private Connection con;
	private KhachHangDAO khachHangDao;
	private NhanVienDAO nhanVienDao;
	private BanHoaDonDao banHoaDonDao;
	private BanDAO banDao;

	public HoaDonDAO() {
		con = connectDb.ConnectDB.getInstance().getConnection();
		khachHangDao = new KhachHangDAO();
		nhanVienDao = new NhanVienDAO();
		banHoaDonDao = new BanHoaDonDao();
		banDao = new BanDAO();
	}

	public HoaDon timHoaDon(int maHD) throws Exception {
		String sql = "select * from HoaDon where maHoaDon = ? ";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, maHD);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new HoaDon(rs.getInt("maHoaDon"), rs.getDate("ngayTao"),
						khachHangDao.timKhachHang(rs.getString("maNhanVien")),
						nhanVienDao.timNhanVien(rs.getString("maKhachHang")), rs.getDouble("thanhTien"),
						rs.getDouble("tienKhachTra"), banDao.timBan(rs.getInt("maBan")));
			}
		}
		return null;
	}

	public boolean themHoaDon(HoaDon hd) throws Exception {
		if (timHoaDon(hd.getMaHoaDon()) != null)
			return false;
		String sql = "" + "INSERT INTO [dbo].[HoaDon]" + "           ([maHoaDon]" + "           ,[ngayTao]"
				+ "           ,[maKhachHang]" + "           ,[maNhanVien]" + "           ,[thanhTien]"
				+ "           ,[tienKhachTra]" + "           ,[maBan])" + "     VALUES"
				+ "           (?,CURRENT_TIMESTAMP,?,?,?,?,?)";
		int n = 0;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, hd.getMaHoaDon());
//			stmt.setDate(2, new Date(hd.getNgayTao().getTime()));
			stmt.setString(2, hd.getKhachHang().getIdKhachHang());
			stmt.setString(3, hd.getNhanVien().getMaNhanVien());
			stmt.setDouble(4, hd.getThanhTien());
			stmt.setDouble(5, hd.getTienTra());
			stmt.setInt(6, hd.getBan().getMaBan());
			n = stmt.executeUpdate();
		}
		return n > 0;
	}

	public boolean xoaHoaDon(int maHD) throws Exception {
		if (timHoaDon(maHD) == null)
			return false;
		String sql = "DELETE FROM [dbo].[HoaDon] " + "      WHERE maHoaDon = ?";
		int n = 0;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, maHD);
			n = stmt.executeUpdate();
		}
		return n > 0;
	}

	public boolean updateHoaDon(HoaDon hd) throws Exception {
		if (timHoaDon(hd.getMaHoaDon()) == null)
			return false;
		String sql = "UPDATE [dbo].[HoaDon]" + "   SET [maKhachHang] = ? " + "      ,[maNhanVien] = ? "
				+ "      ,[thanhTien] = ? " + "      ,[tienKhachTra] = ? " + "      ,[maBan] = ? "
				+ " WHERE maHoaDon = ? ";
		int n = 0;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(6, hd.getMaHoaDon());
			stmt.setString(1, hd.getKhachHang().getIdKhachHang());
			stmt.setString(2, hd.getNhanVien().getMaNhanVien());
			stmt.setDouble(3, hd.getThanhTien());
			stmt.setDouble(4, hd.getTienTra());
			stmt.setInt(5, hd.getBan().getMaBan());
			n = stmt.executeUpdate();
		}
		return n > 0;
	}

	public List<HoaDon> getAllHoaDon() throws Exception {
		List<HoaDon> dshd = new ArrayList<>();
		String sql = "select * from HoaDon";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				dshd.add(new HoaDon(rs.getInt("maHoaDon"), rs.getDate("ngayTao"),
						khachHangDao.timKhachHang(rs.getString("maNhanVien")),
						nhanVienDao.timNhanVien(rs.getString("maKhachHang")), rs.getDouble("thanhTien"),
						rs.getDouble("tienKhachTra"), banDao.timBan(rs.getInt("maBan"))));
		}
		return dshd;

	}

	public Double getThanhTien(HoaDon hd) throws Exception {
		String sql = "select hd.maHoaDon, sum(donGia*soLuong) as total from HoaDon hd join ChiTietHoaDon ct\r\n"
				+ "	on hd.maHoaDon = ct.maHoaDon join MatHang mh on ct.maMatHang = mh.maMatHang where hd.maHoaDon = ? group by hd.maHoaDon";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, hd.getMaHoaDon());
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return rs.getDouble("total");
		}
		return null;
	}

	public int timMaMoiNhat() throws Exception {
		String sql = "select max (maHoaDon) as m from HoaDon ";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return rs.getInt("m");
		}
		return 0;
	}
	public Time getGioTao(int maHD) throws Exception {
		String sql = "select * from HoaDon where maHoaDon = ? ";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, maHD);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getTime("ngayTao"));
				return rs.getTime("ngayTao");
			}
		}
		return null;
	}
}
