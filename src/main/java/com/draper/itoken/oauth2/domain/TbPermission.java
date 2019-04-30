package com.draper.itoken.oauth2.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@Data
@TableName(value = "tb_permission")
public class TbPermission implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父权限
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 权限名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 权限英文名称
     */
    @TableField(value = "enname")
    private String enname;

    /**
     * 授权路径
     */
    @TableField(value = "url")
    private String url;

    /**
     * 备注
     */
    @TableField(value = "description")
    private String description;

    @TableField(value = "create_at")
    private Long createAt;

    @TableField(value = "update_at")
    private Long updateAt;

    private static final long serialVersionUID = 1L;

    public static final String COL_PARENT_ID = "parent_id";

    public static final String COL_NAME = "name";

    public static final String COL_ENNAME = "enname";

    public static final String COL_URL = "url";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_CREATE_AT = "create_at";

    public static final String COL_UPDATE_AT = "update_at";
}