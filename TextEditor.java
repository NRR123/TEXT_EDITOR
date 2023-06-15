import javax.swing.*;
import javax.swing.border.EmptyBorder;
//import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TextEditor implements ActionListener {
    //Declaring items and properties of text editor
    JFrame frame;
    JMenuBar menubar;
    JMenu file,edit;

    JMenuItem Newfile,Openfile,saveFile;
    JMenuItem cut,copy,paste,selectall,close;

    JTextArea textArea;

    TextEditor(){
        //initializing the properties
        frame=new JFrame();
        //initializing menu bar
        menubar=new JMenuBar();
        //initializing text area
        textArea=new JTextArea();
        //adding menu bar items
        file=new JMenu("File");
        edit=new JMenu("Edit");


        //Initializing menu items for file
        Newfile=new JMenuItem("NEW FILE");
        Openfile=new JMenuItem("OPEN FILE");
        saveFile=new JMenuItem("SAVE FILE");

        //tie action listener with all menu bar items so we action can be taken
        Newfile.addActionListener(this);
        Openfile.addActionListener(this);
        saveFile.addActionListener(this);

        //adding menu items to menu
        file.add(Newfile);
        file.add(Openfile);
        file.add(saveFile);

        //Initializing menu items for Edit
        cut=new JMenuItem("CUT");
        copy=new JMenuItem("COPY");
        paste=new JMenuItem("PASTE");
        selectall=new JMenuItem("SELECT ALL");
        close=new JMenuItem("CLOSE");

        //tie action listener with all menu bar items so we action can be taken
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);


        //adding menu items to edit
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);


        //adding menus to the menubar
        menubar.add(file);
        menubar.add(edit);

        //adding menu bar in the frame
        //before we add menu bar in the frame we must ensure al the menus are added in menu bar
        frame.setJMenuBar(menubar);
        //adding text area to frame
        //instead of just adding we want out content to be in the form of scrollable format
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        panel.add(textArea,BorderLayout.CENTER);

        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //Add scroll pane to panel
        panel.add(scrollPane);
        frame.add(panel);
        //frame.add(textArea);


        //before declaring the size of frame we should add menus to the frame

        frame.setBounds(0,0,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);//what this is doing?

    }
    @Override
    //action performed will take the action on the basis of the action event
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()== cut){
            //perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()== copy){
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()== paste){
            //perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()== selectall){
            //perform selectall operation
            textArea.selectAll();
        }
        if(actionEvent.getSource()== close){
            //perform close operation
            System.exit(0);
        }
        //didn't understand the open function
        if(actionEvent.getSource()==Openfile){
            //perform openfile operation
            //open file chooser
            JFileChooser fileChooser=new JFileChooser("C:");
            //default path of file chooser is C drive;
            int chooseOption=fileChooser.showOpenDialog(null);

            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //getting selected file
                File file=fileChooser.getSelectedFile();
                String filepath= file.getPath();
                try {
                    FileReader fileReader=new FileReader(filepath);
                    //Initialize buffered reader
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String intermediate="",output="";
                    //read content of file line by line
                    while((intermediate= bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);

                } catch(IOException ioException){
                    ioException.printStackTrace();
                }

            }




        }
        if(actionEvent.getSource()==saveFile){
            //perform openfile operation
            //open file chooser
            JFileChooser fileChooser=new JFileChooser("C:");
            //default path of file chooser is C drive;
            int chooseOption=fileChooser.showSaveDialog(null);

            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //getting selected file
               File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                try {
                    FileWriter fileWriter=new FileWriter(file);
                    //Initialize buffered reader
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();

                } catch(IOException ioException){
                    ioException.printStackTrace();
                }

            }



        }
        if(actionEvent.getSource()==Newfile){
            TextEditor texteditor =new TextEditor();
        }

    }



    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        TextEditor texteditor =new TextEditor();




    }
}