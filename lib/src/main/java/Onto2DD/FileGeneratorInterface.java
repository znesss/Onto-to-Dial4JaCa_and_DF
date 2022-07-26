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

    public String[] intentsNames;
    public String fileName;
    FileGenerator filegenerator= new FileGenerator();
    OwlApiExtract owlapiextract= new OwlApiExtract();
    private JLabel lblOntoRead = new JLabel("");
    private JLabel lblOntoName = new JLabel("");
    private JButton btnLoadOnto = new JButton("Load the Ontology");
    private JButton btnAsl = new JButton("Build .asl file");
    private JButton btnJson = new JButton("Build .json files");

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
				JFileChooser ontochooser= new  JFileChooser();
				ontochooser.showOpenDialog(null);
				File ontofile = ontochooser.getSelectedFile();
				fileName= ontofile.getAbsolutePath();//getName()
				if (fileName.endsWith(".owl") || fileName.endsWith(".OWL")){
					btnLoadOnto.setEnabled(true);
					lblOntoName.setText(ontofile.getName());
					owlapiextract.main();
				}
				else {
					lblOntoName.setText("Invalid file format. Please choose an owl file.");
				}
			}
		});
		btnBrowseOnto.setBounds(139, 18, 172, 23);
		frmFileGenerator.getContentPane().add(btnBrowseOnto);


		//JButton btnLoadOnto = new JButton("Load the Ontology");
		btnLoadOnto.setEnabled(false);
		btnLoadOnto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        //String fileName = "src/main/resources/Miro-intents-names.owl";
		        intentsNames= filegenerator.get_ontointent_classes(fileName);
		        lblOntoRead.setText("Ontology Successfully read!");
		        btnAsl.setEnabled(true);
		        btnJson.setEnabled(true);

			}
		});
		btnLoadOnto.setBounds(160, 87, 129, 23);
		frmFileGenerator.getContentPane().add(btnLoadOnto);
		btnAsl.setEnabled(false);



		//JButton btnAsl = new JButton("Build .asl file");
		btnAsl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aslmsg=filegenerator.makeasl(intentsNames);
				JOptionPane.showMessageDialog(null, aslmsg, "done!", 1);
			}
		});
		btnAsl.setBounds(160, 165, 129, 23);
		frmFileGenerator.getContentPane().add(btnAsl);
		btnJson.setEnabled(false);




		//JButton btnJson = new JButton("Build .json files");
		btnJson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String jsonmsg=filegenerator.makejsons(intentsNames);
				JOptionPane.showMessageDialog(null, jsonmsg, "done!", 1);
			}
		});
		btnJson.setBounds(160, 201, 129, 23);
		frmFileGenerator.getContentPane().add(btnJson);
		lblOntoRead.setHorizontalAlignment(SwingConstants.CENTER);


		lblOntoRead.setBounds(139, 125, 172, 14);
		frmFileGenerator.getContentPane().add(lblOntoRead);
		lblOntoName.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblOntoName.setHorizontalAlignment(SwingConstants.CENTER);


		lblOntoName.setBounds(34, 52, 377, 14);
		frmFileGenerator.getContentPane().add(lblOntoName);

	}
}
