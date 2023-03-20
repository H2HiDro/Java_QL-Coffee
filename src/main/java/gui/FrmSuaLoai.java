/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dao.LoaiHangDAO;
import entity.KhachHang;
import entity.LoaiHang;
import tablemodel.LoaiHangTableModel;

/**
 *
 * @author hieud
 */
public class FrmSuaLoai extends javax.swing.JFrame {

    private static final String[] HEADERS = "Mã loại hàng;Tên loại hàng".split(";");
	private List<LoaiHang> dslh;
	private LoaiHangDAO loaiHangDAO;
	/**
     * Creates new form FrmLoaiMatHang
     */
    public FrmSuaLoai() {
    	loaiHangDAO = new LoaiHangDAO();
        initComponents();
        addControls();
    }

    private void addControls() {
    	jTable1.setRowHeight(30);
		jTable1.setIntercellSpacing(new java.awt.Dimension(5, 5));
		jTable1.getTableHeader().setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 18));
    	try {
			dslh = loaiHangDAO.layDanhSachLoaiHang();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	updateModelTable(dslh);
    	jTable1.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			String item = (String) jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0);
				try {
					LoaiHang lh = loaiHangDAO.timLoaiHang(item);
					if (lh != null) {
						fillLoaiHang(lh);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
    		}

			private void fillLoaiHang(LoaiHang lh) {
				tfMaLoai.setText(lh.getMaLoaiHang());
				tfTenLoai.setText(lh.getTenLoaiHang());
				
			}
		});
    	btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String tenLoai = JOptionPane.showInputDialog(null,"Nhập tên loại hàng:");
				if (!tenLoai.isEmpty()) {
					try {
						String maLoai= loaiHangDAO.getMaLoaiHangCaoNhat();
						maLoai= getMaLoaiNew(maLoai);
						if (loaiHangDAO.themLoaiHang(new LoaiHang(maLoai, tenLoai))) {
							JOptionPane.showMessageDialog(null, "Thêm thành công!!");
							dslh = loaiHangDAO.layDanhSachLoaiHang();
							updateModelTable(dslh);
						}else
							JOptionPane.showMessageDialog(null, "Thêm thất bại!!","Thất bại",JOptionPane.ERROR_MESSAGE);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else
					JOptionPane.showMessageDialog(null, "Không để trống!!","Trống",JOptionPane.ERROR_MESSAGE);
			}

		});
    	btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String maLoai = tfMaLoai.getText();
				if (!maLoai.isEmpty()) {
					LoaiHang lh = null;
					try {
						lh = loaiHangDAO.timLoaiHang(maLoai );
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int c = JOptionPane.showConfirmDialog(null, String.format("Bạn có chắn chắn muốn xóa loại hàng %s không?",lh.getTenLoaiHang()),"Xóa",JOptionPane.YES_NO_OPTION);
					if (c==JOptionPane.YES_OPTION) {
						try {
							if(loaiHangDAO.deleteLoaiHang(maLoai)) {
								JOptionPane.showMessageDialog(null, "Xóa thành công");
								dslh = loaiHangDAO.layDanhSachLoaiHang();
								updateModelTable(dslh);
							}else {
								JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại!!","Lỗi", JOptionPane.ERROR_MESSAGE);
							}
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	
					}
				}else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 loại sản phẩm trong bảng bên cạnh để xóa!!");
				}
				clearText();
			}
		});
    	btnSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String maLoai = tfMaLoai.getText();
				if (!maLoai.isEmpty()) {
					LoaiHang lh = null;
					try {
						lh = loaiHangDAO.timLoaiHang(maLoai );
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String tenLoaiNew = JOptionPane.showInputDialog(null,String.format("Vui lòng nhập tên mới cho mã loại %s", maLoai));
					
					if (!tenLoaiNew.isEmpty()) {
						int c = JOptionPane.showConfirmDialog(null, String.format("Bạn có chắn chắn muốn sửa loại hàng %s thành %s không?",lh.getTenLoaiHang(), tenLoaiNew),"Sửa",JOptionPane.YES_NO_OPTION);
						if (c==JOptionPane.YES_OPTION) {
							try {
								lh.setTenLoaiHang(tenLoaiNew);
								if(loaiHangDAO.capNhatLoaiHang(lh)) {
									JOptionPane.showMessageDialog(null, "Sửa thành công");
									dslh = loaiHangDAO.layDanhSachLoaiHang();
									updateModelTable(dslh);
								}else {
									JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại!!","Lỗi", JOptionPane.ERROR_MESSAGE);
								}
								
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		
						}
					}else {
						JOptionPane.showMessageDialog(null, "Không được để tên loại hàng trống","Tên loại hàng bị trống", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 loại sản phẩm trong bảng bên cạnh để sửa!!");
				}
				clearText();
			}
		});
	}
    
    private void clearText() {
    	tfMaLoai.setText("");
		tfTenLoai.setText("");
    }
    private String getMaLoaiNew(String maLoai) {
    	int maLoaiTnt = 1;
    	if (maLoai != null)
    		maLoaiTnt = Integer.parseInt(maLoai.replaceAll("\\D+", "")) + 1;
    	return String.format("LH%02d", maLoaiTnt);
    }
	private void updateModelTable(List<LoaiHang> dslh) {
		LoaiHangTableModel loaiHangTableModel = new LoaiHangTableModel(HEADERS, dslh);
		jTable1.setModel(loaiHangTableModel);
		jTable1.updateUI();
	}

	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jlbMaLoai = new javax.swing.JLabel();
        tfMaLoai = new javax.swing.JTextField();
        jlbTenLoai = new javax.swing.JLabel();
        tfTenLoai = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jpnChucNang = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnTK = new javax.swing.JButton();


        jPanel1.setBackground(new java.awt.Color(230, 219, 209));

        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBackground(new java.awt.Color(230, 219, 209));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jlbMaLoai.setFont(new java.awt.Font("Times New Roman", 2, 20)); // NOI18N
        jlbMaLoai.setText("Mã Loại:");

        tfMaLoai.setEditable(false);
        tfMaLoai.setBackground(new java.awt.Color(230, 219, 209));
        tfMaLoai.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        tfMaLoai.setText("");
        tfMaLoai.setBorder(null);

        jlbTenLoai.setFont(new java.awt.Font("Times New Roman", 2, 20)); // NOI18N
        jlbTenLoai.setText("Tên Loại:");

        tfTenLoai.setEditable(false);
        tfTenLoai.setBackground(new java.awt.Color(230, 219, 209));
        tfTenLoai.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        tfTenLoai.setText("");
        tfTenLoai.setBorder(null);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jpnChucNang.setBackground(new java.awt.Color(230, 219, 209));
        jpnChucNang.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        btnThem.setBackground(new java.awt.Color(230, 219, 209));
        btnThem.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnThem.setForeground(new java.awt.Color(102, 102, 102));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Plus_30px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnXoa.setBackground(new java.awt.Color(230, 219, 209));
        btnXoa.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(102, 102, 102));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Recycle Bin_30px.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnSua.setBackground(new java.awt.Color(230, 219, 209));
        btnSua.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnSua.setForeground(new java.awt.Color(102, 102, 102));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/recycle_30px.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnTK.setBackground(new java.awt.Color(230, 219, 209));
        btnTK.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        btnTK.setForeground(new java.awt.Color(102, 102, 102));
        btnTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search_30px.png"))); // NOI18N
        btnTK.setText("Tìm Kiếm");
        btnTK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout jpnChucNangLayout = new javax.swing.GroupLayout(jpnChucNang);
        jpnChucNang.setLayout(jpnChucNangLayout);
        jpnChucNangLayout.setHorizontalGroup(
            jpnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnChucNangLayout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnChucNangLayout.createSequentialGroup()
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTK, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnChucNangLayout.setVerticalGroup(
            jpnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator1)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jlbMaLoai)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tfMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jlbTenLoai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTK;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlbMaLoai;
    private javax.swing.JLabel jlbTenLoai;
    private javax.swing.JPanel jpnChucNang;
    private javax.swing.JTextField tfMaLoai;
    private javax.swing.JTextField tfTenLoai;
    // End of variables declaration                   
}
