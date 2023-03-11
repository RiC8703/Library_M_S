
package User;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author kunj
 */
public class User_SignupPage extends javax.swing.JFrame {

    boolean setVisible;

    /**
     * Creates new form SignupPage
     */
    public User_SignupPage() {
        initComponents();
    }
    //method to insert values into the users table
    public void insertSignupDetails(){
        String name = txt_username.getText();
        String pwd = txt_password.getText();
        String email = txt_email.getText();
        String contact = txt_contact.getText();
        
        try {
            Connection con = User_DBConnection.getConnection();
            String sql = "insert into users(name,password,email,contact) values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, name);
            pst.setString(2, pwd);
            pst.setString(3, email);
            pst.setString(4, contact);
            
            int updatedRowCount = pst.executeUpdate();
            
            if(updatedRowCount > 0){
                ImageIcon icon = new ImageIcon(User_SignupPage.class.getResource("/test/checked.png"));
                JOptionPane.showMessageDialog(this,"<html><b style=\"font-family:Book Antiqua;font-size:14px;font-family:Book Antiqua;font-size:14px;color:green\">Successfully Login</b></html>","Message",JOptionPane.INFORMATION_MESSAGE,icon);
                User_LoginPage page = new User_LoginPage();
                page.setVisible(true);
                dispose();
                
                
            }
            else
            {
                ImageIcon icon = new ImageIcon(User_SignupPage.class.getResource("/test/error.png"));
                JOptionPane.showMessageDialog(this,"<html><b style=\"font-family:Book Antiqua;font-size:14px;font-family:Book Antiqua;font-size:14px;color:red\">Failed To Login</b></html>","Message",JOptionPane.INFORMATION_MESSAGE,icon);
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    
    //validation method
    
    public boolean validateSignup(){
        String name = txt_username.getText();
        String pwd = txt_password.getText();
        String repwd = txt_reenterpassword.getText();
        String email = txt_email.getText();
        String contact = txt_contact.getText();
        
        if(name.equals("") || pwd.equals("") || !pwd.equals(repwd) || email.equals("") || !email.matches("^.+@.+\\..+$") || contact.equals("" ) || contact.length() != 10){
            //JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;font-family:Book Antiqua;font-size:14px;color:red\">Please Enter UserID</b></html>","Message",JOptionPane.ERROR_MESSAGE);
            //userid_error.setText("*Enter User ID");
            return false;
        }
        /*
        if(pwd.equals("")){
            JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;font-family:Book Antiqua;font-size:14px;color:red\">Please Enter Password</b></html>","Message",JOptionPane.ERROR_MESSAGE);
            //pass_error.setText("*Please Enter Password");
            return false;
        }
        if(!pwd.equals(repwd)){
            JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;font-family:Book Antiqua;font-size:14px;color:red\">Password Not Matched!!</b></html>","Message",JOptionPane.ERROR_MESSAGE);
            //repass_error.setText("*Password Not Matched!!!");
            return false;
        }
        if(email.equals("") || !email.matches("^.+@.+\\..+$")){
            JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;font-family:Book Antiqua;font-size:14px;color:red\">Please Enter Valid Email Id</b></html>","Message",JOptionPane.ERROR_MESSAGE);
            //email_error.setText("*Please Enter Valid Email Id");
            return false;
        }
       
        
        if(contact.equals("" ) || contact.length() != 10){
            JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;font-family:Book Antiqua;font-size:14px;color:red\">Please Enter Valid Contact No</b></html>","Message",JOptionPane.ERROR_MESSAGE);
            //contact_error.setText("*Please Enter Valid Contact No");
            return false;
        } 
        */
        
        return true;
        
        
        
        
        
    }
    
    
    //check duplicate users
    
    public boolean checkDuplicateUser(){
        
        String name = txt_username.getText();
        boolean isExist = false;
        try {
            Connection con = User_DBConnection.getConnection();
            
            PreparedStatement pstname = con.prepareStatement("select * from users where name = ?");
            pstname.setString(1, name);
            ResultSet rname = pstname.executeQuery();
            if(rname.next())
            {
                isExist = true;
            }
            else
            {
                isExist = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExist;
    }
    public boolean checkDuplicateemail(){
        
        String email = txt_email.getText();
        boolean isExist = false;
        try {
            Connection con = User_DBConnection.getConnection();
            
            PreparedStatement pstemail = con.prepareStatement("select * from users where email = ?");
            pstemail.setString(1,email);
            ResultSet remail = pstemail.executeQuery();
            if(remail.next())
            {
                isExist = true;
            }
            else
            {
                isExist = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExist;
    }
    public boolean checkDuplicatecontact(){
        
        String contact = txt_contact.getText();
        boolean isExist = false;
        try {
            Connection con = User_DBConnection.getConnection();
            
            PreparedStatement pstcontact = con.prepareStatement("select * from users where contact = ?");
            pstcontact.setString(1, contact);
            ResultSet rcontact = pstcontact.executeQuery();
            if(rcontact.next())
            {
                isExist = true;
            }
            else
            {
                isExist = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExist;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_username = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_email = new app.bolivia.swing.JCTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_contact = new app.bolivia.swing.JCTextField();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        jLabel7 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        jLabel17 = new javax.swing.JLabel();
        txt_reenterpassword = new javax.swing.JPasswordField();
        checkbox = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pass_error = new javax.swing.JLabel();
        userid_error = new javax.swing.JLabel();
        email_error = new javax.swing.JLabel();
        contact_error = new javax.swing.JLabel();
        repass_error = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 155));
        jLabel1.setText("Library Management System");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 580, 40));

        jLabel3.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 155));
        jLabel3.setText("Welcome To");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 240, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/signup-library-icon.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 780, 590));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 870));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Imprint MT Shadow", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/exit.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, -1, -1));

