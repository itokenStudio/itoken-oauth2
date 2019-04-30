package com.draper.itoken.oauth2.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@Data
@TableName(value = "tb_user")
public class TbUser implements Serializable {

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 用户名
	 */
	@TableField(value = "username")
	private String username;

	/**
	 * 密码，加密存储
	 */
	@TableField(value = "password")
	private String password;

	/**
	 * 注册手机号
	 */
	@TableField(value = "phone")
	private String phone;

	/**
	 * 注册邮箱
	 */
	@TableField(value = "email")
	private String email;

	@TableField(value = "create_at")
	private Long createAt;

	@TableField(value = "update_at")
	private Long updateAt;

	private static final long serialVersionUID = 1L;

	public static final String COL_USERNAME = "username";

	public static final String COL_PASSWORD = "password";

	public static final String COL_PHONE = "phone";

	public static final String COL_EMAIL = "email";

	public static final String COL_CREATE_AT = "create_at";

	public static final String COL_UPDATE_AT = "update_at";

}