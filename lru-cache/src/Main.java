import handler.CacheHandler;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initiating Caching Process");

        final CacheHandler<Integer, String> handler = new CacheHandler<>();
        handler.setData(1, "abc");
        handler.setData(2, "def");
        handler.getData(1);
        handler.setData(3, "ghi");
        handler.getData(2);
        handler.setData(4, "jkl");
        handler.setData(5, "mno");
    }
}