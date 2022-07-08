package com.teenkung.devgang;

import org.bukkit.ChatColor;

public enum gangPermission {

    MEMBER, TRUST, MODERATOR, CoOWNER, OWNER, UNKNOWN;

    public String getString() {
        if (this.equals(MEMBER)) { return "MEMBER"; }
        else if (this.equals(TRUST)) { return "TRUST"; }
        else if (this.equals(MODERATOR)) { return "MODERATOR"; }
        else if (this.equals(CoOWNER)) { return "CoOwner"; }
        else if (this.equals(OWNER)) { return "OWNER"; }
        else return "UNKNOWN";
    }

    public static gangPermission getFromString(String rank) {
        if (ChatColor.stripColor(rank).equalsIgnoreCase("member")) { return gangPermission.MEMBER; }
        else if (ChatColor.stripColor(rank).equalsIgnoreCase("trust")) { return gangPermission.TRUST; }
        else if (ChatColor.stripColor(rank).equalsIgnoreCase("moderator")) { return gangPermission.MODERATOR; }
        else if (ChatColor.stripColor(rank).equalsIgnoreCase("coowner") || ChatColor.stripColor(rank).equalsIgnoreCase("co-owner")) { return gangPermission.CoOWNER; }
        else if (ChatColor.stripColor(rank).equalsIgnoreCase("owner")) { return gangPermission.OWNER; }
        else { return gangPermission.UNKNOWN; }
    }

}
