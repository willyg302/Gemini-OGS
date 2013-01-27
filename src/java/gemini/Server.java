package gemini;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Server {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
    
    @Persistent
    private String account;
    
    @Persistent
    private Date date;
    
    @Persistent
    private String addr;
    
    @Persistent
    private int port;
    
    @Persistent
    private String name;
    
    @Persistent
    private String pass;
    
    @Persistent
    private int type;
    
    @Persistent
    private String list;
    
    @Persistent
    private int curr;
    
    @Persistent
    private int maxp;
    
    @Persistent
    private int rank;
    
    @Persistent
    private boolean bing;
      
    public Server(String account, Date date, String addr, int port, 
            String name, String pass, int type, String list, int curr, 
            int maxp, int rank, boolean bing) {
        this.account = account;
        this.date = date;
        this.addr = addr;
        this.port = port;
        this.name = name;
        this.pass = pass;
        this.type = type;
        this.list = list;
        this.curr = curr;
        this.maxp = maxp;
        this.rank = rank;
        this.bing = bing;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getAccount() {
        return account;
    }

    public Date getDate() {
        return date;
    }
    
    public String getAddr() {
        return addr;
    }
    
    public int getPort() {
        return port;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPass() {
        return pass;
    }
    
    public int getType() {
        return type;
    }
    
    public String getList() {
        return list;
    }
    
    public int getCurr() {
        return curr;
    }
    
    public int getMaxp() {
        return maxp;
    }
    
    public int getRank() {
        return rank;
    }
    
    public boolean getBing() {
        return bing;
    }
    
    public void update(Date date, int port, 
            String name, String pass, int type, String list, int curr, 
            int maxp, int rank, boolean bing) {
        this.date = date;
        this.port = port;
        this.name = name;
        this.pass = pass;
        this.type = type;
        this.list = list;
        this.curr = curr;
        this.maxp = maxp;
        this.rank = rank;
        this.bing = bing;
    }
    
}