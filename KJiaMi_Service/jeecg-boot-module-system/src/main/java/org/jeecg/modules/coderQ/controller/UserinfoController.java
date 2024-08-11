package org.jeecg.modules.coderQ.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.coderQ.entity.Userinfo;
import org.jeecg.modules.coderQ.service.IUserinfoService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: userinfo
 * @Author: 公众号：程序员辉哥 (技术答疑,项目分享)
 * @Date:   2023-05-11
 * @Version: V1.0
 */
@Api(tags="userinfo")
@RestController
@RequestMapping("/coderQ/userinfo")
@Slf4j
public class UserinfoController extends JeecgController<Userinfo, IUserinfoService> {
	@Autowired
	private IUserinfoService userinfoService;

	/**
	 * 分页列表查询
	 *
	 * @param userinfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "userinfo-分页列表查询")
	@ApiOperation(value="userinfo-分页列表查询", notes="userinfo-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Userinfo userinfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Userinfo> queryWrapper = QueryGenerator.initQueryWrapper(userinfo, req.getParameterMap());
		Page<Userinfo> page = new Page<Userinfo>(pageNo, pageSize);
		IPage<Userinfo> pageList = userinfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param userinfo
	 * @return
	 */
	@AutoLog(value = "userinfo-添加")
	@ApiOperation(value="userinfo-添加", notes="userinfo-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Userinfo userinfo) {
		userinfoService.save(userinfo);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param userinfo
	 * @return
	 */
	@AutoLog(value = "userinfo-编辑")
	@ApiOperation(value="userinfo-编辑", notes="userinfo-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Userinfo userinfo) {
		userinfoService.updateById(userinfo);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "userinfo-通过id删除")
	@ApiOperation(value="userinfo-通过id删除", notes="userinfo-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		userinfoService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "userinfo-批量删除")
	@ApiOperation(value="userinfo-批量删除", notes="userinfo-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.userinfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "userinfo-通过id查询")
	@ApiOperation(value="userinfo-通过id查询", notes="userinfo-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Userinfo userinfo = userinfoService.getById(id);
		if(userinfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(userinfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param userinfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Userinfo userinfo) {
        return super.exportXls(request, userinfo, Userinfo.class, "患者数据");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Userinfo.class);
    }

}