        jLabel6.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        jLabel6.setText("Create New Account");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 410, 40));

        txt_username.setBackground(new java.awt.Color(153, 204, 255));
        txt_username.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txt_username.setToolTipText("Enter UserID....");
        txt_username.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_username.setFont(new java.awt.Font("Book Antiqua", 0, 18)); // NOI18N
        txt_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_usernameFocusLost(evt);
            }
        });
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        jPanel2.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 350, 40));

        jLabel9.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel9.setText("UserID:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 120, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/gmail.png"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 50, 50));

        jLabel11.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel11.setText("E-Mail:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, 120, 30));

        txt_email.setBackground(new java.awt.Color(153, 204, 255));
        txt_email.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txt_email.setToolTipText("Enter E-Mail ID....");
        txt_email.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_email.setFont(new java.awt.Font("Book Antiqua", 0, 18)); // NOI18N
        txt_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_emailFocusLost(evt);
            }
        });
        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });
        jPanel2.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 500, 350, 40));

        jLabel13.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel13.setText("Password:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 90, 30));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/phone.png"))); // NOI18N
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 590, 50, 50));

        jLabel15.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel15.setText("Contact No:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 570, 120, 30));

        txt_contact.setBackground(new java.awt.Color(153, 204, 255));
        txt_contact.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txt_contact.setToolTipText("Enter Contact No....");
        txt_contact.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_contact.setFont(new java.awt.Font("Book Antiqua", 0, 18)); // NOI18N
        txt_contact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_contactFocusLost(evt);
            }
        });
        txt_contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_contactActionPerformed(evt);
            }
        });
        jPanel2.add(txt_contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 600, 350, 40));
        txt_contact.getAccessibleContext().setAccessibleName("");

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(51, 51, 255));
        rSMaterialButtonRectangle1.setText("Login");
        rSMaterialButtonRectangle1.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });
        jPanel2.add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 690, 130, 70));

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonRectangle2.setText("Signup");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });
        rSMaterialButtonRectangle2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rSMaterialButtonRectangle2KeyPressed(evt);
            }
        });
        jPanel2.add(rSMaterialButtonRectangle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 690, 130, -1));

        jLabel7.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        jLabel7.setText("Signup Page");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 250, 40));

        txt_password.setBackground(new java.awt.Color(153, 204, 255));
        txt_password.setFont(new java.awt.Font("Calisto MT", 1, 18)); // NOI18N
        txt_password.setToolTipText("Enter Password...."); // NOI18N
        txt_password.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txt_password.setName(""); // NOI18N
        txt_password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_passwordFocusLost(evt);
            }
        });
        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });
        jPanel2.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 350, 40));

        jLabel17.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel17.setText("Re-Enter Password:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 180, 30));

        txt_reenterpassword.setBackground(new java.awt.Color(153, 204, 255));
        txt_reenterpassword.setFont(new java.awt.Font("Calisto MT", 1, 18)); // NOI18N
        txt_reenterpassword.setToolTipText("Re-Enter Password...."); // NOI18N
        txt_reenterpassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txt_reenterpassword.setName(""); // NOI18N
        txt_reenterpassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_reenterpasswordFocusLost(evt);
            }
        });
        txt_reenterpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_reenterpasswordActionPerformed(evt);
            }
        });
        jPanel2.add(txt_reenterpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 350, 40));

        checkbox.setFont(new java.awt.Font("Century Schoolbook", 1, 12)); // NOI18N
        checkbox.setText("View Password");
        checkbox.setToolTipText("");
        checkbox.setActionCommand("");
        checkbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxActionPerformed(evt);
            }
        });
        jPanel2.add(checkbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 120, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/login.png"))); // NOI18N
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 700, 50, 50));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/signup.png"))); // NOI18N
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 700, 50, 50));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/password.png"))); // NOI18N
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 50, 50));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/password.png"))); // NOI18N
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 50, 50));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/member.png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 50, 50));

        pass_error.setFont(new java.awt.Font("Book Antiqua", 0, 16)); // NOI18N
        pass_error.setForeground(new java.awt.Color(255, 0, 0));
        pass_error.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel2.add(pass_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 340, 220, 20));

        userid_error.setFont(new java.awt.Font("Book Antiqua", 0, 16)); // NOI18N
        userid_error.setForeground(new java.awt.Color(255, 0, 0));
        userid_error.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel2.add(userid_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 350, 20));

        email_error.setFont(new java.awt.Font("Book Antiqua", 0, 16)); // NOI18N
        email_error.setForeground(new java.awt.Color(255, 0, 0));
        email_error.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel2.add(email_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 540, 350, 20));

        contact_error.setFont(new java.awt.Font("Book Antiqua", 0, 16)); // NOI18N
        contact_error.setForeground(new java.awt.Color(255, 0, 0));
        contact_error.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel2.add(contact_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 640, 350, 20));

        repass_error.setFont(new java.awt.Font("Book Antiqua", 0, 16)); // NOI18N
        repass_error.setForeground(new java.awt.Color(255, 0, 0));
        repass_error.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel2.add(repass_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, 350, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 550, 870));

        setSize(new java.awt.Dimension(1535, 870));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

    private void txt_contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contactActionPerformed

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        if (validateSignup() == true){
            if(checkDuplicateUser() == false && checkDuplicateemail() == false && checkDuplicatecontact() == false)
            {
                insertSignupDetails(); 
            }
            else
            {
                ImageIcon icon = new ImageIcon(User_SignupPage.class.getResource("/test/error.png"));
                JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;font-family:Book Antiqua;font-size:14px;color:red\">Username,Email ID Or Contact No Already Exist!</b></html>","Message",JOptionPane.INFORMATION_MESSAGE,icon);
                   
            }
           
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void txt_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusLost
        /*
        
        if(checkDuplicateUser() == true )
        {
            //JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;font-family:Book Antiqua;font-size:14px;color:red\">UserID Already Exist!</b></html>","Message",JOptionPane.ERROR_MESSAGE);
            userid_error.setText("*UserID Already Exist!");
        }
        if(name.equals("")){
            userid_error.setText("Enter User ID");
        }
        else
        {
            userid_error.setText("");
        }
        */
        String name = txt_username.getText();
        if(!name.equals("")){
            if(checkDuplicateUser() == true)
            {
                userid_error.setText("*UserID Already Exist");
            }
            else
            {
                userid_error.setText("");
            }
        }
        else
        {
            userid_error.setText("*Enter User ID");
        }
    }//GEN-LAST:event_txt_usernameFocusLost

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        // TODO add your handling code here:
        User_LoginPage UserLogin = new User_LoginPage();
        UserLogin.setVisible(true);
        dispose();
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void txt_reenterpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_reenterpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_reenterpasswordActionPerformed

    private void txt_reenterpasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_reenterpasswordFocusLost
        // TODO add your handling code here:
        String repwd = txt_reenterpassword.getText();
        String pwd = txt_password.getText();
        if(!pwd.equals(repwd)){
            repass_error.setText("*Password Not Matched");
        }
        else
        {
            repass_error.setText("");
        }
    }//GEN-LAST:event_txt_reenterpasswordFocusLost

    private void txt_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_emailFocusLost
        String email = txt_email.getText();
        if(!email.equals("") || email.matches("^.+@.+\\..+$")){
            if(checkDuplicateemail() == true)
            {
                email_error.setText("*Email ID Already Exist!");
                //JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;font-family:Book Antiqua;font-size:14px;color:red\">Email ID Already Exist!</b></html>","Message",JOptionPane.ERROR_MESSAGE);
            }
            else{
                email_error.setText("");
            }
        }
        else
        {
            email_error.setText("*Enter Valid Email ID");
        }
    }//GEN-LAST:event_txt_emailFocusLost

    private void txt_contactFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_contactFocusLost
        // TODO add your handling code here:
        String contact = txt_contact.getText();

        if(!contact.equals("" ) || contact.length() == 10){
            if(checkDuplicatecontact() == true)
            {
                contact_error.setText("*Contact Number Already Exist!");
                //JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;font-family:Book Antiqua;font-size:14px;color:red\">Contact No Already Exist!</b></html>","Message",JOptionPane.ERROR_MESSAGE);
            }
            else{
                contact_error.setText("");
            }
        }
        else{
            contact_error.setText("*Enter Valid Contact No");
        }
    
    }//GEN-LAST:event_txt_contactFocusLost

    private void checkboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxActionPerformed
        // TODO add your handling code here:
        if(checkbox.isSelected())
        {
            txt_password.setEchoChar((char)0);
            
        }
        else
        {
            txt_password.setEchoChar('*');
        }
    }//GEN-LAST:event_checkboxActionPerformed

    private void rSMaterialButtonRectangle2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle2KeyPressed

    private void txt_passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_passwordFocusLost
        // TODO add your handling code here:
        String pwd = txt_password.getText();
        if(pwd.equals("")){
            pass_error.setText("*Please Enter Password");
        }
        else
        {
            pass_error.setText("");
        }
    }//GEN-LAST:event_txt_passwordFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(User_SignupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User_SignupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User_SignupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User_SignupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User_SignupPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkbox;
    private javax.swing.JLabel contact_error;
    private javax.swing.JLabel email_error;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel pass_error;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private javax.swing.JLabel repass_error;
    private app.bolivia.swing.JCTextField txt_contact;
    private app.bolivia.swing.JCTextField txt_email;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JPasswordField txt_reenterpassword;
    private app.bolivia.swing.JCTextField txt_username;
    private javax.swing.JLabel userid_error;
    // End of variables declaration//GEN-END:variables


}
