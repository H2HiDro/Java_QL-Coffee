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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;

import dao.BanDAO;
import dao.BanHoaDonDao;
import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import entity.Ban;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import tablemodel.ChiTietHoaDonTableModel;

/**
 *
 * @author hieud
 */
public class JpnBan extends javax.swing.JPanel {
	private static final String[] HEADER = "Tên Sản Phẩm;Số Lượng;Đơn giá;Thành Tiền".split(";");
	private int soBan;
	private BanDAO banDao;
	private HoaDon hd;
	private KhachHangDAO khachHangDAO;
	private NhanVienDAO nhanVienDAO;
	private HoaDonDAO hoaDonDAO;
	private JpnBanCon jpnBanCon;
	private FrmMenu frmMenu;
	private ChiTietHoaDonTableModel chiTietHoaDonTableModel;
	private List<JpnBanCon> dsBanCon;
	private ChiTietHoaDonDAO chiTietHoaDonDAO;
	private List<ChiTietHoaDon> dscthd;
	private int maBanNow;
	private BanHoaDonDao banHoaDonDao;
	private NhanVien nv;

	/**
	 * Creates new form jpnBan
	 */
	public JpnBan(NhanVien nv) {
		this.nv = nv;
		maBanNow =-1;
		khachHangDAO = new KhachHangDAO();
		chiTietHoaDonDAO = new ChiTietHoaDonDAO();
		hoaDonDAO = new HoaDonDAO();
		nhanVienDAO = new NhanVienDAO();
		dsBanCon = new ArrayList<JpnBanCon>();
		dscthd = new ArrayList<>();
		banHoaDonDao = new BanHoaDonDao();
		banDao = new BanDAO();
		try {
			soBan = banDao.layDanhSachBan().size();
			initComponents();
			addControls();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		plnBan.removeAll();
//		plnBan.setLayout(new BorderLayout());
//		plnBan.validate();
//		plnBan.updateUI();
	}

	private void addControls() throws Exception {
		
		jtbDonHang.setFont(new java.awt.Font("Times New Roman", 0, 20)); 
		jtbDonHang.setRowHeight(30);
		jtbDonHang.setIntercellSpacing(new java.awt.Dimension(15, 0));
		jtbDonHang.getTableHeader().setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 18));
		loadBan();

		jpnBanScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jpnBanScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		// event thêm bàn
		btnThemBan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Ban ban = new Ban(-1,false);
				try {
					banDao.themBan(ban);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					loadBan();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// event xóa bàn

		// event xem thông tin bàn

		dsBanConActionListener();
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					chiTietHoaDonDAO.xoaChiTietHoaDon(dscthd.get(jtbDonHang.getSelectedRow()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dscthd.remove(jtbDonHang.getSelectedRow());
				updateModelTable(dscthd);
				JOptionPane.showMessageDialog(null, "Xóa thành công!!");
			}
		});
		BtnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frmMenu = new FrmMenu(hd);
				frmMenu.setLocationRelativeTo(null);
				frmMenu.setVisible(true);
				frmMenu.addWindowListener(new WindowListener() {

					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowClosing(WindowEvent e) {
						try {
							if (hd != null)
								dscthd = chiTietHoaDonDAO.getChiTietHoaDonTheoMaHD(hd.getMaHoaDon());
							else
								dscthd = new ArrayList<>();
							hd.setThanhTien(hoaDonDAO.getThanhTien(hd));
							hoaDonDAO.updateHoaDon(hd);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						updateModelTable(dscthd);
						JOptionPane.showMessageDialog(null, "Thêm thành công!!");

					}

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub

					}
				});
			}
		});
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Ban ban = banDao.timBan(maBanNow);
					hd = new HoaDon(hoaDonDAO.timMaMoiNhat()+1, new Date(), khachHangDAO.timKhachHang("KH0000"), nv, 0, 0, ban);
					hoaDonDAO.themHoaDon(hd);
					banHoaDonDao.themBanHoaDon(hd);
					ban.setTrangThai(true);
					banDao.capNhapTrangThaiBan(ban);
					loadBan();
					addJpnDatBan(true);
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn hoặc chọn mang về","Chưa chọn bàn", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		};
		btnTaoDon.addActionListener(listener);
	}

	private void loadBan() throws Exception {
		dsBanCon = new ArrayList<>();
		jpnBan.removeAll();
		int col = soBan / 2 -1;
		jpnBan.setLayout(new GridLayout(col, 2));
		for (Ban b : banDao.layDanhSachBan()) {
			dsBanCon.add(new JpnBanCon(b,0));
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
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					btnBan.setBackground(new Color(170,219,209));
					
				}
			});
			btnBan.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					maBanNow = maBan;
					if (maBanNow!=0)
					tfBan.setText(maBanNow + "");
					else
						tfBan.setText("Mang về");
					try {
						dsBanCon = new ArrayList<>();
						if (banDao.timBan(maBanNow).isTrangThai()) {
							addJpnDatBan(true);
							hd = banHoaDonDao.timHoaDonTheoBan(maBanNow);
							if (hd != null)
								dscthd = chiTietHoaDonDAO.getChiTietHoaDonTheoMaHD(hd.getMaHoaDon());

							updateModelTable(dscthd);
						} else {
							addJpnDatBan(false);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
	}


	private void addJpnDatBan(boolean b) {
		jPanel3.removeAll();
		if (b) {
			javax.swing.GroupLayout plnBanLayout = new javax.swing.GroupLayout(plnBan);
	        plnBan.setLayout(plnBanLayout);
	        plnBanLayout.setHorizontalGroup(
	            plnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(plnBanLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(782, Short.MAX_VALUE))
	            .addGroup(plnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(plnBanLayout.createSequentialGroup()
	                    .addGap(562, 562, 562)
	                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addContainerGap()))
	        );
	        plnBanLayout.setVerticalGroup(
	            plnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(plnBanLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	            .addGroup(plnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(plnBanLayout.createSequentialGroup()
	                    .addContainerGap()
	                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
	        );

			javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
	        jPanel3.setLayout(jPanel3Layout);
	        jPanel3Layout.setHorizontalGroup(
	            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 0, Short.MAX_VALUE)
	            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(jPanel3Layout.createSequentialGroup()
	                    .addContainerGap()
	                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
	        );
	        jPanel3Layout.setVerticalGroup(
	            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 0, Short.MAX_VALUE)
	            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(jPanel3Layout.createSequentialGroup()
	                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addContainerGap()))
	        );
		}
		else {
			javax.swing.GroupLayout plnBanLayout = new javax.swing.GroupLayout(plnBan);
	        plnBan.setLayout(plnBanLayout);
	        plnBanLayout.setHorizontalGroup(
	            plnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(plnBanLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(782, Short.MAX_VALUE))
	            .addGroup(plnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(plnBanLayout.createSequentialGroup()
	                    .addGap(562, 562, 562)
	                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addContainerGap()))
	        );
	        plnBanLayout.setVerticalGroup(
	            plnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(plnBanLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	            .addGroup(plnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(plnBanLayout.createSequentialGroup()
	                    .addContainerGap()
	                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
	        );

			javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
	        jPanel3.setLayout(jPanel3Layout);
	        jPanel3Layout.setHorizontalGroup(
	            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 0, Short.MAX_VALUE)
	            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(jPanel3Layout.createSequentialGroup()
	                    .addContainerGap()
	                    .addComponent(jPanelXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
	        );
	        jPanel3Layout.setVerticalGroup(
	            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 0, Short.MAX_VALUE)
	            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(jPanel3Layout.createSequentialGroup()
	                    .addComponent(jPanelXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addContainerGap()))
	        );
			updateModelTable(new ArrayList<>());
		}
		jPanel3.validate();
		jPanel3.repaint();
		jPanel3.updateUI();
	}

	private void updateModelTable(List<ChiTietHoaDon> dscthd1) {
		chiTietHoaDonTableModel = new ChiTietHoaDonTableModel(HEADER, dscthd1);
		jtbDonHang.setModel(chiTietHoaDonTableModel);
		jtbDonHang.updateUI();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        plnBan = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnThemBan = new javax.swing.JButton();
        jpnBanScroll = new javax.swing.JScrollPane();
        jpnBan = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbDonHang = new javax.swing.JTable();
        plnChucNang = new javax.swing.JPanel();
        BtnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        BtnXuatHD = new javax.swing.JButton();
        jlbBan = new javax.swing.JLabel();
        tfBan = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnTaoDon = new javax.swing.JButton();
        jPanelXacNhan = new javax.swing.JPanel();
        setPreferredSize(new java.awt.Dimension(1343, 750));
        

        plnBan.setBackground(new java.awt.Color(230, 219, 209));

        jPanel1.setBackground(new java.awt.Color(230, 219, 209));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Bàn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        btnThemBan.setBackground(new java.awt.Color(230, 219, 209));
        btnThemBan.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnThemBan.setText("Thêm Bàn");
        btnThemBan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jpnBan.setBackground(new java.awt.Color(230, 219, 209));
        jPanelXacNhan.setBackground(new java.awt.Color(230, 219, 209));
        jPanelXacNhan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        javax.swing.GroupLayout jpnBanLayout = new javax.swing.GroupLayout(jpnBan);
        jpnBan.setLayout(jpnBanLayout);
        jpnBanLayout.setHorizontalGroup(
            jpnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 597, Short.MAX_VALUE)
        );
        jpnBanLayout.setVerticalGroup(
            jpnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 785, Short.MAX_VALUE)
        );
        btnTaoDon.setBackground(new java.awt.Color(230, 219, 209));
        btnTaoDon.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnTaoDon.setText("Tạo đơn hàng mới");
        btnTaoDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        
        jpnBanScroll.setViewportView(jpnBan);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemBan, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpnBanScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnBanScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThemBan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(230, 219, 209));

        jPanel2.setBackground(new java.awt.Color(230, 219, 209));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Đơn Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        jtbDonHang.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbDonHang);

        plnChucNang.setBackground(new java.awt.Color(230, 219, 209));
        plnChucNang.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N

        BtnThem.setBackground(new java.awt.Color(230, 219, 209));
        BtnThem.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        BtnThem.setForeground(new java.awt.Color(102, 102, 102));
        BtnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Plus_30px.png"))); // NOI18N
        BtnThem.setText("Thêm");
        BtnThem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnXoa.setBackground(new java.awt.Color(230, 219, 209));
        btnXoa.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(102, 102, 102));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Recycle Bin_30px.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        BtnXuatHD.setBackground(new java.awt.Color(230, 219, 209));
        BtnXuatHD.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        BtnXuatHD.setForeground(new java.awt.Color(102, 102, 102));
        BtnXuatHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/activity_history_30px.png"))); // NOI18N
        BtnXuatHD.setText("Thông tin khách hàng");
        BtnXuatHD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout plnChucNangLayout = new javax.swing.GroupLayout(plnChucNang);
        plnChucNang.setLayout(plnChucNangLayout);
        plnChucNangLayout.setHorizontalGroup(
            plnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnChucNangLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnXuatHD, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );
        plnChucNangLayout.setVerticalGroup(
            plnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnChucNangLayout.createSequentialGroup()
                .addGroup(plnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnThem)
                    .addComponent(btnXoa)
                    .addComponent(BtnXuatHD))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jlbBan.setBackground(new java.awt.Color(230, 219, 209));
        jlbBan.setFont(new java.awt.Font("Times New Roman", 2, 20)); // NOI18N
        jlbBan.setText("Bàn:");

        tfBan.setEditable(false);
        tfBan.setBackground(new java.awt.Color(230, 219, 209));
        tfBan.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        tfBan.setText("1");
        tfBan.setBorder(null);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        javax.swing.GroupLayout jPanelXacNhanLayout = new javax.swing.GroupLayout(jPanelXacNhan);
        jPanelXacNhan.setLayout(jPanelXacNhanLayout);
        jPanelXacNhanLayout.setHorizontalGroup(
            jPanelXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelXacNhanLayout.createSequentialGroup()
                .addContainerGap(277, Short.MAX_VALUE)
                .addComponent(btnTaoDon, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(273, 273, 273))
        );
        jPanelXacNhanLayout.setVerticalGroup(
            jPanelXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelXacNhanLayout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addComponent(btnTaoDon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(405, Short.MAX_VALUE))
        );
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jlbBan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfBan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                        .addComponent(plnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbBan)
                            .addComponent(tfBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(plnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 775, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelXacNhan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 744, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addComponent(jPanelXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout plnBanLayout = new javax.swing.GroupLayout(plnBan);
        plnBan.setLayout(plnBanLayout);
        plnBanLayout.setHorizontalGroup(
            plnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnBanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(782, Short.MAX_VALUE))
            .addGroup(plnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(plnBanLayout.createSequentialGroup()
                    .addGap(562, 562, 562)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        plnBanLayout.setVerticalGroup(
            plnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnBanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(plnBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(plnBanLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plnBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plnBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JButton BtnThem;
    private javax.swing.JButton BtnXuatHD;
    private javax.swing.JButton btnThemBan;
    private javax.swing.JButton btnXoa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlbBan;
    private javax.swing.JPanel jpnBan;
    private javax.swing.JScrollPane jpnBanScroll;
    private javax.swing.JTable jtbDonHang;
    private javax.swing.JPanel plnBan;
    private javax.swing.JPanel plnChucNang;
    private javax.swing.JTextField tfBan;
    private javax.swing.JButton btnTaoDon;
    private javax.swing.JPanel jPanelXacNhan;
    // End of variables declaration                   
}
