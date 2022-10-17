package Onto2DD;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.JDesktopPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class FileGeneratorInterface {

	private JFrame frmFileGenerator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileGeneratorInterface window = new FileGeneratorInterface();
					window.frmFileGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FileGeneratorInterface() {
		initialize();
	}
	public JFileChooser ontochooser= new  JFileChooser();
    public String fileAddress;
    public String selectedDest;
    private JLabel lblOntoRead = new JLabel("");
    private JLabel lblOntoName = new JLabel("");
    private JLabel lblDestRead = new JLabel("");
    private JButton btnSetDest = new JButton("Set the Destination");
    private JButton btnAsl = new JButton("Generate agent file");
    private JButton btnJson = new JButton("Generate Dialogflow zip");

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFileGenerator = new JFrame();
		frmFileGenerator.setTitle(" File Generator");
		frmFileGenerator.setBounds(100, 100, 475, 295);
		frmFileGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFileGenerator.getContentPane().setLayout(null);


		JButton btnBrowseOnto = new JButton("Browse the Ontology");
		btnBrowseOnto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ontochooser.showOpenDialog(null);
				File ontofile = ontochooser.getSelectedFile();
				fileAddress= ontofile.getAbsolutePath();// full address
				if (fileAddress.endsWith(".owl") || fileAddress.endsWith(".OWL")){
					lblOntoName.setText(ontofile.getName()+" is chosen, "); //just name
					//Load the Ontology:
					String ontomsg= FileGenerator.readOnto(fileAddress);
					lblOntoName.setText(lblOntoName.getText()+ontomsg);
					String classesmsg= FileGenerator.getOntoClasses(fileAddress);
					lblOntoRead.setText(classesmsg);
					if (classesmsg == "Successfully read the Classes!") {
						btnSetDest.setEnabled(true);
					}
				}
				else {
					lblOntoName.setText("Invalid file format. Please choose an owl file.");
				}
			}
		});
		btnBrowseOnto.setBounds(139, 18, 185, 23);
		frmFileGenerator.getContentPane().add(btnBrowseOnto);
		
		
		
		btnSetDest.setEnabled(false);
		btnSetDest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        //Set the Source:
		        JFileChooser destchooser = new JFileChooser();
		        destchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		        destchooser.showDialog(null, "Select Folder");
		        File pathDest = destchooser.getSelectedFile();
		        selectedDest = destchooser.getSelectedFile().toString();
		        if (!pathDest.exists()){
		        	selectedDest = selectedDest.substring(0, selectedDest.lastIndexOf("/"));
		        }
		        System.out.println("selectedDest: "+selectedDest);
		        lblDestRead.setText(selectedDest);
		        btnAsl.setEnabled(true); 
		        btnJson.setEnabled(true);
			}
		});
		
		
		btnSetDest.setBounds(139, 108, 185, 23);
		frmFileGenerator.getContentPane().add(btnSetDest);
		btnAsl.setForeground(Color.BLACK);
		btnAsl.setEnabled(false);



		btnAsl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Name = ontochooser.getSelectedFile().getName().toString();
				String folderName = "";
				folderName = Name.substring(0, Name.lastIndexOf("."));
				String aslmsg=FileGenerator.feedDJ(folderName,selectedDest);
				JOptionPane.showMessageDialog(null, aslmsg, "done!", 1);
			}
		});
		btnAsl.setBounds(139, 178, 185, 23);
		frmFileGenerator.getContentPane().add(btnAsl);
		btnJson.setEnabled(false);



		btnJson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Name = ontochooser.getSelectedFile().getName().toString();
				String folderName = "";
				folderName = Name.substring(0, Name.lastIndexOf("."));
				String[] jsonmsg=FileGenerator.feedDF(folderName,selectedDest);
				JOptionPane.showMessageDialog(null, jsonmsg[0], "done!", 1);
				JOptionPane.showMessageDialog(null, jsonmsg[1], "done!", 1);
				JOptionPane.showMessageDialog(null, jsonmsg[2], "done!", 1);
				JOptionPane.showMessageDialog(null, jsonmsg[3], "done!", 1);
			}
		});
		btnJson.setBounds(139, 208, 185, 23);
		frmFileGenerator.getContentPane().add(btnJson);
		lblOntoRead.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblOntoRead.setHorizontalAlignment(SwingConstants.CENTER);


		lblOntoRead.setBounds(20, 70, 420, 18);
		frmFileGenerator.getContentPane().add(lblOntoRead);
		lblOntoName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblOntoName.setHorizontalAlignment(SwingConstants.CENTER);


		lblOntoName.setBounds(20, 53, 420, 18);
		frmFileGenerator.getContentPane().add(lblOntoName);
		
		
		lblDestRead.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestRead.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblDestRead.setBounds(20, 138, 420, 18);
		frmFileGenerator.getContentPane().add(lblDestRead);

	}
}
