package com.ry.base;

import java.util.*;

public class MessageEvent {
    public static HashMap<Integer, List<Listener>> map = new HashMap<>();

    public static MessageEvent messageObserver = new MessageEvent();

    private MessageEvent() {

    }

    public static MessageEvent getInstance() {
        return messageObserver;
    }

    public void register(int code, Listener obj) {
        List<Listener> list;
        if (map.containsKey(code)) {
            list = map.get(code);
        } else {
            list = new ArrayList<>();
        }
        if (!list.contains(obj)) {
            list.add(obj);
            map.put(code, list);
        }
    }

    public void remove(int code, Listener obj) {
        if (map.containsKey(code)) {
            List<Listener> list = map.get(code);
            if (list.contains(obj)) {
                list.remove(obj);
                map.put(code, list);
            }
        }
    }

    public void remove(int code) {
        if (map.containsKey(code)) {
            map.remove(code);
        }
    }

    public void remove(Listener obj) {
        Iterator<Map.Entry<Integer, List<Listener>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Listener>> entry = iterator.next();
            for (Listener o : entry.getValue()) {
                if (o == obj) {
                    List<Listener> temp = map.get(entry.getKey());
                    temp.remove(obj);
                    map.put(entry.getKey(), temp);
                    break;
                }
            }
        }
    }

    public <T> void post(int event, T msg) {
        if (!map.containsKey(event)) {
            return;
        }
        List<Listener> list = map.get(event);
        for (Listener o : list) {
            if(o != null) {
                o.onEvent(event, msg);
            }
        }
    }

    public interface Listener<T> {
        public void onEvent(int event, T message);
    }
}