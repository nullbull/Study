package main.java.util;

/**
 * @author 牛贞昊（niuzhenhao@58.com）
 * @date 2019/3/27 10:54
 * @desc
 */
public class DeathCode {
    /**
     *  从wlist 获取
     */
    //    private IPage<CommentVO> getCommentVOFromWList(IPage commentPage, String courseName, String targetId, int pageSize, int pageNo, Long userId,
//                                                   Date beginTime, Date endTime, List<CommentVO> commentList) throws Exception {
//        /**
//         * courseName 不能为空
//         */
//        ParameterAsserts.isValid(courseName, "获取评论异常， 课程名不能为空");
//        log.info("[queryCommentByPage] 从Wlist获取评论");
//        byte[] byteOfTargetId = targetId.getBytes();
//        StringBuilder sb = new StringBuilder();
//        if (userId > 0) {
//            sb.append("userId=").append(userId);
//        }
//        if (null != beginTime && null != endTime) {
//            sb.append("&createTime=").append(beginTime.getTime() / 1000).append("_");
//            sb.append(endTime .getTime() / 1000);
//        }
//        else if (null != beginTime && null == endTime) {
//            sb.append("&createTime=").append(beginTime.getTime() / 1000).append("_");
//            sb.append(System.currentTimeMillis() / 1000);
//        }
//        else if (null == beginTime && null != endTime) {
//            sb.append("&createTime=").append("0_");
//            sb.append(endTime .getTime() / 1000);
//        }
//        long total = wlistClient.count(Constants.DEFAULT_WLIST_ID, byteOfTargetId, sb.toString());
//        commentPage.setTotal(total);
//        if (total > 0) {
//            //scanRange，按createTime倒序排列，获取所有字段
//            RangeReply rangeReply = wlistClient.range(Constants.DEFAULT_WLIST_ID, byteOfTargetId, "*", "", "createTime desc",
//                    pageNo, pageSize);
//            /**
//             * 封装CommentVO
//             */
//            if (rangeReply != null && rangeReply.getErrCode() == ErrorCode.EcOk) {
//                List<RangeItem> itemList = rangeReply.getItems();
//                String finalUserId = userId.toString();
//                /**
//                 * 拼装数据，并且根据dto里的数据进行过滤
//                 */
//                commentList = itemList.stream().map(i -> {
//                    Item item = i.getItem();
//                    CommentVO vo = new CommentVO();
//                    vo.setId(String.valueOf( Longs.fromByteArray(i.getItemKey())));
//                    try {
//                        vo.setBizCode((byte) item.getIntField("bizCode"));
//                        vo.setContent(item.getStringField("content"));
//                        vo.setUserId(String.valueOf(item.getIntField("userId")));
//                        vo.setStarNum((byte) item.getIntField("starNum"));
//                        vo.setCreateTime(new Timestamp(item.getIntField("createTime") * 1000));
//                        vo.setTargetId(item.getStringField("targetId"));
//                        vo.setTargetType((byte) item.getIntField("targetType"));
//                        vo.setUserName(item.getStringField("userName"));
//                        vo.setCourseName(courseName);
//                    } catch (Exception e) {
//                        log.error("[queryCommentByPage] error", e);
//                        throw new CommentException("批量删除出现异常" + e.getMessage());
//                    }
//                    return vo;
//                }).filter(c -> {
//                    if (!finalUserId.equals("0")) {
//                        if (c.getUserId().equals(finalUserId)) {
//                            return true;
//                        }
//                        return false;
//                    }
//                    return true;
//                }).filter(c -> {
//                    if (null != beginTime) {
//                        if (beginTime.compareTo(c.getCreateTime()) < 0) {
//                            return true;
//                        }
//                        return false;
//                    }
//                    return true;
//                }).filter(c -> {
//                    if (null != endTime) {
//                        if (endTime.compareTo(c.getCreateTime()) >= 0) {
//                            return true;
//                        }
//                        return false;
//                    }
//                    return true;
//                }).collect(Collectors.toList());
//            }
//            commentPage.setRecords(commentList);
//            log.info("[queryCommentByPage] success");
//            return commentPage;
//        }
//        return commentPage;
//    }

}
