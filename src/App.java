package src;

public class App {
  public static void main(String[] args) {
    Db db = Db.getDb();
    db.name = "Jun";
    System.out.println(db.name);

    Db db2 = Db.getDb();
    if(db == db2){
      System.out.println("equal!");
    }
    System.out.println(db2.getClass().getSimpleName());
  }
}