package main.java.gla.ac.in.chatbox;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;

class ChatbotAppliaction extends JFrame implements KeyListener{

    JPanel p=new JPanel();
    JTextArea dialog=new JTextArea(20,50);
    JTextArea input=new JTextArea(1,50);
    JScrollPane scroll=new JScrollPane(
            dialog,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    String[][] chatBot={
            //standard greetings
            {"hi","hello","hola","ola","howdy"},
            {"hi","hello","hey"},
            //question greetings
            {"how are you","how r you","how r u","how are u"},
            {"good","doing well","i'm fine thanks for asking"},
            //ask about the bot
            {"who are you","tell me about you","what are you","about you"},
            {"I am Silver a conversational chatbot"},
            //ask favourite movie
            {"what is your favourite movie","favourite movie","what's your favourite movie","your favourite movie","any movie you like"},
            {"My favourite is Gravity (2013)"},
            //ask about creator
            {"who created you","your creator","about your creator","creators info"},
            {"I was created by team Chatbox from GLA University"},
            //about the language
            {"which language were you created in","what language were you made in","about your coded language"},
            {"I was created in java language"},
            //about the user
            {"do you think i am smart","am i smart","do u think i m smart","do u think i am smart"},
            {"Everyone is smart in it's own way"},
            //no input response
            {" "},
            {"Please type something"},
            //mock the bot
            {"idiot","fool","moron","stupid"},
            {"shut up","stop talking","you're bad"},
            //jokes
            {"jokes","tell jokes","any jokes","tell me jokes","make me laugh","i want to laugh","joke"},
            {"Two fish in a tank. \nOne says: “How do you drive this thing?","What does a clam do on his birthday? \nHe shellabrates!",
                    "What did the grandma cat say to her grandson when she saw him slouching?\n A: You need to pay more attention to my pawsture.",
                    "Wanna hear a bad cat joke? A: Just kitten!"},
            //apologizes
            {"sorry","i'm sorry","i am sorry","i m sorry"},
            {"it's okay","no problem"},
            //yes
            {"yes"},
            {"no","NO","NO!!!!!!!"},
            //default
            {"can't understand"}
    };

    public static void main(String[] args){
        new ChatbotAppliaction();
    }

    public ChatbotAppliaction(){
        super("Chat Bot");
        setSize(600,400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        dialog.setEditable(false);
        input.addKeyListener(this);

        p.add(scroll);
        p.add(input);
        p.setBackground(new Color(53, 248, 255));
        add(p);

        setVisible(true);
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            input.setEditable(false);
            //-----grab quote-----------
            String quote=input.getText();
            input.setText("");
            addText("-->You:\t"+quote);
            quote.trim();
            while(
                    quote.charAt(quote.length()-1)=='!' ||
                            quote.charAt(quote.length()-1)=='.' ||
                            quote.charAt(quote.length()-1)=='?'
            ){
                quote=quote.substring(0,quote.length()-1);
            }
            quote.trim();
            byte response=0;
			/*
			0:we're searching through chatBot[][] for matches
			1:we didn't find anything
			2:we did find something
			*/
            //-----check for matches----
            int j=0;//which group we're checking
            while(response==0){
                if(inArray(quote.toLowerCase(),chatBot[j*2])){
                    response=2;
                    int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
                    addText("\n-->Silver\t"+chatBot[(j*2)+1][r]);
                }
                j++;
                if(j*2==chatBot.length-1 && response==0){
                    response=1;
                }
            }

            //-----default--------------
            if(response==1){
                int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
                addText("\n-->Silver\t"+chatBot[chatBot.length-1][r]);
            }
            addText("\n");
        }
    }

    public void keyReleased(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            input.setEditable(true);
        }
    }

    public void keyTyped(KeyEvent e){}

    public void addText(String str){
        dialog.setText(dialog.getText()+str);
    }

    public boolean inArray(String in,String[] str){
        boolean match=false;
        for(int i=0;i<str.length;i++){
            if(str[i].equals(in)){
                match=true;
            }
        }
        return match;
    }
}
