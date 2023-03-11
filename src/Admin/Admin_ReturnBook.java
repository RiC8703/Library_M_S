/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Admin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 *
 * @author kunj
 */
public class Admin_ReturnBook extends javax.swing.JFrame {

    /**
     * Creates new form Admin_IssueBook
     */
    Color mouseEnterColor = new Color(0,0,0);
    Color mouseEnterRed = new Color(255,0,0);
    Color mouseExitRed = new Color(255,51,51);
    Color mouseExitColor = new Color(51,51,51);
    Color mouseExitBlue = new Color(51,51,255);
    Color mouseEnterBlue = new Color(0,0,235);
    
    public Admin_ReturnBook(){
        initComponents();
    }
    
    public void getBookDetails(){
        int bookId = Integer.parseInt(txt_bookid.getText());
        try {
            Connection con = Admin_DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from book_details where book_id = ?");
            pst.setInt(1, bookId);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                lbl_author.setText(rs.getString("author"));
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getStudentDetails(){
        Long studentId = Long.valueOf(txt_studentid.getText());
        try {
            Connection con = Admin_DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from student_details where student_id = ?");
            pst.setLong(1, studentId);
            ResultSet rs = pst.executeQuery();
            
            {
            if(rs.next())
            {
                
                lbl_course.setText(rs.getString("course"));
                lbl_branch.setText(rs.getString("branch"));
            }
        }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //to fatch bookdetails from databse to disply
    
    public void getIssueBookDetails(){
        int bookId = Integer.parseInt(txt_bookid.getText());
        long studentId = Long.parseLong(txt_studentid.getText());
        
        try {
            Connection con = Admin_DBConnection.getConnection();
            String sql = "select * from issue_book_details where book_id = ? and student_id = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setLong(2, studentId);
            pst.setString(3,"pending");
            
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                
                lbl_issueid.setText(rs.getString("id"));
                lbl_bookname.setText(rs.getString("book_name"));
                lbl_studentname.setText(rs.getString("student_name"));
                lbl_Issuedate.setText(rs.getString("issue_date"));
                lbl_duedate.setText(rs.getString("due_date"));
                lbl_bookid.setText(rs.getString("book_id"));
                lbl_studentid.setText(rs.getString("student_id"));
                getBookDetails();
                getStudentDetails();
                lbl_iderror.setText("");
                
            }
            else{
                lbl_iderror.setText("No Record Found");
                lbl_issueid.setText("");
                lbl_bookname.setText("");
                lbl_studentname.setText("");
                lbl_Issuedate.setText("");
                lbl_duedate.setText("");
                lbl_bookid.setText("");
                lbl_studentid.setText("");
                lbl_author.setText("");
                lbl_branch.setText("");
                lbl_course.setText("");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //return book
    
    public boolean returnbook(){
        boolean isReturned = false;
        int bookId = Integer.parseInt(txt_bookid.getText());
        long studentId = Long.parseLong(txt_studentid.getText());
        
        try {
            Connection con = Admin_DBConnection.getConnection();
            String sql = "update issue_book_details set status = ? where student_id = ? and book_id = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "returned");
            pst.setLong(2, studentId);
            pst.setInt(3, bookId);
            pst.setString(4, "pending");
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0)
            {
                isReturned = true;
                
            }
            else
            {
                isReturned = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isReturned; 
    }
    //updating book counts after return
    
    public void updateBookCount(){
        int bookId = Integer.parseInt(txt_bookid.getText());
        try {
            Connection con = Admin_DBConnection.getConnection();
            String sql = "update book_details set quantity = quantity + 1 where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanelLMS = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanelHomePage = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanelmanagebooks = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanelIssuebook = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanelreturnbook = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanellogout = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanelmanagestudents = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txt_bookid = new app.bolivia.swing.JCTextField();
        txt_studentid = new app.bolivia.swing.JCTextField();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        jLabel18 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jLabel27 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        rSMaterialButtonRectangle3 = new rojerusan.RSMaterialButtonRectangle();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        lbl_issueid = new javax.swing.JLabel();
        lbl_iderror = new javax.swing.JLabel();
        lbl_studentname = new javax.swing.JLabel();
        lbl_duedate = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lbl_Issuedate = new javax.swing.JLabel();
        lbl_bookid = new javax.swing.JLabel();
        lbl_studentid = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        lbl_branch = new javax.swing.JLabel();
        lbl_bookname = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setPreferredSize(new java.awt.Dimension(5, 50));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Book Antiqua", 1, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/admin.png"))); // NOI18N
        jLabel2.setText(" Welcome, Admin");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, 270, 70));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 0, 25)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Library Management System");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_menu_48px_1.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 50, 70));

        jLabel11.setFont(new java.awt.Font("Imprint MT Shadow", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/exit.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1490, 0, 50, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 70));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelLMS.setBackground(new java.awt.Color(51, 51, 51));
        jPanelLMS.setPreferredSize(new java.awt.Dimension(340, 60));
        jPanelLMS.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/monitor40.png"))); // NOI18N
        jLabel5.setText("   LMS Dashboard");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });
        jPanelLMS.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 240, -1));

        jPanel2.add(jPanelLMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 270, 60));

        jPanelHomePage.setBackground(new java.awt.Color(255, 51, 51));
        jPanelHomePage.setPreferredSize(new java.awt.Dimension(340, 60));
        jPanelHomePage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home40.png"))); // NOI18N
        jLabel17.setText("    Home Page");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel17MouseExited(evt);
            }
        });
        jPanelHomePage.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 240, -1));

        jPanel2.add(jPanelHomePage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 270, 60));

        jLabel7.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Features");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 250, -1));

        jPanelmanagebooks.setBackground(new java.awt.Color(51, 51, 51));
        jPanelmanagebooks.setPreferredSize(new java.awt.Dimension(340, 60));
        jPanelmanagebooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/open-book40.png"))); // NOI18N
        jLabel9.setText("   Manage Books");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel9MouseExited(evt);
            }
        });
        jPanelmanagebooks.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 240, 40));

        jPanel2.add(jPanelmanagebooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 270, 60));

        jPanelIssuebook.setBackground(new java.awt.Color(51, 51, 51));
        jPanelIssuebook.setPreferredSize(new java.awt.Dimension(340, 60));
        jPanelIssuebook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/notebook40.png"))); // NOI18N
        jLabel10.setText("   Issue Book");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel10MouseExited(evt);
            }
        });
        jPanelIssuebook.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 240, -1));

        jPanel2.add(jPanelIssuebook, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 270, 60));

        jPanelreturnbook.setBackground(new java.awt.Color(51, 51, 51));
        jPanelreturnbook.setPreferredSize(new java.awt.Dimension(340, 60));
        jPanelreturnbook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/return40.png"))); // NOI18N
        jLabel8.setText("   Return Book");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel8MouseExited(evt);
            }
        });
        jPanelreturnbook.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 240, -1));

        jPanel2.add(jPanelreturnbook, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 270, 60));

        jPanellogout.setBackground(new java.awt.Color(51, 51, 255));
        jPanellogout.setPreferredSize(new java.awt.Dimension(340, 60));
        jPanellogout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logout40.png"))); // NOI18N
        jLabel12.setText("   Logout");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel12MouseExited(evt);
            }
        });
        jPanellogout.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 240, -1));

        jPanel2.add(jPanellogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 270, 60));

        jPanelmanagestudents.setBackground(new java.awt.Color(51, 51, 51));
        jPanelmanagestudents.setPreferredSize(new java.awt.Dimension(340, 60));
        jPanelmanagestudents.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel43.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/graduated40.png"))); // NOI18N
        jLabel43.setText("   Manage Students");
        jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel43MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel43MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel43MouseExited(evt);
            }
        });
        jPanelmanagestudents.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 240, -1));

        jPanel2.add(jPanelmanagestudents, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 270, 60));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 270, 960));

        jPanel12.setBackground(new java.awt.Color(102, 204, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel19.setText("Student ID:");
        jPanel12.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 140, 30));

        txt_bookid.setBackground(new java.awt.Color(102, 204, 255));
        txt_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txt_bookid.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_bookid.setFont(new java.awt.Font("Book Antiqua", 0, 18)); // NOI18N
        txt_bookid.setPlaceholder("Enter Book ID.....");
        txt_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookidFocusLost(evt);
            }
        });
        txt_bookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookidActionPerformed(evt);
            }
        });
        jPanel12.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 350, 40));

        txt_studentid.setBackground(new java.awt.Color(102, 204, 255));
        txt_studentid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txt_studentid.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_studentid.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txt_studentid.setFont(new java.awt.Font("Book Antiqua", 0, 18)); // NOI18N
        txt_studentid.setPlaceholder("Enter Student ID.....");
        txt_studentid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentidFocusLost(evt);
            }
        });
        txt_studentid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentidActionPerformed(evt);
            }
        });
        jPanel12.add(txt_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 350, 40));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(51, 51, 255));
        rSMaterialButtonRectangle1.setText("Find");
        rSMaterialButtonRectangle1.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });
        jPanel12.add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 120, -1));

        jLabel18.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel18.setText("Book ID:");
        jPanel12.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 140, 30));
        jPanel12.add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, -1, -1));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/return70.png"))); // NOI18N
        jPanel12.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 90, 70, 80));

        jLabel33.setBackground(new java.awt.Color(102, 102, 255));
        jLabel33.setForeground(new java.awt.Color(102, 102, 255));
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/member.png"))); // NOI18N
        jPanel12.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 50, 40));

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(255, 102, 0));
        rSMaterialButtonRectangle2.setText("Return");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });
        jPanel12.add(rSMaterialButtonRectangle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, 120, -1));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/reply.png"))); // NOI18N
        jPanel12.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, 50, 50));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/id-cards.png"))); // NOI18N
        jPanel12.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 50, 40));

        jLabel21.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        jLabel21.setText("Return Book");
        jLabel21.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel12.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 100, 250, 60));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/bookfind.png"))); // NOI18N
        jPanel12.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 50, 50));

        getContentPane().add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 1270, 270));

        rSMaterialButtonRectangle3.setBackground(new java.awt.Color(255, 102, 0));
        rSMaterialButtonRectangle3.setBorder(null);
        rSMaterialButtonRectangle3.setText("ISSUE BOOK");
        rSMaterialButtonRectangle3.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        rSMaterialButtonRectangle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle3ActionPerformed(evt);
            }
        });
        getContentPane().add(rSMaterialButtonRectangle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 790, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/notebook_50px.png"))); // NOI18N
        jLabel6.setText("Issued Book Details");
        jLabel6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 450, -1));

        jLabel31.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel31.setText("Branch:");
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 330, 70, 40));

        jLabel32.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel32.setText("Course:");
        jPanel4.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 250, -1, 40));

        jLabel34.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel34.setText("Book Name:");
        jPanel4.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, 40));

        jLabel35.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel35.setText("Issue ID:");
        jPanel4.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, 40));

        lbl_issueid.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_issueid.setForeground(new java.awt.Color(0, 0, 153));
        jPanel4.add(lbl_issueid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 500, 40));

        lbl_iderror.setFont(new java.awt.Font("Book Antiqua", 1, 24)); // NOI18N
        lbl_iderror.setForeground(new java.awt.Color(255, 0, 0));
        jPanel4.add(lbl_iderror, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 470, 350, 40));

        lbl_studentname.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_studentname.setForeground(new java.awt.Color(0, 0, 153));
        jPanel4.add(lbl_studentname, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 450, 40));

        lbl_duedate.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_duedate.setForeground(new java.awt.Color(0, 0, 153));
        jPanel4.add(lbl_duedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 500, 40));

        jLabel24.setBackground(new java.awt.Color(102, 102, 255));
        jLabel24.setForeground(new java.awt.Color(102, 102, 255));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/id-card (1).png"))); // NOI18N
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 50, 40));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/name (1).png"))); // NOI18N
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 50, 40));

        jLabel15.setBackground(new java.awt.Color(102, 102, 255));
        jLabel15.setForeground(new java.awt.Color(102, 102, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/online-learning.png"))); // NOI18N
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 240, 50, -1));

        jLabel37.setBackground(new java.awt.Color(102, 102, 255));
        jLabel37.setForeground(new java.awt.Color(102, 102, 255));
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/branch.png"))); // NOI18N
        jPanel4.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 320, 50, 50));

        jLabel20.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel20.setText("Author:");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 410, 70, 40));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/poem.png"))); // NOI18N
        jPanel4.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 400, 50, 50));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/name (1).png"))); // NOI18N
        jPanel4.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 50, 40));

        jLabel40.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel40.setText("Student Name:");
        jPanel4.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, -1, 40));

        jLabel41.setBackground(new java.awt.Color(102, 102, 255));
        jLabel41.setForeground(new java.awt.Color(102, 102, 255));
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/schedule.png"))); // NOI18N
        jPanel4.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 50, 50));

        jLabel44.setBackground(new java.awt.Color(102, 102, 255));
        jLabel44.setForeground(new java.awt.Color(102, 102, 255));
        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/due-date.png"))); // NOI18N
        jPanel4.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 50, 50));

        jLabel22.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel22.setText("Issue Date:");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 100, 40));

        jLabel14.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel14.setText("Due Date:");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 90, 40));

        jLabel45.setBackground(new java.awt.Color(102, 102, 255));
        jLabel45.setForeground(new java.awt.Color(102, 102, 255));
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/member.png"))); // NOI18N
        jPanel4.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 50, 40));

        jLabel23.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel23.setText("Book ID:");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 90, 80, 40));

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/id-cards.png"))); // NOI18N
        jPanel4.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, 50, 40));

        jLabel25.setBackground(new java.awt.Color(0, 0, 0));
        jLabel25.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel25.setText("Student ID:");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 170, 100, 40));

        lbl_Issuedate.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_Issuedate.setForeground(new java.awt.Color(0, 0, 153));
        jPanel4.add(lbl_Issuedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 490, 40));

        lbl_bookid.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_bookid.setForeground(new java.awt.Color(0, 0, 153));
        jPanel4.add(lbl_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 90, 410, 40));

        lbl_studentid.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_studentid.setForeground(new java.awt.Color(0, 0, 153));
        jPanel4.add(lbl_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 170, 380, 40));

        lbl_author.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(0, 0, 153));
        jPanel4.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 410, 410, 40));

        lbl_course.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_course.setForeground(new java.awt.Color(0, 0, 153));
        jPanel4.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 250, 410, 40));

        lbl_branch.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_branch.setForeground(new java.awt.Color(0, 0, 153));
        jPanel4.add(lbl_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 330, 410, 40));

        lbl_bookname.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_bookname.setForeground(new java.awt.Color(0, 0, 153));
        jPanel4.add(lbl_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 470, 40));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 1270, 530));

        setSize(new java.awt.Dimension(1540, 870));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        // TODO add your handling code here:
        jPanelLMS.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        // TODO add your handling code here:
        jPanelLMS.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        
        Admin_HomePage homepage = new Admin_HomePage();
        homepage.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
        // TODO add your handling code here:
        jPanelHomePage.setBackground(mouseEnterRed);
    }//GEN-LAST:event_jLabel17MouseEntered

    private void jLabel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseExited
        // TODO add your handling code here:
        jPanelHomePage.setBackground(mouseExitRed);
    }//GEN-LAST:event_jLabel17MouseExited

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        Admin_ManageBooks books= new Admin_ManageBooks();
        books.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
        // TODO add your handling code here:

        jPanelmanagebooks.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel9MouseEntered

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
        // TODO add your handling code here:
        jPanelmanagebooks.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel9MouseExited

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        Admin_IssueBook issuebook = new Admin_IssueBook();
        issuebook.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseEntered
        // TODO add your handling code here:
        jPanelIssuebook.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel10MouseEntered

    private void jLabel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseExited
        // TODO add your handling code here:
        jPanelIssuebook.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel10MouseExited

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        // TODO add your handling code here:
        jPanelreturnbook.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseExited
        // TODO add your handling code here:
        jPanelreturnbook.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel8MouseExited

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        ImageIcon icon = new ImageIcon(Admin_ReturnBook.class.getResource("/test/question.png"));
        int a = JOptionPane.showConfirmDialog(this,"<html><b style=\"font-family:Book Antiqua;font-size:14px;color:red;\">Are You Sure You Want To Logout?</b></html>","Message",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,icon);

        if(a == JOptionPane.YES_OPTION)
        {

            Admin_Login loginpage = new Admin_Login();
            loginpage.setVisible(true);
            dispose();
        }
        setDefaultCloseOperation(JOptionPane.NO_OPTION);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
        // TODO add your handling code here:
        jPanellogout.setBackground(mouseEnterBlue);
    }//GEN-LAST:event_jLabel12MouseEntered

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        // TODO add your handling code here:
        jPanellogout.setBackground(mouseExitBlue);
    }//GEN-LAST:event_jLabel12MouseExited

    private void jLabel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseClicked
        // TODO add your handling code here:
        Admin_ManageStudents managestudent = new Admin_ManageStudents();
        managestudent.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel43MouseClicked

    private void jLabel43MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseEntered
        // TODO add your handling code here:
        jPanelmanagestudents.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel43MouseEntered

    private void jLabel43MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseExited
        // TODO add your handling code here:
        jPanelmanagestudents.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel43MouseExited

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        // TODO add your handling code here:
        getIssueBookDetails();
         
        
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void txt_studentidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentidActionPerformed

    private void txt_studentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentidFocusLost
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txt_studentidFocusLost

    private void txt_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookidActionPerformed

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_bookidFocusLost

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        // TODO add your handling code here:
        if(returnbook() == true)
        {
            ImageIcon icon = new ImageIcon(Admin_ReturnBook.class.getResource("/test/checked.png"));
            JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;color:green\">Book Returned Successfully</b></html>","Message",JOptionPane.PLAIN_MESSAGE,icon);
            updateBookCount();
        }
        else{
            ImageIcon icon = new ImageIcon(Admin_ReturnBook.class.getResource("/test/error.png"));
            JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;color:red\">Book Returned Failed</b></html>","Message",JOptionPane.PLAIN_MESSAGE,icon);
            
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void rSMaterialButtonRectangle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle3ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_rSMaterialButtonRectangle3ActionPerformed

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
            java.util.logging.Logger.getLogger(Admin_ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_ReturnBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelHomePage;
    private javax.swing.JPanel jPanelIssuebook;
    private javax.swing.JPanel jPanelLMS;
    private javax.swing.JPanel jPanellogout;
    private javax.swing.JPanel jPanelmanagebooks;
    private javax.swing.JPanel jPanelmanagestudents;
    private javax.swing.JPanel jPanelreturnbook;
    private javax.swing.JLabel lbl_Issuedate;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookid;
    private javax.swing.JLabel lbl_bookname;
    private javax.swing.JLabel lbl_branch;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_duedate;
    private javax.swing.JLabel lbl_iderror;
    private javax.swing.JLabel lbl_issueid;
    private javax.swing.JLabel lbl_studentid;
    private javax.swing.JLabel lbl_studentname;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle3;
    private app.bolivia.swing.JCTextField txt_bookid;
    private app.bolivia.swing.JCTextField txt_studentid;
    // End of variables declaration//GEN-END:variables
}
