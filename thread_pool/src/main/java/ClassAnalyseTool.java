import com.duoshilin.thread_pool.ThreadPoolExecutorTest;

import java.lang.reflect.Field;

/**
 * Created by duoshilin on 2019/2/28.
 */
public class ClassAnalyseTool {

    private static final String flag = "|-";

    public static void main(String[] args) {
        System.out.println(analyse(ThreadPoolExecutorTest.class, new StringBuffer(), 0));
    }

    public static String analyse(Class clz, StringBuffer sb, int count) {
        if (count == 4) return null;
        if (count == 0) {
            sb.append(clz.getName() + "\n");
        }
        count++;
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            int countTemp = count;
            String fieldName = field.getType().getName();
            for (int i = 0; i < countTemp; i++) {
                sb.append("\t");
            }
            sb.append("|- " + fieldName + ": " + field.getName() + "\n");
            if (!isBasicTypeOrString(fieldName)) {
                analyse(field.getType(), sb, countTemp);
            }
        }
        return sb.toString();
    }

    private static boolean isBasicTypeOrString(String className) {
        if (className.contains(short.class.getName()) || className.contains(Short.class.getName()) ||
                className.contains(int.class.getName()) || className.contains(Integer.class.getName()) ||
                className.contains(long.class.getName()) || className.contains(Long.class.getName()) ||
                className.contains(char.class.getName()) || className.contains(Character.class.getName()) ||
                className.contains(float.class.getName()) || className.contains(Float.class.getName()) ||
                className.contains(double.class.getName()) || className.contains(Double.class.getName()) ||
                className.contains(byte.class.getName()) || className.contains(Byte.class.getName()) ||
                className.contains(boolean.class.getName()) || className.contains(Boolean.class.getName()) ||
                className.contains(String.class.getName())) {
            return true;
        }
        return false;
    }
}

