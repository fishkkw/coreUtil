package core.sys.controller.common;

import core.webbassist.frameConfig.valid.ValiadUrlUtil;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author wangkai
 * @Copyright: Copyright (c) 兆日科技股份有限公司  2020
 * @date 2020/9/27,9:33
 */
public class ValidUrlImpl implements ValiadUrlUtil {
    @Override
    public void valiadUrl(HashMap<String, String> errMassage, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String errMsg = errMassage.get("error");
        if (StringUtils.isEmpty(errMsg)) {
            return;
        }
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), errMsg);
    }
}
