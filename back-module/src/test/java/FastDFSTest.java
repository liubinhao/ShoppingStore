
import com.shop.dev.utils.FastDFSUtils;
import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 创建人: 武奇
 * 创建事件: 2018/11/7
 */
public class FastDFSTest {

    @Test
    public void test() throws IOException, MyException {

        ClientGlobal.initByProperties("fastdfs.properties");
        System.out.println(ClientGlobal.configInfo());
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
//        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        byte[] bytes = IOUtils.toByteArray(new FileInputStream(new File("C:\\Users\\Administrator\\Downloads\\photo-1466854076813-4aa9ac0fc347.jpg")));
        String[] strings = storageClient.upload_file(bytes, 0, bytes.length, "jpg", null);
//        byte[] bytes = "HelloFastDFS".getBytes();
//        String[] strings = storageClient.upload_file(bytes, 0, bytes.length, "txt", null);
        System.out.println(Arrays.toString(strings));

    }

    // 测试FastDFSUtils工具类
    @Test
    public void imageTest() {
        String s = FastDFSUtils.imageHandler("fastdfs.properties", "C:\\Users\\Administrator\\Downloads\\photo-1466854076813-4aa9ac0fc347.jpg");
        System.out.println(s);
    }

}
