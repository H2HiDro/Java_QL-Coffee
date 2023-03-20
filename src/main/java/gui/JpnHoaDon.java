/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import dao.BanDAO;
import dao.BanHoaDonDao;
import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import entity.Ban;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import io.CharacterStreamIOBuffered;
import setting.Regex;
import tablemodel.ChiTietHoaDonTableModel;

/**
 *
 * @author hieud
 */
public class JpnHoaDon extends javax.swing.JPanel {

	protected static final String[] HEADERS = "Tên Sản Phẩm;Số Lượng;Đơn giá;Tổng".split(";");
	private int soBan;
	private BanDAO banDao;
	private HoaDon hd;
	private KhachHangDAO khachHangDAO;
	private NhanVienDAO nhanVienDAO;
	private HoaDonDAO hoaDonDAO;

	private List<JpnBanCon> dsBanCon;
	private ChiTietHoaDonDAO chiTietHoaDonDAO;
	private List<ChiTietHoaDon> dscthd;
	private int maBanNow;
	private BanHoaDonDao banHoaDonDao;
	private DecimalFormat decimalFormat = new DecimalFormat("###,###,### đ");
	private NhanVien nv;
	private JPanel jPnThongBaoChon;
	private JLabel lbThongBaoChon;
	
	/**
     * Creates new form jpnHoaDon
     */
    public JpnHoaDon() {
    	
        initComponents();
       	jPnThongBaoChon = new javax.swing.JPanel();
    	lbThongBaoChon = new javax.swing.JLabel();
        taoJpnThongBaoChon();
        setPanelHandle(false);
        try {
        	
    		khachHangDAO = new KhachHangDAO();
    		chiTietHoaDonDAO = new ChiTietHoaDonDAO();
    		hoaDonDAO = new HoaDonDAO();
    		nhanVienDAO = new NhanVienDAO();
    		dsBanCon = new ArrayList<JpnBanCon>();
    		dscthd = new ArrayList<>();
    		banHoaDonDao = new BanHoaDonDao();
    		banDao = new BanDAO();
	    	soBan = banDao.layDanhSachBan().size();
	    	loadBan();
			addControls();
		} catch (Exception e) {
			e.printStackTrace();
		}
        clearText();
    }

    private void setPanelHandle(boolean b) {
    	jPanel4.removeAll();
    	jPanel4.setLayout(new BorderLayout());
    	if (b) {
            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jpnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jpnChucNang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
    	}else {
    		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPnThongBaoChon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPnThongBaoChon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
    	}
    	jPanel4.repaint();
    	jPanel4.validate();
    	jPanel4.updateUI();
	}

	private void taoJpnThongBaoChon() {
 
    	
    	jPnThongBaoChon.setBackground(new java.awt.Color(230, 219, 209));

        lbThongBaoChon.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        lbThongBaoChon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongBaoChon.setText("<Vui lòng chọn bàn để thanh toán>");

        javax.swing.GroupLayout jPnThongBaoChonLayout = new javax.swing.GroupLayout(jPnThongBaoChon);
        jPnThongBaoChon.setLayout(jPnThongBaoChonLayout);
        jPnThongBaoChonLayout.setHorizontalGroup(
            jPnThongBaoChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnThongBaoChonLayout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(lbThongBaoChon, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(321, Short.MAX_VALUE))
        );
        jPnThongBaoChonLayout.setVerticalGroup(
            jPnThongBaoChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnThongBaoChonLayout.createSequentialGroup()
                .addGap(318, 318, 318)
                .addComponent(lbThongBaoChon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
	}

	private void clearText() {
    	tfGiamGia.setText("");
    	tfGioTao.setText("");
    	tfKhachTra.setText("");
    	tfMaHD.setText("");
    	tfNgayTao.setText("");
    	tfSoDT.setText("");
    	tfThanhTien.setText("");
    	tfTienDu.setText("");
	}

	private void addControls() throws Exception {
		jtbChiTietHD.setFont(new java.awt.Font("Times New Roman", 0, 20)); 
		jtbChiTietHD.setRowHeight(30);
		jtbChiTietHD.setIntercellSpacing(new java.awt.Dimension(15, 0));
		jtbChiTietHD.getTableHeader().setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 18));
    	tfSoDT.setEditable(false);
    	
    	tfKhachTra.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateTienDu();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateTienDu();
			}
			
