package gemini;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.*;
import java.io.IOException;

public class EmailMethod extends HttpServlet {

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        return convertToHex(md.digest());
    }

    public static String Perform(String toPerform) {
        String newString = "";
        try {
            newString = SHA1(toPerform);
        } catch (NoSuchAlgorithmException ex) {
            //
        } catch (UnsupportedEncodingException ex) {
            //
        }
        return newString;
    }

    //true if email is valid, false if invalid
    private static boolean Validate(String emailToCheck) {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(emailToCheck);
        return m.matches();
    }

    /**
     * Tries to send the email.
     *
     * Return ERROR specifies if there was an error. 0 - no error 1 - invalid
     * email address 2 - problem sending message
     */
    public static int sendMail(String recipient, String name) throws UnsupportedEncodingException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        int error = 0;

        if (Validate(recipient)) {
            String msgBody =
                    "Thank you for trying Gemini. Your verification code is below:\n\n"
                    + Perform(recipient + /*SECRET SALT...obviously not gonna release this!*/).substring(0, 15)
                    + "\n\nCopy and paste this code into the appropriate box on the "
                    + "validation page. Please save the first 7 characters of this code; "
                    + "you will need it to use your account. Also note that by creating "
                    + "your account, you are accepting the Gemini Terms of Service, "
                    + "available at http://willyg302.wordpress.com/gemini/."
                    + "\n\nRegards,\nThe Gemini Dev Team";

            try {
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress("geminiogs@gmail.com", "Gemini Online"));
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient, name));
                msg.setSubject("Gemini OGS Activation Code");
                msg.setText(msgBody);
                Transport.send(msg);
            } catch (AddressException e) {
                error = 1;
            } catch (MessagingException e) {
                error = 2;
            }
        } else {
            error = 1;
        }
        return error;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int error = sendMail(req.getParameter("email"), req.getParameter("name"));
        if (error > 0) {
            resp.sendRedirect("/signup.jsp?error=" + error);
        } else {
            resp.sendRedirect("/validate.jsp?verify=" + req.getParameter("email"));
        }
    }
}