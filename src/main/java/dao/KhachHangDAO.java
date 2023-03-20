package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.KhachHang;
import entity.NhanVien;

public class KhachHangDAO {
	private Connection con;

	public KhachHangDAO() {
		con = connectDb.ConnectDB.getInstance().getConnection();
	}

	public KhachHang timKhachHang(String maKH) throws Exception {
		String sql = "select * from KhachHang where maKhachHang = ? ";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, maKH);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new KhachHang(rs.getString("maKhachHang"), rs.getString("tenKhachHang"),
						rs.getBoolean("gioiTinh") ? true : false, rs.getDate("ngaySinh"), rs.getString("soDienThoai"),
						rs.getInt("diemTichLuy"));
			}
		}
		return null;
	}

	public boolean themKhachHang(KhachHang kh) throws Exception {
		if (timKhachHang(kh.getIdKhachHang()) != null)
			return false;
		int n = 0;
		String sql = "INSERT INTO [dbo].[KhachHang] values (?,?,?,?,?,?)";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, kh.getIdKhachHang());
			stmt.setString(2, kh.getTenKhachHang());
			stmt.setBoolean(3, kh.isGioiTinh());
			Date date = null;
			try {
				date = new Date(kh.getNgaySinh().getTime());
			} catch (Exception e) {
			}
			stmt.setDate(4, date);
			stmt.setString(5, kh.getSoDienThoai());
			stmt.setInt(6, kh.getDiemTichLuy());
			n = stmt.executeUpdate();
		}
		return n > 0;
	}

	public boolean updateKhachHang(KhachHang kh) throws Exception {
		if (timKhachHang(kh.getIdKhachHang()) == null)
			return false;
		String sql = "UPDATE [dbo].[KhachHang]\r\n" + "   SET " + "      [tenKhachHang] = ? " + "      ,[gioiTinh] = ? "
				+ "      ,[ngaySinh] = ? " + "      ,[soDienThoai] = ? "
				+ "      ,[diemTichLuy] = ?  where maKhachHang = ? ";
		int n = 0;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(6, kh.getIdKhachHang());
			stmt.setString(1, kh.getTenKhachHang());
			stmt.setBoolean(2, kh.isGioiTinh());
			Date date = null;
			try {
				date = new Date(kh.getNgaySinh().getTime());
			} catch (Exception e) {
			}
			stmt.setDate(3, date);
			stmt.setString(4, kh.getSoDienThoai());
			stmt.setInt(5, kh.getDiemTichLuy());
			n = stmt.executeUpdate();
		}
		return n > 0;
	}

	public boolean deleteKhachHang(String maKh) throws Exception {
		String sql = "DELETE FROM [dbo].[KhachHang] " + "      WHERE maKhachHang = ? ";
		int n = 0;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, maKh);
			n = stmt.executeUpdate();
		}
		return n > 0;
	}

	public List<KhachHang> getAllKhachHang() throws Exception {
		List<KhachHang> dskh = new ArrayList<>();
		String sql = "select * from KhachHang";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				dskh.add(new KhachHang(rs.getString("maKhachHang"), rs.getString("tenKhachHang"),
						rs.getBoolean("gioiTinh"), rs.getDate("ngaySinh"), rs.getString("soDienThoai"),
						rs.getInt("diemTichLuy")));
			}
		}
		dskh.remove(0);
		return dskh;
	}

	public String getMaxIDKhachHang() throws Exception {
		String sql = "SELECT MAX(maKhachHang) FROM KhachHang";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return rs.getString(1);
		}
		return null;
	}

	public KhachHang timKhachHangTheoSDT(String sdtKh) throws Exception {
		String sql = "select * from KhachHang where soDienThoai = ? ";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, sdtKh);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("tenKhachHang"),
						rs.getBoolean("gioiTinh"), rs.getDate("ngaySinh"), rs.getString("soDienThoai"),
						rs.getInt("diemTichLuy"));
				System.out.println(kh);
				return kh;
			}
		}
		return null;
	}

	public List<KhachHang> timKiemKhachHangTheoNhieuThuocTinh(String maKH, String tenKH, String gioiTinh,
			java.util.Date ngaySinh, String soDienThoai, String hang) throws Exception {
		String sql = "select * from KhachHang ";
		String where = "where ";
		if (maKH != null || tenKH != null || gioiTinh != null || ngaySinh != null || soDienThoai != null
				|| hang != null) {
			List<KhachHang> dsKH = new ArrayList<>();
			if (maKH != null)
				where += "maKhachHang = '" + maKH + "'";
			else {
				if (tenKH != null)
					where += "and tenKhachHang like N'%" + tenKH.trim() + "%' ";
				if (gioiTinh != null)
					where += "and gioiTinh = '" + (gioiTinh == "Nam" ? 1 : 0) + "' ";
				if (ngaySinh != null)
					where += "and ngaySinh = '" + new Date(ngaySinh.getTime()) + "' ";

				if (soDienThoai != null)
					where += "and soDienThoai like '%" + soDienThoai + "%' ";
				if (hang != null) {
					int diemMin = 0;
					int diemMax = 0;
					if (hang.equalsIgnoreCase("E")) {
						diemMin = 0;
						diemMax = 50;
					} else if (hang.equalsIgnoreCase("D")) {
						diemMin = 50;
						diemMax = 100;
					} else if (hang.equalsIgnoreCase("C")) {
						diemMin = 100;
						diemMax = 200;
					} else if (hang.equalsIgnoreCase("B")) {
						diemMin = 200;
						diemMax = 500;
					} else if (hang.equalsIgnoreCase("A")) {
						diemMin = 500;
						diemMax = 1000;
					} else if (hang.equalsIgnoreCase("S")) {
						diemMin = 1000;
						diemMax = 100000000;
					}
					where += "and diemtichluy >= " + diemMin + " and diemtichluy <" + diemMax;
				}

				where = where.replaceFirst("and", "");
			}
			sql += where;
			try (

					PreparedStatement stmt = con.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getDate(4),
							rs.getString(5), Integer.parseInt(rs.getString(6)));
					dsKH.add(kh);
				}
			}
			return dsKH;
		} else
			return getAllKhachHang();
	}
}
