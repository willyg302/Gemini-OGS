package geminitester;

import javax.swing.JFrame;

public class GeminiTester {
    public static void main(String[] args) {
        TcpLinkTester theTcp = new TcpLinkTester();
        JFrame frame = new TcpLinkUI(theTcp);
        frame.setTitle("Gemini OGS Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}