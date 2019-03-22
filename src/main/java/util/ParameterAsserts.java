//package util;
//
//import com.bj58.bic.wubacollege.manager.exception.CommentException;
//import com.bj58.bic.wubacollege.manager.exception.NavigationException;
//import com.bj58.bic.wubacollege.manager.exception.WuBaCollegeException;
//import com.bj58.bic.wubacollege.manager.pojo.Result;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.collections.MapUtils;
//
//import java.lang.reflect.Constructor;
//import java.util.Collection;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @author 牛贞昊（niuzhenhao@58.com）
// * @date 2019/3/21 15:26
// * @desc
// */
//public class ParameterAsserts {
//
//    private static ConcurrentHashMap<Class<?>, Constructor<?>> map = new ConcurrentHashMap<>(8);
//    static {
//        try {
//            map.put(CommentException.class, CommentException.class.getConstructor(int.class, String.class));
//            map.put(NavigationException.class, NavigationException.class.getConstructor(int.class, String.class));
//            map.put(WuBaCollegeException.class, WuBaCollegeException.class.getConstructor(int.class, String.class));
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static final int CODE = Result.FAIL;
//
//    private static final String MESSAGE = "参数不合法";
//
//    public static void isValid(final Object o, final String msg, Class<?> clazz) throws Exception{
//        isValid(o, CODE, msg, clazz);
//    }
//    public static void isValid(final Object o, final String msg) throws Exception{
//        isValid(o, CODE, msg, null);
//    }
//    public static void isValid(final Object o, final Class<?> clazz) throws Exception{
//        isValid(o, CODE, MESSAGE, clazz);
//    }
//    public static void isValid(final Object o) throws Exception {
//        isValid(o, MESSAGE);
//    }
//
//    /**
//     *  String 不能为空 && 不能空白
//     *  Long 不能为空 && 大于零
//     *  Integer 不能为空 && 大于零
//     *  Map 不能为空 不为 null
//     *  Collection 不能为空 不能为 null
//     * @param o 对象
//     * @param code 错误码
//     * @param msg 错误信息
//     * @param clazz 异常类
//     * @throws Exception
//     */
//    public static void isValid(final Object o, final int code, final String msg, Class<?> clazz) throws Exception{
//        boolean isInvalid = true;
//        if (o instanceof String) {
//            if (CommonUtil.validString((String) o)) {
//                isInvalid = false;
//            }
//        }
//        else if (o instanceof Long) {
//            if (CommonUtil.validLong((Long) o)) {
//                isInvalid = false;
//            }
//        }
//        else if (o instanceof Integer) {
//            if (CommonUtil.validInteger((Integer) o)) {
//                isInvalid = false;
//            }
//        }
//        else if (o instanceof Collection) {
//            if (null == o || CollectionUtils.isEmpty((Collection) o)) {
//                isInvalid = false;
//            }
//        }
//        else if (o instanceof Map) {
//            if (null == o || MapUtils.isEmpty((Map) o)) {
//                isInvalid = false;
//            }
//        }
//        else {
//            if (null == o) {
//                isInvalid = false;
//            }
//        }
//        /**
//         * 如果验证不通过，才反射;
//         */
//        if (!isInvalid) {
//            WuBaCollegeException exception = null;
//            if (null != clazz && !WuBaCollegeException.class.equals(clazz)) {
//                Constructor<?> constructor = map.get(clazz);
//                if (null == constructor) {
//                    constructor = clazz.getConstructor(int.class, String.class);
//                    map.put(clazz, constructor);
//                }
//                exception = (WuBaCollegeException) constructor.newInstance(code, msg);
//            } else {
//                exception = new WuBaCollegeException(code, msg);
//            }
//            throw exception;
//        }
//    }
//
////    public static final int CODE = Result.FAIL;
////
////    private static final String MESSAGE = "参数不合法";
////
////    public static void isValid(final Object o, final String msg, ExceptionEnum exceptionEnum) throws Exception{
////        isValid(o, CODE, msg, exceptionEnum);
////    }
////    public static void isValid(final Object o, final String msg) throws Exception{
////        isValid(o, CODE, msg, null);
////    }
////    public static void isValid(final Object o, final ExceptionEnum exceptionEnum) throws Exception{
////        isValid(o, CODE, MESSAGE, exceptionEnum);
////    }
////    public static void isValid(final Object o) throws Exception {
////        isValid(o, MESSAGE);
////    }
////
////    /**
////     *  String 不能为空 && 不能空白
////     *  Long 不能为空 && 大于零
////     *  Integer 不能为空 && 大于零
////     *  Map 不能为空 不为 null
////     *  Collection 不能为空 不能为 null
////     * @param o 对象
////     * @param code 错误码
////     * @param msg 错误信息
////     * @param exceptionEnum 异常类
////     * @throws Exception
////     */
////    public static void isValid(final Object o, final int code, final String msg, ExceptionEnum exceptionEnum) throws Exception{
////        WuBaCollegeException exception = null;
////        switch (exceptionEnum) {
////            case COMMENT:
////                exception = new CommentException(code, msg);
////                break;
////            case NAVIGATION:
////                exception = new NavigationException(code, msg);
////                break;
////            default:
////                exception = new WuBaCollegeException(code, msg);
////                break;
////        }
////        if (o instanceof String) {
////            if (CommonUtil.validString((String) o)) {
////                throw exception;
////            }
////        }
////        else if (o instanceof Long) {
////            if (CommonUtil.validLong((Long) o)) {
////                throw exception;
////            }
////        }
////        else if (o instanceof Integer) {
////            if (CommonUtil.validInteger((Integer) o)) {
////                throw exception;
////            }
////        }
////        else if (o instanceof Collection) {
////            if (null == o || CollectionUtils.isEmpty((Collection) o)) {
////                throw exception;
////            }
////        }
////        else if (o instanceof Map) {
////            if (null == o || MapUtils.isEmpty((Map) o)) {
////                throw exception;
////            }
////        }
////        else {
////            if (null == o) {
////                throw exception;
////            }
////        }
////
////    }
//}
