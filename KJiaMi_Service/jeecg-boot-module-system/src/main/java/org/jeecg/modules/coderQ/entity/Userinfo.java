package org.jeecg.modules.coderQ.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: userinfo
 * @Author: 公众号：程序员辉哥 (技术答疑,项目分享)
 * @Date:   2023-05-11
 * @Version: V1.0
 */
@Data
@TableName("userinfo")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="userinfo对象", description="userinfo")
public class Userinfo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    private String name;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
    private String age;
	/**电话*/
	@Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
    private String phone;
	/**身份证号*/
	@Excel(name = "身份证号", width = 15)
    @ApiModelProperty(value = "身份证号")
    private String idcard;
	/**邮政编码*/
	@Excel(name = "邮政编码", width = 15)
    @ApiModelProperty(value = "邮政编码")
    private String postcard;
	/**疾病*/
	@Excel(name = "疾病", width = 15)
    @ApiModelProperty(value = "疾病")
    private String jibin;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
