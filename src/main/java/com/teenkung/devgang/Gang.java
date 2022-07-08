package com.teenkung.devgang;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.teenkung.devgang.DevGang.colorize;

public class Gang {

    private String gangName;
    private String gangColorName;
    private Integer gangLevel;
    private ArrayList<String> gangMemberList = new ArrayList<>();
    private HashMap<String, gangPermission> gangPermissionList = new HashMap<>();
    private ArrayList<String> gangBanList = new ArrayList<>();

    public static HashMap<Integer, String> getGangsList() {
        try {
            PreparedStatement statement = DevGang.getDatabaseConnection().prepareStatement("SELECT * FROM " + DevGang.getConfigLoaderInstance().getDatabaseGangTable());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                HashMap<Integer, String> returns = new HashMap<>();
                while (rs.next()) {
                    returns.put(rs.getInt("ID"), rs.getString("GangName"));
                }
                return returns;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public Gang(String name) {
        gangName = name;
        gangColorName = colorize(name);


    }

}
