package com.ry.base.server.protocol.tcp;


import io.netty.util.AsciiString;
import io.netty.util.internal.MathUtil;
import io.netty.util.internal.ObjectUtil;

public class TCPMethod implements Comparable<TCPMethod> {

    public static final TCPMethod GET = new TCPMethod("GET");
    public static final TCPMethod NOTIFY = new TCPMethod("NOTIFY");
    private static final EnumNameMap<TCPMethod> methodMap;
    private final AsciiString name;

    public static TCPMethod valueOf(String name) {
        if (name == GET.name()) {
            return GET;
        } else if (name == NOTIFY.name()) {
            return NOTIFY;
        } else {
            TCPMethod result = (TCPMethod)methodMap.get(name);
            return result != null ? result : new TCPMethod(name);
        }
    }

    public TCPMethod(String name) {
        name = ObjectUtil.checkNonEmptyAfterTrim(name, "name");

        for(int i = 0; i < name.length(); ++i) {
            char c = name.charAt(i);
            if (Character.isISOControl(c) || Character.isWhitespace(c)) {
                throw new IllegalArgumentException("invalid character in name");
            }
        }

        this.name = AsciiString.cached(name);
    }

    public String name() {
        return this.name.toString();
    }

    public AsciiString asciiName() {
        return this.name;
    }

    public int hashCode() {
        return this.name().hashCode();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof TCPMethod)) {
            return false;
        } else {
            TCPMethod that = (TCPMethod)o;
            return this.name().equals(that.name());
        }
    }

    public String toString() {
        return this.name.toString();
    }

    public int compareTo(TCPMethod o) {
        return o == this ? 0 : this.name().compareTo(o.name());
    }

    static {
        methodMap = new EnumNameMap(new EnumNameMap.Node[]{new EnumNameMap.Node(GET.toString(), GET), new EnumNameMap.Node(NOTIFY.toString(), NOTIFY)});
    }

    private static final class EnumNameMap<T> {
        private final EnumNameMap.Node<T>[] values;
        private final int valuesMask;

        EnumNameMap(EnumNameMap.Node<T>... nodes) {
            this.values = (EnumNameMap.Node[])(new EnumNameMap.Node[MathUtil.findNextPositivePowerOfTwo(nodes.length)]);
            this.valuesMask = this.values.length - 1;
            EnumNameMap.Node[] var2 = nodes;
            int var3 = nodes.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                EnumNameMap.Node<T> node = var2[var4];
                int i = hashCode(node.key) & this.valuesMask;
                if (this.values[i] != null) {
                    throw new IllegalArgumentException("index " + i + " collision between values: [" + this.values[i].key + ", " + node.key + ']');
                }

                this.values[i] = node;
            }

        }

        T get(String name) {
            EnumNameMap.Node<T> node = this.values[hashCode(name) & this.valuesMask];
            return node != null && node.key.equals(name) ? node.value : null;
        }

        private static int hashCode(String name) {
            return name.hashCode() >>> 6;
        }

        private static final class Node<T> {
            final String key;
            final T value;

            Node(String key, T value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}
