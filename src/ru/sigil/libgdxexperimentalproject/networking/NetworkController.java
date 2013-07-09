package ru.sigil.libgdxexperimentalproject.networking;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import ru.sigil.libgdxexperimentalproject.model.Player;

//Тут будет поток, который обменивается данными с сервером
public class NetworkController extends Thread {

    private byte playerRole;
    private String opponentLogin;
    private Socket socket;
    private DataOutputStream dout;
    private DataInputStream din;
    private MessageWriter mw = new MessageWriter();
    private MessageReader mr = new MessageReader();
    public static final byte PAINTER = 0;
    public static final byte ANSWERER = 1;
    private static final byte MESSAGE_SEND_PLAYER_CONNECTED = 0;
    private static final byte MESSAGE_GET_PLAYER_NOT_IN_MATCH = 1;
    private static final byte MESSAGE_GET_PLAYERS_ROLE = 9;
    private static final byte MESSAGE_GET_OPPONENTS_NICKNAME = 10;
    private static final byte MESSAGE_GET_PICTURE = 3;

    public NetworkController() {

    }

    @Override
    public void run() {
        //Code
    }

    public byte getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(byte playerRole) {
        this.playerRole = playerRole;
    }

    public void onConnectButtonClick() { //Сначала коннектимся к серверу
        try {
            socket = new Socket("10.0.2.2", 1955);
            socket.setKeepAlive(true);
            dout = new DataOutputStream(socket.getOutputStream());
            din = new DataInputStream(socket.getInputStream());
            //-----------------------------------------------------------------
            mw.writeByte(MESSAGE_SEND_PLAYER_CONNECTED);
            mw.writeString(String.valueOf(Player.getInstance().getId()));//PlayerId
            mw.writeString(Player.getInstance().getLogin());//Login
            mw.writeByte((byte) 0);//ContinueMatch (Что это?!) вроде boolean
            dout.writeInt(mw.data.length);
            dout.write(mw.data);
            mr = new MessageReader();
            prepareToReceive();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getRole() {//Получаем роль
        playerRole = mr.readByte(din);
        Log.v("Role", String.valueOf(playerRole));
        prepareToReceive();
    }

    private void getOpponentLogin() { //Получаем логин оппонента
        opponentLogin = mr.readString(din);
        Log.v("Opponent login", opponentLogin);
        prepareToReceive();
    }

    private void processMessage(byte messageId) {
        Log.v("Length", String.valueOf(mr.getOffset()));
        if (messageId == MESSAGE_GET_PLAYERS_ROLE) {
            getRole();
        }
        if (messageId == MESSAGE_GET_OPPONENTS_NICKNAME) {
            getOpponentLogin();//TODO
        }
        if (messageId == MESSAGE_GET_PICTURE) {
            getRole();//TODO
        }
        if (messageId == MESSAGE_GET_PLAYER_NOT_IN_MATCH) {
            getNotInMatch();//TODO
        }
        Log.v("Unexpected message:", String.valueOf(messageId));
    }

    private void prepareToReceive() {
        mr.setOffset(0);
        int length = mr.readInt(din);
        mr.setOffset(length);
        processMessage(mr.readByte(din));
    }

    public void getPicture() {
        //TODO
    }

    public void sendPicture(byte b) {
        //TODO
    }

    private void getNotInMatch() {
        //TODO
        Log.v("Not in match", String.valueOf(MESSAGE_GET_PLAYER_NOT_IN_MATCH));
        prepareToReceive();
    }
}