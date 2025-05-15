// common.id.IdGenerator.java
package common.id;

public class IdGenerator {

    public static String generate(String prefix, String... parts) {
        String joined = String.join("-", parts).replaceAll("[^a-zA-Z0-9]", "");
        return prefix + "-" + joined;
    }
}
