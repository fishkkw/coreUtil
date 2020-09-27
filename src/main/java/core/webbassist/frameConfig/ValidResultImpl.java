package core.webbassist.frameConfig;

import core.webbassist.frameConfig.valid.ValidResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangkai
 * @Copyright: Copyright (c) 兆日科技股份有限公司  2020
 * @date 2020/9/25,17:27
 */
public class ValidResultImpl implements ValidResult {
    private static final Logger logger = LoggerFactory.getLogger(ValidResultImpl.class);

    @Override
    public void paramValidResult(Object object) {
        logger.error("服务异常");
    }
}
