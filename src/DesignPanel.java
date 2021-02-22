import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DesignPanel extends JFrame implements ActionListener, MouseListener {
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    JScrollPane scrollPane;
    JLabel nameLable;
    JLabel addressLable;
    JLabel countryLable;
    JLabel genderLable;
    JLabel degreeLable;
    JTextField nameText;
    JTextField addressText;
    JComboBox comboBox;
    JRadioButton maleRadioButton;
    JRadioButton femaleRadioButton;
    ButtonGroup buttonGroup;
    JCheckBox sscBox;
    JCheckBox hscBox;
    JCheckBox bscBox;
    JCheckBox mscBox;
    JTable table;
    DefaultTableModel tableModel;
    int row = -1;

    JButton addBtn;
    JButton saveBtn;
    JButton disTbBtn;
    JButton updateBtn;
    JButton deleteBtn;
    List<PersonalInfo> infoList = new ArrayList<>();
    List<PersonalInfo> infoListBackUp = new ArrayList<>();

    File dir;
    File file;

    public DesignPanel() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600,710);
        this.setLayout(null);

        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(5,5,550,300);
        Border border = BorderFactory.createTitledBorder("Personal info:");
        panel1.setBorder(border);
        nameLable = new JLabel("Name:");
        addressLable = new JLabel("Address:");
        countryLable = new JLabel("Country:");
        genderLable = new JLabel("Gender:");
        degreeLable = new JLabel("Degree:");

        nameText = new JTextField("Md. Fazle Rabbi");
        addressText = new JTextField("Md. Fazle Rabbi's address");
        comboBox = new JComboBox(new String[]{"-Select Country-","A","B","Bangladesh","India"});
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        sscBox = new JCheckBox("S.S.C");
        sscBox.setFocusable(false);
        hscBox = new JCheckBox("H.S.C");
        hscBox.setFocusable(false);
        bscBox = new JCheckBox("B.Sc.");
        bscBox.setFocusable(false);
        mscBox = new JCheckBox("M.Sc.");
        mscBox.setFocusable(false);

        nameLable.setBounds(5,30,60,30);
        addressLable.setBounds(5,80,60,30);
        countryLable.setBounds(5,130,60,30);
        genderLable.setBounds(5,180,60,30);
        degreeLable.setBounds(5,220,60,30);

        nameText.setBounds(70,30,300,30);
        addressText.setBounds(70,80,300,30);
        comboBox.setBounds(70,130,200,30);
        maleRadioButton.setBounds(70,180,80,30);
        femaleRadioButton.setBounds(150,180,80,30);
        sscBox.setBounds(70,220,60,30);
        hscBox.setBounds(130,220,60,30);
        bscBox.setBounds(190,220,60,30);
        mscBox.setBounds(250,220,60,30);
        panel1.add(nameLable);
        panel1.add(addressLable);
        panel1.add(countryLable);
        panel1.add(genderLable);
        panel1.add(degreeLable);
        panel1.add(nameText);
        panel1.add(addressText);
        panel1.add(comboBox);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(maleRadioButton);
        buttonGroup.add(femaleRadioButton);
        panel1.add(maleRadioButton);
        panel1.add(femaleRadioButton);
        panel1.add(sscBox);
        panel1.add(hscBox);
        panel1.add(bscBox);
        panel1.add(mscBox);


        panel2 = new JPanel();
        panel2.setBorder(border);
        panel2.setBounds(5,300,550,200);
        panel2.setLayout(new BorderLayout());

        JLabel label = new JLabel("Table");
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        table = new JTable();
        String[] clmn = {"No.","Name","Adress","Country","Gender","Degree"};
        tableModel = new DefaultTableModel(clmn, 0);


        table.setModel(tableModel);
        table.getColumnModel().getColumn(0).setResizable(false);
  //      table.setEnabled(false);
        scrollPane = new JScrollPane(table);
        panel2.add(scrollPane);

        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(550,30));
        pn.add(label);
        panel2.add(pn,BorderLayout.NORTH);




        panel3 = new JPanel();
        panel3.setLayout(null);
   //     Border border3 = BorderFactory.createTitledBorder("Personal info3:");
        panel3.setBounds(5,500,550,100);
  //      panel3.setBorder(border3);

        panel4 = new JPanel();
        panel4.setBounds(5,600,550,50);
        panel4.setLayout(new FlowLayout());
        addBtn = new JButton("ADD");
        addBtn.setFocusable(false);
        saveBtn = new JButton("SAVE IN FILE");
        saveBtn.setFocusable(false);
        disTbBtn = new JButton("DisplayInTable");
        disTbBtn.setFocusable(false);
        updateBtn = new JButton("UPDATE");
        updateBtn.setFocusable(false);
        deleteBtn = new JButton("Delete Row");
        deleteBtn.setFocusable(false);

        disTbBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        table.addMouseListener(this);
        deleteBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        addBtn.addActionListener(this);
        panel4.add(addBtn);
        panel4.add(saveBtn);
        panel4.add(disTbBtn);
        panel4.add(updateBtn);
        panel4.add(deleteBtn);


        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addBtn){
            //data read
            String name = nameText.getText();
            String address = addressText.getText();
            String country = (String) comboBox.getSelectedItem();
            String gender = "";
            String degree = "";
            if(maleRadioButton.isSelected()){
                gender = "Male";
            }if(femaleRadioButton.isSelected()) gender = "Female";

            boolean errorCheckForDegree = true;
            if(sscBox.isSelected()){
                degree = degree.concat("S.S.C ");
                errorCheckForDegree = false;
            }
            if(hscBox.isSelected()){
                degree = degree.concat("H.S.C ");
                errorCheckForDegree = false;
            }
            if(bscBox.isSelected()){
                degree = degree.concat("B.Sc ");
                errorCheckForDegree = false;
            }
            if(mscBox.isSelected()){
                degree = degree.concat("M.Sc");
                errorCheckForDegree = false;
            }
            //error check
            if(nameText.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"Enter your Name!","Error",JOptionPane.ERROR_MESSAGE);
                nameText.requestFocus();
            }else if(addressText.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"Enter Your address!","Error",JOptionPane.ERROR_MESSAGE);
                addressText.requestFocus();
            }else if(comboBox.getSelectedItem().toString().equals("-Select Country-")){
                JOptionPane.showMessageDialog(this,"Select Your Country !","Error",JOptionPane.ERROR_MESSAGE);
                comboBox.requestFocus();
            }else if(!(maleRadioButton.isSelected() || femaleRadioButton.isSelected())){
                JOptionPane.showMessageDialog(this,"Select Your Gender !","Error",JOptionPane.ERROR_MESSAGE);
                maleRadioButton.requestFocus();
            }else if(errorCheckForDegree) {
                JOptionPane.showMessageDialog(this, "Select Your Degree !", "Error", JOptionPane.ERROR_MESSAGE);
                degreeLable.requestFocus();
            }else{
                PersonalInfo data = new PersonalInfo(name,address,country,gender,degree);
                infoList.add(data);
                tableModel.setRowCount(0);
                for(int i =0; i< infoList.size() ; i++){
                    Object[] rowData = {i+1,infoList.get(i).getName(),infoList.get(i).getAddress(),infoList.get(i).getCountry(),
                            infoList.get(i).getGender(),infoList.get(i).getDegree()};
                    tableModel.addRow(rowData);
                }
                infoListBackUp = infoList;

              //  infoList.clear();

                nameText.setText("");
                addressText.setText("");
                comboBox.setSelectedIndex(0);
                sscBox.setSelected(false);
                hscBox.setSelected(false);
                bscBox.setSelected(false);
                mscBox.setSelected(false);
                buttonGroup.clearSelection();
                nameText.requestFocus();

            }

        }



        if(e.getSource() == saveBtn){
            JFileChooser fc = new JFileChooser(dir);
            FileNameExtensionFilter filter;
            filter = new FileNameExtensionFilter("*.txt",new String[]{"txt","pdf"});
            fc.addChoosableFileFilter(filter);
            int option = fc.showSaveDialog(this);
   /*         if(option == JFileChooser.APPROVE_OPTION){
                dir = fc.getCurrentDirectory();
                file = fc.getSelectedFile();

                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
                    for(PersonalInfo ls : infoList){
                        bufferedWriter.write(ls.getName()+" "+ls.getAddress()+" "+ls.getCountry()+" "+ls.getGender()+" "+ls.getDegree());
                        bufferedWriter.newLine();
                    }
                    bufferedWriter.flush();
                    JOptionPane.showMessageDialog(this,"Save","info",JOptionPane.PLAIN_MESSAGE);
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }*/

            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    file = fc.getSelectedFile();
                    PrintWriter os = new PrintWriter(new FileWriter(file,false));
               //     os.println("");
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        os.print(table.getColumnName(col) + "\t");
                    }

                    os.println("");
