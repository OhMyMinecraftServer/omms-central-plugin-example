package net.zhuruoling.omms.central.plugin.example.whitelist;

import net.zhuruoling.omms.central.whitelist.PlayerAlreadyExistsException;
import net.zhuruoling.omms.central.whitelist.PlayerNotFoundException;
import net.zhuruoling.omms.central.whitelist.Whitelist;

import java.util.ArrayList;
import java.util.List;

/**
 * this is a simple whitelist use List<String> to storage players and perform whitelist modification.
 */
public class ExampleWhitelist extends Whitelist {

    String name;
    List<String> players;

    /**
     *
     * @param name name of this whitelist instance
     */
    public ExampleWhitelist(String name) {
        this.name = name;
        players = new ArrayList<>();
        //generate 5 random name players
        for (int i = 0; i < 5; i++) {
            players.add(net.zhuruoling.omms.central.util.Util.randomStringGen(16));
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean contains(String s) {
        return players.contains(s);
    }

    @Override
    public List<String> getPlayers() {
        return players;
    }

    @Override
    public void addPlayer(String s) throws PlayerAlreadyExistsException {
        if (players.contains(s))throw new PlayerAlreadyExistsException(name, s);
        players.add(s);
    }

    @Override
    public void removePlayer(String s) throws PlayerNotFoundException {
        if (!players.contains(s))throw new PlayerNotFoundException(name, s);
        this.players.remove(s);
    }

    @Override
    public void saveModifiedBuffer() {
        //do nothing
    }

    @Override
    public void deleteWhitelist() {
        //do nothing
    }
}
