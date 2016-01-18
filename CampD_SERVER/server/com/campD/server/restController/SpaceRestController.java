package com.campD.server.restController;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.campD.server.data.SpaceJsonServer;

/**
 * 
 * @author qrh
 *
 */
@RestController
@RequestMapping(value="/space")
public class SpaceRestController extends BaseRestController {
	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private SpaceJsonServer spaceJsonServer;
	
	/**
	 * 后台场地发布
	 * @param reqMap:{creatorId：创建者id，createTime：创建时间，name：场地名称，adress：场地地址，traggic：交通状况，workFor：使用哪些活动，capacity：场地容量，spaceType：场地类型，contactor：场地联系人，cost：花费，contact：联系方式，showImages：场地展示图片，description：场地描述}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/add", method=RequestMethod.POST)
    @ResponseBody
	public Map add(HttpServletRequest request){
		Map reqMap = bindParamToMap(request);
    	Map returnMap = spaceJsonServer.add(reqMap);
    	
        return returnMap;
	}
	
	/**
	 * 后台场地发布
	 * @param reqMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/update", method=RequestMethod.POST)
    @ResponseBody
	public Map update(HttpServletRequest request){
		
		Map reqMap = bindParamToMap(request);
    	Map returnMap = spaceJsonServer.update(reqMap);
    	
        return returnMap;
	}
	
	/**
	 * 后台场地级别更新
	 * @param reqMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/updateLevel", method=RequestMethod.POST)
    @ResponseBody
	public Map updateLevel(HttpServletRequest request){
		
		Map reqMap = bindParamToMap(request);
    	Map returnMap = spaceJsonServer.updateLevel(reqMap);
    	
        return returnMap;
	}
	
	/**
	 * 查询场地列表信息
	 * @param reqMap:{name：场地名称，adress：场地地址，workFor：使用哪些活动(是就是范畴id)，capacity：场地容量，cost：花费}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/getSpaceInfoList", method=RequestMethod.POST)
    @ResponseBody
	public Map getSpaceInfoList(HttpServletRequest request){
		Map reqMap = bindParamToMap(request);
    	Map returnMap = spaceJsonServer.getSpaceInfoList(reqMap);
    	
        return returnMap;
	}
	
	/**
	 * 查询场地详情
	 * @param reqMap:{id:场地id}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/getSpaceInfoById", method=RequestMethod.POST)
    @ResponseBody
	public Map getSpaceInfoById(HttpServletRequest request){
		Map reqMap = bindParamToMap(request);
    	Map returnMap = spaceJsonServer.getSpaceInfoById(reqMap);
    	
        return returnMap;
	}

}
