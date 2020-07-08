import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Perform {
}

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Check {
  String value() default "nothing";
}

// Meta Annotation - Target, Retention
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface SmartPhone {
  String OS() default "Android";

  int version();
}

@SmartPhone(version = 3)
class NokiaASeries {
  @Check(value = "Hi")
  String model;
  int size;

  public NokiaASeries(String model, int size) {
    this.model = model;
    this.size = size;
  }

  @Perform
  public void testMethod() {
  }

  @SuppressWarnings("deprecation")
  public void test1() {
    System.out.println("This is a test method");
  }

}

public class App {

  public static void main(String[] args) {
    // NokiaASeries obj = new NokiaASeries("Fire", 5);
    Class nokia = NokiaASeries.class;

    boolean hasAnnotation = NokiaASeries.class.isAnnotationPresent(SmartPhone.class);
    if (hasAnnotation) {
      SmartPhone smartPhone = NokiaASeries.class.getAnnotation(SmartPhone.class);
      System.out.println(smartPhone.OS());
      System.out.println(smartPhone.version());
    }

    try {
      // get field annotation value
      Field a = nokia.getDeclaredField("model");
      a.setAccessible(true);
      Check check = a.getAnnotation(Check.class);

      if (check != null) {
        System.out.println(check.value());
      }

      // get method annotation value

      Method testMethod = nokia.getDeclaredMethod("testMethod");
      if (testMethod != null) {
        Annotation[] ans = testMethod.getAnnotations();
        for (Annotation annotation : ans) {
          System.out.print("testMethod annotation : ");
          System.out.println(annotation.annotationType().getSimpleName());
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
  }
}