//                    os.println("");

                    for (int i = 0; i < table.getRowCount(); i++) {
                        for (int j = 0; j < table.getColumnCount(); j++) {
                            os.print(table.getValueAt(i, j).toString() + "\t");

                        }
                        os.println("");
                    }
                    os.close();
                    System.out.println("Done!");
                } catch (IOException ioException) {
                    // TODO Auto-generated catch block
                    ioException.printStackTrace();
                }
            }

        }

        if(e.getSource() == disTbBtn){
            JFileChooser fc = new JFileChooser(dir);
            FileNameExtensionFilter filter;
            filter = new FileNameExtensionFilter("*.txt",new String[]{"txt","pdf"});
            fc.addChoosableFileFilter(filter);
            int option = fc.showOpenDialog(this);
            if(option == JFileChooser.APPROVE_OPTION){
                dir = fc.getCurrentDirectory();
                file = fc.getSelectedFile();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    Scanner sc = new Scanner(bufferedReader);
                    String[] stringsArry;
                    tableModel.setRowCount(0);
                    while (sc.hasNextLine()){
                        String temString = sc.nextLine();
                        if(temString.indexOf("\t")>0){
                            stringsArry = temString.split("\t");
                        }else{
                            stringsArry = temString.split("\n");
                        }
                        Object[] data = new Object[stringsArry.length];
                        for(int i = 0;i<data.length;i++){
                            data[i] = stringsArry[i];
                        }

                        tableModel.addRow(data);
                    }


                    try {
                        bufferedReader.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        if(e.getSource() == deleteBtn){
            if(0<=row){
            int choice = JOptionPane.showConfirmDialog(this,"Do you want to delete this row data?",
                    "Delete Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
           if(choice == 0){
                tableModel.removeRow(row);
                infoList.remove(row);
            }
          }
        }

        if(e.getSource() == updateBtn){
            for(int i =0;i<infoList.size();i++){
                infoList.get(i).setName(tableModel.getValueAt(i,1).toString());
                infoList.get(i).setAddress(tableModel.getValueAt(i,2).toString());
                infoList.get(i).setCountry(tableModel.getValueAt(i,3).toString());
                infoList.get(i).setGender(tableModel.getValueAt(i,4).toString());
                infoList.get(i).setDegree(tableModel.getValueAt(i,5).toString());
            }
            
            JOptionPane.showMessageDialog(this,"UPDATE");

        }




    }

    @Override
    public void mouseClicked(MouseEvent e) {
        row = table.getSelectedRow();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
