package br.com.urso.loginfallcraft.entitys;

import java.net.SocketAddress;

public class GamePlayer {
    private boolean isLogged;
    private SocketAddress address;

    public GamePlayer(SocketAddress address) {
        this.isLogged = false;
        this.address = address;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public SocketAddress getAddress() {
        return address;
    }

    public void setAddress(SocketAddress address) {
        this.address = address;
    }
}
