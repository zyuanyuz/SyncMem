package zyz.zyuanyuz.syncmem.example.syncmemutil.serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zyz.zyuanyuz.syncmem.example.syncmemutil.SyncMemProtocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author George Yu
 * @since 2019/10/25 10:10
 */
public class JDKSerializer extends SyncMemSerializer {
  private static final Logger logger = LoggerFactory.getLogger(JDKSerializer.class);

  @Override
  public String serializeObject(SyncMemProtocol protocol) throws Exception {

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream objos = new ObjectOutputStream(baos);
    objos.writeObject(protocol);
    objos.flush();
    objos.close();
    try {
      String result = baos.toString("ISO-8859-1");
      logger.info(result);
      return result;
    } finally {
      baos.close();
    }
  }

  @Override
  public SyncMemProtocol deserializeObject(String msg) throws Exception {
    ByteArrayInputStream bais = new ByteArrayInputStream(msg.getBytes(StandardCharsets.ISO_8859_1));
    ObjectInputStream objis = new ObjectInputStream(bais);
    SyncMemProtocol protocol = (SyncMemProtocol) objis.readObject();
    logger.info("protocol :{}",protocol);
    bais.close();
    objis.close();
    return protocol;
  }
}
