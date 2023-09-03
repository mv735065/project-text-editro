import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;

public class Text_Editor implements ActionListener {
   JFrame frame;
   JMenuBar menubar;
   JMenu file,edit;
   JMenuItem newwindow,save,open,cut,copy,paste,selectall,close;
   JTextArea textarea;
   Text_Editor(){
       frame=new JFrame("TextEditoR");

       // create menubar
       menubar=new JMenuBar();
       textarea=new JTextArea();
//     textarea.setBounds(0,0,1700,1000);
//       textarea.setVisible(true);

       // created a menu options File & Edit
        file=new JMenu("File");
        edit=new JMenu("Edit");

        // create and added menu items to File
       newwindow=new JMenuItem("New Window");
       save=new JMenuItem("Save File");
       open=new JMenuItem("Open File");

       // adding action event to menuitems
       newwindow.addActionListener(this);
       save.addActionListener(this);
       open.addActionListener(this);


       file.add(newwindow);
       file.add(save);
       file.add(open);

       // create and added menu items to Edit
       cut=new JMenuItem("Cut");
       copy=new JMenuItem("Copy");
       paste=new JMenuItem("Paste");
       selectall=new JMenuItem("SelectAll");
       close=new JMenuItem("Close");

       cut.addActionListener(this);
       copy.addActionListener(this);
       selectall.addActionListener(this);
       paste.addActionListener(this);
       close.addActionListener(this);

       edit.add(cut);
       edit.add(copy);
       edit.add(paste);
       edit.add(selectall);
       edit .add(close);
  // addd text area

       menubar.add(file);
       menubar.add(edit);

       frame.setJMenuBar(menubar);

       JPanel panel=new JPanel();
       panel.setBorder(new EmptyBorder(5,5,5,5));
       panel.setLayout(new BorderLayout(0,0));
       panel.add(textarea,BorderLayout.CENTER);

       JScrollPane scroll=new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

       panel.add(scroll);
       frame.add(panel);



      // frame.add(textarea);


       frame.setBounds(100,100,500,500);
         frame.setVisible(true);
       frame.setLayout(null);
    




   }

    public static void main(String args[]){
     //   System.out.println("hello");
  Text_Editor obj=new Text_Editor();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==cut){
         textarea.cut();
        }
       if(e.getSource()==close) System.exit(0);
        if(e.getSource()==copy){
            textarea.copy();
        }
        if(e.getSource()==selectall){
            textarea.selectAll();
        }
        if(e.getSource()==paste){
            textarea.paste();
        }
        if(e.getSource()==newwindow){
            Text_Editor obj=new Text_Editor();
        }
        if(e.getSource()==open){

            JFileChooser fileChooser=new JFileChooser("C:");

             int chooseoption=fileChooser.showOpenDialog(null);

             if(chooseoption==JFileChooser.APPROVE_OPTION){
                 File file=fileChooser.getSelectedFile();
                 String pathfile=file.getPath();

                 try {
                     FileReader fileReader=new FileReader(pathfile);
                     BufferedReader bufferedReader=new BufferedReader(fileReader);

                     String currline="",totalcontnet="";
                     while((currline=bufferedReader.readLine())!=null){
                         totalcontnet+=currline+"\n";
                     }

                     textarea.setText(totalcontnet);

                 } catch (IOException ex) {
                     throw new RuntimeException(ex);
                 }


             }







        }
        if(e.getSource()==save){

              JFileChooser fileChooser=new JFileChooser("C:");
              int choose=fileChooser.showSaveDialog(null);

              if(choose==JFileChooser.APPROVE_OPTION){
                  File file=new File(fileChooser.getSelectedFile().getPath()+".text");


                  try {
                      FileWriter fileWriter=new FileWriter(file);
                      BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);

                      textarea.write(bufferedWriter);
                      bufferedWriter.close();
                  } catch (IOException ex) {
                      throw new RuntimeException(ex);
                  }
              }


        }



    }
}
