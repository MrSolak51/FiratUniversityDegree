import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JTextField;

public class Notlar {
	private static String[] Goster(File file) {
		String[] stringler = new String[(int) file.length()];
		try {
            BufferedReader bReader = new BufferedReader(new FileReader(file));
            for(int i=0;i<file.length();i++) {
            	stringler[i]=bReader.readLine();
            }
            bReader.close();
        }
        
        catch(FileNotFoundException ex){
        	Logger.getLogger(Notlar.class.getName()).log(Level.SEVERE,null, ex);
        }
        
        catch(IOException ex) {
        	
        }
		
		return stringler;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		JFrame frame=new JFrame("Notlar");
		JTextField DersIsmi= new JTextField();
		JTextField VizeNot=new JTextField();
		JTextField FinalNot=new JTextField();
		JButton hesapla= new JButton("Hesapla Ve Kaydet");
		JTextArea yazdir=new JTextArea();
		JToggleButton FinalVarMi=new JToggleButton("Finale girdin mi?");
		
		frame.getContentPane().add(DersIsmi);
		frame.getContentPane().add(VizeNot);
		frame.getContentPane().add(FinalNot);
		frame.getContentPane().add(hesapla);
		frame.getContentPane().add(yazdir);
		frame.getContentPane().add(FinalVarMi);
		
		DersIsmi.setBounds(15,10,250,50);
		VizeNot.setBounds(15,60,250,50);
		FinalVarMi.setBounds(15,110,250,50);
		FinalNot.setBounds(15,160,250,50);
		yazdir.setBounds(15,210,250,50);
		hesapla.setBounds(15,260,250,50);
		
		yazdir.setLineWrap(true);
		yazdir.setEditable(false);
		FinalNot.setVisible(false);
		
		FinalVarMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(FinalVarMi.isSelected()) {
					FinalNot.setVisible(true);
				}
				else {
					FinalNot.setVisible(false);
				}
			}
		});
		
		hesapla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File("C:/Users/90532/Desktop/notlar/Notlarim.txt");
				String[] yazilar;
				String ders=DersIsmi.getText();
				String vize=VizeNot.getText();
				String finalNot;
				if(FinalVarMi.isSelected())
					finalNot=FinalNot.getText();
				else
					finalNot="";
				
				double not;
				if(FinalVarMi.isSelected()) {
					not=(Double.valueOf(vize)*0.4)+(Double.valueOf(finalNot)*0.6);
					if(not<50||Double.valueOf(finalNot)<50)
						not=0;
					}
					else {
						not=50-(Double.valueOf(vize)*0.4);
						not=not/0.6;
						
						if(not<50)
							not=50;
					}
				
				try {
		            BufferedWriter bWriter = new BufferedWriter(new FileWriter(file,true));
		            bWriter.append("\n"+ders+";"+"\n"+"vize = ");
		            bWriter.append(vize);
		            
		            if(!FinalVarMi.isSelected()) {
		            bWriter.append("\n"+"finalden alýnmasý gereken en düþük not : " + not);
		            }
		            else {
		                bWriter.append("\n"+"final = " + finalNot);
		                if(not==0)
		                	bWriter.append("\n"+"büte geldik...");
		                else
		                	bWriter.append("\n"+"not ortalamanýz = " + not);
		            }
		            bWriter.flush();
		            bWriter.close();
		        }
		        
		        catch(FileNotFoundException ex){
		        	Logger.getLogger(Notlar.class.getName()).log(Level.SEVERE,null, ex);
		        }
		        
		        catch(IOException ex) {
		        	
		        }
				
				yazilar=Goster(file);
				int j=0;
				while(yazilar[j]!=null) {
					yazdir.setText(yazilar[j]);
					j++;
				}
			}
		});
		
		frame.setSize(295,350);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}
