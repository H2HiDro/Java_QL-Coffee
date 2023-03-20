package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import connectDb.ConnectDB;
import entity.MatHang;
import entity.NhanVien;

public class MatHangDAO {
	private Connection con;
	private LoaiHangDAO lhDAO;

	public MatHangDAO() {
		con = ConnectDB.getInstance().getConnection();
		lhDAO = new LoaiHangDAO();
	}

	public boolean themMatHang(MatHang mh) throws Exception {
		if (timMatHang(mh.getMaMatHang()) != null)
			return false;
		String sql = "INSERT INTO [dbo].[MatHang]\r\n" + "           ([maMatHang]\r\n" + "           ,[donGia]\r\n"
				+ "           ,[soLuongTon]\r\n" + "           ,[trangThai]\r\n" + "           ,[maLoaiHang]\r\n"
				+ "           ,[linkAnh]\r\n" + "           ,[tenMatHang])\r\n" + "     VALUES\r\n"
				+ "           (?,?,?,?,?,?,?)";
		int n = 0;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, mh.getMaMatHang());
			stmt.setDouble(2, mh.getDonGia());
			stmt.setInt(3, mh.getSoLuongTon());
			stmt.setBoolean(4, mh.isTrangThai());
			stmt.setString(5, mh.getLoaiHang().getMaLoaiHang());
			stmt.setString(6, mh.getLinkAnh());
			stmt.setString(7, mh.getTenMatHang());
			n = stmt.executeUpdate();
		}
		return n > 0;
	}

	public MatHang timMatHang(String maMatHang) throws Exception {
		String sql = "select * from MatHang where maMatHang = ?";
		MatHang mh = null;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, maMatHang);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				mh = new MatHang(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getBoolean(5),
						lhDAO.timLoaiHang(rs.getString(6)), rs.getString(7));

			}
		}

		return mh;
	}

	public List<MatHang> layDanhSachMatHang() throws Exception {
		List<MatHang> dsMH = new ArrayList<>();
		String sql = "select * from MatHang";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				MatHang mh = new MatHang(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
						rs.getBoolean(5), lhDAO.timLoaiHang(rs.getString(6)), rs.getString(7));
				dsMH.add(mh);
			}
		}
		return dsMH;
	}

	public boolean capNhatMatHang(MatHang mh) throws Exception {
		if (timMatHang(mh.getMaMatHang()) != null) {

			String sql = "UPDATE [dbo].[MatHang]\r\n" + "   SET " + "      [donGia] = ? " + "      ,[soLuongTon] = ? "
					+ "      ,[trangThai] = ? " + "      ,[maLoaiHang] = ? " + "      ,[linkAnh] = ? "
					+ "      ,[tenMatHang] = ? " + " WHERE maMatHang = ?";
			int n = 0;

			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setDouble(1, mh.getDonGia());
				stmt.setInt(2, mh.getSoLuongTon());
				stmt.setBoolean(3, mh.isTrangThai());
				stmt.setString(4, mh.getLoaiHang().getMaLoaiHang());
				stmt.setString(5, mh.getLinkAnh());
				stmt.setString(6, mh.getTenMatHang());
				stmt.setString(7, mh.getMaMatHang());
				n = stmt.executeUpdate();
				return n > 0;
			}
		}
		return false;
	}

	public boolean deleteMatHang(String maMatHang) throws Exception {
		String sql = "DELETE FROM [dbo].[MatHang] " + "      WHERE maMatHang = ? ";
		int n = 0;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, maMatHang);
			n = stmt.executeUpdate();
		}
		return n > 0;
	}

	public String getMaMatHangCaoNhat() throws Exception {
		String sql = "SELECT MAX(maMatHang) FROM MatHang";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return rs.getString(1);
		}
		return null;
	}

	public List<MatHang> timKiemMatHangTheoNhieuThuocTinh(String maMH, String tenMH, String donGia, String loaiHang,
			String trangThai) throws Exception {
		String sql = "select * from MatHang ";
		String where = "where ";
		if (maMH != null || tenMH != null || donGia != null || loaiHang != null || trangThai != null) {
			List<MatHang> dsMH = new ArrayList<>();
			if (maMH != null)
				where += "maMatHang = '" + maMH + "'";
			else {
				if (tenMH != null)
					where += "and tenMatHang like N'%" + tenMH.trim() + "%' ";
				if (donGia != null)
					where += "and donGia = '" + Double.parseDouble(donGia) + "' ";
				if (loaiHang != null)
					where += "and maLoaiHang = '" + lhDAO.timTheoTenLoaiHang(loaiHang).getMaLoaiHang() + "' ";

				if (trangThai != null)
					where += "and trangThai = '" + (trangThai.equalsIgnoreCase("Còn hàng") ? 1 : 0) + "' ";

				where = where.replaceFirst("and", "");
			}
			sql += where;
			System.out.println(sql);
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					MatHang mh = new MatHang(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
							rs.getBoolean(5), lhDAO.timLoaiHang(rs.getString(6)), rs.getString(7));
					dsMH.add(mh);
				}
			}
			return dsMH;
		} else
			return layDanhSachMatHang();
	}

	public List<MatHang> sapXepTheoLuaChon(String loaiHang, String luachon) throws Exception {
		String sql = "select * from MatHang ";
		String sortKey, paramater;
		if (!loaiHang.equalsIgnoreCase("Loại thức uống"))
			sql += " where maLoaiHang = N'" + loaiHang + "' ";
		sql += " order by #SORTKEY #PARAMATER";
		List<MatHang> dsMH = new ArrayList<>();

		System.out.println(loaiHang);
		System.out.println(luachon);
		if (luachon.equalsIgnoreCase("A --> Z")) {
			paramater = "ASC";
			sortKey = "tenMatHang";
		} else if (luachon.equalsIgnoreCase("Z --> A")) {
			sortKey = "tenMatHang";
			paramater = "DESC";
		} else if (luachon.equalsIgnoreCase("Giá giảm dần")) {
			sortKey = "donGia";
			paramater = "ASC";
		} else if (luachon.equalsIgnoreCase("Giá tăng dần")) {
			sortKey = "donGia";
			paramater = "DESC";
		} else {
			sortKey = "maMatHang";
			paramater = "ASC";
		}
		sql = sql.replaceAll("#SORTKEY", sortKey).replaceAll("#PARAMATER", paramater);
		System.out.println(sql);
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				MatHang mh = new MatHang(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
						rs.getBoolean(5), lhDAO.timLoaiHang(rs.getString(6)), rs.getString(7));
				dsMH.add(mh);

			}

		}
		return dsMH;
	}

}
