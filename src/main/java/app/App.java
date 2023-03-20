package app;

import gui.FrmDangNhap;
import gui.FrmDoiMatKhau;


public class App {
	public static void main(String[] args) {
		 try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("windows".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(FrmDoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(FrmDoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(FrmDoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(FrmDoiMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
		FrmDangNhap frmDangNhap = new FrmDangNhap();
		frmDangNhap.setLocationRelativeTo(null);
		frmDangNhap.setVisible(true);
		
	}
}