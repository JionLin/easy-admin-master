package com.laker.admin.utils;

import com.alibaba.fastjson.JSON;
import com.laker.admin.module.bus.pojo.CollCommentVo;
import com.laker.admin.module.sys.pojo.MenuVo;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {

    public static List<MenuVo> toTree(List<MenuVo> treeList, Long pid) {
        List<MenuVo> retList = new ArrayList<MenuVo>();
        for (MenuVo parent : treeList) {
            if (pid.equals(parent.getPid())) {
                retList.add(findChildren(parent, treeList));
            }
        }
        return retList;
    }
    private static MenuVo findChildren(MenuVo parent, List<MenuVo> treeList) {
        for (MenuVo child : treeList) {
            if (parent.getId().equals(child.getPid())) {
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(findChildren(child, treeList));
            }
        }
        return parent;
    }

    public static void main(String[] args) {
        List<CollCommentVo> vos=new ArrayList<>();
        CollCommentVo commentVo=new CollCommentVo();
        commentVo.setId(1L);
        commentVo.setParentId(0L);
        vos.add(commentVo);
        CollCommentVo commentVo2=new CollCommentVo();
        commentVo2.setId(2L);
        commentVo2.setParentId(1L);
        vos.add(commentVo2);
        CollCommentVo commentVo3=new CollCommentVo();
        commentVo3.setId(3L);
        commentVo3.setParentId(1L);
        vos.add(commentVo3);
        CollCommentVo commentVo4=new CollCommentVo();
        commentVo4.setId(4L);
        commentVo4.setParentId(0L);
        vos.add(commentVo4);
        CollCommentVo commentVo5=new CollCommentVo();
        commentVo5.setId(5L);
        commentVo5.setParentId(4L);
        vos.add(commentVo5);

        List<CollCommentVo> commentVos = toTreeCollComment(vos, 0L);
        System.out.println(JSON.toJSONString(commentVos));
    }

    public static List<CollCommentVo> toTreeCollComment(List<CollCommentVo> collComments, Long pid) {
        List<CollCommentVo> collCommentVos = new ArrayList<>();
        for (CollCommentVo parent : collComments) {
            if (pid.equals(parent.getParentId())){
                collCommentVos.add(findChildrenCollComment(parent,collComments));
            }
        }

        return collCommentVos;
    }

    private static CollCommentVo findChildrenCollComment(CollCommentVo parent, List<CollCommentVo> treeList) {
        for (CollCommentVo child : treeList) {
            if (parent.getId().equals(child.getParentId())){
                if (parent.getChildren()==null){
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(findChildrenCollComment(child,treeList));
            }
        }
        return parent;

    }

}