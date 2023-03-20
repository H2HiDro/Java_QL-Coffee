/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.raven.chart.ModelChart;
import com.toedter.calendar.JYearChooser;

import dao.ThongKeDAO;

/**
 *
 * @author hieud
 */
public class JpnThongKe extends javax.swing.JPanel {

	/**
	 * Creates new form jpnThongKe
	 */
	private ThongKeDAO thongKeDAO;
	private JPanel jpnChonNam;
	private JLabel jTitle2;
	private JPanel jpnChonMucCanThongKe;
	private JYearChooser jYearChooser;

	public JpnThongKe() {
		thongKeDAO = new ThongKeDAO();
		initComponents();
		initJpanel();
		addQuestion(jPanel3);
		addControls();
	}

	private void initJpanel() {

		btnXacNhan = new javax.swing.JButton();
		btnXacNhan1 = new javax.swing.JButton();
		jpnCHonThangNam = new javax.swing.JPanel();
		jlbThang = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox<>();
		jSeparator1 = new javax.swing.JSeparator();
		jlbChonNam = new javax.swing.JLabel();
		jYearChooser = new com.toedter.calendar.JYearChooser();
		jYearChooser1 = new com.toedter.calendar.JYearChooser();
		jSeparator2 = new javax.swing.JSeparator();
		btnHuy = new javax.swing.JButton();
		btnHuy1 = new javax.swing.JButton();
		btnXacNhan = new javax.swing.JButton();
		jTitle1 = new javax.swing.JLabel();

		btnXacNhan1.setBackground(new java.awt.Color(230, 219, 209));
		btnXacNhan1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
		btnXacNhan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close_30px.png"))); // NOI18N
		btnXacNhan1.setText("Xác nhận");
		btnXacNhan1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

		jpnCHonThangNam.setBackground(new java.awt.Color(230, 219, 209));

		jlbThang.setFont(new java.awt.Font("Times New Roman", 2, 20)); // NOI18N
		jlbThang.setText("Tháng:");

		jComboBox1.setBackground(new java.awt.Color(230, 219, 209));
		jComboBox1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "Chọn tháng", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		jComboBox1.setBorder(null);

		jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

		jlbChonNam.setFont(new java.awt.Font("Times New Roman", 2, 20)); // NOI18N
		jlbChonNam.setText("Năm:");

		jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
		btnHuy.setBackground(new java.awt.Color(230, 219, 209));
		btnHuy.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
		btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close_30px.png"))); // NOI18N
		btnHuy.setText("Hủy");
		btnHuy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
		btnHuy1.setBackground(new java.awt.Color(230, 219, 209));
		btnHuy1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
		btnHuy1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close_30px.png"))); // NOI18N
		btnHuy1.setText("Hủy");
		btnHuy1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

		btnXacNhan.setBackground(new java.awt.Color(230, 219, 209));
		btnXacNhan.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
		btnXacNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/done_30px.png"))); // NOI18N
		btnXacNhan.setText("Xác Nhận");
		btnXacNhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

		jTitle1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
		jTitle1.setText("Vui lòng chọn tháng và năm cần thống kê");

		javax.swing.GroupLayout jpnCHonThangNamLayout = new javax.swing.GroupLayout(jpnCHonThangNam);
		jpnCHonThangNam.setLayout(jpnCHonThangNamLayout);
		jpnCHonThangNamLayout.setHorizontalGroup(jpnCHonThangNamLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jpnCHonThangNamLayout.createSequentialGroup().addGap(237, 237, 237)
						.addGroup(jpnCHonThangNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jpnCHonThangNamLayout.createSequentialGroup().addGap(16, 16, 16)
										.addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(101, 101, 101).addComponent(btnXacNhan,
												javax.swing.GroupLayout.PREFERRED_SIZE, 150,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(jpnCHonThangNamLayout.createSequentialGroup().addGroup(jpnCHonThangNamLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(jSeparator1)
										.addGroup(jpnCHonThangNamLayout.createSequentialGroup().addComponent(jlbThang)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(61, 61, 61)
										.addGroup(jpnCHonThangNamLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addGroup(jpnCHonThangNamLayout.createSequentialGroup()
														.addComponent(jlbChonNam)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(jYearChooser1,
																javax.swing.GroupLayout.PREFERRED_SIZE, 120,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 176,
														javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnCHonThangNamLayout.createSequentialGroup()
						.addContainerGap(106, Short.MAX_VALUE).addComponent(jTitle1).addGap(73, 73, 73)));
		jpnCHonThangNamLayout.setVerticalGroup(jpnCHonThangNamLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jpnCHonThangNamLayout.createSequentialGroup().addGap(225, 225, 225).addComponent(jTitle1)
						.addGap(39, 39, 39)
						.addGroup(jpnCHonThangNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jpnCHonThangNamLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jlbThang, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jlbChonNam, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(jYearChooser1, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(8, 8, 8)
						.addGroup(jpnCHonThangNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jpnCHonThangNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(btnHuy).addComponent(btnXacNhan))
						.addContainerGap(334, Short.MAX_VALUE)));
		jTitle2 = new javax.swing.JLabel();
		jpnChonNam = new javax.swing.JPanel();
		// pnl chon nam
		jTitle2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
		jTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jTitle2.setText("Vui lòng chọn năm cần thống kê");
		jpnChonNam.setBackground(new java.awt.Color(230, 219, 209));
		javax.swing.GroupLayout jpnChonNamLayout = new javax.swing.GroupLayout(jpnChonNam);
		jpnChonNam.setLayout(jpnChonNamLayout);
		jpnChonNamLayout.setHorizontalGroup(
				jpnChonNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								jpnChonNamLayout
										.createSequentialGroup().addGap(106, 106, 106)
										.addComponent(
												jTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
										.addGap(73, 73, 73))
						.addGroup(jpnChonNamLayout.createSequentialGroup().addGroup(jpnChonNamLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jpnChonNamLayout.createSequentialGroup().addGap(374, 374, 374)
										.addGroup(jpnChonNamLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 176,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(jpnChonNamLayout.createSequentialGroup()
														.addComponent(jlbChonNam)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(jYearChooser,
																javax.swing.GroupLayout.PREFERRED_SIZE, 120,
																javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addGroup(jpnChonNamLayout.createSequentialGroup().addGap(253, 253, 253)
										.addComponent(btnHuy1, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(101, 101, 101).addComponent(btnXacNhan1,
												javax.swing.GroupLayout.PREFERRED_SIZE, 150,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jpnChonNamLayout.setVerticalGroup(jpnChonNamLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jpnChonNamLayout.createSequentialGroup().addGap(225, 225, 225)
						.addComponent(jTitle2, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jpnChonNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jlbChonNam, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jYearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jpnChonNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnHuy1).addComponent(btnXacNhan1))
						.addGap(341, 341, 341)));

		//
		jpnChonMucCanThongKe = new javax.swing.JPanel();
		jpnChonMucCanThongKe.setBackground(new java.awt.Color(230, 219, 209));

		javax.swing.GroupLayout jpnChonMucCanThongKeLayout = new javax.swing.GroupLayout(jpnChonMucCanThongKe);
		jpnChonMucCanThongKe.setLayout(jpnChonMucCanThongKeLayout);
		jpnChonMucCanThongKeLayout.setHorizontalGroup(
				jpnChonMucCanThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						jpnChonMucCanThongKeLayout.createSequentialGroup().addGap(106, 106, 106)
								.addComponent(jTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
								.addGap(73, 73, 73)));
		jpnChonMucCanThongKeLayout.setVerticalGroup(jpnChonMucCanThongKeLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jpnChonMucCanThongKeLayout.createSequentialGroup().addGap(225, 225, 225).addComponent(jTitle2,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(437, 437, 437)));
		
		//
		
		jpnChonNgay = new javax.swing.JPanel();
        jlbNBD = new javax.swing.JLabel();
        jdcTuNgay = new com.toedter.calendar.JDateChooser();
        jlbNKT = new javax.swing.JLabel();
        jdcDenNgay = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnXacNhan3 = new javax.swing.JButton();
        btnHuy3 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        jpnChonNgay.setBackground(new java.awt.Color(230, 219, 209));

        jlbNBD.setFont(new java.awt.Font("Times New Roman", 2, 20)); // NOI18N
        jlbNBD.setText("Từ ngày:");

//        jdcTuNgay.setDateFormatString("dd/MM/YYY");
        jdcTuNgay.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N

        jlbNKT.setFont(new java.awt.Font("Times New Roman", 2, 20)); // NOI18N
        jlbNKT.setText("Đến ngày:");

//        jdcDenNgay.setDateFormatString("dd/MM/YYY");
        jdcDenNgay.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        btnXacNhan3.setBackground(new java.awt.Color(230, 219, 209));
        btnXacNhan3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnXacNhan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/done_30px.png"))); // NOI18N
        btnXacNhan3.setText("Xác Nhận");
        btnXacNhan3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnHuy3.setBackground(new java.awt.Color(230, 219, 209));
        btnHuy3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnHuy3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close_30px.png"))); // NOI18N
        btnHuy3.setText("Hủy");
        btnHuy3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setText("Vui lòng chọn ngày bắt đầu và ngày kết thúc");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel22.setText("(Ngày bắt đầu và kế thúc không cách nhau quá 7 ngày)");

        javax.swing.GroupLayout jpnChonNgayLayout = new javax.swing.GroupLayout(jpnChonNgay);
        jpnChonNgay.setLayout(jpnChonNgayLayout);
        jpnChonNgayLayout.setHorizontalGroup(
            jpnChonNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnChonNgayLayout.createSequentialGroup()
                .addGroup(jpnChonNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnChonNgayLayout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(btnHuy3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(btnXacNhan3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnChonNgayLayout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addGroup(jpnChonNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnChonNgayLayout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnChonNgayLayout.createSequentialGroup()
                                .addComponent(jlbNBD)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnChonNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addGroup(jpnChonNgayLayout.createSequentialGroup()
                                        .addComponent(jdcTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jlbNKT)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jdcDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jpnChonNgayLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel13)))
                .addGap(54, 188, Short.MAX_VALUE))
        );
        jpnChonNgayLayout.setVerticalGroup(
            jpnChonNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnChonNgayLayout.createSequentialGroup()
                .addContainerGap(177, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addGap(29, 29, 29)
                .addGroup(jpnChonNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jdcTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbNBD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbNKT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnChonNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jpnChonNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy3)
                    .addComponent(btnXacNhan3))
                .addGap(215, 215, 215))
        );
	}

	private void addControls() {
		cmbDoanhThu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (cmbDoanhThu.getSelectedIndex()) {
				case 3:

					addQuestion(jpnChonNam);
					btnXacNhan1.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int nam = jYearChooser.getValue();
							Color color = new Color(135, 189, 245);
							List<List<String>> ds = null;
							try {
								ds = thongKeDAO.getThongKeTheoNam(nam);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							updatePanel("BIỂU ĐỒ DOANH THU TRONG NĂM " + nam, "", "Tháng", color, ds);

							addQuestion(jPanel3);
							System.out.println(nam);
						}
					});

					break;
				case 2:

					addQuestion(jpnCHonThangNam);
					btnXacNhan.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int nam = jYearChooser.getValue();
							int thang = jComboBox1.getSelectedIndex();
							if (thang == 0)
								JOptionPane.showMessageDialog(null, "Vui lòng chọn tháng!!!");
							else {
								Color color = new Color(135, 189, 245);
								List<List<String>> ds = null;
								try {
									ds = thongKeDAO.getThongKeTheoNamThang(nam, thang);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								updatePanel("BIỂU ĐỒ DOANH THU TRONG THÁNG " + thang + " NĂM " + nam, "", "Tuần", color,
										ds);

								addQuestion(jPanel3);
							}
						}
					});
					break;
				case 1:
					addQuestion(jpnChonNgay);
					btnXacNhan3.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							String nbd = new SimpleDateFormat("yyyy-MM-dd").format(jdcTuNgay.getDate());
							String nkt = new SimpleDateFormat("yyyy-MM-dd").format(jdcDenNgay.getDate());
							List<List<String>> ds= null;
							try {
								ds = thongKeDAO.getDoanhThuTheoKhoangNgay(nbd,nkt);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							updatePanel("BIỂU ĐỒ DOANH THU TỪ NGÀY "+nbd+" ĐẾN NGÀY "+nkt, "","Ngày" ,new Color(135, 189, 245), ds);
							addQuestion(jPanel3);
						}
					});
					break;
				default:
					System.out.println(cmbDoanhThu.getSelectedIndex());
					break;
				}

			}
		});
//	jButton4.addActionListener(new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			updateTopSanPhamMonth();
//			String fromDate = new  SimpleDateFormat("dd/MM/yyyy").format(new Date());
//			String toDate = new SimpleDateFormat("dd/MM/yyyy").format(thongKeDAO.getNgayCachNgayHienTai(new Date()));
//			jLabel2.setText(String.format("Từ ngày %s đến ngày %s",toDate,fromDate ));
//			
//		}
//
//		private void updateTopSanPhamMonth() {
//			Color color = new Color(245, 189, 135);
//			List<List<String>> ds = null;
//			try {
//				ds = thongKeDAO.getTongHangDuocMua();
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			updatePanel("BIỂU ĐỒ TOP 7 SẢN PHẨM ĐƯỢC MUA NHIỀU NHẤT","","Số lượng",color,ds);
//		}
//
//	});
//	jButton3.addActionListener(new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			Color color = new Color(135, 189, 245);
//			List<List<String>> ds = null;
//			try {
//				ds = thongKeDAO.getThongKeTheoNam(2022);
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			updatePanel("BIỂU ĐỒ DOANH THU TỪNG THÁNG","Năm 2022","Tháng",color,ds);
//		}
//
//		
//	});
//	jButton5.addActionListener(new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			Color color = new Color(135, 189, 245);
//			List<List<String>> ds = null;
//			try {
//				ds = thongKeDAO.getTopNhanVien();
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			updatePanel("BIỂU ĐỒ TOP 10 NHÂN VIÊN CÓ LƯƠNG CAO NHẤT","","Mã nhân viên",color,ds);
//		}
//	});
	}

	private void updatePanel(String title, String subTitle, String legend, Color color, List<List<String>> ds) {
		jLabel1.setText(title);
		jLabel2.setText(subTitle);
		chart = new com.raven.chart.Chart();
		resetChart();
		chart.addLegend(legend, color);
		try {
			for (List<String> m : ds) {
				chart.addData(new ModelChart(m.get(0), new double[] { Double.parseDouble(m.get(1)) }));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addQuestion(JPanel jpanel) {
		jPanel1.removeAll();
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jpanel,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jpanel,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		jPanel1.updateUI();
	}

	private void resetChart() {
		jPanel3.removeAll();
		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE))
						.addContainerGap())
				.addGroup(jPanel3Layout.createSequentialGroup().addGap(310, 310, 310).addComponent(jLabel2)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout
						.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel2).addGap(27, 27, 27).addComponent(chart,
								javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		jPanel3.updateUI();
	}

	@SuppressWarnings("unchecked")
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
	private void initComponents() {

		plnThongKe = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		chart = new com.raven.chart.Chart();
		jPanel2 = new javax.swing.JPanel();
		cmbDoanhThu = new javax.swing.JComboBox<>();
		cmbSanPhamHot = new javax.swing.JComboBox<>();
		cmbNhanVien = new javax.swing.JComboBox<>();
		cmbSLSanPham = new javax.swing.JComboBox<>();

		plnThongKe.setBackground(new java.awt.Color(230, 219, 209));

		jPanel3.setBackground(new java.awt.Color(254, 254, 254));

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("VUI LÒNG CHỌN 1 OPTION CẦN THỐNG KÊ");

		jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
		jLabel2.setText("Từ ngày 21/10/2022 đến ngày 21/11/2022");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addGap(310, 310, 310).addComponent(jLabel2)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
								.addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel3Layout.createSequentialGroup().addContainerGap(26, Short.MAX_VALUE).addComponent(jLabel1)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel2).addGap(18, 18, 18)
								.addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 580,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(9, 9, 9)));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jPanel3,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jPanel3,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));

		cmbDoanhThu.setBackground(new java.awt.Color(254, 254, 254));
		cmbDoanhThu.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "Thống kê doanh thu", "Thống kê doanh thu theo ngày",
						"Thống kê doanh thu theo tháng", "Thống kê doanh thu theo năm" }));

