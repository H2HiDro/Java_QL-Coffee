package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connectDb.ConnectDB;
import entity.ChiTietHoaDon;

public class ThongKeDAO {
	private MatHangDAO matHangDAO;
	private Connection con;
	public ThongKeDAO(){
		matHangDAO = new MatHangDAO();
		con = ConnectDB.getInstance().getConnection();
	}
	public Date getNgayCachNgayHienTai(java.util.Date date) {
		String sql = "select CAST(DATEADD(month, -1, GETDATE()) AS DateTime) as date";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				return rs.getDate(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<List<String>> getTongHangDuocMua() throws Exception {
		String sql = " select top(7) cthd.maMatHang, SUM(cthd.soLuong) as sl from HoaDon hd join ChiTietHoaDon cthd on hd.maHoaDon = cthd.maHoaDon\r\n"
				+ "where ngayTao > DATEADD(month, -1, GETDATE()) group by cthd.maMatHang ";
		List<List<String>> ds = new ArrayList<>(); 
		List<String> tmp = new ArrayList<>();
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tmp = new ArrayList<>();
				tmp.add(matHangDAO.timMatHang(rs.getString(1)).getTenMatHang());
				tmp.add(rs.getString(2));
				ds.add(tmp);
			}
		}
		return ds;
	}
	public List<List<String>> getTopNhanVien() throws Exception {
		String sql = "select top(10) maNhanVien, luong from NhanVien order by luong desc";
		List<List<String>> ds = new ArrayList<>();
		List<String> tmp = new ArrayList<>();
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tmp = new ArrayList<>();
				tmp.add(rs.getString(1));
				tmp.add(rs.getString(2));
				ds.add(tmp);
			}
		}
		return ds;
	}
	public List<List<String>> getThongKeTheoNam(int nam) throws Exception {
		String sql = "select  WeekNumber+1 as thang, sum(thanhTien) as thanhTien from\r\n"
				+ "(SELECT \r\n"
				+ "    maHoaDon,\r\n"
				+ "    DATEDIFF(MONTH, CAST(N'"+nam+"-01-01T00:00:00.000' AS DateTime), ngayTao) AS WeekNumber,\r\n"
				+ "    thanhTien\r\n"
				+ "    FROM HoaDon) a where WeekNumber >=0 and WeekNumber<12\r\n"
				+ " group by  WeekNumber order by WeekNumber\r\n";
		
		List<List<String>> ds = new ArrayList<>();
		List<String> tmp = new ArrayList<>();
//		Map<String, Double> ds = new HashMap<>();
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
//				ds.put(rs.getString("thang") , rs.getDouble("thanhTien"));
				tmp = new ArrayList<>();
				tmp.add(rs.getString(1));
				tmp.add(rs.getString(2));
				ds.add(tmp);
			}
		}
		return ds;
	}
	public List<List<String>> getThongKeTheoNamThang(int nam, int thang) throws Exception {
		String sql = "select  WeekNumber+1 as tuan, sum(thanhTien) as thanhTien from\r\n"
				+ "(SELECT \r\n"
				+ "    maHoaDon,\r\n"
				+ "    DATEDIFF(WEEK, CAST(N'"+nam+"-"+thang+"-01T00:00:00.000' AS DateTime), ngayTao) AS WeekNumber,\r\n"
				+ "    thanhTien\r\n"
				+ "    FROM HoaDon) a where WeekNumber >=0 and WeekNumber<4\r\n"
				+ " group by  WeekNumber order by WeekNumber\r\n"
				+ "";
		
		List<List<String>> ds = new ArrayList<>();
		List<String> tmp = new ArrayList<>();
//		Map<String, Double> ds = new HashMap<>();
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
//				ds.put(rs.getString("thang") , rs.getDouble("thanhTien"));
				tmp = new ArrayList<>();
				tmp.add(rs.getString(1));
				tmp.add(rs.getString(2));
				ds.add(tmp);
			}
		}
		return ds;
	}
	public List<List<String>> getDoanhThuTheoKhoangNgay(String nbd, String nkt) throws Exception {
		// TODO Auto-generated method stub
//		if ()
		String sql1 = " select DATEDIFF(day,'"+nbd+"','"+nkt+"')";
		PreparedStatement stmt = con.prepareStatement(sql1);
		ResultSet rs = stmt.executeQuery();
		int datediff = 0;
		if (rs.next())
			datediff = rs.getInt(1);
		if (datediff>7)
			throw new Exception();
		List<List<String>> ds = new ArrayList<>();
		System.out.println(nbd);
		System.out.println(nkt);
		String sql = "select ngayTao, sum(thanhtien) as tt from (select CAST(ngaytao as date) as ngayTao, thanhtien from HoaDon )a where ngayTao >= '"+nbd+"' and ngayTao <= '"+nkt+"'  group by ngayTao";
		try(PreparedStatement stmt1 = con.prepareStatement(sql)){
			
			rs = stmt1.executeQuery();
			List<String> tmp = new ArrayList<>();
			while (rs.next()) {
				tmp = new ArrayList<>();
				tmp.add(rs.getString("ngayTao"));
				tmp.add(rs.getString("tt"));
				ds.add(tmp);
				System.out.println(tmp);
			}
		}
		return ds;
	}
	
}
