package ru.sibcon;

/**
 * User: a.arzamastsev Date: 24.01.2015 Time: 20:00
 */
public class MovePacketTest {
    public static void main(String[] args) {
        String from = "G:\\Temp\\DB Scripts";
        String to = "G:\\_archive\\20150119";
            Boolean x = (Boolean)MovePacket.execute(from,to).get("OK");
            System.out.println("Arguments received. Moved: " +x);
    }
}
