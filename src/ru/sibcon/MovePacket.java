package ru.sibcon;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * User: a.arzamastsev Date: 29.12.2014 Time: 10:59
 */
public class MovePacket {
    public MovePacket() {}

    public static void main(String[] args) {
        if (args.length > 0) {
            Boolean x = (Boolean)execute(args[0],args[1]).get("OK");
            System.out.println("Arguments received. Moved: " +x);
        } else {
            System.out.println("Give me more arguments!");
        }
    }

    public static Map execute(String packetPath, String targetDirPath){
        Map result = new HashMap();
        result.put("ok",true);

        packetPath = removeLastSlash(packetPath);
        targetDirPath = removeLastSlash(targetDirPath);

        String packetName = getPacketNameFromPath(packetPath);

        File targetDir = new File(targetDirPath);
        File packet = new File(packetPath);
        File targetPacket = new File(targetDirPath+"\\"+packetName);

        try {
            Boolean contains = FileUtils.directoryContains(targetDir, targetPacket);
            if (contains) {
                FileUtils.deleteDirectory(targetPacket);
            }
        } catch (Exception e) {
            result.put("ok",false);
            result.put("msg",e.getMessage());
        }
        try {
            FileUtils.moveDirectoryToDirectory(packet, targetDir, true);
        } catch (Exception e) {
            result.put("ok",false);
            result.put("msg",e.getMessage());
        }
        result.put("msg","Package moved to "+targetDirPath+ " successfully.");
        return result;
    }

    private static String getPacketNameFromPath(String path) {
        Integer lastSlashIndx = path.lastIndexOf("\\");
        path = path.substring(++lastSlashIndx);
        return path;
    }

    private static String removeLastSlash(String path) {
        Integer lastSlashIndx = path.lastIndexOf("\\");
        Integer length = path.length();

        if(lastSlashIndx.equals(--length)){
            path = path.substring(0,length);
        }
        return path;
    }
}
