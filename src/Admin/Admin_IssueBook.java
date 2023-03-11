/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Admin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.awt.Color;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 *
 * @author kunj
 */
public class Admin_IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form Admin_IssueBook
     */
    Color mouseEnterColor = new Color(0,0,0);
    Color mouseEnterRed = new Color(255,0,0);
    Color mouseExitRed = new Color(255,51,51);
    Color mouseExitColor = new Color(51,51,51);
    Color mouseExitBlue = new Color(51,51,255);
    Color mouseEnterBlue = new Color(0,0,235);
    public Admin_IssueBook() {
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
                lbl_bookerror.setText("");
                lbl_bookid.setText(rs.getString("book_id"));
                lbl_bookname.setText(rs.getString("book_name"));
                lbl_author.setText(rs.getString("author"));
                lbl_quantity.setText(rs.getString("quantity"));
            }
            else
            {
                
                 lbl_bookerror.setText("Invalid Book ID!!!");
                 lbl_bookid.setText("");
                lbl_bookname.setText("");
                lbl_author.setText("");
                lbl_quantity.setText("");
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
                lbl_studenterror.setText("");
                lbl_studentid.setText(rs.getString("student_id"));
                lbl_studentname.setText(rs.getString("name"));
                lbl_course.setText(rs.getString("course"));
                lbl_branch.setText(rs.getString("branch"));
            }
            else
            {   
                
                lbl_studenterror.setText("Invalid Student ID!!!");
                lbl_studentid.setText("");
                lbl_studentname.setText("");
                lbl_course.setText("");
                lbl_branch.setText("");
            }
            
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //insert issue book details to database
    
    public boolean issueBook(){
        boolean isIssued = false;
        int bookId = Integer.parseInt(txt_bookid.getText());
        long studentId = Long.parseLong(txt_studentid.getText());
        String bookName = lbl_bookname.getText();
        String studentName = lbl_studentname.getText();
        
        Date uIssueDate = date_issue.getDate();
        Date uDueDate = date_due.getDate();
        
        Long l1 = uIssueDate.getTime();
        long l2 = uDueDate.getTime();
        
        
        java.sql.Date sIssueDate = new java.sql.Date(l1);
        java.sql.Date sDueDate = new java.sql.Date(l2);
        
        try {
            Connection con = Admin_DBConnection.getConnection();
            String sql = "insert into issue_book_details(book_id,book_name,student_id,"
                    + "student_name,issue_date,due_date,status)values(?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, bookId);
            pst.setString(2,bookName);
            pst.setLong(3, studentId);
            pst.setString(4,studentName);
            pst.setDate(5, sIssueDate);
            pst.setDate(6, sDueDate);
            pst.setString(7,"Pending");
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0)
            {
                isIssued = true;
            }
            else{
                isIssued = false;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return isIssued;
                
    }
    //updating book counts after issued
    
    public void updateBookCount(){
        int bookId = Integer.parseInt(txt_bookid.getText());
        try {
            Connection con = Admin_DBConnection.getConnection();
            String sql = "update book_details set quantity = quantity - 1 where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                //ImageIcon icon = new ImageIcon(Admin_IssueBook.class.getResource("/test/checked.png"));
                //JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;color:green\">Book Count Updated</b></html>","Message",JOptionPane.PLAIN_MESSAGE,icon);
                int initialCount = Integer.parseInt(lbl_quantity.getText());
                
                lbl_quantity.setText(Integer.toString(initialCount - 1));
                
            }
            else{
                ImageIcon icon = new ImageIcon(Admin_IssueBook.class.getResource("/test/error.png"));
                JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;color:red\">Can Not Update Book Count.</b></html>","Message",JOptionPane.PLAIN_MESSAGE,icon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //checking book is given or not
    public boolean isAlreadyIssued(){
        boolean isAlreadyIssued = false;
        int bookId = Integer.parseInt(txt_bookid.getText());
        long studentId = Long.parseLong(txt_studentid.getText());
        
        try {
            Connection con = Admin_DBConnection.getConnection();
            String sql = "select * from issue_book_details where book_id = ? and student_id = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,bookId);
            pst.setLong(2,studentId);
            pst.setString(3, "pending");
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
            {
                isAlreadyIssued = true;
            }
            else
            {
                isAlreadyIssued = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAlreadyIssued;
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
        jPanel12 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_bookid = new app.bolivia.swing.JCTextField();
        txt_studentid = new app.bolivia.swing.JCTextField();
        jLabel21 = new javax.swing.JLabel();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        jLabel18 = new javax.swing.JLabel();
        date_issue = new com.toedter.calendar.JDateChooser();
        date_due = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbl_bookname = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        lbl_bookid = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lbl_bookerror = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lbl_studentid = new javax.swing.JLabel();
        lbl_studentname = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        lbl_branch = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        lbl_studenterror = new javax.swing.JLabel();
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

        jPanel12.setBackground(new java.awt.Color(102, 204, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel19.setText("Student ID:");
        jPanel12.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 140, 30));

        jLabel14.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel14.setText("Due Date:");
        jPanel12.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 130, 30));

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
        jPanel12.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 350, 40));

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
        jPanel12.add(txt_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 350, 40));

        jLabel21.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel21.setText("Issue Date:");
        jPanel12.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, 130, 30));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(255, 102, 0));
        rSMaterialButtonRectangle1.setText("ISSUE BOOK");
        rSMaterialButtonRectangle1.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });
        jPanel12.add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 100, 150, -1));

        jLabel18.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel18.setText("Book ID:");
        jPanel12.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 140, 30));

        date_issue.setBackground(new java.awt.Color(102, 204, 255));
        date_issue.setFont(new java.awt.Font("Book Antiqua", 0, 18)); // NOI18N
        jPanel12.add(date_issue, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 350, 40));

        date_due.setBackground(new java.awt.Color(102, 204, 255));
        date_due.setFont(new java.awt.Font("Book Antiqua", 0, 18)); // NOI18N
        jPanel12.add(date_due, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 350, 40));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/id-cards.png"))); // NOI18N
        jPanel12.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 50, 40));

        jLabel32.setBackground(new java.awt.Color(102, 102, 255));
        jLabel32.setForeground(new java.awt.Color(102, 102, 255));
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/due-date.png"))); // NOI18N
        jPanel12.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 50, 50));

        jLabel33.setBackground(new java.awt.Color(102, 102, 255));
        jLabel33.setForeground(new java.awt.Color(102, 102, 255));
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/member.png"))); // NOI18N
        jPanel12.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 50, 40));

        jLabel34.setBackground(new java.awt.Color(102, 102, 255));
        jLabel34.setForeground(new java.awt.Color(102, 102, 255));
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/notebook_50px.png"))); // NOI18N
        jPanel12.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 110, 50, 50));

        jLabel35.setBackground(new java.awt.Color(102, 102, 255));
        jLabel35.setForeground(new java.awt.Color(102, 102, 255));
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/schedule.png"))); // NOI18N
        jPanel12.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 50, 50));

        getContentPane().add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 1270, 270));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 2, new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/bookshelf.png"))); // NOI18N
        jLabel13.setText("Book Details");
        jLabel13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 320, 50));

        jLabel20.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel20.setText("Author:");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 70, 40));

        lbl_bookname.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_bookname.setForeground(new java.awt.Color(0, 0, 153));
        jPanel5.add(lbl_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 410, 40));

        lbl_author.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(0, 0, 153));
        jPanel5.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 450, 40));

        lbl_quantity.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(0, 0, 153));
        jPanel5.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 430, 40));

        lbl_bookid.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_bookid.setForeground(new java.awt.Color(0, 0, 153));
        jPanel5.add(lbl_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 440, 40));

        jLabel36.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel36.setText("Book ID:");
        jPanel5.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, 40));

        jLabel40.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel40.setText("Book Name:");
        jPanel5.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, -1, 40));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/name (1).png"))); // NOI18N
        jPanel5.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 50, 40));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/id-cards.png"))); // NOI18N
        jPanel5.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 50, 40));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/poem.png"))); // NOI18N
        jPanel5.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 50, 50));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/quantity.png"))); // NOI18N
        jPanel5.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 50, 40));

        jLabel23.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel23.setText("Quantity:");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 90, 40));

        lbl_bookerror.setFont(new java.awt.Font("Book Antiqua", 1, 24)); // NOI18N
        lbl_bookerror.setForeground(new java.awt.Color(204, 0, 0));
        jPanel5.add(lbl_bookerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, 300, 40));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 640, 530));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/man.png"))); // NOI18N
        jLabel6.setText("Student Details");
        jLabel6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 380, -1));

        jLabel28.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel28.setText("Branch:");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 70, 40));

        jLabel29.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel29.setText("Course:");
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, 40));

        jLabel30.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel30.setText("Student Name:");
        jPanel4.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, -1, 40));

        jLabel31.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel31.setText("Student ID:");
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, 40));

        lbl_studentid.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_studentid.setForeground(new java.awt.Color(0, 0, 153));
        jPanel4.add(lbl_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 420, 40));

        lbl_studentname.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_studentname.setForeground(new java.awt.Color(0, 0, 153));
        jPanel4.add(lbl_studentname, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 390, 40));

        lbl_course.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_course.setForeground(new java.awt.Color(0, 0, 153));
        jPanel4.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 450, 40));

        lbl_branch.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        lbl_branch.setForeground(new java.awt.Color(0, 0, 153));
        jPanel4.add(lbl_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 450, 40));

        jLabel24.setBackground(new java.awt.Color(102, 102, 255));
        jLabel24.setForeground(new java.awt.Color(102, 102, 255));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/member.png"))); // NOI18N
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 50, 40));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/name (1).png"))); // NOI18N
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 50, 40));

        jLabel15.setBackground(new java.awt.Color(102, 102, 255));
        jLabel15.setForeground(new java.awt.Color(102, 102, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/online-learning.png"))); // NOI18N
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 50, -1));

        jLabel37.setBackground(new java.awt.Color(102, 102, 255));
        jLabel37.setForeground(new java.awt.Color(102, 102, 255));
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/branch.png"))); // NOI18N
        jPanel4.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 50, 50));

        lbl_studenterror.setFont(new java.awt.Font("Book Antiqua", 1, 24)); // NOI18N
        lbl_studenterror.setForeground(new java.awt.Color(204, 0, 0));
        jPanel4.add(lbl_studenterror, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 320, 40));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 340, 630, 530));

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

        jPanel2.add(jPanelLMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 270, -1));

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

        jPanel2.add(jPanelHomePage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 270, -1));

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

        jPanel2.add(jPanelmanagebooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 270, -1));

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

        jPanel2.add(jPanelIssuebook, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 270, -1));

        jPanelreturnbook.setBackground(new java.awt.Color(51, 51, 51));
        jPanelreturnbook.setPreferredSize(new java.awt.Dimension(340, 60));
        jPanelreturnbook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/return40.png"))); // NOI18N
        jLabel8.setText("   Return Book");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel8MouseExited(evt);
            }
        });
        jPanelreturnbook.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 240, -1));

        jPanel2.add(jPanelreturnbook, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 270, -1));

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

        jPanel2.add(jPanellogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 270, -1));

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

        jPanel2.add(jPanelmanagestudents, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 270, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 270, 960));

        setSize(new java.awt.Dimension(1540, 870));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost
        // TODO add your handling code here:
        if(!txt_bookid.getText().equals("")){
        getBookDetails();
        }
    }//GEN-LAST:event_txt_bookidFocusLost

    private void txt_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookidActionPerformed

    private void txt_studentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentidFocusLost
        // TODO add your handling code here:
        if(!txt_studentid.getText().equals(""))
        {
            getStudentDetails();
        }
    }//GEN-LAST:event_txt_studentidFocusLost

    private void txt_studentidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentidActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        // TODO add your handling code here:
        if(lbl_quantity.getText().equals("0") || lbl_studentname.getText().equals("") || lbl_bookname.getText().equals("")){
            ImageIcon icon = new ImageIcon(Admin_IssueBook.class.getResource("/test/error.png"));
            JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;color:red\">Book Or Student Is Not Available</b></html>","Message",JOptionPane.PLAIN_MESSAGE,icon);
        }
        else{
        if(isAlreadyIssued() == false)
        {
            if(issueBook() == true){
            
            ImageIcon icon = new ImageIcon(Admin_IssueBook.class.getResource("/test/checked.png"));
            JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;color:green\">Book Issued Successfully</b></html>","Message",JOptionPane.PLAIN_MESSAGE,icon);
            updateBookCount();
        }
            else
            {
                ImageIcon icon = new ImageIcon(Admin_IssueBook.class.getResource("/test/error.png"));
                JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;color:red\">Can Not Issue This Book</b></html>","Message",JOptionPane.PLAIN_MESSAGE,icon);
            }
        }
        else
        {
            ImageIcon icon = new ImageIcon(Admin_IssueBook.class.getResource("/test/error.png"));
            JOptionPane.showMessageDialog(this, "<html><b style=\"font-family:Book Antiqua;font-size:14px;color:red\">This Book Is Already Issued By This Student</b></html>","Message",JOptionPane.PLAIN_MESSAGE,icon);
        }
    } 
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

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
        ImageIcon icon = new ImageIcon(Admin_HomePage.class.getResource("/test/question.png"));
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

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        Admin_ReturnBook returnbook = new Admin_ReturnBook();
        returnbook.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

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
            java.util.logging.Logger.getLogger(Admin_IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser date_due;
    private com.toedter.calendar.JDateChooser date_issue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
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
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelHomePage;
    private javax.swing.JPanel jPanelIssuebook;
    private javax.swing.JPanel jPanelLMS;
    private javax.swing.JPanel jPanellogout;
    private javax.swing.JPanel jPanelmanagebooks;
    private javax.swing.JPanel jPanelmanagestudents;
    private javax.swing.JPanel jPanelreturnbook;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookerror;
    private javax.swing.JLabel lbl_bookid;
    private javax.swing.JLabel lbl_bookname;
    private javax.swing.JLabel lbl_branch;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_studenterror;
    private javax.swing.JLabel lbl_studentid;
    private javax.swing.JLabel lbl_studentname;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private app.bolivia.swing.JCTextField txt_bookid;
    private app.bolivia.swing.JCTextField txt_studentid;
    // End of variables declaration//GEN-END:variables
}