			private void updateTienDu() {
				double tienDu = 0;
				try {
					tienDu = Double.parseDouble(tfKhachTra.getText().replaceAll("\\D+", "")) - hd.getThanhTien()+hd.getThanhTien()*(hd.getKhachHang().getGiamGia()/100.0);
				}catch (Exception e29) {
					tienDu = 0;
				}
				tfTienDu.setText(decimalFormat.format(tienDu));
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateTienDu();
			}
		});
    	tfKhachTra.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				try {
					tfKhachTra.setText(decimalFormat.format(Double.parseDouble(tfKhachTra.getText().replaceAll("\\D+", ""))));
					
				}catch (Exception e2) {
					tfKhachTra.setText("");
				}
				hd.setTienTra(Double.parseDouble(tfKhachTra.getText().replaceAll("\\D+", "")));
				try {
					hoaDonDAO.updateHoaDon(hd);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				try {
					tfKhachTra.setText(tfKhachTra.getText().replaceAll("\\D+", ""));
				}catch (Exception e1) {
					tfKhachTra.setText("");
				}
				
			}
		});
    	cmbLoaiKH.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (cmbLoaiKH.getSelectedItem().toString().equalsIgnoreCase("Thành Viên"))
					tfSoDT.setEditable(true);
				else tfSoDT.setEditable(false);
				tfSoDT.updateUI();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
    	
    	tfSoDT.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				String sdt = null;
				if (Regex.checkRegex(tfSoDT.getText().trim(), "SDT")) {
					sdt = tfSoDT.getText().trim();
					if (!sdt.isEmpty() && cmbLoaiKH.getSelectedItem().toString().equalsIgnoreCase("Thành Viên")) {
						try {
							System.out.println(sdt);
							KhachHang kh = khachHangDAO.timKhachHangTheoSDT(sdt);
							System.out.println(kh);
							if (kh!=null) {
								hd.setKhachHang(kh);
								hoaDonDAO.updateHoaDon(hd);
								tfGiamGia.setText(kh.getGiamGia()+"%");
								updateThanhTien();
							}else
							{
								JOptionPane.showMessageDialog(null, "Số điện thoại này chưa đăng kí thành viên!!");
								tfGiamGia.setText("0%");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}else 
						JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ!!!");
				}else if (sdt!=null)
					JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ!!!");
				
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {				// TODO Auto-generated method stub
				
			}
		});
    	btnXuatHD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(hd.getTienTra()-(hd.getThanhTien()-hd.getThanhTien()*(hd.getKhachHang().getGiamGia()/100.0))<0) {
					JOptionPane.showMessageDialog(null, "Tiền khách trả chưa đủ");
				}
				else if (!tfKhachTra.getText().isEmpty()) {
					hd.setTienTra(Double.parseDouble(tfKhachTra.getText().replaceAll("\\D+", "")));
					try {
						hoaDonDAO.updateHoaDon(hd);
						hd = banHoaDonDao.timHoaDonTheoBan(maBanNow);
						KhachHang kh = hd.getKhachHang();
						if (xuatHoaDon(hd))
						{
							if (!kh.getIdKhachHang().equalsIgnoreCase("KH0000")) {
								kh.setDiemTichLuy((int) (kh.getDiemTichLuy()+(hoaDonDAO.getThanhTien(hd)/10000)));
								khachHangDAO.updateKhachHang(kh);
							}
							System.out.println(kh);
							
							Ban ban=banDao.timBan(maBanNow);
							ban.setTrangThai(false);
							banDao.capNhapTrangThaiBan(ban);
							loadBan();					
							setPanelHandle(false);
							banHoaDonDao.xoaBanHoaDon(hd);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Chưa nhập tiền khách trả");
				}
			}

			private boolean xuatHoaDon(HoaDon hd) throws Exception {
				String bill = String.format("		       Hóa đơn bàn %s\r\n", hd.getBan().getMaBan()+"")
						+ "\r\n"
						+ String.format("Khách hàng: %s", hd.getKhachHang().getTenKhachHang())
						+ String.format("%70s\r\n", String.format("Giờ bắt đầu: %s  %s",new SimpleDateFormat("HH:mm:ss").format(hoaDonDAO.getGioTao(hd.getMaHoaDon())) ,new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayTao())))
						+ String.format("Nhân viên: %s\r\n", hd.getNhanVien().getTenNhanVien())
						+ "-------------------------------------------------------------------------------------\r\n"
						+ String.format("|%-30s|%-10s|%-20s|%-20s|\n","Tên","Số lượng","Giá","Tổng");
				List<String> ds = chiTietHoaDonDAO.xuatHoaDon(hd.getMaHoaDon());
				for(String s:ds) {
					bill += "-------------------------------------------------------------------------------------\r\n";
					bill+=s+"\n";
				}
				bill += "-------------------------------------------------------------------------------------\r\n";
				bill+= String.format( "%65s%20s\r\n"
									+ "%65s%20s\r\n"
									+ "%65s%20s\r\n"
									+ "%65s%20s\r\n"
									+ "%65s%20s","Tiền hàng:",decimalFormat.format(hd.getThanhTien()),String.format("Giảm giá -%s:" ,hd.getKhachHang().getGiamGia()+"%"),"-"+decimalFormat.format(hd.getThanhTien()*(hd.getKhachHang().getGiamGia()/100.0)),"Thành tiền:",
										decimalFormat.format(hd.getThanhTien()-hd.getThanhTien()*(hd.getKhachHang().getGiamGia()/100.0)),
										"Khách trả:",decimalFormat.format(hd.getTienTra()),"Tiền thừa:",decimalFormat.format(hd.getTienTra()-(hd.getThanhTien()-hd.getThanhTien()*(hd.getKhachHang().getGiamGia()/100.0))));
				System.out.println(bill);
				int b =  JOptionPane.showConfirmDialog(null, "Xuất hóa đơn: \n"+ bill,"Xuất hóa đơn",JOptionPane.YES_NO_OPTION);
				if (b==JOptionPane.YES_OPTION) {
					bill += String.format("\n\n\n\n\n%50s","Cảm ơn và hẹn gặp lại quý khách!!!");
					hd.setThanhTien(hd.getThanhTien()-hd.getThanhTien()*(hd.getKhachHang().getGiamGia()/100.0));
					hoaDonDAO.updateHoaDon(hd);
					CharacterStreamIOBuffered c = new CharacterStreamIOBuffered();
					c.writeToFile(bill, String.format("HoaDon/%d.txt", hd.getMaHoaDon()));
					JOptionPane.showMessageDialog(null, "Thành công!!!!");
					return true;
				}else {
					return false;
				}
			}

		});
   	}
