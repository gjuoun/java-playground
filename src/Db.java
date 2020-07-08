package src;

public class Db {

  String name;
  static Db instance = new Db();

  public static Db getDb() {
    return instance;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}