		cmbSanPhamHot.setBackground(new java.awt.Color(254, 254, 254));
		cmbSanPhamHot.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thống kê sản phẩm bán chạy",
				"Thống kê sản phẩm bán chạy theo ngày", "Thống kê sản phẩm bán chạy theo tuần",
				"Thống kê sản phẩm bán chạy theo tháng", "Thống kê sản phẩm bán chạy theo năm" }));

		cmbNhanVien.setBackground(new java.awt.Color(254, 254, 254));
		cmbNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thống kê lương nhân viên",
				"Thống kê lương nhân viên quản lý", "Thống kê lương nhân viên thường" }));

		cmbSLSanPham.setBackground(new java.awt.Color(254, 254, 254));
		cmbSLSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thống kê số lượng sản phẩm",
				"Thống kê top 5 sản phẩm còn nhiều nhất", "Thống kê top 5 sản phẩm còn ít nhất" }));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(cmbDoanhThu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cmbSanPhamHot, 0, 386, Short.MAX_VALUE)
								.addComponent(cmbNhanVien, 0, 386, Short.MAX_VALUE)
								.addComponent(cmbSLSanPham, 0, 386, Short.MAX_VALUE))
						.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(cmbDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(cmbSanPhamHot, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(cmbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(cmbSLSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		javax.swing.GroupLayout plnThongKeLayout = new javax.swing.GroupLayout(plnThongKe);
		plnThongKe.setLayout(plnThongKeLayout);
		plnThongKeLayout.setHorizontalGroup(
				plnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						plnThongKeLayout.createSequentialGroup().addContainerGap()
								.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		plnThongKeLayout.setVerticalGroup(plnThongKeLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plnThongKeLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(plnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(plnThongKe,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(plnThongKe,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	}// </editor-fold>

// Variables declaration - do not modify                     
	private com.raven.chart.Chart chart;
	private javax.swing.JComboBox<String> cmbDoanhThu;
	private javax.swing.JComboBox<String> cmbNhanVien;
	private javax.swing.JComboBox<String> cmbSLSanPham;
	private javax.swing.JComboBox<String> cmbSanPhamHot;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel plnThongKe;
// End of variables declaration       

	private javax.swing.JButton btnXacNhan;
	private javax.swing.JButton btnXacNhan1;
	private javax.swing.JButton btnHuy;
	private javax.swing.JButton btnHuy1;
	private javax.swing.JComboBox<String> jComboBox1;
	private javax.swing.JLabel jTitle1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private com.toedter.calendar.JYearChooser jYearChooser1;
	private javax.swing.JLabel jlbChonNam;
	private javax.swing.JLabel jlbThang;
	private javax.swing.JPanel jpnCHonThangNam;

	private javax.swing.JButton btnXacNhan3;
	private javax.swing.JButton btnHuy3;
	private javax.swing.JLabel jLabel22;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JPanel jpnChonNgay;
	private javax.swing.JSeparator jSeparator12;
	private javax.swing.JSeparator jSeparator22;
	private com.toedter.calendar.JDateChooser jdcDenNgay;
	private com.toedter.calendar.JDateChooser jdcTuNgay;
	private javax.swing.JLabel jlbNBD;
	private javax.swing.JLabel jlbNKT;
}
