package me.codeblooded.timeparkour.plugin.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class FileUtils {
	
	public static void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            
            byte[] buf = new byte[1024];
            
            int len;
            
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            
            out.close();
            
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static void createFile(File file, String path, Plugin plugin) throws Exception {
        if(!file.exists()){
            file.getParentFile().mkdirs();
            
            copy(plugin.getResource(path), file);
        }
    }
	
	public static void loadYamls(FileConfiguration config, File file) throws Exception {
		config.load(file);
    }

}
