package ru.sigil.libgdxexperimentalproject.networking;

import android.os.Environment;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import ru.sigil.libgdxexperimentalproject.model.Player;
import ru.sigil.libgdxexperimentalproject.userinterface.HUD;

//Тут будет поток, который обменивается данными с сервером
public class NetworkController extends Thread {

    private byte playerRole;
    private String keyWord;
    private String opponentLogin;
    private Socket socket;
    private DataOutputStream dout;
    private BufferedInputStream din;
    private MessageWriter mw;
    private MessageReader mr = new MessageReader();
    public static final int DIFFICULTY_EASY = 1;
    public static final int DIFFICULTY_MEDIUM = 2;
    public static final int DIFFICULTY_HARD = 3;
    public static final byte PAINTER = 0;
    public static final byte ANSWERER = 1;
    //----------------------PROTOCOL MESSAGES-----------------------
    private static final byte TO_CLIENT_MESSAGE_PLAYER_NOT_IN_MATCH = 1;
    private static final byte TO_CLIENT_MESSAGE_PLAYERS_ROLE = 9;
    private static final byte TO_CLIENT_MESSAGE_OPPONENTS_NICKNAME = 10;
    private static final byte TO_CLIENT_MESSAGE_PROVIDE_PICTURE = 11;

    private static final byte TO_SERVER_MESSAGE_PLAYER_CONNECTED = 0;
    private static final byte TO_SERVER_MESSAGE_PROVIDE_PICTURE = 12;
    //--------------------------------------------------------------
    private Dialog waitingDialog;
    private Label waitingLabel = new Label("", HUD.getPgSkin());
    private HUD currentHud;

    public NetworkController(Dialog dialog, HUD hud) {
        this.setCurrentHud(hud);
        this.waitingDialog = dialog;
    }

    @Override
    public void run() {
        waitingDialog.text(waitingLabel);
        connectAfterLogin();
        //Code
    }

    public void connectAfterLogin() { //Сначала коннектимся к серверу
        try {
            socket = new Socket("10.0.2.2", 1955);
            socket.setKeepAlive(true);
            dout = new DataOutputStream(socket.getOutputStream());
            din = new BufferedInputStream(socket.getInputStream());
            //-----------------------------------------------------------------
            mw = new MessageWriter();
            mw.writeByte(TO_SERVER_MESSAGE_PLAYER_CONNECTED);
            mw.writeString(String.valueOf(Player.getInstance().getId()));//PlayerId
            mw.writeString(Player.getInstance().getLogin());//Login
            mw.writeByte((byte) 0);//ContinueMatch (Что это?!) вроде boolean
            dout.writeInt(mw.data.length);
            dout.write(mw.data);
            //Login succesfull
            setWaitingDialogText("Ищем оппонента...");
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
        switch (playerRole) {
            case ANSWERER:
                setWaitingDialogText("Оппонент рисует. Ждем...");
                break;
            case PAINTER:
                currentHud.showDifficultyDialog();
                break;
            default:
                Log.v("Unexpected role:", String.valueOf(playerRole));
        }
        prepareToReceive();
    }

    private void getOpponentLogin() { //Получаем логин оппонента
        opponentLogin = mr.readString(din);
        Log.v("Opponent login", opponentLogin);
        prepareToReceive();
    }

    private void processMessage(byte messageId) {
        Log.v("MessageID", String.valueOf(messageId));
        switch (messageId) {
            case TO_CLIENT_MESSAGE_PLAYERS_ROLE:
                getRole();
                break;
            case TO_CLIENT_MESSAGE_OPPONENTS_NICKNAME:
                getOpponentLogin();
                break;
            case TO_CLIENT_MESSAGE_PROVIDE_PICTURE:
                getPicture();//TODO
                break;
            case TO_CLIENT_MESSAGE_PLAYER_NOT_IN_MATCH:
                getNotInMatch();//TODO
                break;
            default:
                Log.v("Unexpected message:", String.valueOf(messageId));
        }
    }

    private void prepareToReceive() {
        mr.setOffset(0);
        int length = mr.readInt(din);
        Log.v("Message length", String.valueOf(length));
        mr.setOffset(length);
        processMessage(mr.readByte(din));
    }

    public void getPicture() {
        Log.v("Prepare to get picture", "");
        byte[] b = mr.readByteArray(din);
        Log.v("Get picture", String.valueOf(b.length));
        //TODO TEST
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + "/pic/");
        dir.mkdirs();
        File file = new File(dir, "received");
        try {
            Log.v("Get picture process1", String.valueOf(b.length));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(b);
            bos.flush();
            bos.close();
            Log.v("Get picture process2", String.valueOf(b.length));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.v("Get picture completed", String.valueOf(b.length));
        prepareToReceive();
    }

    public void sendPicture(byte b[]) {
        Log.v("Send picture length", String.valueOf(b.length));
        mw = new MessageWriter();
        mw.writeByte(TO_SERVER_MESSAGE_PROVIDE_PICTURE);
        mw.writeInt(b.length);
        mw.writeByteArray(b);
        try {
            dout.writeInt(mw.data.length);
            dout.write(mw.data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO TEST
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + "/pic/");
        dir.mkdirs();
        File file = new File(dir, "send");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(b);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getNotInMatch() {
        //TODO
        Log.v("Not in match", String.valueOf(TO_CLIENT_MESSAGE_PLAYER_NOT_IN_MATCH));
        prepareToReceive();
    }

    private void setWaitingDialogText(String text) {
        final String newText = new String(text);
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                waitingLabel.setText(newText);
            }
        });

    }

    public void setCurrentHud(HUD currentHud) {
        this.currentHud = currentHud;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
