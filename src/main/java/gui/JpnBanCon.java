/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.Ban;

/**
 *
 * @author zombi
 */
public class JpnBanCon extends javax.swing.JPanel {

	private Ban ban;

	/**
	 * Creates new form JpnBanCon
	 */
	public JpnBanCon(Ban ban, int otp) {
		this.ban = ban;
		initComponents();
		if (otp ==1)
			btnBan.setEnabled(ban.isTrangThai());
		btnBan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				System.out.println(soBan);
				
			}
		});
	}
	public Ban getBan() {
		return ban;
	}
	public javax.swing.JButton getBtnBan() {
		return btnBan;
	}
	public void btnBanAddActionListener(ActionListener listener) {
    	btnBan.addActionListener(listener);
    }

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		btnBan = new javax.swing.JButton();
		if (ban.isTrangThai())
			btnBan.setBackground(new java.awt.Color(254, 254, 254));
		else
			btnBan.setBackground(new java.awt.Color(125, 125, 125));
		btnBan.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
		if (ban.getMaBan()!=0)
			btnBan.setText("Bàn "+ban.getMaBan());
		else
			btnBan.setText("Mang về");
		btnBan.setToolTipText("");
		btnBan.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnBanActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(btnBan,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(btnBan, javax.swing.GroupLayout.PREFERRED_SIZE, 72,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
	}// </editor-fold>

	private void btnBanActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	// Variables declaration - do not modify
	private javax.swing.JButton btnBan;
	// End of variables declaration

	public int getMaBan() {
		// TODO Auto-generated method stub
		return ban.getMaBan();
	}
}