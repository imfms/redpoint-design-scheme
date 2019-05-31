package ms.imf.redpoint.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Node implements Serializable {

    public static Node instance(String type, String... argValues) {
        HashMap<String, String> args = null;
        if (argValues != null
                && argValues.length > 0) {
            args = new HashMap<>(argValues.length / 2);

            for (int i = 0; i < argValues.length; i += 2) {
                String key = argValues[i];
                String value = null;
                if (i + 1 < argValues.length) {
                    value = argValues[i + 1];
                }
                args.put(key, value);
            }
        }
        return instance(type, args);
    }

    public static Node instance(String type, Map<String, String> args) {
        return new Node(type, args);
    }

    public static Node instance(String type) {
        return instance(type, (String[]) null);
    }

    public final String type;
    public final Map<String, String> args;

    public Node(String type) {
        this(type, null);
    }

    /**
     * todo 20190521 对args的key can't be null参数校验重申
     */
    public Node(String type, Map<String, String> args) {
        if (type == null) { throw new IllegalArgumentException("type can't be null"); }

        if (args != null) {
            int index = -1;
            for (Map.Entry<String, String> entry : args.entrySet()) {
                index++;
                if (entry.getKey() == null) {
                    throw new IllegalArgumentException(String.format("args' key can't be null, but found null key on index '%d'", index));
                }
            }
        }

        this.type = type;
        this.args = args == null
                ? Collections.<String, String>emptyMap()
                : Collections.unmodifiableMap(new HashMap<>(args));
    }

    @Override
    public String toString() {
        return "Node{" +
                "type='" + type + '\'' +
                ", args=" + args +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (type != null ? !type.equals(node.type) : node.type != null) return false;
        return args != null ? args.equals(node.args) : node.args == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (args != null ? args.hashCode() : 0);
        return result;
    }
}