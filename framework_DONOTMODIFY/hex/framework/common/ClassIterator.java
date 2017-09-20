package hex.framework.common;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


 
public class ClassIterator {
 
    public static Class[] getClassesInPackage(String pckgname) {
        File directory = getPackageDirectory(pckgname);
        if (!directory.exists()) {
            throw new IllegalArgumentException("Could not get directory resource for package " + pckgname + ".");
        }
       
        return getClassesInPackage(pckgname, directory);
    }
    public static List<String> getClasseNameInPackage(String pckgname) {
        File directory = getPackageDirectory(pckgname);
        if (!directory.exists()) {
            throw new IllegalArgumentException("Could not get directory resource for package " + pckgname + ".");
        }
       
        return getClassNameInPackage(pckgname, directory);
    }
    private static Class[] getClassesInPackage(String pckgname, File directory) {
        List<Class> classes = new ArrayList<Class>();
        for (String filename : directory.list()) {
            if (filename.endsWith(".class")) {
                String classname = buildClassname(pckgname, filename);
                try {
                    classes.add(Class.forName(classname));
                } catch (ClassNotFoundException e) {
                    System.err.println("Error creating class " + classname);
                }
            }
        }
        return classes.toArray(new Class[classes.size()]);
    }
    private static List<String> getClassNameInPackage(String pckgname, File directory) {
        List<String> classes = new ArrayList<String>();
        for (String filename : directory.list()) {
            if (filename.endsWith(".class")) {
                String classname = buildClassname(pckgname, filename);
                classes.add(classname);
            }
        }
        return classes;
    }
    private static String buildClassname(String pckgname, String filename) {
        return pckgname + '.' + filename.replace(".class", "");
    }
 
    private static File getPackageDirectory(String pckgname) {
        ClassLoader cld = Thread.currentThread().getContextClassLoader();
        if (cld == null) {
            throw new IllegalStateException("Can't get class loader.");
        }
 
        URL resource = cld.getResource(pckgname.replace('.', '/'));
        if (resource == null) {
            throw new RuntimeException("Package " + pckgname + " not found on classpath.");
        }
 
        return new File(resource.getFile());
    }
 
    public static void main(String[] args) {
        Class[] classes = getClassesInPackage("com.mycomp");
        for (Class c : classes) {
            System.out.println("Found: " + c.getCanonicalName());
        }
    }
 
}