package ru.sibcon;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
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
        result.put("OK",true);
        result.put("errMsg","");
        File targetDir = new File(targetDirPath);
        File packet = new File(packetPath);
        try {
            FileUtils.moveDirectoryToDirectory(packet, targetDir, true);
        } catch (IOException e) {
            result.put("OK",false);
            result.put("errMsg",e.getMessage());
        }
        return result;
    }
}
