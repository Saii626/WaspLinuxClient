package SocketManagement.WaspberryMessageHandler.Models;

import SocketManagement.WaspberryMessageHandler.MessageModel;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class Notification implements MessageModel {

    @SerializedName("id")
    public UUID id;

    @SerializedName("timestamp")
    public Date timestamp;

    @SerializedName("source")
    public String source;

    @SerializedName("title")
    public String title;

    @SerializedName("message")
    public String message;

    @SerializedName("destination")
    public String destination;

    @SerializedName("response")
    public String response;

    @Override
    public String toString() {
        return "\nid:\t" + id.toString() +
                "\ntimestamp:\t" + DateFormat.getDateTimeInstance().format(timestamp) +
                "\nsource:\t" + source +
                "\ntitle:\t" + title +
                "\nmessage:\t" + message;

    }
}
