/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Classes.Posts;
import Classes.Users;
import Config.Connect;
import static Lib.LoginPreferences.loadId;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.MatteBorder;

/**
 *
 * @author ACER NITRO
 */
public class Profile extends javax.swing.JFrame {

    /**
     * Creates new form PROFILE
     */
    int offset = 0;
    int limit = 10;
    int user_id;
    public Profile(int id) {
        this.user_id = id;
        initComponents();
        if(this.user_id != loadId()){
            btnEditProfile.setVisible(false);
            btnAddSharing.setVisible(false);
            btnAddDiscussion.setVisible(false);
        }
        discussion.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
        panelPost.setLayout(new BoxLayout(panelPost, BoxLayout.Y_AXIS));
        updatePost('S',limit);
        Users user = new Users(id);
        name.setText("<html><div style='width: 400px;'>" + user.getName() + " ("+user.getGender()+")" + "</div></html>");
        bio.setText("<html><div style='width: 400px;'>" + user.getBio().replace("\n", "<br>") + "</div></html>");
        background.setText(user.getFaculty() + " - " + user.getUniversity());
        
        try (Connection conn = Connect.getConnection(); Statement stmt = conn.createStatement()) {
            String totalShare = "SELECT COUNT(*) FROM posts WHERE type = 's' AND user_id = " + user.getId();
            ResultSet rsShare = stmt.executeQuery(totalShare);
            
            while(rsShare.next()){
                totalSharing.setText(String.valueOf(rsShare.getInt(1)));
            }
            
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        try (Connection conn = Connect.getConnection(); Statement stmt = conn.createStatement()) {
            String totalDis = "SELECT COUNT(*) FROM posts WHERE type = 'd' AND user_id = " + user.getId();
            ResultSet rsDiscussion = stmt.executeQuery(totalDis);
            
            while(rsDiscussion.next()){
                totalDiscussion.setText(String.valueOf(rsDiscussion.getInt(1)));
            }
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    private void updatePost(char type, int limit){
        try (Connection conn = Connect.getConnection(); Statement stmt = conn.createStatement()) {
            String query = "SELECT * FROM posts WHERE type = '" + type + "' AND user_id = '"+ this.user_id +"' ORDER BY id DESC LIMIT " + offset + "," + limit;
            ResultSet rs = stmt.executeQuery(query);
            int total = 0;
            while (rs.next()) {
                offset++;
                Posts post = new Posts(rs.getInt("id")); 
                CardPostUser cp = new CardPostUser(post);
                panelPost.add(cp);
            }
            String query_total = "SELECT COUNT(*) FROM posts WHERE type = '" + type + "' AND user_id = '"+ this.user_id +"' ORDER BY id DESC";
            ResultSet rs_total = stmt.executeQuery(query_total);
            while (rs_total.next()) {
                total = rs_total.getInt(1);
            }
            if(offset < total){
                buttonMore();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void buttonMore(){
        JButton buttonMore = new JButton();
        buttonMore.setSize(400,400);
        buttonMore.setVisible(true);
        buttonMore.setText("See More");
        buttonMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPost.remove(buttonMore);
                panelPost.revalidate();
                panelPost.repaint();
                updatePost('S',limit);
            }
        });
        panelPost.add(buttonMore);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnEditProfile = new rojerusan.RSMaterialButtonRectangle();
        btnAddSharing = new rojerusan.RSMaterialButtonRectangle();
        btnAddDiscussion = new rojerusan.RSMaterialButtonRectangle();
        jPanel5 = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        background = new javax.swing.JLabel();
        bio = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        totalSharing = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        totalDiscussion = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        sharing = new javax.swing.JButton();
        discussion = new javax.swing.JButton();
        panelPost = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnEditProfile.setBackground(new java.awt.Color(22, 50, 91));
        btnEditProfile.setText("Edit Profile");
        btnEditProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProfileActionPerformed(evt);
            }
        });

        btnAddSharing.setBackground(new java.awt.Color(22, 50, 91));
        btnAddSharing.setText("Add Sharing");
        btnAddSharing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSharingActionPerformed(evt);
            }
        });

        btnAddDiscussion.setBackground(new java.awt.Color(22, 50, 91));
        btnAddDiscussion.setText("Add Discussion");
        btnAddDiscussion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDiscussionActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        name.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        name.setText("CONTOHNAMA");

        background.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        background.setText("Faculty - University");

        bio.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        bio.setText("ContohBio");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bio, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name)
                    .addComponent(background))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bio)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        totalSharing.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        totalSharing.setText("1");

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel8.setText("Sharing");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel5.setText("Discussion");

        totalDiscussion.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        totalDiscussion.setText("1");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(totalSharing)
                        .addGap(37, 37, 37))
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(totalDiscussion)
                        .addGap(43, 43, 43))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(totalDiscussion)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel5))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(totalSharing)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel8)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnEditProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddSharing, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddDiscussion, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(577, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(22, 22, 22)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddSharing, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddDiscussion, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        sharing.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        sharing.setText("Sharing");
        sharing.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        sharing.setContentAreaFilled(false);
        sharing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sharingActionPerformed(evt);
            }
        });

        discussion.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        discussion.setText("Discussion");
        discussion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        discussion.setContentAreaFilled(false);
        discussion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discussionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(sharing, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 305, Short.MAX_VALUE)
                .addComponent(discussion, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sharing)
                    .addComponent(discussion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPost.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelPostLayout = new javax.swing.GroupLayout(panelPost);
        panelPost.setLayout(panelPostLayout);
        panelPostLayout.setHorizontalGroup(
            panelPostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelPostLayout.setVerticalGroup(
            panelPostLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelPost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void discussionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discussionActionPerformed
        // TODO add your handling code here:
        offset = 0;
        limit = 10;
        panelPost.removeAll();
        panelPost.revalidate();
        panelPost.repaint();
        updatePost('D',limit);
        discussion.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
        sharing.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
    }//GEN-LAST:event_discussionActionPerformed

    private void sharingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sharingActionPerformed
        // TODO add your handling code here:
        offset = 0;
        limit = 10;
        panelPost.removeAll();
        panelPost.revalidate();
        panelPost.repaint();
        updatePost('S',limit);
        discussion.setBorder(new MatteBorder(0, 0, 0, 0, Color.BLACK));
        sharing.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
    }//GEN-LAST:event_sharingActionPerformed

    private void btnAddDiscussionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDiscussionActionPerformed
        // TODO add your handling code here:
        AddPost AP = new AddPost('D');
        AP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        AP.setVisible(true);
    }//GEN-LAST:event_btnAddDiscussionActionPerformed

    private void btnAddSharingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSharingActionPerformed
        // TODO add your handling code here:
        AddPost AP = new AddPost('S');
        AP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        AP.setVisible(true);
    }//GEN-LAST:event_btnAddSharingActionPerformed

    private void btnEditProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProfileActionPerformed
        // TODO add your handling code here:
        EditProfile EP = new EditProfile();
        EP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        EP.setVisible(true);
    }//GEN-LAST:event_btnEditProfileActionPerformed

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
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Profile(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel bio;
    private rojerusan.RSMaterialButtonRectangle btnAddDiscussion;
    private rojerusan.RSMaterialButtonRectangle btnAddSharing;
    private rojerusan.RSMaterialButtonRectangle btnEditProfile;
    private javax.swing.JButton discussion;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel name;
    private javax.swing.JPanel panelPost;
    private javax.swing.JButton sharing;
    private javax.swing.JLabel totalDiscussion;
    private javax.swing.JLabel totalSharing;
    // End of variables declaration//GEN-END:variables
}
