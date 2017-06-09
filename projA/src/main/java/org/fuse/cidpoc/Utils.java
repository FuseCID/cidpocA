package org.fuse.cidpoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.fuse.cidpoc.Item.Capability;
import org.fuse.cidpoc.Item.Requirement;

public class Utils {

    public static String getVName(Class<?> clazz) {
        return clazz.getSimpleName() + "-" + getVersion(clazz);
    }

    public static String getVersion(Class<?> clazz) {
        try (InputStream ins = clazz.getResourceAsStream("version")) {
            return new BufferedReader(new InputStreamReader(ins)).readLine();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public static Capability getCapability(Class<?> clazz) {
        try (InputStream ins = clazz.getResourceAsStream("capreq")) {
            Properties props = new Properties();
            props.load(ins);
            String spec = props.getProperty("provides");
            return Capability.parse(spec);
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public static List<Requirement> getRequirements(Class<?> clazz) {
        List<Requirement> result = new ArrayList<> ();
        try (InputStream ins = clazz.getResourceAsStream("capreq")) {
            Properties props = new Properties();
            props.load(ins);
            String line = props.getProperty("requires");
            if (line != null) {
                for (String spec : line.split(",")) {
                    result.add(Requirement.parse(spec.trim()));
                }
            }
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        return Collections.unmodifiableList(result);
    }
    
    public static String toString(Item item) {
        return item.getVName();
    } 
}
