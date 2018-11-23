import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class User {
    @Test
    public void test(){
        String password = "66";
        Md5Hash md5Hash= new Md5Hash(password);
        System.out.println(md5Hash);
        md5Hash=new Md5Hash(password,"admin");
        System.out.println(md5Hash);
    }
}
