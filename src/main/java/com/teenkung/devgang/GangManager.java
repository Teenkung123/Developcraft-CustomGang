package com.teenkung.devgang;

import java.util.HashMap;

import static com.teenkung.devgang.DevGang.colorize;

public class GangManager {

    private static final HashMap<Integer, Gang> GangMap = new HashMap<>();
    public void addGang(Integer gangID, Gang gang) {
        if (GangMap.containsKey(gangID)) {
            GangMap.replace(gangID, gang);
        } else {
            GangMap.put(gangID, gang);
        }
    }
    public static void addAllGang() {
        int amount = 0;
        for (Integer id : Gang.getGangsList().keySet()) {
            if (GangMap.containsKey(id)) {
                GangMap.replace(id, new Gang(Gang.getGangsList().get(id)));
            } else {
                GangMap.put(id, new Gang(Gang.getGangsList().get(id)));
            }
            amount++;
        }
        System.out.println(colorize("&aSuccessfully loaded total of &e" + amount + " &aGangs!"));
    }

}
