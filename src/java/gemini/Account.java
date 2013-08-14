package gemini;

import java.util.ArrayList;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Account {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
    private String password;
    
    @Persistent
    private ArrayList<String> messages;
    
    @Persistent
    private int fieldsize;
    
    @Persistent
    private String code;
    
    public Account(String password, String code) {
        this.password = password;
        this.messages = new ArrayList<String>();
        this.fieldsize = 10;
        this.code = code;
    }
    
    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
    
    public String getCode() {
        return code;
    }
    
    public ArrayList<String> getMessages() {
        return messages;
    }
    
    public int getFieldSize() {
        return fieldsize;
    }
    
    public void setPassword(String newPass) {
        this.password = newPass;
    }
    
    public void addMessage(String Message) {
        if(Message.startsWith("<@>"))
            messages.add(0, Message);
    }
    
    public void flushMessages(int Begin) {
        if(Begin < messages.size()) {
            ArrayList<String> temp = new ArrayList<String>();
            int i;
            for(i = 0; i < Begin; i++)
                temp.add(messages.get(i));
            messages = temp;
        }
    }
    
    public void setFieldSize(int Size) {
        if((Size > 0) && (Size < 51))
            fieldsize = Size;
    }
    
    public void removeMessage(int Index) {
        if((Index >= 0) && (Index < fieldsize))
            messages.remove(Index);
    }
    
    public void replaceMessage(int Index, String newMessage) {
        if((Index >= 0) && (Index < fieldsize))
            messages.set(Index, "<@>" + newMessage);
    }
    
    public void appendMessage(int Index, String Message) {
        if((Index >= 0) && (Index < fieldsize))
            messages.set(Index, messages.get(Index) + Message);
    }
    
    public void performOp(int Index, int Op, int numBy) {
        if((Index >= 0) && (Index < fieldsize)) {
            String toOp = messages.get(Index);
            int i = Integer.parseInt(toOp.substring(3,toOp.length()));
            switch(Op) {
                case 1: i += numBy; break;
                case 2: i -= numBy; break;
                case 3: i *= numBy; break;
                case 4: i /= numBy; break;
            }
            messages.set(Index,"<@>" + Integer.toString(i));
        }
    }
}