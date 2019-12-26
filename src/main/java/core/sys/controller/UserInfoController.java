package core.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/account")
public class UserInfoController {

	private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

	@RequestMapping(value = "/selectuserbyaccount", method = RequestMethod.POST)
	public Object getAccount(@RequestBody JSONObject json) {
		logger.info("json：{}",json);
		return json;
	}
}
