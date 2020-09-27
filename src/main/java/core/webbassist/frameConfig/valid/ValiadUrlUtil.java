package core.webbassist.frameConfig.valid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public interface ValiadUrlUtil {
    void valiadUrl(HashMap<String, String> errMassage, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