//	private void xuatHoaDon(HoaDon hd, KhachHang kh, String string, String string2, String string3) {
//		Document doc = new Document();
//		ObjToPdf toPdf = new ObjToPdf();
//		
//		doc.loadFromFile("D:\\LTHSK\\quanlycaffe\\quanlycaffe\\quanlycaffe\\docx\\Invoice-Template.docx");
//		doc.replace("#ID1", kh.getIdKhachHang(), true, true);
//		doc.replace("#Cusname", kh.getTenKhachHang(), true, true);
//		doc.replace("#Rank", kh.getHang(), true, true);
//		doc.replace("#Phone", kh.getSoDienThoai(), true, true);
//		doc.replace("#ID2", hd.getMaHoaDon()+"", true, true);
//		doc.replace("#Ngaytao", new SimpleDateFormat("dd/MM/yyy").format(hd.getNgayTao()), true, true);
//		doc.replace("#Nhanvientao",hd.getNhanVien().getTenNhanVien(), true, true);
//		doc.replace("#1",string, true, true);
//		doc.replace("#2",string3, true, true);
//		doc.replace("#3",string2, true, true);
//		
//		try {
//			List<String[]> ds = chiTietHoaDonDAO.xuatHoaDon(hd.getMaHoaDon());
//			String [][]dsString = new String[ds.size()][3];
//			int i = 0;
//			for (String[] s: ds) {
//				dsString[i++] = s;
//			}
//			System.out.println(dsString);
//			toPdf.writeDataToDocument(doc, dsString);
//			// update fields
//			doc.isUpdateFields(true);
//	
//			// save file in pdf format
//			doc.saveToFile("HoaDon/"+hd.getMaHoaDon()+".pdf", FileFormat.PDF);
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	private void loadBan() throws Exception {
		dsBanCon = new ArrayList<>();
		jpnBan.removeAll();
		int col = soBan/2-1;
		jpnBan.setLayout(new GridLayout(col, 3,1,1));
		for (Ban b : banDao.layDanhSachBan()) {
			dsBanCon.add(new JpnBanCon(b,1));
		}
		dsBanCon.forEach(x -> jpnBan.add(x));
		jpnBan.validate();
		jpnBan.repaint();
		jpnBan.updateUI();
		dsBanConActionListener();
	}

	private void dsBanConActionListener() {
		for (JpnBanCon x : dsBanCon) {
			JButton btnBan = x.getBtnBan();
			int maBan = x.getMaBan();
			btnBan.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					try {
						if (banDao.timBan(maBan).isTrangThai())
							btnBan.setBackground(new java.awt.Color(254, 254, 254));
						else
							btnBan.setBackground(new java.awt.Color(125, 125, 125));
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					setPanelHandle(true);
					btnBan.setBackground(new Color(170,219,209));
					
				}
			});
			btnBan.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					maBanNow = maBan;
					try {
						dsBanCon = new ArrayList<>();
						if (banDao.timBan(maBanNow).isTrangThai()) {
							hd = banHoaDonDao.timHoaDonTheoBan(maBanNow);
							fillTF();
							if (hd != null)
								dscthd = chiTietHoaDonDAO.getChiTietHoaDonTheoMaHD(hd.getMaHoaDon());

							updateModelTable(dscthd);
						}else {
							JOptionPane.showMessageDialog(null, "Bàn này chưa có hóa đơn");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

			});
		}
	}
	private void fillTF() throws Exception {
		tfSoDT.setText(hd.getKhachHang().getSoDienThoai());
		cmbLoaiKH.setSelectedItem((hd.getKhachHang().getIdKhachHang().equalsIgnoreCase("KH0000")? "Thường":"Thành Viên"));
		tfMaHD.setText(hd.getMaHoaDon()+"");
		updateThanhTien();
		tfNgayTao.setText(hd.getNgayTao().toString());
		tfGioTao.setText(new SimpleDateFormat("HH:mm").format(hoaDonDAO.getGioTao(hd.getMaHoaDon())));
		tfGiamGia.setText(hd.getKhachHang().getGiamGia()+"%");
	}
	
	

	private void updateModelTable(List<ChiTietHoaDon> dscthd) {
		ChiTietHoaDonTableModel chiTietHoaDonTableModel = new ChiTietHoaDonTableModel(HEADERS,dscthd);
		jtbChiTietHD.setModel(chiTietHoaDonTableModel);
		jtbChiTietHD.updateUI();
	}
	private void updateThanhTien() {
		try {
			Double thanhTien = hoaDonDAO.getThanhTien(hd);
			thanhTien = thanhTien - (thanhTien *hd.getKhachHang().getGiamGia())/100;
			tfThanhTien.setText(decimalFormat.format(thanhTien));			
		}catch (Exception e) {
			tfThanhTien.setText("");
		}		
	}
	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnBan1 = new javax.swing.JButton();
        btnBan2 = new javax.swing.JButton();
        btnBan3 = new javax.swing.JButton();
        btnBan4 = new javax.swing.JButton();
        btnBan5 = new javax.swing.JButton();
        btnBan6 = new javax.swing.JButton();
        btnBan7 = new javax.swing.JButton();
        btnBan8 = new javax.swing.JButton();
        btnBan9 = new javax.swing.JButton();
        btnBan10 = new javax.swing.JButton();
        btnBan11 = new javax.swing.JButton();
        btnBan12 = new javax.swing.JButton();
        btnBan13 = new javax.swing.JButton();
        btnBan14 = new javax.swing.JButton();
        btnBan15 = new javax.swing.JButton();
        btnBan16 = new javax.swing.JButton();
        btnBan17 = new javax.swing.JButton();
        btnBan18 = new javax.swing.JButton();
        btnBan19 = new javax.swing.JButton();
        btnBan20 = new javax.swing.JButton();
        plnHoaDon = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jpnBan = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jlbMaHD = new javax.swing.JLabel();
        tfMaHD = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jlbNgayTao = new javax.swing.JLabel();
        tfNgayTao = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jlbKH = new javax.swing.JLabel();
        cmbLoaiKH = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        jlbGioTao = new javax.swing.JLabel();
        tfGioTao = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jlbSDT = new javax.swing.JLabel();
        tfSoDT = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jlbThanhTien = new javax.swing.JLabel();
        tfThanhTien = new javax.swing.JTextField();
        jlbGiamGia = new javax.swing.JLabel();
        tfGiamGia = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jlbKachTra = new javax.swing.JLabel();
        tfKhachTra = new javax.swing.JTextField();
        jlbTienDu = new javax.swing.JLabel();
        tfTienDu = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbChiTietHD = new javax.swing.JTable();
        jpnChucNang = new javax.swing.JPanel();
        btnXuatHD = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(230, 219, 209));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Bàn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        btnBan1.setBackground(new java.awt.Color(254, 254, 254));
        btnBan1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan1.setForeground(new java.awt.Color(102, 102, 102));
        btnBan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan1.setText("1");
        btnBan1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnBan2.setBackground(new java.awt.Color(254, 254, 254));
        btnBan2.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan2.setForeground(new java.awt.Color(102, 102, 102));
        btnBan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan2.setText("2");
        btnBan2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnBan3.setBackground(new java.awt.Color(254, 254, 254));
        btnBan3.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan3.setForeground(new java.awt.Color(102, 102, 102));
        btnBan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan3.setText("3");
        btnBan3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnBan4.setBackground(new java.awt.Color(254, 254, 254));
        btnBan4.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan4.setForeground(new java.awt.Color(102, 102, 102));
        btnBan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan4.setText("4");
        btnBan4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnBan5.setBackground(new java.awt.Color(254, 254, 254));
        btnBan5.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan5.setForeground(new java.awt.Color(102, 102, 102));
        btnBan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan5.setText("5");
        btnBan5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnBan6.setBackground(new java.awt.Color(230, 219, 209));
        btnBan6.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan6.setForeground(new java.awt.Color(102, 102, 102));
        btnBan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan6.setText("6");
        btnBan6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnBan7.setBackground(new java.awt.Color(230, 219, 209));
        btnBan7.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan7.setForeground(new java.awt.Color(102, 102, 102));
        btnBan7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan7.setText("7");
        btnBan7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnBan8.setBackground(new java.awt.Color(230, 219, 209));
        btnBan8.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan8.setForeground(new java.awt.Color(102, 102, 102));
        btnBan8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan8.setText("8");
        btnBan8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnBan9.setBackground(new java.awt.Color(230, 219, 209));
        btnBan9.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan9.setForeground(new java.awt.Color(102, 102, 102));
        btnBan9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan9.setText("9");
        btnBan9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnBan10.setBackground(new java.awt.Color(230, 219, 209));
        btnBan10.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan10.setForeground(new java.awt.Color(102, 102, 102));
        btnBan10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan10.setText("10");
        btnBan10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnBan11.setBackground(new java.awt.Color(230, 219, 209));
        btnBan11.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan11.setForeground(new java.awt.Color(102, 102, 102));
        btnBan11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan11.setText("11");
        btnBan11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnBan12.setBackground(new java.awt.Color(230, 219, 209));
        btnBan12.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan12.setForeground(new java.awt.Color(102, 102, 102));
        btnBan12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan12.setText("12");
        btnBan12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBan12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBan12ActionPerformed(evt);
            }
        });

        btnBan13.setBackground(new java.awt.Color(230, 219, 209));
        btnBan13.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan13.setForeground(new java.awt.Color(102, 102, 102));
        btnBan13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan13.setText("13");
        btnBan13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBan13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBan13ActionPerformed(evt);
            }
        });

        btnBan14.setBackground(new java.awt.Color(230, 219, 209));
        btnBan14.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan14.setForeground(new java.awt.Color(102, 102, 102));
        btnBan14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan14.setText("14");
        btnBan14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBan14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBan14ActionPerformed(evt);
            }
        });

        btnBan15.setBackground(new java.awt.Color(230, 219, 209));
        btnBan15.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan15.setForeground(new java.awt.Color(102, 102, 102));
        btnBan15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan15.setText("15");
        btnBan15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBan15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBan15ActionPerformed(evt);
            }
        });

        btnBan16.setBackground(new java.awt.Color(230, 219, 209));
        btnBan16.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan16.setForeground(new java.awt.Color(102, 102, 102));
        btnBan16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan16.setText("16");
        btnBan16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBan16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBan16ActionPerformed(evt);
            }
        });

        btnBan17.setBackground(new java.awt.Color(230, 219, 209));
        btnBan17.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan17.setForeground(new java.awt.Color(102, 102, 102));
        btnBan17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan17.setText("17");
        btnBan17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBan17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBan17ActionPerformed(evt);
            }
        });

        btnBan18.setBackground(new java.awt.Color(230, 219, 209));
        btnBan18.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan18.setForeground(new java.awt.Color(102, 102, 102));
        btnBan18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan18.setText("18");
        btnBan18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBan18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBan18ActionPerformed(evt);
            }
        });

        btnBan19.setBackground(new java.awt.Color(230, 219, 209));
        btnBan19.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan19.setForeground(new java.awt.Color(102, 102, 102));
        btnBan19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan19.setText("19");
        btnBan19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBan19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBan19ActionPerformed(evt);
            }
        });

        btnBan20.setBackground(new java.awt.Color(230, 219, 209));
        btnBan20.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnBan20.setForeground(new java.awt.Color(102, 102, 102));
        btnBan20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table_50px.png"))); // NOI18N
        btnBan20.setText("20");
        btnBan20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnBan20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBan20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnBan1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBan2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBan3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBan4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnBan5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBan6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBan7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBan8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnBan9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBan10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBan11, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBan12, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnBan13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBan14, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBan15, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBan16, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnBan17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBan18, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBan19, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBan20, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBan1)
                    .addComponent(btnBan3)
                    .addComponent(btnBan2)
                    .addComponent(btnBan4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBan5)
                    .addComponent(btnBan6)
                    .addComponent(btnBan7)
                    .addComponent(btnBan8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBan9)
                    .addComponent(btnBan10)
                    .addComponent(btnBan11)
                    .addComponent(btnBan12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBan13)
                    .addComponent(btnBan14)
                    .addComponent(btnBan15)
                    .addComponent(btnBan16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBan17)
                    .addComponent(btnBan18)
                    .addComponent(btnBan19)
                    .addComponent(btnBan20))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        plnHoaDon.setBackground(new java.awt.Color(230, 219, 209));

        jPanel3.setBackground(new java.awt.Color(230, 219, 209));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Bàn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        jpnBan.setBackground(new java.awt.Color(230, 219, 209));

        javax.swing.GroupLayout jpnBanLayout = new javax.swing.GroupLayout(jpnBan);
        jpnBan.setLayout(jpnBanLayout);
        jpnBanLayout.setHorizontalGroup(
            jpnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
        );
        jpnBanLayout.setVerticalGroup(
            jpnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 714, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jpnBan);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(230, 219, 209));

        jPanel1.setBackground(new java.awt.Color(230, 219, 209));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        jlbMaHD.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jlbMaHD.setText("Mã Hóa Đơn:");

        tfMaHD.setEditable(false);
        tfMaHD.setBackground(new java.awt.Color(230, 219, 209));
        tfMaHD.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tfMaHD.setText("HD001");
        tfMaHD.setBorder(null);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jlbNgayTao.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jlbNgayTao.setText("Ngày Tạo:");

        tfNgayTao.setEditable(false);
        tfNgayTao.setBackground(new java.awt.Color(230, 219, 209));
        tfNgayTao.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tfNgayTao.setText("22/02/2022");
        tfNgayTao.setBorder(null);

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jlbKH.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jlbKH.setText("Khách Hàng:");

        cmbLoaiKH.setBackground(new java.awt.Color(230, 219, 209));
        cmbLoaiKH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cmbLoaiKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thường", "Thành Viên" }));
        cmbLoaiKH.setBorder(null);

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jlbGioTao.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jlbGioTao.setText("Giờ Tạo:");

        tfGioTao.setEditable(false);
        tfGioTao.setBackground(new java.awt.Color(230, 219, 209));
        tfGioTao.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tfGioTao.setText("15h00");
        tfGioTao.setBorder(null);

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));

        jlbSDT.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jlbSDT.setText("Điện Thoại:");

        tfSoDT.setBackground(new java.awt.Color(230, 219, 209));
        tfSoDT.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tfSoDT.setText("0366422258");
        tfSoDT.setBorder(null);

        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));

        jlbThanhTien.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jlbThanhTien.setText("Thành Tiền:");

        tfThanhTien.setEditable(false);
        tfThanhTien.setBackground(new java.awt.Color(230, 219, 209));
        tfThanhTien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tfThanhTien.setText("200.000");
        tfThanhTien.setBorder(null);

        jlbGiamGia.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jlbGiamGia.setText("Giảm Giá:");

        tfGiamGia.setEditable(false);
        tfGiamGia.setBackground(new java.awt.Color(230, 219, 209));
        tfGiamGia.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tfGiamGia.setText("10%");
        tfGiamGia.setBorder(null);

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        jlbKachTra.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jlbKachTra.setText("Khách Trả:");

        tfKhachTra.setBackground(new java.awt.Color(230, 219, 209));
        tfKhachTra.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tfKhachTra.setText("500.000");
        tfKhachTra.setBorder(null);

        jlbTienDu.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jlbTienDu.setText("Tiền Dư:");

        tfTienDu.setEditable(false);
        tfTienDu.setBackground(new java.awt.Color(230, 219, 209));
        tfTienDu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tfTienDu.setText("320.000");
        tfTienDu.setBorder(null);

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Chi Tiết Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        jtbChiTietHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtbChiTietHD);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jlbKachTra)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfKhachTra))
                            .addComponent(jSeparator4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jlbThanhTien)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfThanhTien))
                            .addComponent(jSeparator3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jlbKH)
                                .addGap(18, 18, 18)
                                .addComponent(cmbLoaiKH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jSeparator2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jlbNgayTao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfNgayTao))
                            .addComponent(jSeparator1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jlbMaHD)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator6))
                        .addGap(102, 102, 102)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jlbSDT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfSoDT))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jlbTienDu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfTienDu))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jlbGiamGia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jlbGioTao, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfGioTao, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbMaHD)
                            .addComponent(tfMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbNgayTao)
                            .addComponent(tfNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbGioTao)
                            .addComponent(tfGioTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbKH)
                            .addComponent(cmbLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbSDT)
                            .addComponent(tfSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbThanhTien)
                    .addComponent(tfThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbGiamGia)
                    .addComponent(tfGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlbKachTra)
                        .addComponent(tfKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlbTienDu)
                        .addComponent(tfTienDu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jpnChucNang.setBackground(new java.awt.Color(230, 219, 209));

        btnXuatHD.setBackground(new java.awt.Color(230, 219, 209));
        btnXuatHD.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnXuatHD.setText("Xuất HĐ");
        btnXuatHD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout jpnChucNangLayout = new javax.swing.GroupLayout(jpnChucNang);
        jpnChucNang.setLayout(jpnChucNangLayout);
        jpnChucNangLayout.setHorizontalGroup(
            jpnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnChucNangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXuatHD, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpnChucNangLayout.setVerticalGroup(
            jpnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnChucNangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXuatHD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnChucNang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout plnHoaDonLayout = new javax.swing.GroupLayout(plnHoaDon);
        plnHoaDon.setLayout(plnHoaDonLayout);
        plnHoaDonLayout.setHorizontalGroup(
            plnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plnHoaDonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        plnHoaDonLayout.setVerticalGroup(
            plnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(plnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plnHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>                        

    private void btnBan12ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void btnBan13ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void btnBan14ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void btnBan15ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void btnBan16ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void btnBan17ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void btnBan18ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void btnBan19ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void btnBan20ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        


    // Variables declaration - do not modify                     
    private javax.swing.JButton btnBan1;
    private javax.swing.JButton btnBan10;
    private javax.swing.JButton btnBan11;
    private javax.swing.JButton btnBan12;
    private javax.swing.JButton btnBan13;
    private javax.swing.JButton btnBan14;
    private javax.swing.JButton btnBan15;
    private javax.swing.JButton btnBan16;
    private javax.swing.JButton btnBan17;
    private javax.swing.JButton btnBan18;
    private javax.swing.JButton btnBan19;
    private javax.swing.JButton btnBan2;
    private javax.swing.JButton btnBan20;
    private javax.swing.JButton btnBan3;
    private javax.swing.JButton btnBan4;
    private javax.swing.JButton btnBan5;
    private javax.swing.JButton btnBan6;
    private javax.swing.JButton btnBan7;
    private javax.swing.JButton btnBan8;
    private javax.swing.JButton btnBan9;
    private javax.swing.JButton btnXuatHD;
    private javax.swing.JComboBox<String> cmbLoaiKH;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel jlbGiamGia;
    private javax.swing.JLabel jlbGioTao;
    private javax.swing.JLabel jlbKH;
    private javax.swing.JLabel jlbKachTra;
    private javax.swing.JLabel jlbMaHD;
    private javax.swing.JLabel jlbNgayTao;
    private javax.swing.JLabel jlbSDT;
    private javax.swing.JLabel jlbThanhTien;
    private javax.swing.JLabel jlbTienDu;
    private javax.swing.JPanel jpnBan;
    private javax.swing.JPanel jpnChucNang;
    private javax.swing.JTable jtbChiTietHD;
    private javax.swing.JPanel plnHoaDon;
    private javax.swing.JTextField tfGiamGia;
    private javax.swing.JTextField tfGioTao;
    private javax.swing.JTextField tfKhachTra;
    private javax.swing.JTextField tfMaHD;
    private javax.swing.JTextField tfNgayTao;
    private javax.swing.JTextField tfSoDT;
    private javax.swing.JTextField tfThanhTien;
    private javax.swing.JTextField tfTienDu;
    // End of variables declaration                   
}
